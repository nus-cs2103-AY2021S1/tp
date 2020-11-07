package seedu.simplykitchen.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.simplykitchen.model.Model.PREDICATE_SHOW_ALL_FOODS;

import seedu.simplykitchen.logic.commands.exceptions.CommandException;
import seedu.simplykitchen.model.Model;

/**
 * Reverts the {@code model}'s food inventory to its previous state.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_SUCCESS = "Undo success!";
    public static final String MESSAGE_FAILURE = "No more commands to undo!";

    //Solution below adapted from https://github.com/se-edu/addressbook-level4.
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        assert model != null : model;

        if (!model.canUndoFoodInventory()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        model.undoFoodInventory();
        model.updateFilteredFoodList(PREDICATE_SHOW_ALL_FOODS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
