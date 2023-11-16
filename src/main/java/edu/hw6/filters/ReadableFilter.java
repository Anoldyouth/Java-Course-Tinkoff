package edu.hw6.filters;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadableFilter implements AbstractFilter {
    /**
     * Decides if the given directory entry should be accepted or filtered.
     *
     * @param path the directory entry to be tested
     * @return {@code true} if the directory entry should be accepted
     * @throws IOException If an I/O error occurs
     */
    @Override
    public boolean accept(Path path) throws IOException {
        return Files.isReadable(path);
    }
}
