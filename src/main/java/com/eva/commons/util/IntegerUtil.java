package com.eva.commons.util;

import static com.eva.commons.util.AppUtil.checkArgument;

public class IntegerUtil {
    /**
     * Returns true if the integer given is positive.
     *
     * @param test int to test. Cannot be empty.
     */
    public static void requirePositive(int test) {
        checkArgument(test >= 0);
    }

    /**
     * Returns true if the integer given is positive.
     *
     * @param test int to test. Cannot be empty.
     */
    public static void requirePositive(int test, String errorMessage) {
        checkArgument(test >= 0, errorMessage);
    }
}
