package edu.hw8;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

public class FixedThreadPoolTest {
    static class FibonacciTask implements Runnable {
        private final int endIndex;
        private final List<Integer> results;

        public FibonacciTask(int endIndex, List<Integer> results) {
            this.endIndex = endIndex;
            this.results = results;
        }

        @Override
        public void run() {
            results.add(fibonacci(endIndex));
        }

        private int fibonacci(int n) {
            if (n <= 1) {
                return n;
            } else {
                return fibonacci(n - 1) + fibonacci(n - 2);
            }
        }
    }

    @Test
    void fibonacci() throws InterruptedException {
        List<Integer> results = Collections.synchronizedList(new ArrayList<>());
        int[] input = {2, 5, 8, 11};
        int numThreads = 2;

        try (FixedThreadPool pool = new FixedThreadPool(numThreads)) {
            pool.start();

            for (int i: input) {
                FibonacciTask fibonacciTask = new FibonacciTask(i, results);
                pool.execute(fibonacciTask);
            }

            while (results.size() < input.length) {
                sleep(1000);
            }
        }

        assertThat(results.stream().sorted().toList()).isEqualTo(List.of(1, 5, 21, 89));
    }
}
