package seedu.address.logic.parser;

import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class RedoCommandParser implements Parser<RedoCommand> {

    public static final String ERROR_MESSAGE = "No parameter is expected for redo command!";

    @Override
    public RedoCommand parse(String userInput) throws ParseException {
        if (userInput.trim().length() != 0) {
            throw new ParseException(ERROR_MESSAGE);
        }
        return new RedoCommand();
    }
}
