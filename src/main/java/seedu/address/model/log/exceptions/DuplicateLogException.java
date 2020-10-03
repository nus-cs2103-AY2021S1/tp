package seedu.address.model.log.exceptions;

/**
 * Signals that the operation will result in duplicate Logs (Logs are considered duplicates if they have the same
 * date).
 */
public class DuplicateLogException extends RuntimeException {
    public DuplicateLogException() {
        super("Operation would result in duplicate persons");
    }
}
