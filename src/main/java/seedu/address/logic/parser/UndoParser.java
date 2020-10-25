package seedu.address.logic.parser;


import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new UndoCommand object
 */
public class UndoParser implements Parser<UndoCommand> {
    /**
     * Returns a UndoCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UndoCommand parse(String userInput) throws ParseException {
        return new UndoCommand();
    }
}
