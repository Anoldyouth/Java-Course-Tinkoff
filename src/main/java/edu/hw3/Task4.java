package edu.hw3;

import edu.hw3.exceptions.WrongInputException;
import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Collectors;

public class Task4 {
    private static final String[] ROMAN_NUMERAL =
            {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
    private static final int[] DECIMAL_VALUE = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};

    private static final int MAX_NUMBER = 3999;

    private Task4() {
    }

    /**
     * Создать функцию, которая принимает арабское число и преобразует его в римское.
     *
     * @param num арабское число
     * @return римское число в виде строки
     * @throws WrongInputException число не входит в диапазон [1; 3999]
     */
    public static String convertToRoman(int num) throws WrongInputException {
        if (num < 1 || num > MAX_NUMBER) {
            throw new WrongInputException();
        }

        Stack<String> romanStack = Arrays.stream(ROMAN_NUMERAL).collect(Collectors.toCollection(Stack::new));
        Stack<Integer> decimalStack = Arrays.stream(DECIMAL_VALUE).boxed().collect(Collectors.toCollection(Stack::new));

        StringBuilder roman = new StringBuilder();
        String romanNumber;
        int decimalNumber;
        int calcNum = num;

        while (calcNum > 0) {
            romanNumber = romanStack.pop();
            decimalNumber = decimalStack.pop();

            while (calcNum >= decimalNumber) {
                roman.append(romanNumber);
                calcNum -= decimalNumber;
            }
        }

        return roman.toString();
    }
}
