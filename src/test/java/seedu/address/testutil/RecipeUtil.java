package seedu.address.testutil;

//import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSTRUCTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPE_IMAGE;

import seedu.address.logic.commands.AddRecipeCommand;
import seedu.address.logic.commands.EditCommand.EditRecipeDescriptor;
import seedu.address.model.recipe.Recipe;

//import java.util.Set;

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
        sb.append(PREFIX_INGREDIENT + recipe.getIngredient().stream()
                .map(item -> item.getValue())
                .reduce("", (a, b) -> b.equals("") ? a : b + ", " + a) + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditRecipeDescriptor}'s details.
     */
    public static String getEditRecipeDescriptorDetails(EditRecipeDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getIngredient()
                .ifPresent(ingredients -> sb.append(PREFIX_INGREDIENT)
                        .append(ingredients.stream()
                                .map(item -> item.getValue())
                                .reduce("", (a, b) -> b.equals("") ? a : b + ", " + a)).append(" "));
        descriptor.getInstruction().ifPresent(instr -> sb.append(PREFIX_INSTRUCTION).append(" "));
        descriptor.getRecipeImage().ifPresent(img -> sb.append(PREFIX_RECIPE_IMAGE).append(" "));
        return sb.toString();
    }
}
