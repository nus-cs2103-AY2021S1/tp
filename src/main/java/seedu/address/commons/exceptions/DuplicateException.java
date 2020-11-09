package seedu.address.commons.exceptions;

/**
 * Represents an error due to presence of duplicate objects in a unique list.
 */
public class DuplicateException extends RuntimeException {
    /**
     * @param message should contain relevant information on the failed constraint(s)
     */
    public DuplicateException(String message) {
        super(message);
    }
}
