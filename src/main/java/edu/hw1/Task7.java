package edu.hw1;

import edu.hw1.Exceptions.WrongInputException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task7 {
    private final static Logger LOGGER = LogManager.getLogger();

    private Task7() {
    }

//    public static void main(String[] args) throws WrongInputException {
//        LOGGER.info(rotateRight(8, 1)); // 4, 1000 -> 0100
//        LOGGER.info(rotateLeft(16, 1)); // 1, 10000 -> 00001
//        LOGGER.info(rotateLeft(17, 2)); // 6, 10001 -> 00110
//    }

    /**
     * Циклический сдвиг влево
     *
     * @param n     целое положительное число
     * @param shift размер циклического сдвига
     * @return сдвинутое число
     * @throws WrongInputException при отрицательных числах
     */
    public static int rotateLeft(int n, int shift) throws WrongInputException {
        if (n < 0 || shift < 0) {
            throw new WrongInputException();
        }

        String binary = Integer.toBinaryString(n);
        int workShift = shift % binary.length();

        String firstPart = binary.substring(workShift);
        String secondPart = binary.substring(0, workShift);

        return Integer.parseInt(firstPart + secondPart, 2);
    }

    /**
     * Циклический сдвиг вправо
     *
     * @param n     целое положительное число
     * @param shift размер циклического сдвига
     * @return сдвинутое число
     * @throws WrongInputException при отрицательных числах
     */
    public static int rotateRight(int n, int shift) throws WrongInputException {
        if (n < 0 || shift < 0) {
            throw new WrongInputException();
        }

        String binary = Integer.toBinaryString(n);
        int workShift = binary.length() - (shift % binary.length());

        String firstPart = binary.substring(workShift);
        String secondPart = binary.substring(0, workShift);

        return Integer.parseInt(firstPart + secondPart, 2);
    }
}
