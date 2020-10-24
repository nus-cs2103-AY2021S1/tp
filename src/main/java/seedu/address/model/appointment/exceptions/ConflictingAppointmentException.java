package seedu.address.model.appointment.exceptions;

/**
 * Signals that the operation will result in conflicting Appointments (Appointments are considered conflicting
 * if they have overlapping appointment time).
 */
public class ConflictingAppointmentException extends RuntimeException {
    public ConflictingAppointmentException() {
        super("Operation would result in conflicting appointments");
    }
}
