package edu.project1;

import edu.project1.dictionary.Dictionary;
import edu.project1.exceptions.ShortWordException;
import edu.project1.guess_results.Defeat;
import edu.project1.guess_results.GuessResult;
import edu.project1.guess_results.Win;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class ConsoleHangman {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Scanner scanner = new Scanner(System.in);
    private static final int MAX_WORD_LENGTH = 4;
    private final Dictionary dictionary;
    private final int maxAttempts;

    ConsoleHangman(Dictionary dictionary, int maxAttempts) {
        this.dictionary = dictionary;
        this.maxAttempts = maxAttempts;
    }

    public void run() throws Exception {
        LOGGER.info("Game is started!");
        Session session = sessionSetup();

        game(session);
    }

    private void game(Session session) {
        String input;
        GuessResult result;

        while (true) {
            LOGGER.info("Input guess: ");
            input = scanner.nextLine();

            if (input.equals("exit")) {
                result = session.giveUp();
                printState(result);

                break;
            }

            if (input.length() != 1) {
                LOGGER.info("Wrong input length!");
                continue;
            }

            result = tryGuess(session, input);
            printState(result);

            if (result instanceof Win || result instanceof Defeat) {
                break;
            }
        }
    }

    private GuessResult tryGuess(Session session, String input) {
        return session.guess(input.charAt(0));
    }

    private void printState(GuessResult guess) {
        LOGGER.info(guess.message());

        StringBuilder formattedString = new StringBuilder();
        try (Formatter formatter = new Formatter(formattedString)) {
            formatter.format(
                    "Errors: %d, max errors count: %d, current state: %s",
                    guess.attempt(), guess.maxAttempts(), Arrays.toString(guess.state())
            );
        }
        LOGGER.info(formattedString);
    }

    private Session sessionSetup() throws Exception {
        String word = dictionary.randomWord();

        if (word.length() < MAX_WORD_LENGTH) {
            throw new ShortWordException();
        }

        return new Session(
                word,
                maxAttempts
        );
    }
}
