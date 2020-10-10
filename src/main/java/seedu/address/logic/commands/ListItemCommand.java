package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.ui.View;


/**
 * Lists all items in the inventory to the user.
 */
public class ListItemCommand extends Command {

    public static final String COMMAND_WORD = "listi";

    public static final String MESSAGE_SUCCESS = "Listed all items";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(MESSAGE_SUCCESS, false, false, View.InventoryType.ITEMS);
    }
}
