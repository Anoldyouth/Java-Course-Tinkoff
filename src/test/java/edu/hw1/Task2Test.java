package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Успешная работа с нулем")
    void successfulZero() {
        int answer = Task2.countDigits(0);

        assertThat(answer).isEqualTo(1);
    }

    @Test
    @DisplayName("Успешная работа с положительным числом")
    void successfulPositive() {
        int answer = Task2.countDigits(4666);

        assertThat(answer).isEqualTo(4);
    }

    @Test
    @DisplayName("Успешная работа с отрицательным числом")
    void successfulNegative() {
        int answer = Task2.countDigits(-4666);

        assertThat(answer).isEqualTo(4);
    }
}
