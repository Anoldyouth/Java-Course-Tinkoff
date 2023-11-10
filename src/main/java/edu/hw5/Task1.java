package edu.hw5;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Task1 {
    private Task1() {}

    /**
     * Вас попросили сделать аналитику для компьютерного клуба: нужно посчитать,
     * сколько времени в среднем посетители проводят времени за один сеанс.
     * <p>
     * На вход функции даётся набор строк вида 2022-03-12, 20:20 - 2022-03-12, 23:50.
     * <p>
     * Например, для входных данных
     * <p>
     * <p>2022-03-12, 20:20 - 2022-03-12, 23:50</p>
     * <p>2022-04-01, 21:30 - 2022-04-02, 01:20</p>
     * Вывод должен быть 3ч 40м
     * <p>
     * Программа не должна учитывать часовые пояса,
     * дополнительные секунды и другие особые случаи - день может длиться ровно 24 часа.
     * <p>
     * Для решения задания может пригодиться класс Duration.
     *
     * @param sessions список сессий
     * @return среднее время сессий в виде "__ч __м"
     */
    public static String averageSessionTime(List<String> sessions) {
        List<Long> durations = new ArrayList<>();
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");

        for (String session: sessions) {
            String[] parts = session.split(" - ");
            if (parts.length != 2) {
                throw new IllegalArgumentException("Передан неверный формат сессии");
            }

            LocalDateTime from = LocalDateTime.from(formatter.parse(parts[0]));
            LocalDateTime to = LocalDateTime.from(formatter.parse(parts[1]));

            if (!from.isBefore(to)) {
                throw new IllegalArgumentException("Переданы некорректные данные");
            }

            if (!from.plusDays(1).isAfter(to)) {
                throw new IllegalArgumentException("Сессия не может длиться больше суток");
            }

            durations.add(Duration.between(from, to).toMillis()); // продолжительность сессии в миллисекундах
        }

        long averageDuration = (long) durations.stream()
                .mapToDouble(Long::doubleValue)
                .average()
                .orElse(0.0);

        return DateTimeFormatter
                .ofPattern("Hч mmм")
                .withZone(ZoneOffset.UTC)
                .format(Instant.ofEpochMilli(averageDuration));
    }
}
