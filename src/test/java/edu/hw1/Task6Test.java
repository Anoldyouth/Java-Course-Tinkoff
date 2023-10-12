package edu.hw1;

import edu.hw1.Exceptions.WrongInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task6Test {
    @Test
    @DisplayName("Успешный ответ")
    void successful() throws WrongInputException {
        int answer = Task6.countK(6621);

        assertThat(answer).isEqualTo(5);
    }

    @Test
    @DisplayName("Успешный ответ, нижняя граница")
    void successfulLowerLimit() throws WrongInputException {
        int answer = Task6.countK(1000);

        assertThat(answer).isEqualTo(5);
    }

    @Test
    @DisplayName("Успешный ответ, верхняя граница")
    void successfulUpperLimit() throws WrongInputException {
        int answer = Task6.countK(9998);

        assertThat(answer).isEqualTo(5);
    }

    @Test
    @DisplayName("Слишком маленькое число")
    void tooLowNumber() throws WrongInputException {
        WrongInputException thrown = assertThrows(
            WrongInputException.class,
            () -> Task6.countK(999)
        );

        assertThat(thrown).isNotNull();
    }

    @Test
    @DisplayName("Все цифры повторяются")
    void repeatingNumbers() throws WrongInputException {
        WrongInputException thrown = assertThrows(
            WrongInputException.class,
            () -> Task6.countK(9999)
        );

        assertThat(thrown).isNotNull();
    }
}
