package seedu.address.logic.parser.ingredient;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.ingredient.SearchIngredientCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ingredient.IngredientContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new SearchIngredientCommand object
 */
public class SearchIngredientCommandParser implements Parser<SearchIngredientCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SearchIngredientCommand
     * and returns a SearchIngredientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SearchIngredientCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args);

        String value = argMultimap.getPreamble();

        String trimmedArgs = value.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SearchIngredientCommand.MESSAGE_USAGE));
        }

        String[] ingredientKeywords = trimmedArgs.split("\\s+");
        assert ingredientKeywords.length != 0 : "ingredientKeywords should not be empty";

        return new SearchIngredientCommand(new IngredientContainsKeywordsPredicate(Arrays.asList(ingredientKeywords)));
    }
}
