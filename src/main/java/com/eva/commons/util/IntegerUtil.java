package com.eva.commons.util;

import static com.eva.commons.util.AppUtil.checkArgument;

public class IntegerUtil {
    /**
     * Returns true if the given date matches the format required according to {@code VALIDATION_REGEX}.
     * @param test date to test. Cannot be empty.
     */
    public static void requirePositive(int test) {
        checkArgument(test > 0);
    }

    /**
     * Returns true if the given date matches the format required according to {@code VALIDATION_REGEX}.
     * @param test date to test. Cannot be empty.
     */
    public static void requirePositive(int test, String errorMessage) {
        checkArgument(test > 0, errorMessage);
    }
}
