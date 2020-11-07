package seedu.address.logic.commands.recipe;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSTRUCTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPE_IMAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.recipe.Recipe;

/**
 * Adds a recipe to the Wishful Shrinking.
 */
public class AddRecipeCommand extends Command {

    public static final String COMMAND_WORD = "addR";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a recipe to the Wishful Shrinking.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_INGREDIENT + "INGREDIENT "
            + "[" + PREFIX_QUANTITY + "QUANTITY] "
            + "[, MORE INGREDIENTS [ -QUANTITY]] "
            + PREFIX_CALORIES + "CALORIES "
            + PREFIX_INSTRUCTION + "INSTRUCTIONS "
            + "[" + PREFIX_RECIPE_IMAGE + "IMAGE] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Chicken salad "
            + PREFIX_INGREDIENT + "chicken -100.50g, lettuce - a bit, tomato -3/4 cups "
            + PREFIX_CALORIES + "100 "
            + PREFIX_INSTRUCTION + "Cook. Eat. "
            + PREFIX_RECIPE_IMAGE + "images/healthy1.jpg "
            + PREFIX_TAG + "healthy "
            + PREFIX_TAG + "delicious";

    public static final String MESSAGE_SUCCESS = "New recipe added: %1$s";
    public static final String MESSAGE_DUPLICATE_RECIPE = "This recipe already exists in the Wishful Shrinking.";
    public static final String MESSAGE_DUPLICATE_INGREDIENTS = "This recipe contains duplicate ingredients.";
    public static final String MESSAGE_EMPTY_INSTRUCTIONS = "This recipe must contain instruction";

    private final Recipe toAdd;

    /**
     * Creates an AddRecipeCommand to add the specified {@code Recipe}
     */
    public AddRecipeCommand(Recipe recipe) {
        requireNonNull(recipe);
        toAdd = recipe;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasMinimalRecipe(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_RECIPE);
        }

        model.addRecipe(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), ListRecipesCommand.COMMAND_WORD);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddRecipeCommand // instanceof handles nulls
                && toAdd.equals(((AddRecipeCommand) other).toAdd));
    }
}
