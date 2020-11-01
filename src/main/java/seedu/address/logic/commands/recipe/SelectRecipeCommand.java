package seedu.address.logic.commands.recipe;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.recipe.Recipe;

/**
 * Deletes a recipe identified using it's displayed index from the recipe list.
 */
public class SelectRecipeCommand extends Command {

    public static final String COMMAND_WORD = "selectR";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Selects the recipe identified by the index number used in the displayed recipe list.\n"
            + "Parameters: " + "INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SELECT_RECIPE_SUCCESS = "Selected Recipe: %1$s";

    private static Logger logger = Logger.getLogger("SelectLogger");

    private final Index toSelect;

    public SelectRecipeCommand(Index toSelect) {
        this.toSelect = toSelect;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        logger.log(Level.INFO, "going to start selecting");
        requireNonNull(model);
        List<Recipe> lastShownList = model.getFilteredRecipeList();

        if (toSelect.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
        }

        Recipe recipeToSelect = lastShownList.get(toSelect.getZeroBased());
        logger.log(Level.INFO, "end of selecting");
        String selectedRecipe = separateRecipeString(recipeToSelect);
        return new CommandResult(String.format(MESSAGE_SELECT_RECIPE_SUCCESS, selectedRecipe), recipeToSelect);
    }

    public String separateRecipeString(Recipe recipe) {
        String recipeString = recipe.toString();
        String recipeStringSeparated;
        recipeStringSeparated = recipeString.replace("Ingredient:", "\n" + "Ingredient:")
                .replace("Calories:", "\n" + "Calories:")
                .replace("Instructions:", "\n" + "Instructions:")
                .replace("Tags:", "\n" + "Tags:");
        recipeStringSeparated = "\n" + recipeStringSeparated;
        return recipeStringSeparated;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SelectRecipeCommand // instanceof handles nulls
                && toSelect.equals(((SelectRecipeCommand) other).toSelect)); // state check
    }
}
