/* @@author wayneswq */
package seedu.address.logic.parser.hairdresser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.hairdresser.FilterHairdresserCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.hairdresser.HairdresserNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FilterHairdresserCommand object
 */
public class FilterHairdresserCommandParser implements Parser<FilterHairdresserCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterHairdresserCommand
     * and returns an FilterHairdresserCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterHairdresserCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterHairdresserCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FilterHairdresserCommand(new HairdresserNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
