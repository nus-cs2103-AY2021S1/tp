package seedu.address.logic.commands.ingredient;

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
import seedu.address.model.ingredient.Ingredient;

/**
 * Sets the main window command box to the command of the specified ingredient for editing purposes.
 */
public class GetEditIngredientCommand extends Command {
    public static final String COMMAND_WORD = "editF";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Permits edit of the ingredient identified by the index number used in the displayed "
            + "ingredient list" + ".\n"
            + "Parameters: " + "INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_GET_EDIT_INGREDIENT_SUCCESS = "Ingredient to edit shown: %1$s";

    private static Logger logger = Logger.getLogger("GetEditIngredientLogger");

    private final Index toEdit;

    public GetEditIngredientCommand(Index toEdit) {
        this.toEdit = toEdit;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        logger.log(Level.INFO, "Getting ingredient at position " + toEdit.getOneBased());
        List<Ingredient> lastShownList = model.getFilteredIngredientList();

        if (toEdit.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INGREDIENT_DISPLAYED_INDEX);
        }

        Ingredient ingredientToEdit = lastShownList.get(toEdit.getZeroBased());
        int ingredientPositionToEdit = lastShownList.indexOf(ingredientToEdit) + 1;
        assert(ingredientPositionToEdit > 0);
        CommandResult commandResult =
                new CommandResult(String.format(MESSAGE_GET_EDIT_INGREDIENT_SUCCESS, ingredientToEdit.toString()),
                COMMAND_WORD);
        commandResult.setCommandBox(ingredientToEdit.stringify(ingredientPositionToEdit));
        return commandResult;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GetEditIngredientCommand // instanceof handles nulls
                && toEdit.equals(((GetEditIngredientCommand) other).toEdit)); // state check
    }
}
