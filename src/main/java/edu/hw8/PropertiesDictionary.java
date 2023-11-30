package edu.hw8;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesDictionary implements Dictionary {
    private final static String DICTIONARY_PROPERTIES = "src/main/resources/dictionary.properties";
    private final Map<String, String> dictionary;

    /**
     * Инициализация словаря файлом properties
     */
    public PropertiesDictionary() {
        Properties properties = new Properties();
        dictionary = new HashMap<>();

        try (FileInputStream fis = new FileInputStream(DICTIONARY_PROPERTIES)) {
            properties.load(fis);

            properties.forEach((key, value) -> dictionary.put((String) key, (String) value));
        } catch (IOException ignored) {
        }
    }

    /**
     * Инициализация словаря поданным файлом properties
     */
    public PropertiesDictionary(String propertiesFile) {
        Properties properties = new Properties();
        dictionary = new HashMap<>();

        try (FileInputStream fis = new FileInputStream(propertiesFile)) {
            properties.load(fis);

            properties.forEach((key, value) -> dictionary.put((String) key, (String) value));
        } catch (IOException ignored) {
        }
    }

    /**
     * Метод поиска цитаты по ключевому слову
     *
     * @param keyword ключевое слово
     * @return найденная цитата
     */
    @Override
    public String search(String keyword) {
        return dictionary.getOrDefault(keyword, null);
    }
}
