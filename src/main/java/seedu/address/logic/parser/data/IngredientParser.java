package seedu.address.logic.parser.data;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ingredient.Ingredient;

/**
 * Parse user inputted ingredients.
 */
public class IngredientParser {
    /**
     * Parses a String made of 1 or more ingredient name and optional quantity then
     * adds them to an ArrayList of Ingredient objects to be returned
     * @param ingredientString String of 1 or more ingredient name and optional quantity
     * @return ArrayList of Ingredient objects of the ingredients in the parameter
     * @throws ParseException
     */
    public static ArrayList<Ingredient> parse(String ingredientString) throws ParseException {
        requireNonNull(ingredientString);
        String[] ingredientsToken = ingredientString.split(",");
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        for (int i = 0; i < ingredientsToken.length; i++) {
            String ingName = ingredientsToken[i].trim();
            String ingQuantity = "";
            int indexOfDash = ingName.indexOf("-");
            if (indexOfDash != -1) {
                ingName = ingName.substring(0, indexOfDash).trim();
                ingQuantity = getIngredientQuantity(ingredientsToken[i]).trim();
            }
            ingredients.add(new Ingredient(ingName, ingQuantity));
        }
        return ingredients;
    }

    /**
     * Parses a user input ingredient name and optional quantity.
     * Returns quantity if present.
     * @param args ingredient string
     * @return quantity string
     * @throws ParseException
     */
    public static String getIngredientQuantity(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimapQuantity =
                ArgumentTokenizer.tokenize(args, PREFIX_QUANTITY);
        //Getting from an Optional.empty throws the no such element exception
        try {
            return ParserUtil.parseQuantity(argMultimapQuantity.getValue(PREFIX_QUANTITY).get());
        } catch (NoSuchElementException e) {
            throw new ParseException(Ingredient.HYPHEN_CONSTRAINTS);
        }
    }

    /**
     * Detects if there are duplicate ingredient names in a recipe's ingredients.
     * @param ingredients the list of ingredients
     * @return true if there are duplicate ingredients in the recipe
     */
    public static boolean hasDuplicates(ArrayList<Ingredient> ingredients) {
        Set<String> ingredientSet = new HashSet<>(new HashSet<>(ingredients)
                                    .stream().map(ingredient -> ingredient.getValue().toLowerCase())
                                    .collect(Collectors.toList()));

        if (ingredientSet.size() < ingredients.size()) {
            return true;
        }
        return false;
    }

}
