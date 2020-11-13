package seedu.address.model.day.exceptions;

/**
 * Signals that the operation will result in duplicate Days (Days are considered duplicates if they have the same
 * identity).
 */
public class DuplicateDayException extends RuntimeException {
    public DuplicateDayException() {
        super("Operation would result in duplicate days");
    }
}
