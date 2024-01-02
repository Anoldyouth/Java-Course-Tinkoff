package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task4 {
    private static final Logger LOGGER = LogManager.getLogger();

    private Task4() {
    }

    /**
     * Все мои строки перепутались и каждая пара символов поменялась местами.<p>
     * Напишите функцию, которая исправляет такие строки и возвращает правильный порядок.
     *
     * @param string строка с перепутанными символами
     * @return возвращает починенную строку
     */
    public static String fixString(String string) {
        StringBuilder newString = new StringBuilder();
        int length = string.length();

        for (int i = 0; i < length - 1; i += 2) {
            newString.append(string.charAt(i + 1));
            newString.append(string.charAt(i));
        }

        if (length % 2 == 1) {
            newString.append(string.charAt(length - 1));
        }

        return newString.toString();
    }
}
