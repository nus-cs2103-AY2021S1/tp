package seedu.address.model.exceptions;

import seedu.address.model.project.Status;

/**
 * Signals operations on an invalid scope status.
 */
public class InvalidScopeException extends RuntimeException {
    private final Status expected;
    private final Status actual;

    /**
     * Creates an InvalidScopeException with the expected and actual scope status specified.
     */
    public InvalidScopeException(Status expected, Status actual) {
        super();
        this.expected = expected;
        this.actual = actual;
    }
}
