package edu.hw1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Task1 {
    private final static Logger LOGGER = LogManager.getLogger();
    private final static int SECONDS = 60;

    private Task1() {
    }

//    public static void main(String[] args) {
//        LOGGER.info(minutesToSeconds("01:00")); // 60
//        LOGGER.info(minutesToSeconds("13:56")); // 836
//        LOGGER.info(minutesToSeconds("10:60")); // -1
//    }

    /**
     * Дана строка с длиной видео в формате mm:ss, например 12:44.<p>
     * Напишите функцию, которая возвращает общую длину видео в секундах.
     *
     * @param time строка в формате mm:ss
     * @return количество секунд
     */
    public static int minutesToSeconds(String time) {
        Pattern pattern = Pattern.compile("^([0-9]+):([0-5][0-9])$");
        Matcher matcher = pattern.matcher(time);

        if (!matcher.find()) {
            return -1;
        }

        int minutes = Integer.parseInt(matcher.group(1));
        int seconds = Integer.parseInt(matcher.group(2));

        return minutes * SECONDS + seconds;
    }
}
