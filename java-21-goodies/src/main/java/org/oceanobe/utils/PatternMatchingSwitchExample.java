package org.oceanobe.utils;

record Point(int x, int y) {}

public class PatternMatchingSwitchExample {
    public static void main(String[] args) {
        Object obj = "Hello, world!";
        switch (obj) {
            case String s -> System.out.println("It's a string: " + s);
            case Integer i -> System.out.println("It's an integer: " + i);
            default -> System.out.println("Unknown type");
        }


        Object dot = new Point(1, 2);
        if (dot instanceof Point(int x, int y)) {
            System.out.println("Point coordinates: (" + x + ", " + y + ")");
        }
    }
}
