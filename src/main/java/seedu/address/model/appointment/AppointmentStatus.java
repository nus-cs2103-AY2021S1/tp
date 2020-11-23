package seedu.address.model.appointment;

/**
 * Represents the status of an Appointment.
 */
public enum AppointmentStatus {
    // Solution below adapted from
    // https://github.com/cs2103-ay1819s2-w13-1/main/tree/master/src/main/java/seedu/address/model/appointment
    ACTIVE("The appointment is active."),
    COMPLETED("The appointment was completed."),
    MISSED("The appointment was missed.");

    public static final String MESSAGE_CONSTRAINTS = "Appointment status can only be the following:\n"
        + ACTIVE.name() + ": " + ACTIVE.message + " \n"
        + COMPLETED.name() + ": " + COMPLETED.message + " \n"
        + MISSED.name() + ": " + MISSED.message;
    public static final String MESSAGE_CONSTRAINT_PAST = "Only appointments in the past can be marked as "
            + COMPLETED + " or " + MISSED;

    /**
     * The message of the appointment status.
     */
    private final String message;

    AppointmentStatus(String message) {
        this.message = message;
    }

    /**
     * Returns true if a given string is a valid appointment status.
     */
    public static boolean isValidAppointmentStatus(String test) {
        try {
            AppointmentStatus appointmentStatus = AppointmentStatus.valueOf(test);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}
