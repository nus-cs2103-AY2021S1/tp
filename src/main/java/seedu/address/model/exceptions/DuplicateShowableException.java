package seedu.address.model.exceptions;

/**
 * Signals that the operation will result in duplicate {@code Showable} objects.
 * Objects are considered duplicates if they have the same id.
 */
public class DuplicateShowableException extends RuntimeException {
    public DuplicateShowableException() {
        super("Operation would result in duplicate items");
    }
}
