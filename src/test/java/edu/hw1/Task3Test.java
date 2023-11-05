package edu.hw1;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task3Test {
    @Test
    @DisplayName("Два заполненных массива, может быть вложен")
    void successfulBothFilled() {
        boolean answer = Task3.isNestable(new int[] {1, 2, 3, 4}, new int[] {0, 6});

        assertThat(answer).isEqualTo(true);
    }

    @Test
    @DisplayName("Два заполненных массива, не может быть вложен")
    void unsuccessfulBothFilled() {
        boolean answer = Task3.isNestable(new int[] {9, 9, 8}, new int[] {8, 9});

        assertThat(answer).isEqualTo(false);
    }

    @Test
    @DisplayName("Оба массива пустые")
    void bothEmpty() {
        boolean answer = Task3.isNestable(new int[] {}, new int[] {});

        assertThat(answer).isEqualTo(true);
    }

    @Test
    @DisplayName("Первый массив пустой")
    void firstEmpty() {
        boolean answer = Task3.isNestable(new int[] {}, new int[] {1, 2});

        assertThat(answer).isEqualTo(true);
    }

    @Test
    @DisplayName("Второй массив пустой")
    void secondEmpty() {
        boolean answer = Task3.isNestable(new int[] {1, 2}, new int[] {});

        assertThat(answer).isEqualTo(true);
    }
}
