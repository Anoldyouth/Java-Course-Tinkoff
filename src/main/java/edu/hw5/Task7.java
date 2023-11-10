package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task7 {
    private Task7() {}

    /**
     * Содержит не менее 3 символов, причем третий символ равен 0
     *
     * @param string входная строка
     * @return соответствие регулярному выражению
     */
    public static boolean first(String string) {
        Pattern pattern = Pattern.compile("^[01]{2}0[01]*$");
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }

    /**
     * Начинается и заканчивается одним и тем же символом
     *
     * @param string входная строка
     * @return соответствие регулярному выражению
     */
    public static boolean second(String string) {
        Pattern pattern = Pattern.compile("(^0[01]*0$)|(^1[01]*1$)|(^[01]$)");
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }

    /**
     * Длина не менее 1 и не более 3
     *
     * @param string входная строка
     * @return соответствие регулярному выражению
     */
    public static boolean third(String string) {
        Pattern pattern = Pattern.compile("^[01]{1,3}$");
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }
}
