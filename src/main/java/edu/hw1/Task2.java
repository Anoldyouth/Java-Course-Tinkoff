package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task2 {
    private final static Logger LOGGER = LogManager.getLogger();

    private Task2() {
    }

//    public static void main(String[] args) {
//        LOGGER.info(countDigits(4666)); // 4
//        LOGGER.info(countDigits(544)); // 3
//        LOGGER.info(countDigits(0)); // 1
//    }

    /**
     * Напишите функцию, которая возвращает количество цифр в десятичной форме числа.
     * Пользоваться преобразованием в строку запрещено.
     *
     * @param number число, в котором нужно посчитать число цифр
     * @return возвращает количество цифр
     */
    public static int countDigits(int number) {
        return number == 0 ? 1 : (int) Math.log10(Math.abs(number)) + 1;
    }
}
