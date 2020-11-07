package seedu.fma.logic.parser.exceptions;

import seedu.fma.commons.exceptions.IllegalValueException;

/**
 * Represents an error when index is invalid
 */
public class InvalidIndexException extends IllegalValueException {

    public InvalidIndexException(String message) {
        super(message);
    }

    public InvalidIndexException(String message, Throwable cause) {
        super(message, cause);
    }
}
