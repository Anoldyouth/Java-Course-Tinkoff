package edu.project1;

import edu.project1.dictionary.Dictionary;
import edu.project1.dictionary.FileDictionary;
import edu.project1.exceptions.ShortWordException;
import edu.project1.guess_results.Defeat;
import edu.project1.guess_results.FailedGuess;
import edu.project1.guess_results.GuessResult;
import edu.project1.guess_results.RepeatedGuess;
import edu.project1.guess_results.SuccessfulGuess;
import edu.project1.guess_results.Win;
import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.Formatter;
import nl.altindag.log.LogCaptor;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ConsoleHangmanUnitTest {
    private static String stringBuilder(int errors, int maxErrors, String state) {
        StringBuilder formattedString = new StringBuilder();
        try (Formatter formatter = new Formatter(formattedString)) {
            formatter.format(
                    "Errors: %d, max errors count: %d, current state: %s",
                    errors, maxErrors, state
            );
        }

        return formattedString.toString();
    }

    static Arguments[] endStates() {
        return new Arguments[]{
                Arguments.of(new Win(0, 1, new char[] {'w', 'i', 'n'})),
                Arguments.of(new Defeat(0, 1, new char[] {'d', 'e', 'f', 'e', 'a', 't'}))
        };
    }

    @ParameterizedTest
    @DisplayName("Проверка завершения игры")
    @MethodSource("endStates")
    void endGame(GuessResult state) throws Exception {
        try (MockedConstruction<Session> mockedSession = Mockito.mockConstruction(Session.class,
                (mock, context) -> {
                    when(mock.guess(Mockito.anyChar())).thenReturn(state);
                })
        ) {
            Dictionary dictionary = mock(FileDictionary.class);
            when(dictionary.randomWord()).thenReturn("word");

            String input = "w";
            ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
            System.setIn(inputStream);

            var logCaptor = LogCaptor.forClass(ConsoleHangman.class);

            ConsoleHangman hangman = new ConsoleHangman(dictionary, 1);
            hangman.run();

            assertThat(logCaptor.getInfoLogs().getLast())
                    .isEqualTo(stringBuilder(state.attempt(), state.maxAttempts(), Arrays.toString(state.state())));
        }
    }

    static Arguments[] intermediateStates() {
        return new Arguments[]{
                Arguments.of(new FailedGuess(0, 1, new char[] {'f', 'a', 'i', 'l', 'e', 'd'})),
                Arguments.of(new SuccessfulGuess(0, 1, new char[] {'s', 'u', 'c', 'c', 'e', 's', 's'})),
                Arguments.of(new RepeatedGuess(0, 1, new char[] {'r', 'e', 'p', 'e', 'a', 't', 'e', 'd'}))
        };
    }

    @ParameterizedTest
    @DisplayName("Проверка различных состояний")
    @MethodSource("intermediateStates")
    void intermediateState(GuessResult state) throws Exception {
        GuessResult end = new Win(0, 1, new char[] {'e', 'n', 'd'});
        try (MockedConstruction<Session> mockedSession = Mockito.mockConstruction(Session.class,
                (mock, context) -> {
                    when(mock.guess(Mockito.anyChar())).thenReturn(state, end);
                })
        ) {
            Dictionary dictionary = mock(FileDictionary.class);
            when(dictionary.randomWord()).thenReturn("word");

            String input = "w\nw\n";
            ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
            System.setIn(inputStream);

            var logCaptor = LogCaptor.forClass(ConsoleHangman.class);

            ConsoleHangman hangman = new ConsoleHangman(dictionary, 1);
            hangman.run();

            assertThat(logCaptor.getInfoLogs().get(2))
                    .isEqualTo(state.message());
            assertThat(logCaptor.getInfoLogs().get(3))
                    .isEqualTo(stringBuilder(state.attempt(), state.maxAttempts(), Arrays.toString(state.state())));
            assertThat(logCaptor.getInfoLogs().get(5))
                    .isEqualTo(end.message());
        }
    }

    static Arguments[] stringInputArgs() {
        return new Arguments[]{
                Arguments.of("\n"),
                Arguments.of("long input\n")
        };
    }

    @ParameterizedTest
    @DisplayName("Проверка ввода другой длины")
    @MethodSource("stringInputArgs")
    void input(String inputString) throws Exception {
        GuessResult end = new Win(0, 1, new char[] {'e', 'n', 'd'});
        try (MockedConstruction<Session> mockedSession = Mockito.mockConstruction(Session.class,
                (mock, context) -> {
                    when(mock.guess(Mockito.anyChar())).thenReturn(end);
                })
        ) {
            Dictionary dictionary = mock(FileDictionary.class);
            when(dictionary.randomWord()).thenReturn("word");

            String input = inputString + "w\n";
            ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
            System.setIn(inputStream);

            var logCaptor = LogCaptor.forClass(ConsoleHangman.class);

            ConsoleHangman hangman = new ConsoleHangman(dictionary, 1);
            hangman.run();

            assertThat(logCaptor.getInfoLogs().get(2))
                    .isEqualTo("Wrong input length!");
            assertThat(logCaptor.getInfoLogs().get(4))
                    .isEqualTo(end.message());
        }
    }

    @Test
    @DisplayName("Проверка выхода из игры")
    void exit() throws Exception {
        final Session[] mocked = {null};
        try (MockedConstruction<Session> mockedSession = Mockito.mockConstruction(Session.class,
                (mock, context) -> {
                    when(mock.giveUp()).thenReturn(new Defeat(0, 1, new char[] {'d', 'e', 'f', 'e', 'a', 't'}));
                    mocked[0] = mock;
                })
        ) {
            Dictionary dictionary = mock(FileDictionary.class);
            when(dictionary.randomWord()).thenReturn("word");

            String input = "exit\n";
            ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
            System.setIn(inputStream);

            ConsoleHangman hangman = new ConsoleHangman(dictionary, 1);
            hangman.run();

            verify(mocked[0]).giveUp();
        }
    }

    @Test
    @DisplayName("Проверка длины слова")
    void wordLength() throws Exception {
        Dictionary dictionary = mock(FileDictionary.class);
        when(dictionary.randomWord()).thenReturn("wd");

        String input = "exit\n";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        ConsoleHangman hangman = new ConsoleHangman(dictionary, 1);
        ShortWordException thrown = assertThrows(
                ShortWordException.class,
                hangman::run
        );

        assertThat(thrown).isNotNull();
    }
}
