package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CloneFile {
    private static final String DOT = "\\.";

    private CloneFile() {}

    @SuppressWarnings("checkstyle:ReturnCount")
    public static void cloneFile(Path path) throws IOException {
        if (!Files.exists(path)) {
            return;
        }

        String filename = path.getFileName().toString();
        String[] parts = filename.split(DOT);
        int i = 1;
        boolean startsWithDot = filename.startsWith(DOT);
        StringBuilder extensionBuilder = new StringBuilder(".");

        for (int l = startsWithDot ? 2 : 1; l < parts.length; l++) {
            extensionBuilder.append(parts[l]);

            if (l != parts.length - 1) {
                extensionBuilder.append(".");
            }
        }

        String extension = extensionBuilder.toString();

        while (true) {
            StringBuilder builder = new StringBuilder(startsWithDot ? parts[1] : parts[0]);

            builder.append(" - копия");
            if (i > 1) {
                builder.append(" (").append(i).append(")");
            }
            if (!extension.equals(".")) {
                builder.append(extension);
            }

            Path newPath = Path.of(path.getParent().toString(), builder.toString());

            if (Files.exists(newPath)) {
                i++;

                continue;
            }

            Files.copy(path, newPath);
            return;
        }
    }
}
