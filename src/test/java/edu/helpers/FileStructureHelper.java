package edu.helpers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileStructureHelper {

    public static void createStructure(String directory) throws Exception {
        deleteDirectory(new File(directory));
        Files.createDirectory(Path.of(directory));
    }

    public static void deleteStructure(String directory) throws Exception {
        deleteDirectory(new File(directory));
    }

    private static void deleteDirectory(File directory) throws Exception {
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
}
