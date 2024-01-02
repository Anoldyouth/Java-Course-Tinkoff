package edu.hw1;

import edu.hw1.Exceptions.WrongInputException;
import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task6 {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int MIN_FOUR_DIGITS_NUMBER = 1000;
    private static final int MAX_FOUR_DIGITS_NUMBER = 9999;
    private static final int KAPREKAR_NUMBER = 6174;
    private static final int DIGITS_NUMBER = 4;
    private static final int DIGIT = 10;
    private static final int[] ALL_NUMBERS_REPEATING = {1111, 2222, 3333, 4444, 5555, 6666, 7777, 8888, 9999};

    private Task6() {
    }

    /**
     * Выберем любое четырёхзначное число n, больше 1000, в котором не все цифры одинаковы. <p>
     * 1. Расположим цифры сначала в порядке возрастания, затем в порядке убывания. <p>
     * 2. Вычтем из большего меньшее. Производя перестановки цифр и вычитания, нули следует сохранять. <p>
     * Описанное действие назовём функцией Капрекара K(n). <p>
     * Повторяя этот процесс с получающимися разностями, не более чем за семь шагов мы получим число 6174,
     * которое будет затем воспроизводить само себя.
     * Это свойство числа 6174 было открыто в 1949 году. Индийским математиком Д. Р. Капрекаром,
     * в честь которого оно и получило своё название.<p>
     * Требуется написать рекурсивную функцию,
     * которая вернет для заданного числа количество шагов к получению числа 6174.
     *
     * @param number число для проверки
     * @return глубина вхождения
     * @throws WrongInputException Ошибка, когда число находится не в отрезке [1000; 9999]
     */
    public static int countK(int number) throws WrongInputException {
        if (number < MIN_FOUR_DIGITS_NUMBER || number > MAX_FOUR_DIGITS_NUMBER) {
            throw new WrongInputException();
        }

        for (int exclusion: ALL_NUMBERS_REPEATING) {
            if (exclusion == number) {
                throw new WrongInputException();
            }
        }

        int[] newNumbers = reformatNumber(number);
        int numbersSubtraction = Math.abs(newNumbers[0] - newNumbers[1]);

        if (numbersSubtraction == KAPREKAR_NUMBER) {
            return 1;
        }

        return countK(numbersSubtraction, 1);
    }

    /**
     * Функция с подсчетом глубины вхождения
     *
     * @param number число для проверки
     * @param level  текущий уровень вхождения
     * @return глубина вхождения
     */
    private static int countK(int number, int level) {
        int[] newNumbers = reformatNumber(number);
        int numbersSubtraction = Math.abs(newNumbers[0] - newNumbers[1]);

        if (numbersSubtraction == KAPREKAR_NUMBER) {
            return level + 1;
        }

        return countK(numbersSubtraction, level + 1);
    }

    /**
     * Функция для переворачивания числа
     *
     * @param number число
     * @return перевернутое число
     */
    private static int reverseNumber(int number) {
        int result = 0;
        int workNumber = number;

        for (int i = 0; i < DIGITS_NUMBER; i++) {
            result = result * DIGIT + workNumber % DIGIT;
            workNumber /= DIGIT;
        }

        return result;
    }

    /**
     * Функция, которая располагает цифры числа сначала в порядке возрастания, потом в порядке убывания
     *
     * @param number число для изменения
     * @return пара измененных чисел
     */
    private static int[] reformatNumber(int number) {
        char[] charArrayNumber = String.format(Integer.toString(number), "%04d").toCharArray();
        Arrays.sort(charArrayNumber);
        int newNumber = Integer.parseInt(new String(charArrayNumber));

        return new int[] {newNumber, reverseNumber(newNumber)};
    }
}
