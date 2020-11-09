package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.ui.DisplayedInventoryType;

/**
 * Lists all items in the inventory to the user.
 */
public class ListItemCommand extends Command {

    public static final String COMMAND_WORD = "listi";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " :lists all items";

    public static final String MESSAGE_SUCCESS = "Listed all items";
    public static final String MESSAGE_NO_ITEMS = "You have no items in your inventory now.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        if (model.getFilteredItemList().isEmpty()) {
            return new CommandResult(MESSAGE_NO_ITEMS, false, false, DisplayedInventoryType.ITEMS);
        } else {
            return new CommandResult(MESSAGE_SUCCESS, false, false, DisplayedInventoryType.ITEMS);
        }
    }
}
