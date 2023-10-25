package edu.project1.guess_results;

import org.jetbrains.annotations.NotNull;

public record Defeat(int attempt, int maxAttempts, char[] state) implements GuessResult {
    @Override
    public char[] state() {
        return state;
    }

    @Override
    public int attempt() {
        return attempt;
    }

    @Override
    public int maxAttempts() {
        return maxAttempts;
    }

    @Override
    public @NotNull String message() {
        return "You lose.";
    }
}
