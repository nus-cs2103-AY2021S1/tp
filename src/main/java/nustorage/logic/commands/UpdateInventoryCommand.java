package nustorage.logic.commands;

import static java.util.Objects.requireNonNull;
import static nustorage.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static nustorage.model.Model.PREDICATE_SHOW_ALL_INVENTORY;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import nustorage.commons.core.Messages;
import nustorage.commons.core.index.Index;
import nustorage.commons.util.CollectionUtil;
import nustorage.logic.commands.exceptions.CommandException;
import nustorage.model.Model;
import nustorage.model.record.FinanceRecord;
import nustorage.model.record.InventoryRecord;

public class UpdateInventoryCommand extends Command {
    public static final String COMMAND_WORD = "update_inventory";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates the quantity of the item specified "
            + "by the index number used in the displayed inventory. "
            + "Existing values will be updated by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_QUANTITY + "CHANGE IN QUANTITY] ";

    public static final String MESSAGE_DUPLICATE_INVENTORY_RECORD = "Nothing has been updated!";
    public static final String MESSAGE_UPDATE_INVENTORY_SUCCESS = "Updated Item: %1$s";
    public static final String MESSAGE_NOT_UPDATED = "The change in quantity field must be provided.";
    public static final String MESSAGE_INVALID_UPDATE_OPERATION = "You cannot update this item by that quantity!";

    private final Index index;
    private final UpdateInventoryDescriptor updateInventoryDescriptor;

    /**
     * @param index index of the inventory record in the inventory record list to update
     * @param updateInventoryDescriptor details to update the record with
     */
    public UpdateInventoryCommand(Index index, UpdateInventoryDescriptor updateInventoryDescriptor) {
        requireNonNull(index);
        requireNonNull(updateInventoryDescriptor);

        this.index = index;
        this.updateInventoryDescriptor = new UpdateInventoryDescriptor(updateInventoryDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<InventoryRecord> lastShownInventoryList = model.getFilteredInventory();

        if (index.getZeroBased() >= lastShownInventoryList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INVENTORY_DISPLAYED_INDEX);
        }

        InventoryRecord inventoryRecordToUpdate = lastShownInventoryList.get(index.getZeroBased());
        InventoryRecord updatedInventoryRecord;
        try {
            updatedInventoryRecord = createUpdatedInventoryRecord(inventoryRecordToUpdate, updateInventoryDescriptor);
            if (!inventoryRecordToUpdate.equals(updatedInventoryRecord)
                    && model.hasInventoryRecord(updatedInventoryRecord)) {
                throw new CommandException(MESSAGE_DUPLICATE_INVENTORY_RECORD);
            }
        } catch (CommandException ce) {
            throw new CommandException(MESSAGE_INVALID_UPDATE_OPERATION);
        }


        FinanceRecord oldFinanceRecord = model.getFinanceRecord(inventoryRecordToUpdate.getFinanceId());
        double cost = inventoryRecordToUpdate.getUnitCost();
        FinanceRecord newFinanceRecord = new FinanceRecord(updatedInventoryRecord.getQuantity() * cost);
        updatedInventoryRecord.setFinanceRecord(newFinanceRecord);
        model.setFinanceRecord(oldFinanceRecord, newFinanceRecord);
        model.setInventoryRecord(inventoryRecordToUpdate, updatedInventoryRecord);
        model.updateFilteredInventoryList(PREDICATE_SHOW_ALL_INVENTORY);
        return new CommandResult(String.format(MESSAGE_UPDATE_INVENTORY_SUCCESS, updatedInventoryRecord));
    }

    private static InventoryRecord createUpdatedInventoryRecord(
            InventoryRecord inventoryRecord, UpdateInventoryDescriptor updateInventoryDescriptor)
            throws CommandException {
        assert inventoryRecord != null;

        Integer updateQuantityBy = updateInventoryDescriptor.getChangeInQuantity().orElse(0);
        Integer oldQuantity = inventoryRecord.getQuantity();
        Integer newQuantity = updateQuantityBy + oldQuantity;
        Double unitCost = inventoryRecord.getUnitCost();

        if (newQuantity < 0) {
            throw new CommandException(MESSAGE_INVALID_UPDATE_OPERATION);
        }

        String itemDescription = inventoryRecord.getItemName();
        LocalDateTime newDateTime = LocalDateTime.now();

        return new InventoryRecord(itemDescription, newQuantity, unitCost, newDateTime);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class UpdateInventoryDescriptor {
        private Integer changeInQuantity;

        public UpdateInventoryDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public UpdateInventoryDescriptor(UpdateInventoryDescriptor toCopy) {
            setChangeInQuantity(toCopy.changeInQuantity);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(changeInQuantity);
        }

        public void setChangeInQuantity(int quantity) {
            this.changeInQuantity = quantity;
        }

        public Optional<Integer> getChangeInQuantity() {
            return Optional.ofNullable(changeInQuantity);
        }


        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditInventoryCommand.EditInventoryDescriptor)) {
                return false;
            }

            // state check
            EditInventoryCommand.EditInventoryDescriptor e = (EditInventoryCommand.EditInventoryDescriptor) other;

            return getChangeInQuantity().equals(e.getQuantity());
        }
    }
}
