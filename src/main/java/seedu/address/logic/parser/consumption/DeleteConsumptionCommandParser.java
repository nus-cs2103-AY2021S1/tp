package seedu.address.logic.parser.consumption;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.consumption.DeleteConsumptionCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteConsumptionCommandParser implements Parser<DeleteConsumptionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteConsumptionCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            assert index.getOneBased() > 0 : "Index should be bigger than 0";
            return new DeleteConsumptionCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteConsumptionCommand.MESSAGE_USAGE));
        }
    }
}
