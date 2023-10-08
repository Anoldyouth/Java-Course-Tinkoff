package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Успешный тест")
    void successful() {
        int seconds = Task1.minutesToSeconds("01:00");

        assertThat(seconds).isEqualTo(60);
    }

    @Test
    @DisplayName("Неправильный формат данных")
    void wrongInputFormat() {
        int answer = Task1.minutesToSeconds("10:60");

        assertThat(answer).isEqualTo(-1);
    }

    @Test
    @DisplayName("Можно вводить в минуты сколь угодно большое число")
    void anyMinutes() {
        int firstAnswer = Task1.minutesToSeconds("1:59");
        assertThat(firstAnswer).isNotEqualTo(-1);

        int secondAnswer = Task1.minutesToSeconds("9999:59");
        assertThat(secondAnswer).isNotEqualTo(-1);
    }
}
