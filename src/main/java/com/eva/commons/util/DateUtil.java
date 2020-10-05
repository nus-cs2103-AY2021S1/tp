package com.eva.commons.util;

import static java.util.Objects.requireNonNull;

import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static final String VALIDATION_REGEX = "^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    /**
     * Returns true if the given date matches the format required according to {@code VALIDATION_REGEX}.
     * @param test date to test. Cannot be empty.
     */
    public static boolean isValidDate(String test) {
        requireNonNull(test);
        return test.matches(VALIDATION_REGEX);
    }
}
