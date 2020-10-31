package seedu.address.logic.parser.ingredient;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.stream.Stream;

import seedu.address.logic.commands.ingredient.SearchIngredientCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ingredient.IngredientContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class SearchIngredientCommandParser implements Parser<SearchIngredientCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
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

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
