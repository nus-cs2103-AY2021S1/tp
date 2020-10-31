package seedu.tasklist.logic.parser;

import static seedu.tasklist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import seedu.tasklist.commons.core.index.Index;
import seedu.tasklist.logic.commands.RemindCommand;
import seedu.tasklist.logic.parser.exceptions.ParseException;

public class RemindCommandParser implements Parser<RemindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RemindCommand
     * and returns a RemindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemindCommand parse(String args) throws ParseException {
        try {
            List<Index> parsedIndexes = ParserUtil.parseIndexes(args);
            return new RemindCommand(parsedIndexes);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemindCommand.MESSAGE_USAGE), pe);
        }
    }

}
