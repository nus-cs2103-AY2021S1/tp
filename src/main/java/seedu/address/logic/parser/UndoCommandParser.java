package seedu.address.logic.parser;

import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class UndoCommandParser implements Parser<UndoCommand> {

    private static final String ERROR_MESSAGE = "No parameter is expected for undo command!";

    @Override
    public UndoCommand parse(String userInput) throws ParseException {
        if (userInput.length() != 0) {
            throw new ParseException(ERROR_MESSAGE);
        }
        return new UndoCommand();
    }
}
