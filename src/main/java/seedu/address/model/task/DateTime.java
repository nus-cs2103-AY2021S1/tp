package seedu.address.model.task;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.LocalDateTime;

import seedu.address.commons.util.DateUtil;
import seedu.address.model.lesson.Time;

/**
 * Represents a Task's date and time in PlaNus task list.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTime(String)}
 */
public class DateTime {

    protected final LocalDateTime value;

    /**
     * Constructs a {@code DateTime}.
     *
     * @param dateTime A valid date and time.
     */
    public DateTime(String dateTime) {
        if (dateTime.isEmpty() || dateTime.isBlank()) {
            value = DateUtil.DEFAULT_DATETIME;
        } else {
            checkArgument(isValidDateTime(dateTime), DateUtil.MESSAGE_CONSTRAINTS);
            value = LocalDateTime.parse(dateTime, DateUtil.DATETIME_FORMATTER);
        }
    }

    public DateTime(LocalDateTime value) {
        this.value = value;
    }

    public LocalDateTime getValue() {
        return value;
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
        return value.format(DateUtil.DATETIME_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateTime // instanceof handles nulls
                && (value.equals(((DateTime) other).value)));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
