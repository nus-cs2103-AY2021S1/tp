package seedu.address.model.location.exceptions;

/**
 * Signals that the operation will result in duplicate Items (Items are considered duplicates if they have the same
 * identity).
 */
public class DuplicateLocationException extends RuntimeException {
    public DuplicateLocationException() {
        super("Operation would result in duplicate item");
    }
}
