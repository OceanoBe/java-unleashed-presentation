package org.oceanobe.model;

import java.time.LocalDate;

public record Teacher(String name, int age, String field, LocalDate enrollmentDate, Double salary) implements Person {

    @Override
    public void talk() {
        System.out.println("Hello! My name is " + name + " and I am a teacher!");
    }

    @Override
    public void eat() {
        System.out.println("I eat salmon!");
    }

    public void teach() {
        System.out.println("I teach " + field);
    }
}
