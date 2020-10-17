package seedu.address.logic.commands.itemcommand;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.deliverymodel.DeliveryModel;
import seedu.address.model.inventorymodel.InventoryBook;
import seedu.address.model.inventorymodel.InventoryModel;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Clears the inventory book.
 */
public class ItemClearCommand extends ItemCommand {

    public static final String COMMAND_WORD = "clear-i";
    public static final String MESSAGE_SUCCESS = "Inventory book has been cleared!";


    @Override
    public CommandResult execute(InventoryModel inventoryModel, DeliveryModel deliveryModel) {
        requireAllNonNull(inventoryModel, deliveryModel);

        inventoryModel.setInventoryBook(new InventoryBook());
        inventoryModel.commit();
        deliveryModel.commit();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
