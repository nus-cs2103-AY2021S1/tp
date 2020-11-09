package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.RandomCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.category.Category;



/**
 * Parses input arguments and creates a new RandomCommand object
 */
public class RandomCommandParser implements Parser<RandomCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RandomCommand
     * and returns a RandomCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RandomCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RandomCommand.MESSAGE_USAGE));
        }
        String temp = trimmedArgs;
        if (!Category.isValidCategoryName(temp)) {
            throw new ParseException(
                    String.format(Category.MESSAGE_CONSTRAINTS));
        }
        Category category = new Category(temp);
        return new RandomCommand(category);
    }

}
