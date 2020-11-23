package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * Represents an appointment's time.
 * Guarantees: immutable; is valid as declared in {@link #isValidAppointmentTime(String)}
 */
public class AppointmentTime {
    // Solution below adapted from
    // https://github.com/cs2103-ay1819s2-w13-1/main/tree/master/src/main/java/seedu/address/model/appointment
    public static final String MESSAGE_CONSTRAINTS =
        "Appointment time should be in the 24 hour format HH:mm";

    public final LocalTime time;

    /**
     * Constructs a {@code AppointmentTime}.
     *
     * @param time a valid time.
     */
    public AppointmentTime(String time) {
        requireNonNull(time);
        checkArgument(isValidAppointmentTime(time), MESSAGE_CONSTRAINTS);
        this.time = LocalTime.parse(time);
    }

    /**
     * Returns true if a given string is a valid appointment time.
     */
    public static boolean isValidAppointmentTime(String test) {
        try {
            LocalTime time = LocalTime.parse(test);
            return true;

        } catch (DateTimeParseException e) {
            return false;
        }
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AppointmentTime // instanceof handles nulls
            && time.equals(((AppointmentTime) other).time)); // state check
    }

    @Override
    public int hashCode() {
        return time.hashCode();
    }


    @Override
    public String toString() {
        return time.toString();
    }
}
