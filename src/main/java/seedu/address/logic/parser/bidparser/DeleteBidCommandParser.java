package seedu.address.logic.parser.bidparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.bidcommands.DeleteBidCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteBidCommandParser implements Parser<DeleteBidCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteBidCommand
     * and returns a DeleteBidCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteBidCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteBidCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteBidCommand.MESSAGE_USAGE), pe);
        }
    }

}
