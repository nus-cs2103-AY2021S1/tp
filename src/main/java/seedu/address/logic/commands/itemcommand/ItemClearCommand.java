package seedu.address.logic.commands.itemcommand;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.deliverymodel.DeliveryModel;
import seedu.address.model.inventorymodel.InventoryBook;
import seedu.address.model.inventorymodel.InventoryModel;

/**
 * Clears the inventory book.
 */
public class ItemClearCommand extends ItemCommand {

    public static final String COMMAND_WORD = "clear-i";
    public static final String MESSAGE_SUCCESS = "Inventory book has been cleared!";


    @Override
    public CommandResult execute(List<Model> models) throws CommandException {
        InventoryModel inventoryModel = getInventoryModel(models);
        DeliveryModel deliveryModel = getDeliveryModel(models);

        inventoryModel.commit();
        deliveryModel.commit();

        inventoryModel.setInventoryBook(new InventoryBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
