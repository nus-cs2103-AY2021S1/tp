package seedu.address.testutil;

//import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

//import java.util.Set;
import seedu.address.logic.commands.AddRecipeCommand;
import seedu.address.logic.commands.EditCommand.EditRecipeDescriptor;
import seedu.address.model.recipe.Recipe;
//import seedu.address.model.tag.Tag;

/**
 * A utility class for Recipe.
 */
public class RecipeUtil {

    /**
     * Returns an add command string for adding the {@code recipe}.
     */
    public static String getAddRecipeCommand(Recipe recipe) {
        return AddRecipeCommand.COMMAND_WORD + " " + getRecipeDetails(recipe);
    }

    /**
     * Returns the part of command string for the given {@code recipe}'s details.
     */
    public static String getRecipeDetails(Recipe recipe) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + recipe.getName().fullName + " ");
        sb.append(PREFIX_INGREDIENT + recipe.getIngredientString().value + " ");
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
