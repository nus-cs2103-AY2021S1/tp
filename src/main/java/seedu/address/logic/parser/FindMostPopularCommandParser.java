package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.FindMostPopularCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.category.Category;


/**
 * Parses input arguments and creates a new FindMostPopularCommand object
 */
public class FindMostPopularCommandParser implements Parser<FindMostPopularCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindMostPopularCommand
     * and returns a FindMostPopularCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindMostPopularCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindMostPopularCommand.MESSAGE_USAGE));
        }
        String temp = trimmedArgs;
        if (!Category.isValidCategoryName(temp)) {
            throw new ParseException(
                    String.format(Category.MESSAGE_CONSTRAINTS));
        }
        Category category = new Category(temp);
        return new FindMostPopularCommand(category);
    }

}
