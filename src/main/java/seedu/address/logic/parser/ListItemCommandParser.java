package seedu.address.logic.parser;

import seedu.address.logic.commands.ListItemCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class ListItemCommandParser implements Parser<ListItemCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ListItemCommand
     * and returns an ListItemCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListItemCommand parse(String args) throws ParseException {
        if (args.isBlank()) {
            return new ListItemCommand();
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListItemCommand.MESSAGE_USAGE));
        }
    }
}