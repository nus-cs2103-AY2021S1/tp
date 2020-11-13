package seedu.internhunter.logic.parser.exceptions;

import seedu.internhunter.commons.exceptions.IllegalValueException;

/**
 * Represents a parse error encountered by a parser.
 */
public class ParseException extends IllegalValueException {

    public ParseException(String message) {
        super(message);
    }
}
