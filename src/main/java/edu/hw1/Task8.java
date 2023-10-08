package edu.hw1;

import edu.hw1.Exceptions.WrongInputException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task8 {
    private final static Logger LOGGER = LogManager.getLogger();
    private final static int[][] STATES = {{-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {-2, -1}, {-2, 1}, {2, -1}, {2, 1}};

    private final static int MIN_BOARD_CELL_NUMBER = 0;
    private final static int MAX_BOARD_CELL_NUMBER = 7;
    private final static int SIZE = 8;

    private Task8() {
    }

//    public static void main(String[] args) throws WrongInputException {
//        LOGGER.info(knightBoardCapture(new int[][] {
//            {0, 0, 0, 1, 0, 0, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 1, 0, 0, 0, 1, 0, 0},
//            {0, 0, 0, 0, 1, 0, 1, 0},
//            {0, 1, 0, 0, 0, 1, 0, 0},
//            {0, 0, 0, 0, 0, 0, 0, 0},
//            {0, 1, 0, 0, 0, 0, 0, 1},
//            {0, 0, 0, 0, 1, 0, 0, 0}
//        })); // true
//        LOGGER.info(knightBoardCapture(new int[][] {
//            {1, 0, 1, 0, 1, 0, 1, 0},
//            {0, 1, 0, 1, 0, 1, 0, 1},
//            {0, 0, 0, 0, 1, 0, 1, 0},
//            {0, 0, 1, 0, 0, 1, 0, 1},
//            {1, 0, 0, 0, 1, 0, 1, 0},
//            {0, 0, 0, 0, 0, 1, 0, 1},
//            {1, 0, 0, 0, 1, 0, 1, 0},
//            {0, 0, 0, 1, 0, 1, 0, 1}
//        })); // false
//        LOGGER.info(knightBoardCapture(new int[][] {
//            {0, 0, 0, 0, 1, 0, 0, 0},
//            {0, 0, 0, 0, 0, 1, 0, 0},
//            {0, 0, 0, 1, 0, 0, 0, 0},
//            {1, 0, 0, 0, 0, 0, 0, 0},
//            {0, 0, 0, 0, 1, 0, 0, 0},
//            {0, 0, 0, 0, 0, 1, 0, 0},
//            {0, 0, 0, 0, 0, 1, 0, 0},
//            {1, 0, 0, 0, 0, 0, 0, 0}
//        })); // false
//    }

    /**
     * Напишите функцию, которая возвращает true, если кони расставлены на шахматной доске так,
     * что ни один конь не может захватить другого коня.
     *
     * @param board доска с конями
     * @return флаг о том, что ни один конь не может захватить другого коня
     */
    public static boolean knightBoardCapture(int[][] board) throws WrongInputException {
        int x;
        int y;

        if (board.length != SIZE) {
            throw new WrongInputException();
        }

        for (int i = MIN_BOARD_CELL_NUMBER; i < MAX_BOARD_CELL_NUMBER; i++) {
            if (board[i].length != SIZE) {
                throw new WrongInputException();
            }

            for (int j = MIN_BOARD_CELL_NUMBER; j < MAX_BOARD_CELL_NUMBER; j++) {
                if (board[i][j] != 1) {
                    continue;
                }

                for (int[] pair : STATES) {
                    x = j + pair[1];
                    y = i + pair[0];

                    if (x < MIN_BOARD_CELL_NUMBER
                        || x > MAX_BOARD_CELL_NUMBER
                        || y < MIN_BOARD_CELL_NUMBER
                        || y > MAX_BOARD_CELL_NUMBER) {
                        continue;
                    }

                    if (board[x][y] == 1) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
