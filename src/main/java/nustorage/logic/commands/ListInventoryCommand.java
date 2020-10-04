package nustorage.logic.commands;

import static java.util.Objects.requireNonNull;
import static nustorage.model.Model.PREDICATE_SHOW_ALL_INVENTORY;

import javafx.collections.ObservableList;
import nustorage.model.Model;
import nustorage.model.record.InventoryRecord;

/**
 * Lists all persons in the address book to the user.
 */
public class ListInventoryCommand extends Command {

    public static final String COMMAND_WORD = "list_inventory";

    public static final String MESSAGE_SUCCESS = "Listed all inventory items!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredInventoryList(PREDICATE_SHOW_ALL_INVENTORY);
        ObservableList<InventoryRecord> inventory = model.getFilteredInventory();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
