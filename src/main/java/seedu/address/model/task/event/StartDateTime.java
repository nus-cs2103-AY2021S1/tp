package seedu.address.model.task.event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.commons.util.DateUtil;
import seedu.address.model.lesson.Time;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's date and time in PlaNus task list.
 */
public class StartDateTime {

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
            value = DateUtil.DEFAULT_DATETIME;
        } else {
            checkArgument(isValidStartDateTime(dateTime), DateUtil.MESSAGE_CONSTRAINTS);
            value = LocalDateTime.parse(dateTime, DateUtil.FORMATTER);
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
        checkArgument(isValidStartDateTime(date, time), DateUtil.MESSAGE_CONSTRAINTS);
        String datetime = date + " " + time;
        value = LocalDateTime.parse(datetime, DateUtil.FORMATTER);
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
        return DateUtil.isValidDateTime(datetime);
    }


    /**
     * Returns true if a given string is a valid dateTime number.
     *
     * @param date the string value of date to be put to test.
     * @param time the string value of time to be put to test.
     * @return true if the test string is valid and false otherwise
     */
    public static boolean isValidStartDateTime(String date, String time) {
        return DateUtil.isValidDate(date) && DateUtil.isValidTime(time);
    }

    /**
     * Returns true if a given string is a valid search phrase for date time.
     *
     * @param test the string value to be put to test.
     * @return true if the test string is valid and false otherwise
     */
    public static boolean isValidSearchPhrase(String test) {
        return isValidStartDateTime(test) || DateUtil.isValidDate(test) || Time.isValidTime(test);
    }

    @Override
    public String toString() {
        if (isNull) {
            return "";
        } else {
            return value.format(DateUtil.FORMATTER);
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
