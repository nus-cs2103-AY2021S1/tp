package seedu.address.logic.parser;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input command and creates a new ClearCommand object
 */
public class ClearCommandParser implements Parser<ClearCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ClearCommand
     * and returns an ClearCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ClearCommand parse(String args) throws ParseException {
        String trimArgs = args.trim();
        String errorMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                ClearCommand.MESSAGE_USAGE);
        if (trimArgs.length() != 0) {
            throw new ParseException(errorMessage);
        }
        return new ClearCommand();
    }
}
