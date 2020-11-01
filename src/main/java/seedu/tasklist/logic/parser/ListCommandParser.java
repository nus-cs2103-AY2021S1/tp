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
        }
        try {
            Index index = ParserUtil.parseListIndex(args);
            return new ListCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE), pe);
        }
    }
}
