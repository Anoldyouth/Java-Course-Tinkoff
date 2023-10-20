package edu.project1.guess_results;

import org.jetbrains.annotations.NotNull;

sealed public interface GuessResult permits Defeat, FailedGuess, RepeatedGuess, SuccessfulGuess, Win {
    char[] state();

    int attempt();

    int maxAttempts();

    @NotNull String message();
}
