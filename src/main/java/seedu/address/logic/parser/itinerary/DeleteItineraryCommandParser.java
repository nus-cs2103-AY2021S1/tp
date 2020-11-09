package seedu.address.logic.parser.itinerary;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.itinerary.DeleteItineraryCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteItineraryCommand object
 */
public class DeleteItineraryCommandParser implements Parser<DeleteItineraryCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteItineraryCommand
     * and returns a DeleteItineraryCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteItineraryCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteItineraryCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteItineraryCommand.MESSAGE_USAGE), pe);
        }
    }

}
