package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ParserUtil.OBJECT_TYPE_POSITION;
import static seedu.address.logic.parser.ParserUtil.removeDash;

import seedu.address.logic.command.ShowCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ShowCommand object
 */
public class ShowCommandParser implements Parser<ShowCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ShowCommand.
     * and returns a ShowCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public ShowCommand parse(String args) throws ParseException {
        String[] keywords = args.split(" ");
        if (keywords.length != ShowCommand.COMMAND_TOKENS) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowCommand.MESSAGE_USAGE));
        }
        String travelPlanObjectType = removeDash(keywords[OBJECT_TYPE_POSITION], ShowCommand.MESSAGE_USAGE);
        return new ShowCommand(travelPlanObjectType);
    }
}
