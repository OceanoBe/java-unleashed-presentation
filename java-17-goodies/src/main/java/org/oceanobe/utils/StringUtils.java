package org.oceanobe.utils;

import lombok.experimental.UtilityClass;

import java.util.function.Function;

@UtilityClass
public class StringUtils {

    public static String indentText(String text, int numberOfSpaces) {
        return text.indent(numberOfSpaces);
    }

    public static String applyFunction(String text, Function<String,String> function) {
        return text.transform(function);
    }
}
