package edu.hw6.filters;

import java.nio.file.DirectoryStream;
import java.nio.file.Path;

public interface AbstractFilter extends DirectoryStream.Filter<Path> {
    default AbstractFilter and(AbstractFilter filter) {
        return (Path path) -> accept(path) && filter.accept(path);
    }
}
