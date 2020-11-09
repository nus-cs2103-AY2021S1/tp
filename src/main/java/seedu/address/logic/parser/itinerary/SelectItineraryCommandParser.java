package seedu.address.logic.parser.itinerary;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.itinerary.SelectItineraryCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SelectItineraryCommand object
 */
public class SelectItineraryCommandParser implements Parser<SelectItineraryCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SelectItineraryCommand
     * and returns a SelectItineraryCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SelectItineraryCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new SelectItineraryCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, SelectItineraryCommand.MESSAGE_USAGE), pe);
        }
    }

}
