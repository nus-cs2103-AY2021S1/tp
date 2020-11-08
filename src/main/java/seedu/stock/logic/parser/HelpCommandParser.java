package seedu.stock.logic.parser;

import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.stock.commons.core.LogsCenter;
import seedu.stock.logic.commands.HelpCommand;
import seedu.stock.logic.parser.exceptions.ParseException;

public class HelpCommandParser implements Parser<HelpCommand> {
    private static final Logger logger = LogsCenter.getLogger(HelpCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the HelpCommand
     * and returns an HelpCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public HelpCommand parse(String args) throws ParseException {
        logger.log(Level.INFO, "Starting to parse help command");
        // Checks if all the prefixes only appear once in the given command.
        if (!hasNoArguments(args)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        logger.log(Level.INFO, "Finished parsing help command successfully");
        return new HelpCommand();
    }

    public boolean hasNoArguments(String args) {
        return args.length() == 0;
    }
}
