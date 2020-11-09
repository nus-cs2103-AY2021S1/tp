package seedu.address.logic.parser.exceptions;

/**
 * Represents a invalid index error encountered by a parser.
 */
public class InvalidIndexException extends ParseException {

    public InvalidIndexException(String message) {
        super(message);
    }

    public InvalidIndexException(String message, Throwable cause) {
        super(message, cause);
    }
}
