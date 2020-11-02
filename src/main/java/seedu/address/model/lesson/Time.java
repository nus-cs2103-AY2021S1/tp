package seedu.address.model.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;

import seedu.address.commons.util.DateUtil;

/**
 * Represents a Lesson's time in PlaNus task list.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)}
 */
public class Time {
    public static final String MESSAGE_CONSTRAINTS = DateUtil.TIME_CONSTRAINTS;
    public static final String RANGE_CONSTRAINTS = "Start time should be before end time";
    public static final LocalTime DEFAULT_TIME = LocalTime.parse("00:00", DateUtil.TIME_FORMATTER);
    public final LocalTime value;
    public final boolean isDefault;

    /**
     * Constructs a {@code Time}.
     *
     * @param time A valid time.
     */
    public Time(String time) {
        requireNonNull(time);
        checkArgument(isValidTime(time), MESSAGE_CONSTRAINTS);
        value = LocalTime.parse(time, DateUtil.TIME_FORMATTER);
        isDefault = false;
    }

    /**
     * Constructs a default {@code Time}.
     *
     * Caveat: Only called by defaultTime method.
     */
    private Time() {
        value = DEFAULT_TIME; // a dummy value
        isDefault = true;
    }

    /**
     * Returns true if a given string is a valid time number.
     *
     * @param test the string value to be put to test.
     * @return true if the test string is valid and false otherwise
     */
    public static boolean isValidTime(String test) {
        return DateUtil.isValidTime(test);
    }

    @Override
    public String toString() {
        if (isDefault) {
            assert value.equals(DEFAULT_TIME) : "default time using real time value.";
            return "";
        }
        return value.format(DateUtil.TIME_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Time // instanceof handles nulls
                && (value.equals(((Time) other).value)
                || isDefault && ((Time) other).isDefault)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
