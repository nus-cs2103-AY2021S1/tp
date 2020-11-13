package seedu.address.logic.parser.deliverable;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.deliverable.MarkDoneCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MarkDoneCommand object
 */
public class MarkDoneCommandParser implements Parser<MarkDoneCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MarkDoneCommand
     * and returns a MarkDoneCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public MarkDoneCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new MarkDoneCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkDoneCommand.MESSAGE_USAGE), pe);
        }
    }
}
