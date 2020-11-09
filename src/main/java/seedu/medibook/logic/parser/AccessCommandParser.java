package seedu.medibook.logic.parser;

import static seedu.medibook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.medibook.commons.core.index.Index;
import seedu.medibook.logic.commands.AccessCommand;
import seedu.medibook.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AccessCommand object
 */
public class AccessCommandParser implements Parser<AccessCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AccessCommand
     * and returns an AccessCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AccessCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new AccessCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AccessCommand.MESSAGE_USAGE), pe);
        }
    }
}
