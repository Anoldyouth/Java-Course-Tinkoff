package edu.project1;

import edu.project1.guess_results.Defeat;
import edu.project1.guess_results.FailedGuess;
import edu.project1.guess_results.GuessResult;
import edu.project1.guess_results.RepeatedGuess;
import edu.project1.guess_results.SuccessfulGuess;
import edu.project1.guess_results.Win;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SessionUnitTest {
    @Test
    @DisplayName("Проверка на повтор символа")
    void repeated() {
        Session session = new Session("word", 2);
        session.guess('w');

        GuessResult result = session.guess('w');
        assertThat(result).isInstanceOf(RepeatedGuess.class);
        assertThat(result.attempt()).isEqualTo(0);
        assertThat(result.maxAttempts()).isEqualTo(2);
    }

    @Test
    @DisplayName("Проверка на победу")
    void win() {
        Session session = new Session("w", 1);
        GuessResult result = session.guess('w');

        assertThat(result).isInstanceOf(Win.class);
        assertThat(result.attempt()).isEqualTo(0);
        assertThat(result.maxAttempts()).isEqualTo(1);
    }

    @Test
    @DisplayName("Проверка на поражение")
    void defeat() {
        Session session = new Session("c", 1);
        GuessResult result = session.guess('w');

        assertThat(result).isInstanceOf(Defeat.class);
        assertThat(result.attempt()).isEqualTo(1);
        assertThat(result.maxAttempts()).isEqualTo(1);
    }

    @Test
    @DisplayName("Проверка на успешное угадывание")
    void successful() {
        Session session = new Session("word", 2);
        GuessResult result = session.guess('w');

        assertThat(result).isInstanceOf(SuccessfulGuess.class);
        assertThat(result.attempt()).isEqualTo(0);
        assertThat(result.maxAttempts()).isEqualTo(2);
    }

    @Test
    @DisplayName("Проверка на неуспешное угадывание")
    void failed() {
        Session session = new Session("word", 2);
        GuessResult result = session.guess('c');

        assertThat(result).isInstanceOf(FailedGuess.class);
        assertThat(result.attempt()).isEqualTo(1);
        assertThat(result.maxAttempts()).isEqualTo(2);
    }

    @Test
    @DisplayName("Проверка на раннее завершение игры")
    void giveUp() {
        Session session = new Session("word", 2);
        GuessResult result = session.giveUp();

        assertThat(result).isInstanceOf(Defeat.class);
        assertThat(result.attempt()).isEqualTo(0);
        assertThat(result.maxAttempts()).isEqualTo(2);
    }
}
