package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task6 {
    private Task6() {}

    /**
     * Напишите функцию, которая определяет, что заданная строка S является подпоследовательностью другой строки T.
     *
     * @param substring подстрока для проверки на вхождение
     * @param string строка, в которой идет поиск
     * @return подстрока или нет
     */
    public static boolean isSubstring(String substring, String string) {
        StringBuilder stringPattern = new StringBuilder("^.*");

        for (char ch: substring.toCharArray()) {
            stringPattern.append(Pattern.quote(String.valueOf(ch))).append(".*");
        }

        stringPattern.append(".*$");

        Pattern pattern = Pattern.compile(stringPattern.toString());
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }
}
