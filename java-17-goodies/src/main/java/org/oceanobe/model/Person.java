package org.oceanobe.model;

public sealed interface Person extends Animal permits Student, Teacher {
    void talk();

    default void sleep(){
        System.out.println("I sleep 8 hours per day!");
    }
}
