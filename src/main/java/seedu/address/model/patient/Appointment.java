package seedu.address.model.patient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Patient's appointment in Hospify.
 * is valid as declared in {@link #isValidAppointment(String)}
 */
public class Appointment {

    public static final String MESSAGE_CONSTRAINTS =
            "Appointment times should follow the format (dd/MM/yyyy HH:mm)";
    public static final String MISSING_TIMING =
            "Appointment timing is missing!";
    private final String description;
    private final LocalDateTime time;

    /**
     * Constructs a {@code Appointment}.
     */
    public Appointment() {
        this.description = "";
        time = LocalDateTime.MIN;
    }

    /**
     * Constructs a {@code Appointment}.
     *
     * @param description A valid description.
     * @param appointment A valid appointment time.
     */
    public Appointment(String description, LocalDateTime appointment) {
        this.description = description;
        time = appointment;
    }

    /**
     * @param timeString An appointment time.
     * @return An Appointment object with date specified by the given String.
     */
    public Appointment setTime(String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");
        return new Appointment(this.description, LocalDateTime.parse(timeString, formatter));
    }

    /**
     * Returns appointment description.
     */
    public String getAppointmentDescription() {
        return description;
    }

    /**
     * Returns appointment time in LocalDateTime format.
     */
    public LocalDateTime getAppointmentTime() {
        return time;
    }

    /**
     * Returns appointment time in a formatted string.
     */
    public String getAppointmentTimeString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");
        return time.format(formatter);
    }

    /**
     * Returns true if a given string is a valid Appointment.
     */
    public static boolean isValidAppointment(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");
        LocalDateTime localDateTime;
        try {
            localDateTime = LocalDateTime.parse(input, formatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return isPassed(localDateTime);
    }

    /**
     * Returns true if appointment time is ahead of the current local time.
     *
     * @param currentTime current local time.
     */
    public static boolean isPassed(LocalDateTime currentTime) {
        return currentTime.compareTo(java.time.LocalDateTime.now()) > 0;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");
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
