package seedu.address.logic.parser.exceptions;

import java.util.Objects;

import seedu.address.commons.core.Messages;
import seedu.address.model.Status;

/**
 * Signals operations on an invalid scope status.
 */
public class InvalidScopeException extends ParseException {
    private final Status expected;
    private final Status actual;

    /**
     * Creates an InvalidScopeException with the expected and actual scope status specified.
     */
    public InvalidScopeException(Status expected, Status actual) {
        super(String.format(Messages.MESSAGE_INVALID_SCOPE_COMMAND, expected, actual));
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
