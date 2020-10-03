package seedu.address.model.exceptions;

import java.util.Objects;

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

    public Status getExpected() {
        return expected;
    }

    public Status getActual() {
        return actual;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InvalidScopeException)) {
            return false;
        }
        InvalidScopeException that = (InvalidScopeException) o;
        return getExpected() == that.getExpected()
                && getActual() == that.getActual();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getExpected(), getActual());
    }
}
