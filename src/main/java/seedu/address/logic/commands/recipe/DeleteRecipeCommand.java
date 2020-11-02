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
public class DeleteRecipeCommand extends Command {

    public static final String COMMAND_WORD = "deleteR";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the recipe identified by the index number used in the displayed recipe list.\n"
            + "Parameters: " + "INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_RECIPE_SUCCESS = "Deleted Recipe: %1$s";

    private static Logger logger = Logger.getLogger("DeleteLogger");

    private final Index toDelete;

    public DeleteRecipeCommand(Index toDelete) {
        this.toDelete = toDelete;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        logger.log(Level.INFO, "going to start deleting");
        requireNonNull(model);
        List<Recipe> lastShownList = model.getFilteredRecipeList();

        if (toDelete.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
        }

        Recipe recipeToDelete = lastShownList.get(toDelete.getZeroBased());
        model.deleteRecipe(recipeToDelete);
        logger.log(Level.INFO, "end of deleting");
        return new CommandResult(String.format(MESSAGE_DELETE_RECIPE_SUCCESS, recipeToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteRecipeCommand // instanceof handles nulls
                && toDelete.equals(((DeleteRecipeCommand) other).toDelete)); // state check
    }
}
