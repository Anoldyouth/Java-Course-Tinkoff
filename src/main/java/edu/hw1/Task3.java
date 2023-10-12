package edu.hw1;

import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task3 {
    private final static Logger LOGGER = LogManager.getLogger();

    private Task3() {
    }

    /**
     * Напишите функцию, которая возвращает true,
     * если первый массив может быть вложен во второй, и false в противном случае.
     * Массив может быть вложен, если:<p>
     * 1. min(a1) больше чем min(a2)<p>
     * 2. max(a1) меньше, чем max(a2)
     *
     * @param first первый массив
     * @param second второй массив
     * @return возвращает, можно ли вложить первый массив во второй
     */
    public static boolean isNestable(int[] first, int[] second) {
        if (first.length == 0 || second.length == 0) {
            return true;
        }

        int minFirst = Arrays.stream(first).min().getAsInt();
        int minSecond = Arrays.stream(second).min().getAsInt();
        int maxFirst = Arrays.stream(first).max().getAsInt();
        int maxSecond = Arrays.stream(second).max().getAsInt();

        return (minFirst > minSecond) && (maxFirst < maxSecond);
    }
}
