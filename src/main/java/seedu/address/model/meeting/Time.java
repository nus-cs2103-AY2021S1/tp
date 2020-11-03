package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Time {
    public static final String MESSAGE_FORMAT_CONSTRAINTS = "Times should be in the format hh:mm";
    public static final String MESSAGE_TIME_CONSTRAINTS = "The time given should be a valid time";
    public static final String MESSAGE_CONSTRAINTS = "Times should be in the format hh:mm "
            + "the time given should be a valid time";
    public static final String VALIDATION_REGEX = "[0-9]{2}[:]{1}[0-9]{2}";
    public static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("kk:mm");
    public static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("h:mm a");

    // For DB storage. See Json AdaptedMeeting for use.
    public final String value;
    public final LocalTime time;

    /**
     * Constructs a {@code Time}.
     *
     * @param time A valid time.
     */
    public Time(String time) {
        requireNonNull(time);
        checkArgument(isValidFormat(time), MESSAGE_FORMAT_CONSTRAINTS);
        checkArgument(isValidTime(time), MESSAGE_TIME_CONSTRAINTS);
        this.value = time;
        this.time = LocalTime.parse(time, INPUT_FORMAT);
    }

    public LocalTime getLocalTimeFormat() {
        return this.time;
    }

    /**
     * Returns true if a given string is in a valid time format.
     */
    public static boolean isValidFormat(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid time.
     */
    public static boolean isValidTime(String test) {
        try {
            LocalTime.parse(test);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public static boolean isValidTimeInput(String test) {
        return isValidFormat(test) && isValidTime(test);
    }

    @Override
    public String toString() {
        return time.format(OUTPUT_FORMAT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Time // instanceof handles nulls
                && time.equals(((Time) other).time)); // state check
    }

    @Override
    public int hashCode() {
        return time.hashCode();
    }
}
