package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.ingredientcommands.IngredientFindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ingredient.IngredientNameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new {@code IngredientViewSingleCommand} object
 */
public class IngredientFindCommandParser implements Parser<IngredientFindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the IngredientFindCommand
     * and returns an IngredientFindCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public IngredientFindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, IngredientFindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new IngredientFindCommand(new IngredientNameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}

