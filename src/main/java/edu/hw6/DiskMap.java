package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {
    private final String directoryPath;

    public DiskMap(String directoryPath) throws IOException {
        Path directory = Paths.get(directoryPath);

        if (Files.exists(directory) && !Files.isDirectory(directory)) {
            throw new IllegalArgumentException("Передан существующий файл");
        }

        this.directoryPath = directoryPath;
    }

    /**
     * Для работы с путями
     *
     * @return объект Path, указывающий на директорию
     */
    private Path getPath() {
        return Paths.get(directoryPath);
    }

    /**
     * Получение пути файла
     *
     * @param filename необходимый файл в директории
     * @return объект Path, указывающий на файл
     */
    private Path getPath(String filename) {
        if (directoryPath.charAt(directoryPath.length() - 1) == '/') {
            return Paths.get(directoryPath + filename);
        } else {
            return Paths.get(directoryPath, filename);
        }
    }

    /**
     * Создает файл, если он не существует
     *
     * @param filename название файла
     * @throws IOException ошибка создания
     */
    private void createFile(String filename) throws IOException {
        Path filePath = getPath(filename);

        if (!Files.exists(filePath.getParent())) {
            Files.createDirectories(filePath.getParent());
        }

        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
    }

    /**
     * Получение размера Map
     *
     * @return размер Map
     */
    @Override
    public int size() {
        try (var stream = Files.walk(getPath())) {
            return (int) stream
                    .filter(Files::isRegularFile)
                    .count();
        } catch (IOException e) {
            return 0;
        }
    }

    /**
     * Проверка, что Map пуста
     *
     * @return является ли Map пустой
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Проверка, что Map содержит искомый ключ
     *
     * @param key key whose presence in this map is to be tested
     * @return Map содержит искомый ключ
     */
    @Override
    public boolean containsKey(Object key) {
        if (!(key instanceof String)) {
            throw new IllegalArgumentException("Ключ должен быть строкой");
        }

        return Files.exists(getPath((String) key));
    }

    /**
     * Проверка, что Map содержит искомое значение
     *
     * @param value value whose presence in this map is to be tested
     * @return Map содержит искомое значение
     */
    @Override
    public boolean containsValue(Object value) {
        if (!(value instanceof String)) {
            throw new IllegalArgumentException("Значение должно быть строкой");
        }

        try (var stream = Files.list(getPath())) {
            return stream
                    .filter(Files::isRegularFile)
                    .filter(path -> {
                        try {
                            return Files.size(path) == ((String) value).length();
                        } catch (IOException e) {
                            return false;
                        }
                    })
                    .anyMatch(path -> {
                        try {
                            return Objects.equals(Files.readString(path), value);
                        } catch (IOException e) {
                            return false;
                        }
                    });
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Получение значения Map по ключу
     *
     * @param key the key whose associated value is to be returned
     * @return значение ключа
     */
    @Override
    public String get(Object key) {
        try {
            return Files.readString(getPath((String) key));
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Добавление в Map значения по ключу
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return добавленное значение
     */
    @Nullable
    @Override
    public String put(String key, String value) {
        try {
            createFile(key);
            Files.writeString(getPath(key), value);

            return value;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Убрать ключ из Map
     *
     * @param key key whose mapping is to be removed from the map
     * @return значение убранного из Map по ключу
     */
    @Override
    public String remove(Object key) {
        String oldValue = get(key);

        try {
            Files.deleteIfExists(getPath((String) key));
        } catch (IOException ignored) {
        }

        return oldValue;
    }

    /**
     * Добавить в Map все значения
     *
     * @param m mappings to be stored in this map
     */
    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        for (var entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Чистка Map
     */
    @Override
    public void clear() {
        try (var stream = Files.list(getPath())) {
            stream.forEach(path -> {
                try {
                    Files.deleteIfExists(path);
                } catch (IOException ignored) {
                }
            });
        } catch (IOException ignored) {
        }
    }

    /**
     * Получить набор ключей Map
     *
     * @return набор ключей Map
     */
    @NotNull
    @Override
    public Set<String> keySet() {
        Set<String> keys = new HashSet<>();

        try (var stream = Files.list(getPath())) {
            stream.forEach(path -> {
                if (Files.isRegularFile(path)) {
                    keys.add(path.getFileName().toString());
                }
            });
        } catch (IOException ignored) {
        }

        return keys;
    }

    /**
     * Получить лист значений Map
     *
     * @return лист значений Map
     */
    @NotNull
    @Override
    public Collection<String> values() {
        Collection<String> values = new ArrayList<>();

        try (var stream = Files.list(getPath())) {
            stream.forEach(path -> {
                if (Files.isRegularFile(path)) {
                    try {
                        values.add(Files.readString(path));
                    } catch (IOException ignored) {
                    }
                }
            });
        } catch (IOException ignored) {
        }

        return values;
    }

    /**
     * Получить набор пар ключ-значение из Map
     *
     * @return набор пар из Map
     */
    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        Map<String, String> entries = new HashMap<>();

        try (var stream = Files.list(getPath())) {
            stream.forEach(path -> {
                if (Files.isRegularFile(path)) {
                    try {
                        entries.put(path.getFileName().toString(), Files.readString(path));
                    } catch (IOException ignored) {
                    }
                }
            });
        } catch (IOException ignored) {
        }

        return entries.entrySet();
    }
}
