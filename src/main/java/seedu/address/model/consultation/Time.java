package seedu.address.model.consultation;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * Represents a Consultation's time in classes.
 * Guarantees: Immutable
 */
public class Time implements Comparable<Time> {

    public static final String MESSAGE_CONSTRAINTS = "Accepted time format: hh:mm (e.g. 08:30)";

    public final LocalTime time;

    /**
     * Users should only use fromDateString to safely generate the time of consultation
     * @param time Input time.
     */
    private Time(LocalTime time) {
        assert time != null;
        this.time = time;
    }

    /**
     * Creates a new {@code Time} using a time string that will be parsed.
     *
     * @param input time string in hh:mm.
     * @return {@code Time} that corresponds to the input time.
     */
    public static final Time fromTimeString(String input) {
        requireNonNull(input);
        LocalTime time;
        try {
            time = LocalTime.parse(input);
        } catch (DateTimeParseException e) {
            checkArgument(false, MESSAGE_CONSTRAINTS);
            return null; // Never triggers as the above will throw an invalid argument exception
        }
        return new Time(time);
    }
    /**
     * Returns true if a given time is a valid time.
     *
     * @param test Input time.
     * @return Presence of valid time.
     */
    public static boolean isValidTime(String test) {
        try {
            LocalTime time = LocalTime.parse(test);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns string representation of the time.
     *
     * @return time in string.
     */
    @Override
    public String toString() {
        return time.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Time that = (Time) o;
        return time.equals(that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time);
    }

    @Override
    public int compareTo(Time t) {
        assert(this.time != null);
        return this.time.compareTo(t.time);
    }
}
