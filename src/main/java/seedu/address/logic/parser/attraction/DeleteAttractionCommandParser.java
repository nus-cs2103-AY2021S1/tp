package seedu.address.logic.parser.attraction;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.attraction.DeleteAttractionCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteAttractionCommand object
 */
public class DeleteAttractionCommandParser implements Parser<DeleteAttractionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteAttractionCommand
     * and returns a DeleteAttractionCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteAttractionCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteAttractionCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteAttractionCommand.MESSAGE_USAGE), pe);
        }
    }

}
