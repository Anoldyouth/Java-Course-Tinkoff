package edu.hw6;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DiskMapTest {
    private final static String DIRECTORY_PATH = "test_disk_map";

    @BeforeEach
    @AfterEach
    void deleteIfExist() throws Exception {
        deleteDirectory(new File(DIRECTORY_PATH));
    }

    public static void deleteDirectory(File directory) throws Exception {
        if(!directory.exists()) {
            return;
        }

        if (directory.isDirectory()) {
            File[] files = directory.listFiles();

            if (files != null) {
                for (File file : files) {
                    deleteDirectory(file);
                }
            }
        }

        // Удаляем саму директорию
        if (!directory.delete()) {
            throw new Exception("Не удалось удалить файл");
        }
    }


    @Test
    @DisplayName("Успешное создание мапы")
    void successfulConstructor() throws IOException {
        DiskMap map = new DiskMap(DIRECTORY_PATH);

        assertThat(map).isNotNull();
    }

    @Test
    @DisplayName("Директория уже существует")
    void existDirectory() throws IOException {
        Files.createDirectories(Path.of(DIRECTORY_PATH));

        DiskMap map = new DiskMap(DIRECTORY_PATH);

        assertThat(map).isNotNull();
    }

    @Test
    @DisplayName("Существует файл, бросок ошибки")
    void existFile() {
        try {
            Files.createFile(Path.of(DIRECTORY_PATH));

            assertThrows(
                    IllegalArgumentException.class,
                    () -> new DiskMap(DIRECTORY_PATH)
            );
        } catch (IOException e) {
            assertThat(e).isNull();
        }
    }

    @Test
    @DisplayName("Успешно добавляет значения")
    void successPut() throws IOException {
        DiskMap map = new DiskMap(DIRECTORY_PATH);

        map.put("test", "test");

        Path test = Path.of(DIRECTORY_PATH, "test");
        assertTrue(Files.exists(test));
        assertThat(Files.readString(test)).isEqualTo("test");
    }

    @Test
    @DisplayName("Файл уже существует")
    void putToExistingFile() throws IOException {
        DiskMap map = new DiskMap(DIRECTORY_PATH);
        Path test = Path.of(DIRECTORY_PATH, "test");

        Files.createDirectories(test.getParent());
        Files.createFile(test);
        map.put("test", "test");

        assertTrue(Files.exists(test));
        assertThat(Files.readString(test)).isEqualTo("test");
    }

    @Test
    @DisplayName("Удаление файла")
    void removeFile() throws IOException {
        DiskMap map = new DiskMap(DIRECTORY_PATH);
        Path test = Path.of(DIRECTORY_PATH, "test");

        Files.createDirectories(test.getParent());
        Files.createFile(test);
        assertThat(Files.exists(test)).isTrue();

        map.remove("test");
        assertThat(Files.exists(test)).isFalse();
    }

    @Test
    @DisplayName("Удаление несуществующего файла")
    void removeNonExistingFile() throws IOException {
        DiskMap map = new DiskMap(DIRECTORY_PATH);
        Path test = Path.of(DIRECTORY_PATH, "test");

        assertThat(map.remove("test")).isNull();
        assertThat(Files.exists(test)).isFalse();
    }

    @Test
    @DisplayName("Количество ключей")
    void getSize() throws IOException {
        DiskMap map = new DiskMap(DIRECTORY_PATH);

        Files.createDirectories(Path.of(DIRECTORY_PATH));
        Files.createFile(Path.of(DIRECTORY_PATH, "first"));
        Files.createFile(Path.of(DIRECTORY_PATH, "second"));
        Files.createFile(Path.of(DIRECTORY_PATH, "third"));
        assertThat(map.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("Количество ключей при отсутствии файлов")
    void getZeroSize() throws IOException {
        DiskMap map = new DiskMap(DIRECTORY_PATH);

        Files.createDirectories(Path.of(DIRECTORY_PATH));
        assertThat(map.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("Проверка на пустоту")
    void isEmpty() throws IOException {
        DiskMap map = new DiskMap(DIRECTORY_PATH);

        Files.createDirectories(Path.of(DIRECTORY_PATH));
        assertThat(map.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Проверка на отсутствие пустоты")
    void isNotEmpty() throws IOException {
        DiskMap map = new DiskMap(DIRECTORY_PATH);

        Files.createDirectories(Path.of(DIRECTORY_PATH));
        Files.createFile(Path.of(DIRECTORY_PATH, "first"));
        Files.createFile(Path.of(DIRECTORY_PATH, "second"));
        Files.createFile(Path.of(DIRECTORY_PATH, "third"));

        assertThat(map.isEmpty()).isFalse();
    }

    @Test
    @DisplayName("Ключ существует")
    void keyExist() throws IOException {
        DiskMap map = new DiskMap(DIRECTORY_PATH);

        Files.createDirectories(Path.of(DIRECTORY_PATH));
        Files.createFile(Path.of(DIRECTORY_PATH, "test"));
        assertThat(map.containsKey("test")).isTrue();
    }

    @Test
    @DisplayName("Ключ не существует")
    void keyNotExist() throws IOException {
        DiskMap map = new DiskMap(DIRECTORY_PATH);

        Files.createDirectories(Path.of(DIRECTORY_PATH));
        assertThat(map.containsKey("test")).isFalse();
    }

    @Test
    @DisplayName("Ключ не строка")
    void keyNotString() throws IOException {
        DiskMap map = new DiskMap(DIRECTORY_PATH);

        Files.createDirectories(Path.of(DIRECTORY_PATH));
        assertThrows(
                IllegalArgumentException.class,
                () -> map.containsKey(List.of())
        );
    }

    @Test
    @DisplayName("Значение существует")
    void valueExist() throws IOException {
        DiskMap map = new DiskMap(DIRECTORY_PATH);
        Path first = Path.of(DIRECTORY_PATH, "first");
        Path second = Path.of(DIRECTORY_PATH, "second");

        Files.createDirectories(Path.of(DIRECTORY_PATH));
        Files.createFile(first);
        Files.createFile(second);
        Files.writeString(first, "first");
        Files.writeString(second, "second");

        assertThat(map.containsValue("first")).isTrue();
    }

    @Test
    @DisplayName("Значение не существует")
    void valueNotExist() throws IOException {
        DiskMap map = new DiskMap(DIRECTORY_PATH);

        Files.createDirectories(Path.of(DIRECTORY_PATH));
        assertThat(map.containsValue("test")).isFalse();
    }

    @Test
    @DisplayName("Значение не строка")
    void valueNotString() throws IOException {
        DiskMap map = new DiskMap(DIRECTORY_PATH);

        Files.createDirectories(Path.of(DIRECTORY_PATH));
        assertThrows(
                IllegalArgumentException.class,
                () -> map.containsValue(List.of())
        );
    }

    @Test
    @DisplayName("Получение значения по ключу")
    void getByKey() throws IOException {
        DiskMap map = new DiskMap(DIRECTORY_PATH);
        Path first = Path.of(DIRECTORY_PATH, "first");

        Files.createDirectories(Path.of(DIRECTORY_PATH));
        Files.createFile(first);
        Files.writeString(first, "first");

        assertThat(map.get("first")).isEqualTo("first");
    }

    @Test
    @DisplayName("Получение по ключу, значения нет")
    void getNull() throws IOException {
        DiskMap map = new DiskMap(DIRECTORY_PATH);

        Files.createDirectories(Path.of(DIRECTORY_PATH));

        assertThat(map.get("test")).isNull();
    }

    @Test
    @DisplayName("Успешно добавляет значения")
    void successPutAll() throws IOException {
        DiskMap map = new DiskMap(DIRECTORY_PATH);
        Map<String, String> putValues = Map.of(
                "first", "first",
                "second", "second"
        );

        map.putAll(putValues);

        Path first = Path.of(DIRECTORY_PATH, "first");
        assertTrue(Files.exists(first));
        assertThat(Files.readString(first)).isEqualTo("first");

        Path second = Path.of(DIRECTORY_PATH, "second");
        assertTrue(Files.exists(second));
        assertThat(Files.readString(second)).isEqualTo("second");
    }

    @Test
    @DisplayName("Файлы уже существует")
    void putAllToExistingFiles() throws IOException {
        DiskMap map = new DiskMap(DIRECTORY_PATH);
        Map<String, String> putValues = Map.of(
                "first", "first",
                "second", "second"
        );
        Path first = Path.of(DIRECTORY_PATH, "first");
        Path second = Path.of(DIRECTORY_PATH, "second");

        Files.createDirectories(first.getParent());
        Files.createFile(first);
        Files.createFile(second);

        map.putAll(putValues);

        assertTrue(Files.exists(first));
        assertThat(Files.readString(first)).isEqualTo("first");

        assertTrue(Files.exists(second));
        assertThat(Files.readString(second)).isEqualTo("second");
    }

    @Test
    @DisplayName("Чистка папки")
    void clear() throws IOException {
        DiskMap map = new DiskMap(DIRECTORY_PATH);

        Files.createDirectories(Path.of(DIRECTORY_PATH));
        Files.createFile(Path.of(DIRECTORY_PATH, "first"));
        Files.createFile(Path.of(DIRECTORY_PATH, "second"));
        Files.createFile(Path.of(DIRECTORY_PATH, "third"));
        map.clear();

        assertThat(Files.list(Path.of(DIRECTORY_PATH)).count()).isEqualTo(0);
    }

    @Test
    @DisplayName("Получение списка ключей")
    void getKeys() throws IOException {
        DiskMap map = new DiskMap(DIRECTORY_PATH);
        Path first = Path.of(DIRECTORY_PATH, "first");
        Path second = Path.of(DIRECTORY_PATH, "second");

        Files.createDirectories(first.getParent());
        Files.createFile(first);
        Files.createFile(second);

        assertThat(map.keySet()).isEqualTo(Set.of("first", "second"));
    }

    @Test
    @DisplayName("Получение списка значений")
    void getValues() throws IOException {
        DiskMap map = new DiskMap(DIRECTORY_PATH);
        Path first = Path.of(DIRECTORY_PATH, "first");
        Path second = Path.of(DIRECTORY_PATH, "second");

        Files.createDirectories(first.getParent());
        Files.createFile(first);
        Files.createFile(second);

        Files.writeString(first, "first");
        Files.writeString(second, "second");

        assertThat(map.values().stream().sorted().toList()).isEqualTo(List.of("first", "second"));
    }

    @Test
    @DisplayName("Получение сущностей")
    void getEntrySet() throws IOException {
        DiskMap map = new DiskMap(DIRECTORY_PATH);
        Path first = Path.of(DIRECTORY_PATH, "first");
        Path second = Path.of(DIRECTORY_PATH, "second");

        Files.createDirectories(first.getParent());
        Files.createFile(first);
        Files.createFile(second);

        Files.writeString(first, "first");
        Files.writeString(second, "second");

        assertThat(map.entrySet()).isEqualTo(Map.of(
                "first", "first",
                "second", "second"
        ).entrySet());
    }
}
