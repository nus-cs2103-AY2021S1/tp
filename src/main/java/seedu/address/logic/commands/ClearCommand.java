package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.results.CommandResult;
import seedu.address.model.inventorymodel.InventoryBook;
import seedu.address.model.inventorymodel.InventoryModel;

/**
 * Clears the inventory book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Inventory book has been cleared!";


    @Override
    public CommandResult execute(InventoryModel inventoryModel) {
        requireNonNull(inventoryModel);
        inventoryModel.setInventoryBook(new InventoryBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
