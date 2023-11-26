package edu.hw7;

import java.util.concurrent.atomic.AtomicInteger;

public class RaceCondition {
    private static final AtomicInteger COUNTER = new AtomicInteger(0);

    private RaceCondition() {}

    private static void increment() {
        COUNTER.incrementAndGet();
    }

    @SuppressWarnings("checkstyle:RegexpSinglelineJava")
    public static int count(int threadsCount, int repeats) {
        COUNTER.set(0);

        try {
            for (int i = 0; i < threadsCount; i++) {
                var incrementor = new Thread(() -> {
                    for (int k = 0; k < repeats; k++) {
                        increment();
                    }
                });
                incrementor.start();
                incrementor.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return COUNTER.get();
    }
}
