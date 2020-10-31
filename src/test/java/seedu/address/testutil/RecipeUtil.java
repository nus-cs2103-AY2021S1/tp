package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSTRUCTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPE_IMAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Set;

import seedu.address.logic.commands.recipe.AddRecipeCommand;
import seedu.address.logic.commands.recipe.EditRecipeCommand.EditRecipeDescriptor;
import seedu.address.model.recipe.Instruction;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.Tag;

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
                .map(item -> {
                    String ingredientString = item.getValue() + " ";
                    if (item.getValue() != "") {
                        ingredientString += PREFIX_QUANTITY + item.getQuantity();
                    }
                    return ingredientString;
                })
                .reduce("", (a, b) -> b.equals("") ? a : b + ", " + a) + " ");
        sb.append(PREFIX_CALORIES + String.valueOf(recipe.getCalories().value) + " ");
        ArrayList<Instruction> oriInstrList = recipe.getInstruction();
        ArrayList<Instruction> newInstrList = new ArrayList<>();
        for (Instruction instr : oriInstrList) {
            for (int i = 0; i < instr.toString().length(); i++) {
                if (instr.toString().charAt(i) == ')') {
                    newInstrList.add(new Instruction(instr.toString().substring(i + 2)));
                    break;
                }
            }
        }
        sb.append(PREFIX_INSTRUCTION + newInstrList.stream()
                .map(item -> item.toString() + ". ")
                .reduce("", (a, b) -> a + b).trim() + " ");
        sb.append(PREFIX_RECIPE_IMAGE + recipe.getRecipeImage().getValue() + " ");
        recipe.getTags().stream().forEach(s -> sb.append(PREFIX_TAG + s.tagName + " "));
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
        descriptor.getInstruction()
                .ifPresent(instr -> sb.append(PREFIX_INSTRUCTION)
                        .append(instr.stream()
                                .map(item -> item.toString() + ". ")
                                .reduce("", (a, b) -> a + b).trim()).append(" "));
        descriptor.getInstruction().ifPresent(instr -> sb.append(PREFIX_INSTRUCTION).append(" "));
        descriptor.getRecipeImage().ifPresent(img -> sb.append(PREFIX_RECIPE_IMAGE).append(" "));
        descriptor.getCalories().ifPresent(cal -> sb.append(PREFIX_CALORIES).append(cal.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
