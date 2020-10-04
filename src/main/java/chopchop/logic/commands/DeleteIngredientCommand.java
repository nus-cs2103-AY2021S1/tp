package chopchop.logic.commands;

import chopchop.commons.core.Messages;
import chopchop.commons.core.index.Index;
import chopchop.logic.commands.exceptions.CommandException;
import chopchop.model.Model;
import chopchop.model.ingredient.Ingredient;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Deletes an ingredient identified using it's displayed index from the ingredient book.
 */
public class DeleteIngredientCommand extends DeleteCommand {

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the ingredient identified by the index number used in the displayed recipe list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_INGREDIENT_SUCCESS = "Deleted Ingredient: %1$s";

    public DeleteIngredientCommand(Index targetIndex) {
        super(targetIndex);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Ingredient> lastShownList = model.getFilteredIngredientList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INGREDIENT_DISPLAYED_INDEX);
        }

        Ingredient ingredientToDelete = lastShownList.get(super.targetIndex.getZeroBased());
        model.deleteIngredient(ingredientToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_INGREDIENT_SUCCESS, ingredientToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteIngredientCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteIngredientCommand) other).targetIndex)); // state check
    }
}
