package edu.hw1;

import edu.hw1.Exceptions.WrongInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task5Test {
    @Test
    @DisplayName("Успешный ответ, само число палиндром")
    void successful() throws WrongInputException {
        boolean answer = Task5.isPalindromeDescendant(12321);

        assertThat(answer).isEqualTo(true);
    }

    @Test
    @DisplayName("Успешный ответ, потомок палиндром")
    void successfulChild() throws WrongInputException {
        boolean answer = Task5.isPalindromeDescendant(11211230);

        assertThat(answer).isEqualTo(true);
    }

    @Test
    @DisplayName("Успешный ответ, одноразрядное число")
    void successfulOneDigit() throws WrongInputException {
        boolean answer = Task5.isPalindromeDescendant(1);

        assertThat(answer).isEqualTo(true);
    }

    @Test
    @DisplayName("Неуспешный ответ")
    void unsuccessful() throws WrongInputException {
        boolean answer = Task5.isPalindromeDescendant(12345);

        assertThat(answer).isEqualTo(false);
    }

    @Test
    @DisplayName("Бросок ошибки при отрицательном числе")
    void negativeException() throws WrongInputException {
        WrongInputException thrown = assertThrows(
            WrongInputException.class,
            () -> Task5.isPalindromeDescendant(-12345)
        );

        assertThat(thrown).isNotNull();
    }
}
