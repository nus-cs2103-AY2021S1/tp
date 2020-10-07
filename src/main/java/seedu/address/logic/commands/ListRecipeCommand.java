package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.ui.View;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_RECIPES;

/**
 * Lists all recipes in the inventory to the user.
 */
public class ListRecipeCommand extends Command {

    public static final String COMMAND_WORD = "listr";

    public static final String MESSAGE_SUCCESS = "Listed all recipes";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_SUCCESS, false, false, View.InventoryType.RECIPES);
    }
}
