package seedu.address.model.util;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Time implements Comparable<Time> {
    public static final String TIME_REGEX = "(([0-1]\\d)|(2[0-3])):([0-5]\\d)";
    public static final String CONSTRAINTS = "%s should be in HH:mm format.";
    public static final String MESSAGE_CONSTRAINTS = String.format(CONSTRAINTS, "Time");

    public static final String VALIDATION_REGEX = TIME_REGEX;

    public static final DateTimeFormatter TIME_FORMATTER =
            DateTimeFormatter.ofPattern("HH:mm");

    public final LocalTime value;

    /**
     * Constructs a {@code time}.
     *
     * @param time A valid time.
     */
    public Time(String time) {
        requireNonNull(time);

        // Check for constraints
        checkArgument(isValidTime(time), MESSAGE_CONSTRAINTS);

        //Parse value
        this.value = LocalTime.parse(time, TIME_FORMATTER);
    }

    /**
     * Returns the LocalTime value object.
     *
     * @return the value of Time.
     */
    public LocalTime getLocalTime() {
        return this.value;
    }

    /**
     * Returns true if a given string is a valid Time.
     *
     * @param test string to test.
     * @return result of match.
     */
    public static boolean isValidTime(String test) {
        return isValidTimePattern(test);
    }

    /**
     * Returns true if a given string is a valid Time.
     *
     * @param test string to test.
     * @return result of match.
     */
    public static boolean isValidTimePattern(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public int compareTo(Time o) {
        return this.value.compareTo(o.value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Time) // instanceof handles nulls
                && ((this.value.equals(((Time) other).value)));
    }

    @Override
    public String toString() {
        return this.value.format(TIME_FORMATTER);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
