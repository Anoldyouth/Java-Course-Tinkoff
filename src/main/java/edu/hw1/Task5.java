package edu.hw1;

import edu.hw1.Exceptions.WrongInputException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task5 {
    private final static Logger LOGGER = LogManager.getLogger();
    private final static int ZERO = 0;
    private final static int MIN_TWO_DIGITS = 10;

    private Task5() {
    }

//    public static void main(String[] args) throws Exception {
//        LOGGER.info(isPalindromeDescendant(11211230)); // true, 11211230 -> 2333 -> 56 -> 11
//        LOGGER.info(isPalindromeDescendant(13001120)); // true, 13001120 -> 4022 ➞ 44
//        LOGGER.info(isPalindromeDescendant(23336014)); // true, 23336014 -> 5665
//        LOGGER.info(isPalindromeDescendant(11)); // true
//        LOGGER.info(isPalindromeDescendant(12345)); // false, 12345 -> 159 -> 114 -> 15 -> 6
//    }

    /**
     * Будем называть потомком числа новое число, которое создается путем суммирования каждой пары соседних цифр. <p>
     * Напишите функцию, которая будет возвращать true,
     * если число является палиндромом
     * или если любой из его потомков длиной > 1 (как минимум 2 цифры) является палиндромом.
     *
     * @throws WrongInputException Ошибка при отрицательном числе
     * @param number число для проверки на палиндром
     * @return возвращает ответ, является ли число палиндромом
     */
    public static boolean isPalindromeDescendant(int number) throws WrongInputException {
        if (number < ZERO) {
            throw new WrongInputException();
        }

        if (number < MIN_TWO_DIGITS) {
            return true;
        }

        String stringNumber;
        int workNumber  = number;
        do {
            stringNumber = String.valueOf(workNumber);
            if ((new StringBuilder(stringNumber).reverse().toString().equals(stringNumber))) {
                return true;
            }

            workNumber = sumNumbers(workNumber);
        } while (workNumber > MIN_TWO_DIGITS);

        return false;
    }

    /**
     * Функция для получения потомка
     *
     * @param number число, от которого нужно получить потомка
     * @return потомок
     */
    private static int sumNumbers(int number) {
        StringBuilder newString = new StringBuilder();
        String string = String.valueOf(number);
        int i = 0;

        if (string.length() % 2 == 1) {
            newString.append(string.charAt(0));
            i = 1;
        }

        for (; i < string.length() - 1; i += 2) {
            newString.append(
                Character.getNumericValue(string.charAt(i)) + Character.getNumericValue(string.charAt(i + 1))
            );
        }

        return Integer.parseInt(newString.toString());
    }
}
