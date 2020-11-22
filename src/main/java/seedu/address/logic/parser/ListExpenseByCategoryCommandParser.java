package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.ListExpenseByCategoryCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.expense.CategoryContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new ListExpenseByCategoryCommand object
 */
public class ListExpenseByCategoryCommandParser implements Parser<ListExpenseByCategoryCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListExpenseByCategoryCommand
     * and returns a ListExpenseByCategoryCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListExpenseByCategoryCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListExpenseByCategoryCommand.MESSAGE_USAGE));
        }
        String[] categoryKeywords = trimmedArgs.split("\\s+");

        return new ListExpenseByCategoryCommand(new CategoryContainsKeywordsPredicate(Arrays.asList(categoryKeywords)));
    }

}
