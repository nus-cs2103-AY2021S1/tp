package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPE_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPE_INGREDIENTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPE_PRODUCT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPE_PRODUCT_QUANTITY;
import static seedu.address.logic.parser.RecipeParserUtil.DEFAULT_DESCRIPTION;
import static seedu.address.logic.parser.RecipeParserUtil.DEFAULT_PRODUCT_QUANTITY;

import java.util.List;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddRecipeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.recipe.IngredientPrecursor;
import seedu.address.model.recipe.ProductQuantity;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.RecipePrecursor;

/**
 * Parses input arguments and creates a new AddRecipeCommand object
 */
public class AddRecipeCommandParser implements Parser<AddRecipeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddRecipeCommand
     * and returns an AddRecipeCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddRecipeCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_RECIPE_PRODUCT_NAME, PREFIX_RECIPE_INGREDIENTS,
                        PREFIX_RECIPE_PRODUCT_QUANTITY, PREFIX_RECIPE_DESCRIPTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_RECIPE_PRODUCT_NAME, PREFIX_RECIPE_INGREDIENTS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRecipeCommand.MESSAGE_USAGE));
        }

        String productName = RecipeParserUtil.parseProductName(argMultimap.getValue(PREFIX_RECIPE_PRODUCT_NAME).get());
        List<IngredientPrecursor> ingredientPrecursors = RecipeParserUtil
                .parseIngredients(argMultimap.getValue(PREFIX_RECIPE_INGREDIENTS).get());
        ProductQuantity productQuantity = RecipeParserUtil
                .parseProductQuantity(argMultimap.getValue(PREFIX_RECIPE_PRODUCT_QUANTITY)
                        .orElse(DEFAULT_PRODUCT_QUANTITY));
        String description = RecipeParserUtil.parseDescription(argMultimap.getValue(PREFIX_RECIPE_DESCRIPTION)
                .orElse(DEFAULT_DESCRIPTION));

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
