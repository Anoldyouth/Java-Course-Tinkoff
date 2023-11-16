package edu.hw6.filters;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import org.jetbrains.annotations.NotNull;

public class GlobFilter implements AbstractFilter {
    private final PathMatcher pathMatcher;

    /**
     * Поиск по сопоставлению с образцом
     *
     * @param extension искомый формат файла
     */
    public GlobFilter(@NotNull String extension) {
        pathMatcher = FileSystems.getDefault().getPathMatcher("glob:" + extension);
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
        return pathMatcher.matches(path.getFileName());
    }
}
