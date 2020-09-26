package seedu.address.testutil;

//import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

//import java.util.Set;
import seedu.address.logic.commands.AddIngredientCommand;
import seedu.address.logic.commands.EditCommand.EditRecipeDescriptor;
import seedu.address.model.recipe.Ingredient;

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
        sb.append(PREFIX_INGREDIENT + ingredient.getValue() + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditRecipeDescriptor}'s details.
     */
    public static String getEditRecipeDescriptorDetails(EditRecipeDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getIngredientString()
                .ifPresent(ingredientString -> sb.append(PREFIX_INGREDIENT)
                        .append(ingredientString.value).append(" "));

        return sb.toString();
    }
}
