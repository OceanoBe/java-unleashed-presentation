package org.oceanobe.utils;

import java.util.List;

public class UnnamedClassExample {

    public static void main(String[] args) {
        List<String> names = List.of("Alice", "Bob", "Charlie");

        names.stream()
                .map(name -> new Object() {
                    String greeting() {
                        return "Hello, " + name + "!";
                    }
                })
                .forEach(unnamedClassInstance -> System.out.println(unnamedClassInstance.greeting()));
        // Outputs:
        // Hello, Alice!
        // Hello, Bob!
        // Hello, Charlie!
    }
}
