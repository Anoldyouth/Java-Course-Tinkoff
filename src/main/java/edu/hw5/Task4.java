package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task4 {
    private Task4() {}

    /**
     * Напишите регулярное выражение, которое возвращает true тогда и только тогда,
     * когда пароль содержит один из требуемых символов.
     *
     * @param password пароль для валидации
     * @return пароль содержит спец символы или нет
     */
    public static boolean validatePassword(String password) {
        Pattern pattern = Pattern.compile("[~!@#$%^&*|]");
        Matcher matcher = pattern.matcher(password);

        return matcher.find();
    }
}
