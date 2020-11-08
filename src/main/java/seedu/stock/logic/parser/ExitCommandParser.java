package seedu.stock.logic.parser;

import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.stock.commons.core.LogsCenter;
import seedu.stock.logic.commands.ExitCommand;
import seedu.stock.logic.parser.exceptions.ParseException;

/**
 * Parses and ensures no other user input is present other than the command word
 * itself.
 */
public class ExitCommandParser implements Parser<ExitCommand> {
    private static final Logger logger = LogsCenter.getLogger(ExitCommandParser.class);

    @Override
    public ExitCommand parse(String userInput) throws ParseException {
        logger.log(Level.INFO, "Starting to parse exit command");
        if (userInput.trim().length() != 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExitCommand.MESSAGE_USAGE));
        }
        logger.log(Level.INFO, "Finished parsing exit command successfully");
        return new ExitCommand();
    }
}
