package edu.hw1;

import edu.hw1.Exceptions.WrongInputException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task8 {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int[][] STATES = {{-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {-2, -1}, {-2, 1}, {2, -1}, {2, 1}};
    private static final int SIZE = 8;

    private Task8() {
    }

    /**
     * Напишите функцию, которая возвращает true, если кони расставлены на шахматной доске так,
     * что ни один конь не может захватить другого коня.
     *
     * @param board доска с конями
     * @return флаг о том, что ни один конь не может захватить другого коня
     * @throws WrongInputException ошибка при неверных параметрах длины и ширины доски
     */
    public static boolean knightBoardCapture(int[][] board) throws WrongInputException {
        checkInput(board);

        for (int i = 0; i < SIZE; i++) {

            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] != 1) {
                    continue;
                }

                if (isCaptured(board, j, i)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Валидация размеров доски
     *
     * @param board доска с конями
     * @throws WrongInputException ошибка при неверных параметрах длины и ширины доски
     */
    private static void checkInput(int[][] board) throws WrongInputException {
        if (board.length != SIZE) {
            throw new WrongInputException();
        }

        for (int i = 0; i < SIZE; i++) {
            if (board[i].length != SIZE) {
                throw new WrongInputException();
            }
        }
    }

    /**
     * Проверка на то, что конь не захвачен
     *
     * @param board доска с конями
     * @param x0 текущая координата по x
     * @param y0 текущая координата по y
     * @return является ли конь захваченным другой фигурой
     */
    private static boolean isCaptured(int[][] board, int x0, int y0) {
        int x;
        int y;

        for (int[] pair : STATES) {
            x = x0 + pair[1];
            y = y0 + pair[0];

            if (x < 0 || x > SIZE - 1 || y < 0 || y > SIZE - 1) {
                continue;
            }

            if (board[x][y] == 1) {
                return true;
            }
        }

        return false;
    }
}
