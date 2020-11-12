package seedu.address.logic.parser.exceptions;


/**
 * Represents a multiple attributes error encountered when parsing.
 */
public class MultipleAttributesException extends Exception {

    public MultipleAttributesException(String message) {
        super(message);
    }

    public MultipleAttributesException(String message, Throwable cause) {
        super(message, cause);
    }
}
