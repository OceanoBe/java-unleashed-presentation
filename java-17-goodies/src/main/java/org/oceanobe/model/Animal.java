package org.oceanobe.model;

public sealed interface Animal permits Dog, Person {
    void eat();

    void sleep();
}
