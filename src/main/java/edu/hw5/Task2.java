package edu.hw5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Task2 {
    private static final int DAY = 13;

    private Task2() {}

    /**
     * Напишите программу, которая ищет все пятницы, выпадающие на 13-е число в заданном году.
     *
     * @param year требуемый год
     * @return список дат
     */
    public static List<LocalDate> searchAllFridayThirteenthOfTheYear(int year) {
        return IntStream.rangeClosed(Month.JANUARY.getValue(), Month.DECEMBER.getValue())
                .mapToObj(month -> LocalDate.of(year, month, DAY))
                .filter(date -> date.getDayOfWeek().equals(DayOfWeek.FRIDAY))
                .toList();
    }

    /**
     * Используя TemporalAdjuster, напишите функцию, которая для заданной даты ищет следующую ближайшую пятницу 13.
     *
     * @param date дата, относительно которой ведется поиск
     * @return ближайшая пятница тринадцатое
     */
    public static LocalDate searchNextFridayThirteenth(LocalDate date) {
        return date.with(temporal -> {
            LocalDate date1 = LocalDate.from(temporal);

            return Stream.concat(
                    searchAllFridayThirteenthOfTheYear(temporal.get(ChronoField.YEAR)).stream(),
                    searchAllFridayThirteenthOfTheYear(temporal.get(ChronoField.YEAR) + 1).stream()
            ).filter(current -> current.isAfter(date1)).min(LocalDate::compareTo).get();
        });
    }
}


