package seedu.address.logic.parser.exceptions;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a parse error encountered by a parser.
 */
public class ParseException extends IllegalValueException {

    /**
     * Constructs a new {@code ParseException} with the specified detail {@code message}.
     * @param message
     */
    public ParseException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code ParseException} with the specified detail {@code message} and {@code cause}.
     * @param message
     * @param cause
     */
    public ParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
