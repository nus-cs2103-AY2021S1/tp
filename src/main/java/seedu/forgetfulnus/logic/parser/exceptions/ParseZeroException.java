package seedu.forgetfulnus.logic.parser.exceptions;

/**
 * Represents a parse error due to a param of '0' encountered by a parser.
 */
public class ParseZeroException extends ParseException {

    public ParseZeroException(String message) {
        super(message);
    }

    public ParseZeroException(String message, Throwable cause) {
        super(message, cause);
    }
}
