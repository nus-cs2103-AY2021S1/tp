package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.recipe.IngredientPrecursor;
import seedu.address.model.recipe.ProductQuantity;

public class RecipeParserUtil {
    public static final String DEFAULT_DESCRIPTION = "None";
    public static final String DEFAULT_PRODUCT_QUANTITY = "1";

    private static final Pattern SPLIT_INGREDIENT_FORMAT = Pattern.compile("[^\\[]+\\[\\d+");
    private static final String MESSAGE_INGREDIENT_FORMAT = "Ingredients should be listed as itemName[qty], ..."
            + "qty should be an integer between 0 and 2,147,483,647";

    /**
     * Parses a {@code String name} into a {@code String}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static String parseProductName(String productName) {
        requireNonNull(productName);
        return productName.trim();
    }

    /**
     * Converts user-input string of ingredients into list of {@code IngredientPrecursor}.
     *
     * @param ingredients User-input string.
     * @return Parsed list of IngredientPrecursors.
     * @throws ParseException If the input is invalid.
     */
    public static List<IngredientPrecursor> parseIngredients(String ingredients) throws ParseException {
        String[] splitIngredients = ingredients.split("],|]");
        if (splitIngredients.length < 1 || !RecipeParserUtil.checkIngredients(splitIngredients)) {
            throw new ParseException(MESSAGE_INGREDIENT_FORMAT);
        }

        List<IngredientPrecursor> ingredientPrecursors = new ArrayList<>();
        String[] splitParts;
        for (String ingredient : splitIngredients) {
            splitParts = ingredient.trim().split("\\[");
            ingredientPrecursors.add(new IngredientPrecursor(splitParts[0].trim(), Integer.parseInt(splitParts[1].trim())));
        }
        return ingredientPrecursors;
    }

    private static boolean checkIngredients(String[] splitIngredients) {
        for (String ingredient : splitIngredients) {
            Matcher matcher = SPLIT_INGREDIENT_FORMAT.matcher(ingredient);
            if (!matcher.matches()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Parses a {@code String productQuantity} into a {@code String}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static ProductQuantity parseProductQuantity(String productQuantity) throws ParseException {
        requireNonNull(productQuantity);
        String trimmedQuantity = productQuantity.trim();
        if (!ProductQuantity.isValidQuantity(trimmedQuantity)) {
            throw new ParseException(ProductQuantity.MESSAGE_CONSTRAINTS);
        }
        return new ProductQuantity(trimmedQuantity);
    }

    /**
     * Parses a {@code String description} into a {@code String}.
     * Leading and trailing whitespaces will be trimmed.
     */
    public static String parseDescription(String description) {
        requireNonNull(description);
        return description.trim();
    }

}
