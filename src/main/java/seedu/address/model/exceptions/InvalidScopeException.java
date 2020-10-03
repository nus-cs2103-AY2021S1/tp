package seedu.address.model.exceptions;

import seedu.address.model.project.Status;

public class InvalidScopeException extends RuntimeException {
    private final Status expected;
    private final Status actual;

    public InvalidScopeException(Status expected, Status actual) {
        super();
        this.expected = expected;
        this.actual = actual;
    }
}
