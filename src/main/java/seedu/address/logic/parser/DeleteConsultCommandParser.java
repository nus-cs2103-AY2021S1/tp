package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteConsultCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteConsultCommand object
 */
public class DeleteConsultCommandParser implements Parser<DeleteConsultCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteConsultCommand
     * and returns a DeleteConsultCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteConsultCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteConsultCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteConsultCommand.MESSAGE_USAGE), pe);
        }
    }

}
