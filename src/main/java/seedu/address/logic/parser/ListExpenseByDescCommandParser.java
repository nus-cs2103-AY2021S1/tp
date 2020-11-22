package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.ListExpenseByDescCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.expense.DescContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new ListExpenseByDescCommand object
 */
public class ListExpenseByDescCommandParser implements Parser<ListExpenseByDescCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListExpenseByDescCommand
     * and returns a ListExpenseByDescCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListExpenseByDescCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListExpenseByDescCommand.MESSAGE_USAGE));
        }
        String[] categoryKeywords = trimmedArgs.split("\\s+");

        return new ListExpenseByDescCommand(new DescContainsKeywordsPredicate(Arrays.asList(categoryKeywords)));
    }

}
