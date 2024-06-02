package org.oceanobe.model;

import java.time.LocalDate;

public record Dog(String name, int age, LocalDate dateOfBirth) implements Animal {

    @Override
    public void eat() {
        System.out.println("I eat treats!");
    }

    @Override
    public void sleep() {
        System.out.println("I sleep 10 hours per day!");
    }
}
