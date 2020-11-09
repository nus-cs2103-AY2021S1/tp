package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ParserUtil.ACTIVITY_INDEX_POSITION;
import static seedu.address.logic.parser.ParserUtil.TRAVELPLAN_INDEX_POSITION;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.command.copy.MoveCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MoveCommand object
 */
public class MoveCommandParser implements Parser<MoveCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MoveCommand
     * and returns a MoveCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MoveCommand parse(String args) throws ParseException {
        String[] keywords = args.split(" ");
        if (keywords.length != MoveCommand.COMMAND_TOKENS) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MoveCommand.MESSAGE_USAGE));
        }
        Index activityIndex = ParserUtil.parseIndex(keywords[ACTIVITY_INDEX_POSITION]);
        Index travelPlanIndex = ParserUtil.parseIndex(keywords[TRAVELPLAN_INDEX_POSITION]);

        return new MoveCommand(activityIndex, travelPlanIndex);
    }

}
