package seedu.stock.logic.parser;

import seedu.stock.logic.commands.PrintCommand;
import seedu.stock.logic.parser.exceptions.ParseException;

import static seedu.stock.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input arguments and creates a new PrintCommand object
 */
public class PrintCommandParser implements Parser<PrintCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PrintCommand
     * and returns an PrintCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PrintCommand parse(String args) throws ParseException {

        // Checks if all the prefixes only appear once in the given command.
        if (!hasNoArguments(args)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, PrintCommand.MESSAGE_USAGE));
        }

        return new PrintCommand();
    }

    public boolean hasNoArguments(String args) {
        return args.length() == 0;
    }
}
