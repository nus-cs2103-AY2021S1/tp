package seedu.address.model.task.event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import seedu.address.commons.util.DateUtil;
import seedu.address.model.lesson.Time;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's date and time in PlaNus task list.
 * Guarantees: immutable; is valid as declared in {@link #isValidEndDateTime(String)}
 */
public class EndDateTime {

    public final LocalDateTime value;
    public final boolean isNull;

    /**
     * Constructs a {@code DateTime}.
     *
     * @param dateTime A valid date and time.
     */
    public EndDateTime(String dateTime) {
        if (dateTime.isEmpty() || dateTime.isBlank() || dateTime == null) {
            this.isNull = true;
            value = DateUtil.DEFAULT_DATETIME;
        } else {
            checkArgument(isValidEndDateTime(dateTime), DateUtil.MESSAGE_CONSTRAINTS);
            value = LocalDateTime.parse(dateTime, DateUtil.DATE_FORMATTER);
            isNull = false;
        }
    }

    /**
     * Constructs a {@code DateTime}.
     *
     * @param date A valid date.
     * @param time A valid time.
     */
    public EndDateTime(String date, String time) {
        checkArgument(isValidEndDateTime(date, time), DateUtil.MESSAGE_CONSTRAINTS);
        String datetime = date + " " + time;
        value = LocalDateTime.parse(datetime, DateUtil.DATETIME_FORMATTER);
        isNull = false;
    }

    public boolean isNull() {
        return isNull;
    }

    /**
     * Returns true if a given string is a valid dateTime number.
     *
     * @param test the string value to be put to test.
     * @return true if the test string is valid and false otherwise
     */
    public static boolean isValidEndDateTime(String test) {
        return DateUtil.isValidDateTime(test);
    }

    public static boolean isValidEndDateTime(String date, String time) {
        return DateUtil.isValidDate(date) && DateUtil.isValidTime(time);
    }

    /**
     * Returns true if a given string is a valid search phrase for date time.
     *
     * @param test the string value to be put to test.
     * @return true if the test string is valid and false otherwise
     */
    public static boolean isValidSearchPhrase(String test) {
        return isValidEndDateTime(test) || DateUtil.isValidDate(test) || Time.isValidTime(test);
    }


    @Override
    public String toString() {
        if (isNull) {
            return "";
        } else {
            return value.format(DateUtil.DATETIME_FORMATTER);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EndDateTime // instanceof handles nulls
                && (value.equals(((EndDateTime) other).value)
                    || isNull && ((EndDateTime) other).isNull())); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
