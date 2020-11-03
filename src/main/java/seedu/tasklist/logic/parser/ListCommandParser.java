package seedu.tasklist.logic.parser;

import static seedu.tasklist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.tasklist.commons.core.index.Index;
import seedu.tasklist.logic.commands.ListCommand;
import seedu.tasklist.logic.parser.exceptions.ParseException;

public class ListCommandParser implements Parser<ListCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        boolean hasArgumentIndex = args.matches("(.+|[$&+,:;=?@#|'<>.^*()%!-])$"); //Has argument
        if (!hasArgumentIndex) {
            return new ListCommand(Index.fromZeroBased(0));
        } else {
            boolean argumentRange = args.trim().matches("^([1-9]|[1-4][0-9]|50)$");
            if (argumentRange) {
                Index index = ParserUtil.parseListIndex(args);
                return new ListCommand(index);
            } else {
                throw new ParseException(ListCommand.MESSAGE_INDEX_NOT_IN_RANGE);
            }
        }
    }
}
