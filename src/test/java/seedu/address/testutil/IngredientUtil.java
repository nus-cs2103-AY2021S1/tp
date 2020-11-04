package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

//import java.util.Set;
import seedu.address.logic.commands.ingredient.AddIngredientCommand;
import seedu.address.logic.commands.ingredient.EditIngredientCommand;
import seedu.address.logic.commands.recipe.EditRecipeCommand.EditRecipeDescriptor;
import seedu.address.model.ingredient.Ingredient;

/**
 * A utility class for Ingredient.
 */
public class IngredientUtil {

    /**
     * Returns an add command string for adding the {@code ingredient}.
     */
    public static String getAddIngredientCommand(Ingredient ingredient) {
        return AddIngredientCommand.COMMAND_WORD + " " + getIngredientDetails(ingredient);
    }

    /**
     * Returns the part of command string for the given {@code ingredient}'s details.
     */
    public static String getIngredientDetails(Ingredient ingredient) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_INGREDIENT + ingredient.getValue() + " -" + ingredient.getQuantity());
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditIngredientDescriptor}'s details.
     */
    public static String getEditIngredientDescriptorDetails(EditIngredientCommand.EditIngredientDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getIngredient().ifPresent(ingredient -> {
            if (ingredient.getQuantity() != "") {
                sb.append(PREFIX_INGREDIENT).append(ingredient.getValue() + " " + PREFIX_QUANTITY).append(" ");
            } else {
                sb.append(PREFIX_INGREDIENT).append(ingredient.getValue()).append(" ");
            }
        });
        return sb.toString();
    }
}
