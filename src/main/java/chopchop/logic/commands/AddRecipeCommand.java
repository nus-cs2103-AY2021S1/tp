package chopchop.logic.commands;

import static java.util.Objects.requireNonNull;
import static chopchop.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static chopchop.logic.parser.CliSyntax.PREFIX_QTY;
import static chopchop.logic.parser.CliSyntax.PREFIX_STEP;

import chopchop.logic.commands.exceptions.CommandException;
import chopchop.model.Model;
import chopchop.model.recipe.Recipe;
import chopchop.model.recipe.Ingredient;

/**
 * Adds a person to the address book.
 */
public class AddRecipeCommand extends Command {

    public static final String COMMAND_WORD = "add recipe";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a recipe to the recipe book. "
            + "Parameters: "
            + "NAME "
            + "[" + PREFIX_INGREDIENT + "INGREDIENT [" + PREFIX_QTY + " QUANTITY]]..."
            + "[" + PREFIX_STEP + "STEP]...\n"
            + "Example: " + COMMAND_WORD + " "
            + "Sugar Tomato"
            + PREFIX_INGREDIENT + "Sugar "
            + PREFIX_INGREDIENT + "Tomato /qty 5 "
            + PREFIX_STEP + "Chop tomatoes. "
            + PREFIX_STEP + "Add sugar to it and mix well. ";

    public static final String MESSAGE_SUCCESS = "New recipe added: %1$s";
    public static final String MESSAGE_DUPLICATE_RECIPE = "This recipe already exists in the recipe book";

    private final Recipe recipeToAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddRecipeCommand(Recipe recipe) {
        requireNonNull(recipe);
        recipeToAdd = recipe;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasRecipe(recipeToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_RECIPE);
        }

        model.addRecipe(recipeToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, recipeToAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddRecipeCommand // instanceof handles nulls
                && recipeToAdd.equals(((AddRecipeCommand) other).recipeToAdd));
    }
}
