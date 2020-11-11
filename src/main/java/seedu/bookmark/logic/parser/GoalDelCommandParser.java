package seedu.bookmark.logic.parser;

import static seedu.bookmark.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.bookmark.commons.core.index.Index;
import seedu.bookmark.logic.commands.GoalDelCommand;
import seedu.bookmark.logic.parser.exceptions.ParseException;

public class GoalDelCommandParser implements Parser<GoalDelCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the GoalDelCommand
     * and returns a GoalDelCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GoalDelCommand parse(String input) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(input);
            return new GoalDelCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GoalDelCommand.MESSAGE_USAGE), pe);
        }
    }

}
