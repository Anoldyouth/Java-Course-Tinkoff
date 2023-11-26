package edu.hw7;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class MonteCarloMethod {
    private MonteCarloMethod() {}

    @SuppressWarnings("checkstyle:MagicNumber")
    public static double serial(int count) {
        int circleCount = 0;
        int totalCount = 0;

        for (int i = 0; i < count; i++) {
            double x = ThreadLocalRandom.current().nextDouble();
            double y = ThreadLocalRandom.current().nextDouble();

            if (x * (1 - x) + y * (1 - y) >= 0.25) {
                circleCount++;
            }

            totalCount++;
        }

        return 4 * ((double) circleCount / totalCount);
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    public static double parallel(int repeatCount, int threadCount) throws InterruptedException {
        AtomicInteger circleCount = new AtomicInteger(0);
        AtomicInteger totalCount = new AtomicInteger(0);
        CountDownLatch latch = new CountDownLatch(threadCount);

        int partSize = repeatCount / threadCount;
        int remainder = repeatCount % threadCount;

        for (int i = 0; i < threadCount; i++) {
            int count = partSize + (i < remainder ? 1 : 0);
            var thread = new Thread(new MonteCarloMethodThread(count, circleCount, totalCount, latch));
            thread.start();
        }

        latch.await();

        return 4 * ((double) circleCount.get() / totalCount.get());
    }

    @SuppressWarnings({
            "checkstyle:MagicNumber",
            "checkstyle:RegexpSinglelineJava",
            "checkstyle:MultipleStringLiterals"
    })
    public static void collectMetrics(String[] args) throws InterruptedException {
        int[] countArray = {10_000_000, 100_000_000, 1_000_000_000};
        double theoreticalPiSum;
        long start;
        long end;

        for (int count: countArray) {
            theoreticalPiSum = 0;

            start = System.nanoTime();
            for (int i = 0; i < 5; i++) {
                theoreticalPiSum += serial(count);
            }
            end = System.nanoTime();
            System.out.printf(
                    "Последовательное выполнение. Количество эл-тов: %d,"
                            + " время (миллисекунды): %.2f, погрешность (%%): %f\n",
                    count,
                    (double) ((end - start) / 1_000_000) / 5,
                    Math.abs((5 * Math.PI - theoreticalPiSum) / (5 * Math.PI)) * 100
            );

            for (int i = 2; i <= 15; i++) {
                theoreticalPiSum = 0;
                start = System.nanoTime();
                for (int k = 0; k < 5; k++) {
                    theoreticalPiSum += parallel(count, i);
                }
                end = System.nanoTime();
                System.out.printf(
                        "Параллельное выполнение. Количество потоков: %d, количество эл-тов: %d,"
                                + " время (миллисекунды): %.2f, погрешность (%%): %f\n",
                        i,
                        count,
                        (double) ((end - start) / 1_000_000) / 5,
                        Math.abs((5 * Math.PI - theoreticalPiSum) / (5 * Math.PI)) * 100
                );
            }

            System.out.println("\n");
        }
    }
}
