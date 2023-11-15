package edu.hw6.filters;

import java.io.IOException;
import java.nio.file.Path;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;

public class FileNameRegexFilter implements AbstractFilter {
    private final String regex;

    public FileNameRegexFilter(@NotNull String regex) {
        this.regex = regex;
    }

    /**
     * Decides if the given directory entry should be accepted or filtered.
     *
     * @param path the directory entry to be tested
     * @return {@code true} if the directory entry should be accepted
     * @throws IOException If an I/O error occurs
     */
    @Override
    public boolean accept(Path path) throws IOException {
        return Pattern.compile(regex).matcher(path.getFileName().toString()).matches();
    }
}
