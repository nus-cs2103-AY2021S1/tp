package seedu.stock.logic.parser;

import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.stock.commons.core.LogsCenter;
import seedu.stock.logic.commands.ClearCommand;
import seedu.stock.logic.parser.exceptions.ParseException;

/**
 * Parses and ensures no other user input is present other than the command word
 * itself.
 */
public class ClearCommandParser implements Parser<ClearCommand> {
    private static final Logger logger = LogsCenter.getLogger(ClearCommandParser.class);

    @Override
    public ClearCommand parse(String userInput) throws ParseException {
        logger.log(Level.INFO, "Starting to parse clear command");
        if (userInput.trim().length() != 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearCommand.MESSAGE_USAGE));
        }
        logger.log(Level.INFO, "Finished parsing clear command successfully");
        return new ClearCommand();
    }
}
