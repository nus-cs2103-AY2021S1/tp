package seedu.address.model.appointment;

/**
 * Represents an appointment's ID.
 * Guarantees: immutable
 */
public class AppointmentDuration {
    public static final String MESSAGE_CONSTRAINTS =
            "Appointment duration must a positive integer representing the number of minutes";
    public static final int DEFAULT_DURATION = 120;
    private final int duration;

    public AppointmentDuration() {
        this.duration = DEFAULT_DURATION;
    }

    /**
     * @return number of minutes equivalent
     */
    public int getNumMinutes() {
        return duration;
    }
}
