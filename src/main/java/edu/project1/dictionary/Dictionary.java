package edu.project1.dictionary;

import org.jetbrains.annotations.NotNull;

public interface Dictionary {
    @NotNull String randomWord() throws Exception;
}
