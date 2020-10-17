package seedu.address.model.patient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Appointment {

    public static final String MESSAGE_CONSTRAINTS =
            "Appointment times should follow the format (dd/MM/yyyy HH:mm)";
    public final LocalDateTime value;

    /**
     * Constructs an appointment with a given appointment time.
     *
     * @param appointment A valid appointment.
     */
    public Appointment(String appointment) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");
        value = LocalDateTime.parse(appointment, formatter) ;
    }

    /**
     * Returns true if a given string is a valid Appointment.
     */
    public static boolean isValidAppointment(String test) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");
        LocalDateTime localDateTime;
        try {
            localDateTime = LocalDateTime.parse(test, formatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return isPassed(localDateTime);
    }

    public static boolean isPassed(LocalDateTime currentTime) {
        return currentTime.compareTo(java.time.LocalDateTime.now()) > 0;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a");
        return value.format(formatter);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Appointment // instanceof handles nulls
                && value.equals(((Appointment) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
