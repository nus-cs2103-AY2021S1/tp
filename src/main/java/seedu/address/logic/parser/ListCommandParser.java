package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_NON_EMPTY_ARGUMENT;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ListCommandParser implements Parser<ListCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns an ListCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        requireNonNull(args);

        if (!args.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_NON_EMPTY_ARGUMENT,
                ListCommand.MESSAGE_USAGE));
        }

        return new ListCommand();
    }
}
