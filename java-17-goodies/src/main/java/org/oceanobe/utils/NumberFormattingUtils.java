package org.oceanobe.utils;

import lombok.experimental.UtilityClass;

import java.text.NumberFormat;
import java.util.Locale;

@UtilityClass
public class NumberFormattingUtils {

    public static String compactNumber(int number) {
        NumberFormat formatter = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);
        return formatter.format(number);
    }
}
