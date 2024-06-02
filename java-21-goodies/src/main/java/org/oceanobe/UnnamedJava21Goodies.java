import org.oceanobe.multithreading.Task;
import org.oceanobe.utils.TemplateUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.oceanobe.multithreading.MultiThreadingUtils.createAndStartNewVirtualThread;
import static org.oceanobe.multithreading.MultiThreadingUtils.createStructureTaskScope;

void main() throws InterruptedException, ExecutionException {
    createStructureTaskScope();
    stringTemplateExamples();

    createAndStartNewVirtualThread();
    phisicalThreadExample();
    //virtualThreadsExample();
}

private static void phisicalThreadExample() throws InterruptedException, ExecutionException {
    try (ExecutorService executor = Executors.newFixedThreadPool(100)) {
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < 1_000; i++) {
            tasks.add(new Task(i));
        }

        long time = System.currentTimeMillis();

        List<Future<Integer>> futures = executor.invokeAll(tasks);

        long sum = 0;
        for (Future<Integer> future : futures) {
            sum += future.get();
        }

        time = System.currentTimeMillis() - time;

        System.out.println(TemplateUtils.measureTemplate(sum, time));
    }
}

private static void virtualThreadsExample() throws InterruptedException, ExecutionException {
    try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < 1_000; i++) {
            tasks.add(new Task(i));
        }

        long time = System.currentTimeMillis();

        List<Future<Integer>> futures = executor.invokeAll(tasks);

        long sum = 0;
        for (Future<Integer> future : futures) {
            sum += future.get();
        }

        time = System.currentTimeMillis() - time;

        System.out.println(TemplateUtils.measureTemplate(sum, time));
    }
}

private static void stringTemplateExamples() {
    System.out.println(TemplateUtils.greetingTemplate("John"));
    System.out.println(TemplateUtils.sumTemplate(5, 10));
}
