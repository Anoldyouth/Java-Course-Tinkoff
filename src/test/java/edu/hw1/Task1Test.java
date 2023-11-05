package edu.hw1;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    @ParameterizedTest
    @ValueSource(strings = {"1:59", "9999:59"})
    @DisplayName("Можно вводить в минуты сколь угодно большое число")
    void anyMinutes(String time) {
        int answer = Task1.minutesToSeconds(time);
        assertThat(answer).isNotEqualTo(-1);
    }
}
