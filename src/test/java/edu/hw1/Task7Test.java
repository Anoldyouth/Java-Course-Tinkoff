package edu.hw1;

import edu.hw1.Exceptions.WrongInputException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task7Test {
    @Test
    @DisplayName("Успешный ответ, сдвиг вправо")
    void successfulRight() throws WrongInputException {
        int answer = Task7.rotateRight(8, 1);

        assertThat(answer).isEqualTo(4);
    }

    @Test
    @DisplayName("Успешный ответ, сдвиг влево")
    void successfulLeft() throws WrongInputException {
        int answer = Task7.rotateLeft(16, 1);

        assertThat(answer).isEqualTo(1);
    }

    @Test
    @DisplayName("Успешный ответ, сдвиг вправо на ноль")
    void successfulZeroShiftRight() throws WrongInputException {
        int answer = Task7.rotateRight(8, 0);

        assertThat(answer).isEqualTo(8);
    }

    @Test
    @DisplayName("Успешный ответ, сдвиг влево на ноль")
    void successfulZeroShiftLeft() throws WrongInputException {
        int answer = Task7.rotateLeft(16, 0);

        assertThat(answer).isEqualTo(16);
    }

    @Test
    @DisplayName("Отрицательное число, вправо")
    void negativeRight() throws WrongInputException {
        WrongInputException thrown = assertThrows(
            WrongInputException.class,
            () -> Task7.rotateRight(-20, 1)
        );

        assertThat(thrown).isNotNull();
    }

    @Test
    @DisplayName("Отрицательное число, влево")
    void negativeLeft() throws WrongInputException {
        WrongInputException thrown = assertThrows(
            WrongInputException.class,
            () -> Task7.rotateLeft(-20, 1)
        );

        assertThat(thrown).isNotNull();
    }

    @Test
    @DisplayName("Отрицательное смещение, вправо")
    void negativeShiftRight() throws WrongInputException {
        WrongInputException thrown = assertThrows(
            WrongInputException.class,
            () -> Task7.rotateRight(20, -1)
        );

        assertThat(thrown).isNotNull();
    }

    @Test
    @DisplayName("Отрицательное смещение, влево")
    void negativeShiftLeft() throws WrongInputException {
        WrongInputException thrown = assertThrows(
            WrongInputException.class,
            () -> Task7.rotateLeft(20, -1)
        );

        assertThat(thrown).isNotNull();
    }
}
