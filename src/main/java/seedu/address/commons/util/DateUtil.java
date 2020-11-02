package seedu.address.commons.util;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class DateUtil {

    public static final String TIME_CONSTRAINTS =
            "Time should be valid and in the format of HH:mm.";
    public static final String DATE_TIME_CONSTRAINTS =
            "DateTime should be in the format of dd-MM-yyyy HH:mm.";
    public static final String DATE_FORMAT = "dd-MM-yyyy";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-uuuu")
            .withResolverStyle(ResolverStyle.STRICT);
    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm")
            .withResolverStyle(ResolverStyle.STRICT);
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm")
            .withResolverStyle(ResolverStyle.STRICT);
    public static final String SEARCH_TIME_CONSTRAINTS =
            "Search phrase for time should be in the format of HH:mm.";
    public static final String SEARCH_DATE_CONSTRAINTS =
            String.format("Search phrase for date should be in the format of %1$s.", DATE_FORMAT);
    public static final String DAY_MESSAGE_CONSTRAINTS =
            "Day should be in the format of MON, TUE, ..., SUN or MONDAY, TUESDAY, ..., SUNDAY";
    public static final LocalDateTime DEFAULT_DATETIME = LocalDateTime.parse("01-01-1000 00:00",
            DATETIME_FORMATTER.withResolverStyle(ResolverStyle.SMART)); // smartly resolve the default datetime
    public static final String RANGE_CONSTRAINTS = "Start date should be before end date";

    /**
     * Returns true if a given string is a valid date number.
     *
     * @param test the string value to be put to test.
     * @return true if the test string is valid and false otherwise
     */
    public static boolean isValidDate(String test) {
        try {
            LocalDate.parse(test, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if a given string is a valid date number.
     *
     * @param test the string value to be put to test.
     * @return true if the test string is valid and false otherwise
     */
    public static boolean isValidDateTime(String test) {
        try {
            LocalDateTime.parse(test, DATETIME_FORMATTER);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if a given string is a valid date number.
     *
     * @param test the string value to be put to test.
     * @return true if the test string is valid and false otherwise
     */
    public static boolean isValidTime(String test) {
        try {
            LocalTime.parse(test, TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * @param input the string value for a date
     * @return a LocalDate object representing the specific date
     */
    public static LocalDate toLocalDate(String input) {
        checkArgument(isValidDate(input), DATE_TIME_CONSTRAINTS);
        return LocalDate.parse(input, DateUtil.DATE_FORMATTER);
    }
}
