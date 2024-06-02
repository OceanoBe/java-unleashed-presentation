package org.oceanobe.flow;

import org.oceanobe.model.Student;

import java.util.List;
import java.util.concurrent.SubmissionPublisher;

public class StudentPublisher extends SubmissionPublisher<Student> {

    /**
     * The publisher produces data and calls onNext on the subscriber with each item
     *
     * @param students
     */
    public void publishStudents(List<Student> students) {
        System.out.println("Publishing students...");
        students.forEach(student -> {
            System.out.println("Publishing: " + student);
            this.submit(student);
        });
        this.close();
    }
}
