package edu.hw6.filters;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LargerThanFilter implements AbstractFilter {
    private final int size;

    public LargerThanFilter(int size) {
        this.size = size;
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
        return Files.size(path) >= size;
    }
}
