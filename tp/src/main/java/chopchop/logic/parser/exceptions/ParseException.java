// ParseException.java

package chopchop.logic.parser.exceptions;

/**
 * An exception thrown when a parsing error occurs.
 */
public class ParseException extends Exception {
    public ParseException(String message) {
        super(message);
    }
}
