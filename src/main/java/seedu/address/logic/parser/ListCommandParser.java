package seedu.address.logic.parser;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ListCommand parse(String userInput) throws ParseException {
        if (!userInput.equals(ListCommand.VALID_USER_INPUT)) {
            throw new ParseException(ListCommand.MESSAGE_INVALID_USER_INPUT);
        }

        return new ListCommand();
    }
}
