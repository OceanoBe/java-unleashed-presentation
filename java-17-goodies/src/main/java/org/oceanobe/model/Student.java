package org.oceanobe.model;

import java.time.LocalDate;
import java.util.Date;

public record Student(String name, int age, double grade, LocalDate enrollmentDate,
                      Date graduationDate) implements Person {

    @Override
    public void talk() {
        System.out.println("Hello! My name is " + name + " and I am a student!");
    }

    @Override
    public void eat() {
        System.out.println("I eat pizza!");
    }

    public void learn() {
        System.out.println("I learn for my exams! My overall grade is " + grade);
    }
}
