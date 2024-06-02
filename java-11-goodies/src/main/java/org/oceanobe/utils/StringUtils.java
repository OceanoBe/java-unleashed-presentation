package org.oceanobe.utils;

import lombok.experimental.UtilityClass;

import java.util.stream.Stream;

@UtilityClass
public class StringUtils {

    public static boolean isBlank(String s) {
        return s.isBlank();
    }

    public static Stream<String> lines(String s) {
        return s.lines();
    }

    public static String strip(String s) {
        return s.strip();
    }

    public static String stripLeading(String s) {
        return s.stripLeading();
    }

    public static String stripTrailing(String s) {
        return s.stripTrailing();
    }

    public static String repeat(String s, int numberOfOccurrences) {
        return s.repeat(numberOfOccurrences);
    }

}
