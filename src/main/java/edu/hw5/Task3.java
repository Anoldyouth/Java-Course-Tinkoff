package edu.hw5;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task3 {
    private Task3() {}

    /**
     * Напишите метод parseDate(String string), который распознает перечисленные выше форматы.
     *
     * @param string строка, которая может содержать дату
     * @return дата или пустой объект
     */
    public static Optional<LocalDate> parseDate(String string) {
        return parseDateWithHyphen(string);
    }

    private static Optional<LocalDate> parseDateWithHyphen(String string) {
        try {
            return Optional.of(LocalDate.from(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(string)));
        } catch (DateTimeParseException ignored) {
        }

        try {
            return Optional.of(LocalDate.from(DateTimeFormatter.ofPattern("yyyy-M-d").parse(string)));
        } catch (DateTimeParseException ignored) {
        }

        return parseDateWithSlashes(string);
    }

    private static Optional<LocalDate> parseDateWithSlashes(String string) {
        try {
            return Optional.of(LocalDate.from(DateTimeFormatter.ofPattern("d/M/yyyy").parse(string)));
        } catch (DateTimeParseException ignored) {
        }

        try {
            return Optional.of(LocalDate.from(DateTimeFormatter.ofPattern("d/M/yy").parse(string)));
        } catch (DateTimeParseException ignored) {
        }

        return parseDateRelative(string);
    }

    private static Optional<LocalDate> parseDateRelative(String string) {
        return switch (string) {
            case "tomorrow" -> Optional.of(LocalDate.now().plusDays(1));
            case "today" -> Optional.of(LocalDate.now());
            case "yesterday" -> Optional.of(LocalDate.now().minusDays(1));
            default -> parseDateDaysAgo(string);
        };
    }

    private static Optional<LocalDate> parseDateDaysAgo(String string) {
        if (string.equals("1 day ago")) {
            return Optional.of(LocalDate.now().minusDays(1));
        }

        Pattern pattern = Pattern.compile("^(\\d+) days ago$");
        Matcher matcher = pattern.matcher(string);

        if (matcher.find()) {
            return Optional.of(LocalDate.now().minusDays(Integer.parseInt(matcher.group(1))));
        }

        return Optional.empty();
    }
}
