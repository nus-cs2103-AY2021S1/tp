package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.recipe.Recipe;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class GetEditRecipeCommand extends Command {
    public static final String COMMAND_WORD = "editR";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Permits edit of the recipe identified by the index number used in the displayed recipe list.\n"
            + "Parameters: " + "INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_RECIPE_SUCCESS = "Recipe to edit shown: %1$s";

    private final Index toEdit;

    public GetEditRecipeCommand(Index toEdit) {
        this.toEdit = toEdit;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Recipe> lastShownList = model.getFilteredRecipeList();

        if (toEdit.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
        }

        Recipe recipeToEdit = lastShownList.get(toEdit.getZeroBased());
        model.setCommandBox(recipeToEdit.stringify());
        return new CommandResult(String.format(MESSAGE_DELETE_RECIPE_SUCCESS, recipeToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteRecipeCommand // instanceof handles nulls
                && toDelete.equals(((DeleteRecipeCommand) other).toDelete)); // state check
    }

}
