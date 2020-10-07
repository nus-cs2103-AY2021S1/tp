package seedu.address.logic.parser;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.recipe.Ingredient;

import java.util.ArrayList;

import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;

/**
 * Parse user inputted ingredients.
 */
public class IngredientParser {
    /**
     * Parses a String made of 1 or more ingredient name and optional quantity then 
     * adds them to an ArrayList<Ingredient> to be returned
     * @param ingredientString String of 1 or more ingredient name and optional quantity
     * @return ArrayList<Ingredient> of the ingredients in the parameter
     * @throws ParseException
     */
    public static ArrayList<Ingredient> parse(String ingredientString) throws ParseException {
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
    private static String getIngredientQuantity(String args) throws ParseException {
        ArgumentMultimap argMultimapQuantity =
                ArgumentTokenizer.tokenize(args, PREFIX_QUANTITY);
//        if (!argMultimapQuantity.getPreamble().isEmpty()) {
//            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddIngredientCommand.MESSAGE_USAGE));
//        }
        return ParserUtil.parseQuantity(argMultimapQuantity.getValue(PREFIX_QUANTITY).get());
    }
}
