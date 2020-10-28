package seedu.address.model.lesson;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Lesson's time in PlaNus task list.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)}
 */
public class Time {
    public static final String MESSAGE_CONSTRAINTS =
            "Time should be in the format of HH:mm";
    public static final String RANGE_CONSTRAINTS = "Start time should be before end time";
    public static final String OVERLAP_CONSTRAINTS = "This lesson overlaps with an existing lesson";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    public static final LocalTime DEFAULT_TIME = LocalTime.parse("00:00", FORMATTER);
    public static final String VALIDATION_REGEX = "^(2[0-3]|[01][0-9]):([0-5][0-9])$";
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
        value = LocalTime.parse(time, FORMATTER);
        isDefault = false;
    }

    /**
     * Returns true if a given string is a valid time number.
     *
     * @param test the string value to be put to test.
     * @return true if the test string is valid and false otherwise
     */
    public static boolean isValidTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        if (isDefault) {
            assert value.equals(DEFAULT_TIME) : "default time using real time value.";
            return "";
        }
        return value.format(FORMATTER);
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
