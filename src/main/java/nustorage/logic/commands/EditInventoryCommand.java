package nustorage.logic.commands;

import static java.util.Objects.requireNonNull;
import static nustorage.logic.parser.CliSyntax.PREFIX_DATETIME;
import static nustorage.logic.parser.CliSyntax.PREFIX_ITEM_DESCRIPTION;
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

/**
 * Edits the details of an existing item in the InventoryWindow
 */
public class EditInventoryCommand extends Command {
    public static final String COMMAND_WORD = "edit_inventory";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the Item specified "
            + "by the index number used in the displayed inventory. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_QUANTITY + "QUANTITY] "
            + "[" + PREFIX_ITEM_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_DATETIME + "[DATE] [TIME]] ";

    public static final String MESSAGE_EDIT_INVENTORY_SUCCESS = "Edited Inventory record: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_INVENTORY_RECORD = "This inventory record already "
            + "exists in the InventoryWindow";

    private final Index index;
    private final EditInventoryDescriptor editInventoryDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editInventoryDescriptor details to edit the person with
     */
    public EditInventoryCommand(Index index, EditInventoryDescriptor editInventoryDescriptor) {
        requireNonNull(index);
        requireNonNull(editInventoryDescriptor);

        this.index = index;
        this.editInventoryDescriptor = new EditInventoryDescriptor(editInventoryDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<InventoryRecord> lastShownList = model.getFilteredInventory();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_INVENTORY_DISPLAYED_INDEX);
        }

        InventoryRecord inventoryRecordToEdit = lastShownList.get(index.getZeroBased());
        InventoryRecord editedInventoryRecord = createEditedInventoryRecord(
                inventoryRecordToEdit, editInventoryDescriptor);
        if (!inventoryRecordToEdit.equals(editedInventoryRecord)
                && model.hasInventoryRecord(editedInventoryRecord)) {
            throw new CommandException(MESSAGE_DUPLICATE_INVENTORY_RECORD);
        }
        FinanceRecord financeRecordToEdit = model.getFinanceRecord(inventoryRecordToEdit);
        FinanceRecord editedFinanceRecord = new FinanceRecord(
                editedInventoryRecord.getUnitCost() * editedInventoryRecord.getQuantity(),
                editedInventoryRecord.getDateTime(), true);
        editedInventoryRecord.setFinanceRecord(editedFinanceRecord);
        editedInventoryRecord.setFinanceRecord(editedFinanceRecord);
        model.setInventoryRecord(inventoryRecordToEdit, editedInventoryRecord);
        model.setFinanceRecord(financeRecordToEdit, editedFinanceRecord);

        model.updateFilteredInventoryList(PREDICATE_SHOW_ALL_INVENTORY);
        return new CommandResult(String.format(MESSAGE_EDIT_INVENTORY_SUCCESS, editedInventoryRecord));
    }

    /**
     * Creates and returns a {@code InventoryRecord} with the details of {@code inventoryRecord}
     * edited with {@code editPersonDescriptor}.
     */
    private static InventoryRecord createEditedInventoryRecord(
            InventoryRecord inventoryRecord, EditInventoryDescriptor editInventoryDescriptor) {
        assert inventoryRecord != null;

        Integer updatedQuantity = editInventoryDescriptor.getQuantity().orElse(inventoryRecord.getQuantity());
        String updatedDescription = editInventoryDescriptor.getDescription().orElse(inventoryRecord.getItemName());
        LocalDateTime dateTime = editInventoryDescriptor.getDateTime().orElse(inventoryRecord.getDateTime());
        Double unitCost = editInventoryDescriptor.getUnitCost().orElse(inventoryRecord.getUnitCost());

        return new InventoryRecord(updatedDescription, updatedQuantity, unitCost, dateTime);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditInventoryCommand)) {
            return false;
        }

        // state check
        EditInventoryCommand e = (EditInventoryCommand) other;
        return index.equals(e.index)
                && editInventoryDescriptor.equals(e.editInventoryDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditInventoryDescriptor {
        private Integer quantity;
        private String description;
        private LocalDateTime dateTime;
        private Double unitCost;


        public EditInventoryDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditInventoryDescriptor(EditInventoryDescriptor toCopy) {
            setQuantity(toCopy.quantity);
            setDescription(toCopy.description);
            setDateTime(toCopy.dateTime);
            setUnitCost(toCopy.unitCost);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(quantity, description);
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public Optional<Integer> getQuantity() {
            return Optional.ofNullable(quantity);
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Optional<String> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
        }

        public Optional<LocalDateTime> getDateTime() {
            return Optional.ofNullable(dateTime);
        }

        public void setUnitCost(Double unitCost) {
            this.unitCost = unitCost;
        }

        public Optional<Double> getUnitCost() {
            return Optional.ofNullable(unitCost);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditInventoryDescriptor)) {
                return false;
            }

            // state check
            EditInventoryDescriptor e = (EditInventoryDescriptor) other;

            return getQuantity().equals(e.getQuantity())
                    && getDescription().equals(e.getDescription());
        }
    }
}

