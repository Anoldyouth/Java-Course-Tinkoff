package edu.project1;

import edu.project1.dictionary.Dictionary;
import edu.project1.dictionary.FileDictionary;
import edu.project1.exceptions.EmptyFileException;
import edu.project1.exceptions.OpeningFileException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FileDictionaryUnitTest {
    @Test
    @DisplayName("Успешный ответ")
    void successful() throws Exception {
        Dictionary dictionary = new FileDictionary("src/test/java/edu/project1/test_resources/file.txt");

        assertThat(dictionary.randomWord()).isEqualTo("word");
    }

    @Test
    @DisplayName("Пустой файл")
    void emptyFile() {
        Dictionary dictionary = new FileDictionary("src/test/java/edu/project1/test_resources/empty_file.txt");

        EmptyFileException thrown = assertThrows(
                EmptyFileException.class,
                dictionary::randomWord
        );

        assertThat(thrown).isNotNull();
    }

    @Test
    @DisplayName("Нет файла")
    void nonExistingFile() {
        Dictionary dictionary = new FileDictionary("src/test/java/edu/project1/test_resources/non_existing_file.txt");

        OpeningFileException thrown = assertThrows(
                OpeningFileException.class,
                dictionary::randomWord
        );

        assertThat(thrown).isNotNull();
    }
}
