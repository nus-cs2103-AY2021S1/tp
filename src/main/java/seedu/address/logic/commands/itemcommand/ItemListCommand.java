package seedu.address.logic.commands.itemcommand;

import static seedu.address.model.inventorymodel.InventoryModel.PREDICATE_SHOW_ALL_ITEMS;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.inventorymodel.InventoryModel;

/**
 * Lists all items in the inventory book to the user.
 */
public class ItemListCommand extends ItemCommand {

    public static final String COMMAND_WORD = "list-i";

    public static final String MESSAGE_SUCCESS = "Listed all items";


    @Override
    public CommandResult execute(List<Model> models) throws CommandException {
        InventoryModel inventoryModel = getInventoryModel(models);

        inventoryModel.updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
