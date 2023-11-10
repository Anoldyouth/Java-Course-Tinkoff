package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task8 {
    private Task8() {}

    /**
     * Нечетной длины
     *
     * @param string входная строка
     * @return соответствие регулярному выражению
     */
    public static boolean first(String string) {
        Pattern pattern = Pattern.compile("^[01]([01]{2})*$");
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }

    /**
     * Начинается с 0 и имеет нечетную длину, или начинается с 1 и имеет четную длину
     *
     * @param string входная строка
     * @return соответствие регулярному выражению
     */
    public static boolean second(String string) {
        Pattern pattern = Pattern.compile("(^0([01]{2})*$)|(^1[01]([01]{2})*$)");
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }

    /**
     * Количество 0 кратно 3
     *
     * @param string входная строка
     * @return соответствие регулярному выражению
     */
    public static boolean third(String string) {
        Pattern pattern = Pattern.compile("^((1*0){3})+1*$");
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }

    /**
     * Любая строка, кроме 11 или 111
     *
     * @param string входная строка
     * @return соответствие регулярному выражению
     */
    public static boolean fourth(String string) {
        Pattern pattern = Pattern.compile("^(?!11$|111$)[01]*");
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }

    /**
     * Каждый нечетный символ равен 1
     *
     * @param string входная строка
     * @return соответствие регулярному выражению
     */
    public static boolean fifth(String string) {
        Pattern pattern = Pattern.compile("^1([01]1)*[01]?$");
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }

    /**
     * Содержит не менее двух 0 и не более одной 1
     *
     * @param string входная строка
     * @return соответствие регулярному выражению
     */
    public static boolean sixth(String string) {
        Pattern pattern = Pattern.compile("^(?=[01]*0[01]*0)(?![01]*1[01]*1)[01]+$");
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }

    /**
     * Нет последовательных 1
     *
     * @param string входная строка
     * @return соответствие регулярному выражению
     */
    public static boolean seventh(String string) {
        Pattern pattern = Pattern.compile("^(?![01]*11)[01]*$");
        Matcher matcher = pattern.matcher(string);

        return matcher.find();
    }
}
