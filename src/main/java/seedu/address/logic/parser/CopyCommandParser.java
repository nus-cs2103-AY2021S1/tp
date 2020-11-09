package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ParserUtil.ACTIVITY_INDEX_POSITION;
import static seedu.address.logic.parser.ParserUtil.TRAVELPLAN_INDEX_POSITION;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.command.copy.CopyCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new CopyCommand object
 */
public class CopyCommandParser implements Parser<CopyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CopyCommand
     * and returns a CopyCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CopyCommand parse(String args) throws ParseException {
        String[] keywords = args.split(" ");
        if (keywords.length != CopyCommand.COMMAND_TOKENS) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CopyCommand.MESSAGE_USAGE));
        }

        Index activityIndex = ParserUtil.parseIndex(keywords[ACTIVITY_INDEX_POSITION]);
        Index travelPlanIndex = ParserUtil.parseIndex(keywords[TRAVELPLAN_INDEX_POSITION]);

        return new CopyCommand(activityIndex, travelPlanIndex);
    }

}
