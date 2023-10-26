package edu.hw1;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task4Test {
    @Test
    @DisplayName("Успешное выполнение, четный")
    void successful() {
        String answer = Task4.fixString("123456");

        assertThat(answer).isEqualTo("214365");
    }

    @Test
    @DisplayName("Успешное выполнение, нечетный")
    void successfulOdd() {
        String answer = Task4.fixString("badce");

        assertThat(answer).isEqualTo("abcde");
    }

    @Test
    @DisplayName("Успешное выполнение, пустая строка")
    void successfulEmpty() {
        String answer = Task4.fixString("");

        assertThat(answer).isEqualTo("");
    }
}
