package org.oceanobe.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.Executors.newVirtualThreadPerTaskExecutor;

public class VirtualThreadsExample {

    public static void main(String[] args) throws InterruptedException {
        int numThreads = 10000; // Large number of threads
        ExecutorService executor = newVirtualThreadPerTaskExecutor();

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < numThreads; i++) {
            executor.submit(() -> {
                // Simulate a task with sleep
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        long endTime = System.currentTimeMillis();
        System.out.println("Virtual threads took: " + (endTime - startTime) + " ms");
    }
}
