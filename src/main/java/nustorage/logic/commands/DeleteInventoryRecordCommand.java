package nustorage.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import nustorage.commons.core.Messages;
import nustorage.commons.core.index.Index;
import nustorage.logic.commands.exceptions.CommandException;
import nustorage.model.Model;
import nustorage.model.record.FinanceRecord;
import nustorage.model.record.InventoryRecord;

public class DeleteInventoryRecordCommand extends Command {

    public static final String COMMAND_WORD = "delete_inventory";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the inventory record identified by the index number used in Inventory.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_INVENTORY_RECORD_SUCCESS = "Deleted InventoryRecord: %1$s";

    private final Index targetIndex;

    public DeleteInventoryRecordCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        assert !model.getFilteredInventory().isEmpty();
        List<InventoryRecord> lastShownList = model.getFilteredInventory();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        InventoryRecord inventoryRecordToDelete = lastShownList.get(targetIndex.getZeroBased());
        FinanceRecord financeRecordToDelete = model.getFinanceRecord(inventoryRecordToDelete);
        model.deleteFinanceRecord(financeRecordToDelete);
        model.deleteInventoryRecord(inventoryRecordToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_INVENTORY_RECORD_SUCCESS, inventoryRecordToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteInventoryRecordCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteInventoryRecordCommand) other).targetIndex)); // state check
    }
}
