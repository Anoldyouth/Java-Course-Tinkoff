package edu.hw6;

import edu.hw6.filters.FileNameRegexFilter;
import edu.hw6.filters.GlobFilter;
import edu.hw6.filters.LargerThanFilter;
import edu.hw6.filters.LessThanFilter;
import edu.hw6.filters.MagicNumberFilter;
import edu.hw6.filters.ReadableFilter;
import edu.hw6.filters.WritableFilter;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import static java.nio.file.Files.newDirectoryStream;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.HashSet;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FiltersTest {
    private final static String DIRECTORY_PATH = "test_filters";

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
    void readable() throws IOException {
        Path first = Path.of(DIRECTORY_PATH, "first");
        Path second = Path.of(DIRECTORY_PATH, "second");

        Set<PosixFilePermission> readable = new HashSet<>();
        readable.add(PosixFilePermission.OWNER_READ);
        readable.add(PosixFilePermission.GROUP_READ);

        Set<PosixFilePermission> writable = new HashSet<>();
        writable.add(PosixFilePermission.OWNER_WRITE);
        writable.add(PosixFilePermission.OTHERS_READ);

        FileAttribute<Set<PosixFilePermission>> readableAttributes =
                PosixFilePermissions.asFileAttribute(readable);

        FileAttribute<Set<PosixFilePermission>> writableAttributes =
                PosixFilePermissions.asFileAttribute(writable);


        Files.createFile(first, readableAttributes);
        Files.createFile(second, writableAttributes);

        try(var stream = Files.newDirectoryStream(Path.of(DIRECTORY_PATH), new ReadableFilter())) {
            var iterator = stream.iterator();
            assertThat(iterator.next()).isEqualTo(first);
            assertThat(iterator.hasNext()).isFalse();
        }
    }

    @Test
    void writable() throws IOException {
        Path first = Path.of(DIRECTORY_PATH, "first");
        Path second = Path.of(DIRECTORY_PATH, "second");

        Set<PosixFilePermission> readable = new HashSet<>();
        readable.add(PosixFilePermission.OWNER_READ);
        readable.add(PosixFilePermission.GROUP_READ);

        Set<PosixFilePermission> writable = new HashSet<>();
        writable.add(PosixFilePermission.OWNER_WRITE);
        writable.add(PosixFilePermission.OTHERS_WRITE);

        FileAttribute<Set<PosixFilePermission>> readableAttributes =
                PosixFilePermissions.asFileAttribute(readable);

        FileAttribute<Set<PosixFilePermission>> writableAttributes =
                PosixFilePermissions.asFileAttribute(writable);


        Files.createFile(first, readableAttributes);
        Files.createFile(second, writableAttributes);

        try(var stream = Files.newDirectoryStream(Path.of(DIRECTORY_PATH), new WritableFilter())) {
            var iterator = stream.iterator();
            assertThat(iterator.next()).isEqualTo(second);
            assertThat(iterator.hasNext()).isFalse();
        }
    }

    @Test
    void lessThan() throws IOException {
        Path first = Path.of(DIRECTORY_PATH, "first");
        Path second = Path.of(DIRECTORY_PATH, "second");

        try (RandomAccessFile firstFile = new RandomAccessFile(first.toString(), "rw")) {
            // Установка размера файла
            firstFile.setLength(200);
        }

        try (RandomAccessFile secondFile = new RandomAccessFile(second.toString(), "rw")) {
            // Установка размера файла
            secondFile.setLength(100);
        }

        try(var stream = Files.newDirectoryStream(Path.of(DIRECTORY_PATH), new LessThanFilter(150))) {
            var iterator = stream.iterator();
            assertThat(iterator.next()).isEqualTo(second);
            assertThat(iterator.hasNext()).isFalse();
        }
    }

    @Test
    void moreThanThan() throws IOException {
        Path first = Path.of(DIRECTORY_PATH, "first");
        Path second = Path.of(DIRECTORY_PATH, "second");

        try (RandomAccessFile firstFile = new RandomAccessFile(first.toString(), "rw")) {
            // Установка размера файла
            firstFile.setLength(200);
        }

        try (RandomAccessFile secondFile = new RandomAccessFile(second.toString(), "rw")) {
            // Установка размера файла
            secondFile.setLength(100);
        }

        try(var stream = Files.newDirectoryStream(Path.of(DIRECTORY_PATH), new LargerThanFilter(150))) {
            var iterator = stream.iterator();
            assertThat(iterator.next()).isEqualTo(first);
            assertThat(iterator.hasNext()).isFalse();
        }
    }

    @Test
    void glob() throws IOException {
        Path first = Path.of(DIRECTORY_PATH, "first.java");
        Path second = Path.of(DIRECTORY_PATH, "second.txt");

        Files.createFile(first);
        Files.createFile(second);

        try(var stream = Files.newDirectoryStream(Path.of(DIRECTORY_PATH), new GlobFilter("*.txt"))) {
            var iterator = stream.iterator();
            assertThat(iterator.next()).isEqualTo(second);
            assertThat(iterator.hasNext()).isFalse();
        }
    }

    @Test
    void regex() throws IOException {
        Path first = Path.of(DIRECTORY_PATH, "first.java");
        Path second = Path.of(DIRECTORY_PATH, "second.txt");

        Files.createFile(first);
        Files.createFile(second);

        try(var stream = Files.newDirectoryStream(Path.of(DIRECTORY_PATH), new FileNameRegexFilter("^.{6}\\.txt$"))) {
            var iterator = stream.iterator();
            assertThat(iterator.next()).isEqualTo(second);
            assertThat(iterator.hasNext()).isFalse();
        }
    }

    @Test
    void magic() throws IOException {
        Path first = Path.of(DIRECTORY_PATH, "first.java");
        Path second = Path.of(DIRECTORY_PATH, "second.png");
        Files.createFile(first);
        Path testFile = Path.of("src","test", "java", "resources", "test.png");

        Files.copy(testFile, second);

        Object[] data = {(byte) 0x89, 'P', 'N', 'G'};

        try(var stream = Files.newDirectoryStream(Path.of(DIRECTORY_PATH), new MagicNumberFilter(data))) {
            var iterator = stream.iterator();
            assertThat(iterator.next()).isEqualTo(second);
            assertThat(iterator.hasNext()).isFalse();
        }
    }

    @Test
    void and() throws IOException {
        Path file = Path.of(DIRECTORY_PATH, "file.java");
        Path test = Path.of(DIRECTORY_PATH, "test");

        Set<PosixFilePermission> permissions = new HashSet<>();
        permissions.add(PosixFilePermission.OWNER_READ);
        permissions.add(PosixFilePermission.GROUP_READ);
        permissions.add(PosixFilePermission.OWNER_WRITE);
        permissions.add(PosixFilePermission.OTHERS_WRITE);

        FileAttribute<Set<PosixFilePermission>> attributes =
                PosixFilePermissions.asFileAttribute(permissions);

        Files.createFile(file, attributes);
        Files.createFile(test);

        try (RandomAccessFile firstFile = new RandomAccessFile(file.toString(), "rw")) {
            // Установка размера файла
            firstFile.setLength(200);
        }

        try(var stream = Files.newDirectoryStream(
                Path.of(DIRECTORY_PATH),
                new ReadableFilter().and(new WritableFilter()).and(new LargerThanFilter(150))
        )) {
            var iterator = stream.iterator();
            assertThat(iterator.next()).isEqualTo(file);
            assertThat(iterator.hasNext()).isFalse();
        }
    }
}
