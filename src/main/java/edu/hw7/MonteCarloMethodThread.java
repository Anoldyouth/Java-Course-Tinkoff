package edu.hw7;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class MonteCarloMethodThread implements Runnable {
    private final int count;
    private final AtomicInteger circleCount;
    private final AtomicInteger totalCount;
    private final CountDownLatch latch;

    public MonteCarloMethodThread(
            int count,
            AtomicInteger circleCount,
            AtomicInteger totalCount,
            CountDownLatch latch
    ) {
        this.count = count;
        this.circleCount = circleCount;
        this.totalCount = totalCount;
        this.latch = latch;
    }

    @Override
    @SuppressWarnings("checkstyle:MagicNumber")
    public void run() {
        int localCircleCount = 0;
        int localTotalCount = 0;

        for (int i = 0; i < count; i++) {
            double x = ThreadLocalRandom.current().nextDouble();
            double y = ThreadLocalRandom.current().nextDouble();

            if (x * (1 - x) + y * (1 - y) >= 0.25) {
                localCircleCount++;
            }

            localTotalCount++;
        }

        totalCount.addAndGet(localTotalCount);
        circleCount.addAndGet(localCircleCount);
        latch.countDown();
    }
}
