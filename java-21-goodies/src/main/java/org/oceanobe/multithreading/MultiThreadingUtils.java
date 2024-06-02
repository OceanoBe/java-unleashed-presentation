package org.oceanobe.multithreading;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;

public class MultiThreadingUtils {

    public static void createAndStartNewVirtualThread() throws InterruptedException {
        Thread.startVirtualThread(
                () -> System.out.println("Running in a virtual thread with id " + Thread.currentThread().threadId())
        ).join();
    }

    public static void createStructureTaskScope() throws InterruptedException, ExecutionException {
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            var future1 = scope.fork(() -> {
                // Task 1
                Thread.sleep(1000);
                return "Result from task 1";
            });

            var future2 = scope.fork(() -> {
                // Task 2
                Thread.sleep(2000);
                return "Result from task 2";
            });

            scope.join();           // Wait for all tasks to finish
            scope.throwIfFailed();  // Throw exception if any task failed

            System.out.println(future1.get());
            System.out.println(future2.get());
        }
    }
}
