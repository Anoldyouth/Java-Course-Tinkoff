package edu.hw1;

import edu.hw1.Exceptions.WrongInputException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task8Test {
    @Test
    @DisplayName("Успешный ответ")
    void successful() throws WrongInputException {
        boolean answer = Task8.knightBoardCapture(new int[][] {
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0}
        });

        assertThat(answer).isEqualTo(true);
    }

    @Test
    @DisplayName("Неуспешный ответ")
    void unsuccessful() throws WrongInputException {
        boolean answer = Task8.knightBoardCapture(new int[][] {
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 1, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 1, 0, 1, 0, 1}
        });

        assertThat(answer).isEqualTo(false);
    }

    @Test
    @DisplayName("Неверная высота массива")
    void invalidArrayHeight() throws WrongInputException {
        WrongInputException thrown = assertThrows(
            WrongInputException.class,
            () -> Task8.knightBoardCapture(new int[][] {
                {1, 0, 1, 0, 1, 0, 1, 0}
            })
        );

        assertThat(thrown).isNotNull();
    }

    @Test
    @DisplayName("Неверная ширина массива")
    void invalidArrayWidth() throws WrongInputException {
        WrongInputException thrown = assertThrows(
            WrongInputException.class,
            () -> Task8.knightBoardCapture(new int[][] {
                {1, 0, 1},
                {0, 1, 0, 1, 0, 1, 0, 1},
                {0, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 1, 0, 0, 1, 0, 1},
                {1, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 1, 0, 1},
                {1, 0, 0, 0, 1, 0, 1, 0},
                {0, 0, 0, 1, 0, 1, 0, 1}
            })
        );

        assertThat(thrown).isNotNull();
    }
}
