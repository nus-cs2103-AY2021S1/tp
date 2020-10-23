package seedu.address.model.task.deadline;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;

import seedu.address.commons.util.DateUtil;
import seedu.address.model.lesson.Time;

/**
 * Represents a Task's date and time in PlaNus task list.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTime(String)}
 */
public class DeadlineDateTime {

    public static final LocalDateTime DEFAULT_DATETIME = LocalDateTime.parse("01-01-1000 00:00", DateUtil.DATETIME_FORMATTER);
    public final LocalDateTime value;
    public final boolean isNull;

    /**
     * Constructs a {@code DateTime}.
     *
     * @param dateTime A valid date and time.
     */
    public DeadlineDateTime(String dateTime) {
        if (dateTime.isEmpty() || dateTime.isBlank() || dateTime == null || dateTime.equals(DEFAULT_DATETIME)) {
            this.isNull = true;
            value = DEFAULT_DATETIME;
        } else {
            checkArgument(isValidDateTime(dateTime), DateUtil.MESSAGE_CONSTRAINTS);
            value = LocalDateTime.parse(dateTime, DateUtil.DATETIME_FORMATTER);
            isNull = false;
        }
    }

    public static DeadlineDateTime createNullDeadlineDateTime() {
        return new DeadlineDateTime("");
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
    public static boolean isValidDateTime(String test) {
        return DateUtil.isValidDateTime(test);
    }

    /**
     * Returns true if a given string is a valid search phrase for date time.
     *
     * @param test the string value to be put to test.
     * @return true if the test string is valid and false otherwise
     */
    public static boolean isValidSearchPhrase(String test) {
        return isValidDateTime(test) || DateUtil.isValidDate(test) || Time.isValidTime(test);
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
                || (other instanceof DeadlineDateTime // instanceof handles nulls
                && (value.equals(((DeadlineDateTime) other).value)
                    || isNull && ((DeadlineDateTime) other).isNull())); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
