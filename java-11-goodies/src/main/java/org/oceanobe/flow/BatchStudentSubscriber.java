package org.oceanobe.flow;

import org.oceanobe.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow;

public class BatchStudentSubscriber implements Flow.Subscriber<Student> {
    private Flow.Subscription subscription;
    private int batchSize;
    private List<Student> studentBatch = new ArrayList<>();

    public BatchStudentSubscriber(int batchSize) {
        this.batchSize = batchSize;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        System.out.println("Batch-subscriber: Subscribed!");
        this.subscription = subscription;
        subscription.request(batchSize); // Request the first batch
    }

    @Override
    public void onNext(Student student) {
        System.out.println("BatchSubscriber Received student: " + student);
        studentBatch.add(student);
        if (studentBatch.size() >= batchSize) {
            processBatch();
            studentBatch.clear();
            subscription.request(batchSize); // Request the next batch
        }
    }

    @Override
    public void onError(Throwable throwable) {
        System.err.println("Error occurred:");
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("BatchSubscriber received all students.");
        if (!studentBatch.isEmpty()) {
            processBatch(); // Process any remaining students
        }
    }

    private void processBatch() {
        System.out.println("Processing batch of " + studentBatch.size() + " students");
        for (Student student : studentBatch) {
            System.out.println("Processed: " + student);
        }

    }
}
