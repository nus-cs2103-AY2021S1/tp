package seedu.address.model.attendance.exceptions;

/**
 * Signals that the operation will result in duplicate Classes (Classes are considered duplicates if they have the same
 * date).
 */
public class DuplicateClassException extends RuntimeException {
    public DuplicateClassException() {
        super("Operation would result in duplicate classes");
    }
}
