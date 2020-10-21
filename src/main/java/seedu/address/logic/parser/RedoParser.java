package seedu.address.logic.parser;

import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new RedoCommand object
 */
public class RedoParser {
    /**
     * Returns a RedoCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RedoCommand parse(String userInput) throws ParseException {
        return new RedoCommand();
    }
}
