package seedu.address.model.patient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Appointment {

    public static final String MESSAGE_CONSTRAINTS =
            "Appointment times should follow the format (yyyy-MM-dd HH:mm)";
    private final LocalDateTime time;

    public Appointment() {
        time = LocalDateTime.MIN;
    }

    public Appointment(LocalDateTime appointment) {
        time = appointment;
    }

    /**
     * @param timeString
     * @return an Appointment object with date specified by the given String
     */
    public Appointment setTime(String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return new Appointment(LocalDateTime.parse(timeString, formatter));
    }

    /**
     * Returns true if a given string is a valid Appointment.
     */
    public static boolean isValidAppointment(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime localDateTime;
        try {
            localDateTime = LocalDateTime.parse(input, formatter);
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
        return time.format(formatter);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Appointment // instanceof handles nulls
                && time.equals(((Appointment) other).time)); // state check
    }

    @Override
    public int hashCode() {
        return time.hashCode();
    }

}
