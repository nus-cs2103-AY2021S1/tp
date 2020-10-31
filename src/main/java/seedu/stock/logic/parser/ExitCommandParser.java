package seedu.stock.logic.parser;

import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.stock.logic.commands.ExitCommand;
import seedu.stock.logic.parser.exceptions.ParseException;

/**
 * Parses and ensures no other user input is present other than the command word
 * itself.
 */
public class ExitCommandParser implements Parser<ExitCommand> {
    @Override
    public ExitCommand parse(String userInput) throws ParseException {
        if (userInput.trim().length() != 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExitCommand.MESSAGE_USAGE));
        }
        return new ExitCommand();
    }
}
