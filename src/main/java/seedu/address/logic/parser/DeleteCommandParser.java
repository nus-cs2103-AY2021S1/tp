package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INDEX_OUT_OF_BOUNDS;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.Nric;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        try {
            if (Nric.isValidNric(args)) {
                Nric nric = ParserUtil.parseNric(args.trim());
                return new DeleteCommand(nric);
            } else {
                Index index = ParserUtil.parseIndex(args);
                return new DeleteCommand(index);
            }
        } catch (ParseException pe) {
            if (StringUtil.isInteger(args.trim()) && !StringUtil.isValidIndexRange(args.trim())) {
                throw new ParseException(
                        String.format(MESSAGE_INDEX_OUT_OF_BOUNDS, Index.RANGE), pe);
            }
            throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }
    }
}
