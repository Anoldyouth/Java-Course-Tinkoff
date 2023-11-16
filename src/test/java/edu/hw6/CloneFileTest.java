package edu.hw6;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CloneFileTest {
    private final static String DIRECTORY_PATH = "test_clone_file";

    @BeforeEach
    void createCloneFIle() throws Exception {
        deleteDirectory(new File(DIRECTORY_PATH));
        Files.createDirectory(Path.of(DIRECTORY_PATH));
    }

    @AfterEach
    void deleteAllFiles() throws Exception {
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
    @DisplayName("Первое успешное клонирование")
    void firstClone() throws IOException {
        Path path = Path.of(DIRECTORY_PATH, "test");
        Files.createFile(path);
        Files.writeString(path, "test");

        CloneFile.cloneFile(path);

        Path newPath = Path.of(DIRECTORY_PATH, "test - копия");
        assertThat(Files.exists(newPath)).isTrue();
        assertThat(Files.readString(newPath)).isEqualTo("test");
    }

    @Test
    @DisplayName("Более одного успешного клонирования")
    void nClone() throws IOException {
        Path path = Path.of(DIRECTORY_PATH, "test");
        Files.createFile(path);
        CloneFile.cloneFile(path);
        CloneFile.cloneFile(path);

        assertThat(Files.exists(Path.of(DIRECTORY_PATH, "test - копия (2)"))).isTrue();
    }

    @Test
    @DisplayName("Работа с расширениями")
    void cloneWithExtension() throws IOException {
        Path path = Path.of(DIRECTORY_PATH, "test.txt");
        Files.createFile(path);
        CloneFile.cloneFile(path);

        assertThat(Files.exists(Path.of(DIRECTORY_PATH, "test - копия.txt"))).isTrue();
    }
}
