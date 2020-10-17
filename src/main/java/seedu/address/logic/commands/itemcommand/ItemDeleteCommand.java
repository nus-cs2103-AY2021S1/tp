package seedu.address.logic.commands.itemcommand;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.deliverymodel.DeliveryModel;
import seedu.address.model.inventorymodel.InventoryModel;
import seedu.address.model.item.Item;

/**
 * Deletes a item identified using it's displayed index from the inventory book.
 */
public class ItemDeleteCommand extends ItemCommand {

    public static final String COMMAND_WORD = "delete-i";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the item identified by the index number used in the displayed item list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_ITEM_SUCCESS = "Deleted Item: %1$s";

    private final Index targetIndex;

    public ItemDeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(List<Model> models) throws CommandException {
        InventoryModel inventoryModel = getInventoryModel(models);
        DeliveryModel deliveryModel = getDeliveryModel(models);
        List<Item> lastShownList = inventoryModel.getFilteredItemList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ITEM_DISPLAYED_INDEX);
        }

        inventoryModel.commit();
        deliveryModel.commit();

        Item itemToDelete = lastShownList.get(targetIndex.getZeroBased());
        inventoryModel.deleteItem(itemToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_ITEM_SUCCESS, itemToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ItemDeleteCommand // instanceof handles nulls
                && targetIndex.equals(((ItemDeleteCommand) other).targetIndex)); // state check
    }
}
