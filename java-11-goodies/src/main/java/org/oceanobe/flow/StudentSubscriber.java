package org.oceanobe.flow;

import org.oceanobe.model.Student;

import java.util.concurrent.Flow;

public class StudentSubscriber implements Flow.Subscriber<Student> {
    private Flow.Subscription subscription;

    /**
     * When a subscriber subscribes to a publisher,
     * the publisher calls onSubscribe on the subscriber with a subscription object. The subscriber stores
     * the subscription object and uses it to control the data flow.
     *
     * @param subscription a new subscription
     */
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        System.out.println("Student subscriber: Subscribed!");
        this.subscription = subscription;
        subscription.request(1); // This means the subscriber is ready to process 1 student
    }

    /**
     * The publisher produces data and calls onNext on the subscriber with each item.
     *
     * @param student the item
     */
    @Override
    public void onNext(Student student) {

        System.out.println("Student subscriber received: " + student);
        subscription.request(1); // Request the next item
    }

    /**
     * If an error occurs, the publisher calls onError on the subscriber.
     *
     * @param throwable the exception
     */
    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    /**
     * The publisher calls onComplete on the subscriber when all data is sent.
     */
    @Override
    public void onComplete() {
        System.out.println("Student subscriber received all students!");
    }
}
