package seedu.address.logic.parser;

import seedu.address.logic.commands.ListRecipeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class ListRecipeCommandParser implements Parser<ListRecipeCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ListRecipeCommand
     * and returns an ListRecipeCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListRecipeCommand parse(String args) throws ParseException {
        if (args.isBlank()) {
            return new ListRecipeCommand();
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListRecipeCommand.MESSAGE_USAGE));
        }
    }
}
