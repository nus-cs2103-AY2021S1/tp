package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class AppointmentDateTime {

    public static final String MESSAGE_CONSTRAINTS =
            "Times should be entered in the format: 2007-12-03T10:15";

    public static final String VALIDATION_REGEX = "\\d{4}-[01]\\d-[0-3]\\dT[0-2]\\d:[0-5]\\d";
    
    public final LocalDateTime dateTime;

    public AppointmentDateTime(String dateTime) {
        requireNonNull(dateTime);
        checkArgument(isValidDateTime(dateTime), MESSAGE_CONSTRAINTS);
        this.dateTime = LocalDateTime.parse(dateTime);
    }

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
