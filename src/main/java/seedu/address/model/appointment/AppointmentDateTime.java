package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AppointmentDateTime {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final String MESSAGE_CONSTRAINTS =
            "Invalid time. Please check if your year, month, day, hour and minute inputs are valid.\n "
                    + "\nEnsure that the time is entered in this format: yyyy-MM-dd HH:mm"
                    + "\nInput YYYY as 19xx to 2xxx."
                    + "\nInput MM as 01 to 12."
                    + "\nInput dd as 01 to 31."
                    + "\nInput HH as 00 to 24."
                    + "\nInput mm as 00 to 59.";
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
        this.dateTime = LocalDateTime.parse(dateTime, DATE_TIME_FORMATTER);
    }

    /**
     * Constructs a {@code AppointmentDateTime}.
     *
     * @param dateTime a valid dateTime string to be parsed by LocalDateTime.
     * @param duration a valid duration to add on
     */
    public AppointmentDateTime(String dateTime, int duration) {
        assert duration > 0 : "Invalid duration";
        assert duration <= Integer.MAX_VALUE : "Invalid duration";
        requireNonNull(dateTime);
        checkArgument(isValidDateTime(dateTime), MESSAGE_CONSTRAINTS);
        this.dateTime = LocalDateTime.parse(dateTime, DATE_TIME_FORMATTER).plusMinutes(duration);
    }

    public String getDateTimeStr() {
        return this.dateTime.format(DATE_TIME_FORMATTER);
    }

    public long computeDuration(AppointmentDateTime other) {
        return Duration.between(this.dateTime, other.dateTime).toMinutes();
    }

    /**
     * Returns true if a given string is a valid AppointmentDateTime.
     */
    public static boolean isValidDateTime(String test) {
        try {
            LocalDateTime.parse(test, DATE_TIME_FORMATTER);
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
        return dateTime.format(DATE_TIME_FORMATTER);
    }
}
