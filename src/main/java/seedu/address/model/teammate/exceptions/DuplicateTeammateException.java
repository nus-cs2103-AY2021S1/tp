package seedu.address.model.teammate.exceptions;

/**
 * Signals that the operation will result in duplicate Projects (Projects are considered duplicates if they have the
 * same identity).
 */
public class DuplicateTeammateException extends RuntimeException {
    public DuplicateTeammateException() {
        super("Operation would result in duplicate projects");
    }
}
