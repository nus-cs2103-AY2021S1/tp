package seedu.address.logic.parser.ingredient;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT;

import java.util.ArrayList;
import java.util.stream.Stream;

import seedu.address.logic.commands.ingredient.AddIngredientCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.data.IngredientParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ingredient.Ingredient;

/**
 * Parses input arguments and creates a new AddIngredientCommand object
 */
public class AddIngredientCommandParser implements Parser<AddIngredientCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddIngredientCommand
     * and returns an AddIngredientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddIngredientCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_INGREDIENT);

        if (!arePrefixesPresent(argMultimap, PREFIX_INGREDIENT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddIngredientCommand.MESSAGE_USAGE));
        }

        String ingredientString = ParserUtil.parseIngredients(argMultimap.getValue(PREFIX_INGREDIENT).get());
        if (ingredientString.length() == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddIngredientCommand.MESSAGE_USAGE));
        }
        assert ingredientString.length() != 0 : "ingredientString should not be empty";

        ArrayList<Ingredient> ingredients = IngredientParser.parse(ingredientString);

        if (IngredientParser.hasDuplicates(ingredients)) {
            throw new ParseException(AddIngredientCommand.MESSAGE_ADD_DUPLICATE_INGREDIENTS);
        }

        return new AddIngredientCommand(ingredients);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
