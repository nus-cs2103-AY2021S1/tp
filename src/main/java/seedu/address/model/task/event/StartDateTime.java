package seedu.address.model.task.event;

import seedu.address.model.lesson.Date;
import seedu.address.model.lesson.Time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's date and time in PlaNus task list.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTime(String)}
 */
public class StartDateTime {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    public static final String MESSAGE_CONSTRAINTS =
            "DateTime should be in the format of dd-MM-yyyy HH:mm.";
    public static final String DAY_MESSAGE_CONSTRAINTS =
            "Day should be in the format of MON, TUE, ..., SUN or MONDAY, TUESDAY, ..., SUNDAY";
    public static final String SEARCH_CONSTRAINTS =
            "Search phrase for date should be in the format of dd-MM-yyyy or HH:mm or dd-MM-yyyy HH:mm.";
    public static final String VALIDATION_REGEX =
            "^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4} (2[0-3]|[01][0-9]):([0-5][0-9])$";
    public static final String DATE_VALIDATION_REGEX = "^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}$";
    public static final String TIME_VALIDATION_REGEX = "^(2[0-3]|[01][0-9]):([0-5][0-9])$";
    public static final LocalDateTime DEFAULT_DATETIME = LocalDateTime.parse("01-01-1000 00:00", FORMATTER);
    public final LocalDateTime value;
    public final boolean isNull;

    /**
     * Constructs a {@code DateTime}.
     *
     * @param dateTime A valid date and time.
     */
    public StartDateTime(String dateTime) {
        if (dateTime.isEmpty() || dateTime.isBlank() || dateTime == null) {
            this.isNull = true;
            value = DEFAULT_DATETIME;
        } else {
            checkArgument(isValidStartDateTime(dateTime), MESSAGE_CONSTRAINTS);
            value = LocalDateTime.parse(dateTime, FORMATTER);
            isNull = false;
        }
    }

    /**
     * Constructs a {@code DateTime}.
     *
     * @param date A valid date.
     * @param time A valid time.
     */
    public StartDateTime(String date, String time) {
        checkArgument(isValidStartDateTime(date, time), MESSAGE_CONSTRAINTS);
        String datetime = date + " " + time;
        value = LocalDateTime.parse(datetime, FORMATTER);
        isNull = false;
    }

    public boolean isNull() {
        return isNull;
    }

    /**
     * Returns true if a given string is a valid dateTime number.
     *
     * @param datetime the string value to be put to test.
     * @return true if the test string is valid and false otherwise
     */
    public static boolean isValidStartDateTime(String datetime) {
        return datetime.matches(VALIDATION_REGEX);
    }


    /**
     * Returns true if a given string is a valid dateTime number.
     *
     * @param date the string value of date to be put to test.
     * @param time the string value of time to be put to test.
     * @return true if the test string is valid and false otherwise
     */
    public static boolean isValidStartDateTime(String date, String time) {
        return date.matches(DATE_VALIDATION_REGEX) && time.matches(TIME_VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid search phrase for date time.
     *
     * @param test the string value to be put to test.
     * @return true if the test string is valid and false otherwise
     */
    public static boolean isValidSearchPhrase(String test) {
        return isValidStartDateTime(test) || Date.isValidDate(test) || Time.isValidTime(test);
    }

    @Override
    public String toString() {
        if (isNull) {
            return "";
        } else {
            return value.format(FORMATTER);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StartDateTime // instanceof handles nulls
                && (value.equals(((StartDateTime) other).value)
                    || isNull && ((StartDateTime) other).isNull())); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
