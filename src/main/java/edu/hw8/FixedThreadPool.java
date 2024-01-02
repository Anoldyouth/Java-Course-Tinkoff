package edu.hw8;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FixedThreadPool implements ThreadPool {
    private final int size;
    private final List<Thread> threads;
    private final BlockingQueue<Runnable> tasks;

    public FixedThreadPool(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Размер пула должен быть положительным");
        }

        this.size = size;
        threads = new LinkedList<>();
        tasks = new LinkedBlockingQueue<>();
    }

    @Override
    public void start() {
        for (int i = 0; i < size; i++) {
            threads.add(new Thread(() -> {
                while (!(Thread.currentThread().isInterrupted())) {
                    try {
                        Runnable task = tasks.take();
                        task.run();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }));
            threads.get(i).start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        try {
            tasks.put(runnable);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void close() {
        for (Thread thread: threads) {
            thread.interrupt();
        }
    }
}
