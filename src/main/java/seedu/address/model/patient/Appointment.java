package seedu.address.model.patient;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Patient's appointment in Hospify.
 * is valid as declared in {@link #isValidDateTime(String)}
 */
public class Appointment {

    public static final String MESSAGE_CONSTRAINTS =
            "Appointment dates should be valid and follow the format (dd/MM/yyyy HH:mm)";
    public static final String MESSAGE_CONSTRAINTS_INVALID_DESCRIPTION =
            "Description length should not exceed 300 characters.";
    public static final String MISSING_TIMING =
            "Appointment timing is missing!";
    public static final String TIME_RANGE_CONSTRAINTS = "Time entered cannot be earlier than system time!";
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
        requireNonNull(description);
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
     * @param description An appointment description.
     * @return An Appointment object with description specified by the given String.
     */
    public Appointment setDescription(String description) {
        return new Appointment(description, this.time);
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
     *
     * @param localDateTime
     * @return date and time formatted to stipulated format.
     */
    public static String getAppointmentTimeString(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");
        return localDateTime.format(formatter);
    }

    /**
     * Returns true if a given string is a valid Appointment.
     */
    public static boolean isValidDateTime(String input) {
        DateFormat dateFormat = new SimpleDateFormat("d/M/yyyy HH:mm");
        dateFormat.setLenient(false);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");
        LocalDateTime localDateTime;
        try {
            dateFormat.parse(input);
            localDateTime = LocalDateTime.parse(input, formatter);
        } catch (DateTimeParseException | ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if description length is less than or equal to 300.
     */
    public static boolean isValidDescription(String description) {
        return description.length() <= 10;
    }

    /**
     * Returns true if appointment time is past the current local time.
     *
     * @param givenTime current local time.
     */
    public static boolean isPassed(LocalDateTime givenTime) {
        return givenTime.compareTo(java.time.LocalDateTime.now()) < 0;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");
        return time.format(formatter) + " " + PREFIX_DESCRIPTION + description;
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
