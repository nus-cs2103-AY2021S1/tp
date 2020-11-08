package com.eva.commons.util;

import static com.eva.commons.util.AppUtil.checkArgument;
import static com.eva.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.eva.logic.parser.exceptions.ParseException;

public class DateUtil {
    public static final String MESSAGE_CONSTRAINTS = "Given date: %s could not be parsed.\n"
            + "Date should be in the format: dd/MM/yyyy\nDate year cannot be less than 1000";
    public static final String VALIDATION_REGEX = "^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/([12][0-9]{3})$";
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
        checkArgument(isValidDate(dateStr), String.format(MESSAGE_CONSTRAINTS, dateStr));
        return LocalDate.parse(dateStr, DATE_TIME_FORMATTER);
    }

    /**
     * Returns a parsed LocalDate.
     * @param dateStr The str to be parsed.
     * @throws ParseException if {@code dateStr} is invalid.
     */
    public static LocalDate dateParsed(String dateStr, String errorMessage) throws ParseException {
        try {
            checkArgument(isValidDate(dateStr), MESSAGE_CONSTRAINTS);
            return LocalDate.parse(dateStr, DATE_TIME_FORMATTER);
        } catch (IllegalArgumentException e) {
            throw new ParseException(errorMessage);
        }
    }

    /**
     * Returns a list of parsed LocalDates.
     * @param dateStrings The list of strings to be parsed.
     */
    public static List<LocalDate> datesParsed(List<String> dateStrings) {
        dateStrings.forEach(str -> checkArgument(isValidDate(str), MESSAGE_CONSTRAINTS));
        return dateStrings.stream()
                .map(str -> LocalDate.parse(str, DATE_TIME_FORMATTER))
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Returns the string representation of a LocalDate using {@code DATE_TIME_FORMATTER}.
     * @param date the LocalDate to be converted.
     */
    public static String dateToString(LocalDate date) {
        requireNonNull(date);
        return date.format(DATE_TIME_FORMATTER);
    }
    /**
     * Returns the number of days elapsed between two dates.
     * @param start cannot be empty, inclusive.
     * @param end cannot be empty, inclusive.
     */
    public static int getDaysBetween(LocalDate start, LocalDate end) {
        requireAllNonNull(start, end);
        List<LocalDate> datesBetween = getDatesBetween(start, end);
        return datesBetween.size();
    }

    /**
     * Returns a list of LocalDates between the two given {@code dates}.
     * @param start cannot be empty
     * @param end cannot be empty.
     */
    public static List<LocalDate> getDatesBetween(LocalDate start, LocalDate end) {
        requireAllNonNull(start, end);
        List<LocalDate> out = start.datesUntil(end).collect(Collectors.toList());
        out.add(end);
        return out;
    }
}
