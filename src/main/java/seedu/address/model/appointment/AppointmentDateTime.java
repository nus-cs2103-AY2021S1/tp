package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class AppointmentDateTime {

    public static final String MESSAGE_CONSTRAINTS =
            "Times should be entered in the format: 2007-12-03T10:15";
    // Compared to other classes, this class uses the LocalDateTime class to check validity of the String
    // rather than a regex.

    public final LocalDateTime dateTime;

    /**
     * Constructs a {@code AppointmentDateTime}.
     *
     * @param dateTime a valid dateTime string to be parsed by LocalDateTime.
     */
    public AppointmentDateTime(String dateTime) {
        requireNonNull(dateTime);
        checkArgument(isValidDateTime(dateTime), MESSAGE_CONSTRAINTS);
        this.dateTime = LocalDateTime.parse(dateTime);
    }

    /**
     * Returns true if a given string is a valid AppointmentDateTime.
     */
    public static boolean isValidDateTime(String test) {
        try {
            LocalDateTime.parse(test);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public int compareTo(AppointmentDateTime other) {
        return dateTime.compareTo(other.dateTime);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AppointmentDateTime
                && dateTime.equals(((AppointmentDateTime) other).dateTime));
    }

    @Override
    public int hashCode() {
        return dateTime.hashCode();
    }

    @Override
    public String toString() {
        return dateTime.toString();
    }
}
