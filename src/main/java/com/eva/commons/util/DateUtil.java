package com.eva.commons.util;

import static com.eva.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static final String MESSAGE_CONSTRAINTS = "Given date could not be parsed.\n"
            + "Date should be in the format: dd/MM/yyyy";
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

    /**
     * Returns a parsed LocalDate.
     * @param dateStr The str to be parsed.
     */
    public static LocalDate dateParsed(String dateStr) {
        checkArgument(isValidDate(dateStr), MESSAGE_CONSTRAINTS);
        return LocalDate.parse(dateStr, DATE_TIME_FORMATTER);
    }

    /**
     * Returns the string representation of a LocalDate using {@code DATE_TIME_FORMATTER}.
     * @param date the LocalDate to be converted.
     */
    public static String dateToString(LocalDate date) {
        requireNonNull(date);
        return date.format(DATE_TIME_FORMATTER);
    }
}
