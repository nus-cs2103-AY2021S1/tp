package seedu.homerce.logic.parser.service;

import static seedu.homerce.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.homerce.commons.core.index.Index;
import seedu.homerce.logic.commands.service.DeleteServiceCommand;
import seedu.homerce.logic.parser.Parser;
import seedu.homerce.logic.parser.ParserUtil;
import seedu.homerce.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteServiceCommand object
 */
public class DeleteServiceCommandParser implements Parser<DeleteServiceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteServiceCommand
     * and returns a DeleteServiceCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteServiceCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteServiceCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteServiceCommand.MESSAGE_USAGE), pe);
        }
    }
}
