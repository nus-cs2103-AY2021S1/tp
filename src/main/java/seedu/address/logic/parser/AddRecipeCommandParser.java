package seedu.address.logic.parser;

import seedu.address.logic.commands.AddItemCommand;
import seedu.address.logic.commands.AddRecipeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.item.Item;
import seedu.address.model.item.Quantity;
import seedu.address.model.recipe.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;


/**
 * Parses input arguments and creates a new AddItemCommand object
 */
public class AddRecipeCommandParser implements Parser<AddRecipeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddItemCommand
     * and returns an AddItemCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddRecipeCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_RECIPE_PRODUCT_NAME, PREFIX_RECIPE_INGREDIENTS,
                        PREFIX_RECIPE_PRODUCT_QUANTITY, PREFIX_RECIPE_DESCRIPTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_RECIPE_PRODUCT_NAME, PREFIX_RECIPE_INGREDIENTS,
                PREFIX_RECIPE_PRODUCT_QUANTITY, PREFIX_RECIPE_DESCRIPTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRecipeCommand.MESSAGE_USAGE));
        }

        String productName = RecipeParserUtil.parseProductName(argMultimap.getValue(PREFIX_RECIPE_PRODUCT_NAME).get());
        List<IngredientPrecursor> ingredientPrecursors = RecipeParserUtil.parseIngredients(argMultimap.getValue(PREFIX_RECIPE_INGREDIENTS).get());
        ProductQuantity productQuantity = RecipeParserUtil.parseProductQuantity(argMultimap.getValue(PREFIX_RECIPE_PRODUCT_QUANTITY).get());
        String description = RecipeParserUtil.parseDescription(argMultimap.getValue(PREFIX_RECIPE_DESCRIPTION).get());

        RecipePrecursor recipePre = new RecipePrecursor(Recipe.getIdCounter() + 1, ingredientPrecursors,
                productName, productQuantity, description);
        return new AddRecipeCommand(recipePre);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
