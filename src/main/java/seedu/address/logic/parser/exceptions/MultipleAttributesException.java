package seedu.address.logic.parser.exceptions;

public class MultipleAttributesException extends ParseException {

    public MultipleAttributesException(String message) {
        super(message);
    }

    public MultipleAttributesException(String message, Throwable cause) {
        super(message, cause);
    }
}
