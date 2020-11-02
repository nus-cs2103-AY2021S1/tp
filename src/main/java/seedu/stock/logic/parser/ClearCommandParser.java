package seedu.stock.logic.parser;

import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.stock.logic.commands.ClearCommand;
import seedu.stock.logic.parser.exceptions.ParseException;

/**
 * Parses and ensures no other user input is present other than the command word
 * itself.
 */
public class ClearCommandParser implements Parser<ClearCommand> {
    @Override
    public ClearCommand parse(String userInput) throws ParseException {
        if (userInput.trim().length() != 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearCommand.MESSAGE_USAGE));
        }
        return new ClearCommand();
    }
}
