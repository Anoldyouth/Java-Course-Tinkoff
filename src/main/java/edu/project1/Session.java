package edu.project1;

import edu.project1.guess_results.Defeat;
import edu.project1.guess_results.FailedGuess;
import edu.project1.guess_results.GuessResult;
import edu.project1.guess_results.RepeatedGuess;
import edu.project1.guess_results.SuccessfulGuess;
import edu.project1.guess_results.Win;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Session {
    private final Map<Character, int[]> answer;
    private char[] userGuesses;
    private final char[] userAnswer;
    private final int maxAttempts;
    private int attempts;

    Session(String answer, int maxAttempts) {
        this.answer = new HashMap<>();
        this.userGuesses = new char[] {};
        this.userAnswer = new char[answer.length()];
        this.maxAttempts = maxAttempts;
        this.attempts = 0;

        for (int i = 0; i < answer.length(); i++) {
            char ch = answer.charAt(i);
            if (this.answer.containsKey(ch)) {
                this.answer.put(ch, IntStream.concat(
                        Arrays.stream(this.answer.get(ch)),
                        Arrays.stream(new int[]{i})
                ).toArray());
            } else {
                this.answer.put(ch, new int[]{i});
            }
        }
    }

    GuessResult guess(char guess) {
        if (containsUserGuess(guess)) {
            return new RepeatedGuess(attempts, maxAttempts, userAnswer);
        }

        addUserGuess(guess);

        if (!answer.containsKey(guess)) {
            return fail();
        }

        for (int i: answer.get(guess)) {
            userAnswer[i] = guess;
        }
        answer.remove(guess);

        if (answer.isEmpty()) {
            return new Win(attempts, maxAttempts, userAnswer);
        }

        return new SuccessfulGuess(attempts, maxAttempts, userAnswer);
    }

    private GuessResult fail() {
        attempts++;
        if (attempts == maxAttempts) {
            return new Defeat(attempts, maxAttempts, userAnswer);
        }

        return new FailedGuess(attempts, maxAttempts, userAnswer);
    }

    GuessResult giveUp() {
        return new Defeat(attempts, maxAttempts, userAnswer);
    }

    private boolean containsUserGuess(char guess) {
        for (char ch: userGuesses) {
            if (ch == guess) {
                return true;
            }
        }

        return false;
    }

    private void addUserGuess(char guess) {
        char[] mergedArray = new char[userGuesses.length + 1];

        System.arraycopy(userGuesses, 0, mergedArray, 0, userGuesses.length);
        System.arraycopy(new char[] {guess}, 0, mergedArray, userGuesses.length, 1);

        userGuesses = mergedArray;
    }
}
