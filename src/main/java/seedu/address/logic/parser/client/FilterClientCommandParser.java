/* @@author wayneswq */
package seedu.address.logic.parser.client;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.client.FilterClientCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.client.ClientNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FilterClientCommand object
 */
public class FilterClientCommandParser implements Parser<FilterClientCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterHairdresserCommand
     * and returns an FilterHClientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterClientCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterClientCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FilterClientCommand(new ClientNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
