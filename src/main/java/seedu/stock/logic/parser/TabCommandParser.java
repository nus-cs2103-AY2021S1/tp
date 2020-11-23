package seedu.stock.logic.parser;

import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.stock.commons.core.LogsCenter;
import seedu.stock.logic.commands.TabCommand;
import seedu.stock.logic.parser.exceptions.ParseException;

public class TabCommandParser implements Parser<TabCommand> {

    private static final Logger logger = LogsCenter.getLogger(TabCommandParser.class);

    @Override
    public TabCommand parse(String userInput) throws ParseException {
        logger.log(Level.INFO, "Starting to parse tab command");
        if (userInput.trim().length() != 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TabCommand.MESSAGE_USAGE));
        }
        logger.log(Level.INFO, "Finished parsing tab command successfully");
        return new TabCommand();
    }
}
