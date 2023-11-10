package edu.hw5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task5 {
    private Task5() {}

    /**
     * Напишите регулярное выражение для валидации российских номерных знаков.
     *
     * @param autoNumber номер автомобиля
     * @return валидность номера
     */
    public static boolean validateAutoNumber(String autoNumber) {
        // Правила:
        // 1. 12 буквенных символов
        // 2. регион из двух знаков
        // 3. при окончании лимита добавляется цифра - 1, 3, 7
        // 4. вид: первая цифра серии, регистрационный символ, оставшиеся цифры серии, регион
        Pattern pattern = Pattern.compile(
                "^[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]{2}[137]?\\d{2}$"
        );
        Matcher matcher = pattern.matcher(autoNumber);

        return matcher.find();
    }
}
