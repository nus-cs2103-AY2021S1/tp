package seedu.address.model.appointment.exceptions;

/**
 * Signals that the operation will result in duplicate vEvents (vEvents are considered duplicates
 * if they have the same summary, start time, and end time).
 */
public class DuplicateVEventException extends RuntimeException {
    public DuplicateVEventException() {
        super("Operation would result in conflicting vEvents");
    }
}
