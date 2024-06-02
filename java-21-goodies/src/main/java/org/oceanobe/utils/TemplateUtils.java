package org.oceanobe.utils;

public class TemplateUtils {

    public static String greetingTemplate(String placeholderValue) {

        return STR."Hello \{placeholderValue}!";
    }

    public static String sumTemplate(Integer factor1, Integer factor2) {

        return STR."Sum of \{factor1} and \{factor2} is \{factor1 + factor2}";
    }

    public static String measureTemplate(Long sum, Long time) {
        return STR."sum = \{sum}; time = \{time} ms";
    }
}
