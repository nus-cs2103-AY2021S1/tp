package nustorage.logic.commands;

import static java.util.Objects.requireNonNull;
import static nustorage.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static nustorage.logic.parser.CliSyntax.PREFIX_DATETIME;
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

/**
 * Edits the details of an existing item in the InventoryWindow
 */
public class EditFinanceCommand extends Command {
    public static final String COMMAND_WORD = "edit_finance";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the FinanceWindow Record specified "
            + "by the index number used in the displayed inventory. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_AMOUNT + "AMOUNT] "
            + "[" + PREFIX_DATETIME + "[DATE] [TIME]] ";

    public static final String MESSAGE_EDIT_FINANCE_SUCCESS = "Edited finance record: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    private final Index index;
    private final EditFinanceDescriptor editFinanceDescriptor;

    /**
     * @param index of the finance record in the filtered finance list to edit
     * @param editFinanceDescriptor details to edit the finance record with
     */
    public EditFinanceCommand(Index index, EditFinanceDescriptor editFinanceDescriptor) {
        requireNonNull(index);
        requireNonNull(editFinanceDescriptor);

        this.index = index;
        this.editFinanceDescriptor = new EditFinanceDescriptor(editFinanceDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        if (!editFinanceDescriptor.isAnyFieldEdited()) {
            throw new CommandException(MESSAGE_NOT_EDITED);
        }

        requireNonNull(model);
        List<FinanceRecord> lastShownList = model.getFilteredFinanceList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FINANCE_DISPLAYED_INDEX);
        }

        FinanceRecord financeRecordToEdit = lastShownList.get(index.getZeroBased());

        if (financeRecordToEdit.taggedToInventory()) {
            throw new CommandException(Messages.MESSAGE_FINANCE_HAS_INVENTORY);
        }

        FinanceRecord editedFinanceRecord = createEditedFinanceRecord(
                financeRecordToEdit, editFinanceDescriptor);

        model.setFinanceRecord(financeRecordToEdit, editedFinanceRecord);
        model.updateFilteredInventoryList(PREDICATE_SHOW_ALL_INVENTORY);
        return new CommandResult(String.format(MESSAGE_EDIT_FINANCE_SUCCESS, editedFinanceRecord));
    }

    /**
     * Creates and returns a {@code FinanceRecord} with the details of {@code financeRecord}
     * edited with {@code editFinanceDescriptor}.
     */
    private static FinanceRecord createEditedFinanceRecord(
            FinanceRecord financeRecord, EditFinanceDescriptor editFinanceDescriptor) {
        assert financeRecord != null;

        int updatedId = financeRecord.getID();
        double updatedAmount = editFinanceDescriptor.getAmount().orElse(financeRecord.getAmount());
        LocalDateTime updatedDatetime = editFinanceDescriptor.getDatetime().orElse(financeRecord.getDateTime());
        boolean updatedHasInventory = financeRecord.taggedToInventory();
        return new FinanceRecord(updatedId, updatedAmount, updatedDatetime, updatedHasInventory);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditFinanceCommand)) {
            return false;
        }

        // state check
        EditFinanceCommand e = (EditFinanceCommand) other;
        return index.equals(e.index)
                && editFinanceDescriptor.equals(e.editFinanceDescriptor);
    }

    /**
     * Stores the details to edit the finance record with. Each non-empty field value will replace the
     * corresponding field value of the finance record.
     */
    public static class EditFinanceDescriptor {

        private Integer id;
        private Double amount;
        private LocalDateTime datetime;

        public EditFinanceDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditFinanceDescriptor(EditFinanceDescriptor toCopy) {
            setId(toCopy.id);
            setAmount(toCopy.amount);
            setDatetime(toCopy.datetime);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(amount, datetime);
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Optional<Integer> getId() {
            return Optional.ofNullable(id);
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }

        public Optional<Double> getAmount() {
            return Optional.ofNullable(amount);
        }

        public void setDatetime(LocalDateTime datetime) {
            this.datetime = datetime;
        }

        public Optional<LocalDateTime> getDatetime() {
            return Optional.ofNullable(datetime);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditFinanceDescriptor)) {
                return false;
            }

            // state check
            EditFinanceDescriptor e = (EditFinanceDescriptor) other;

            return getAmount().equals(e.getAmount())
                    && getDatetime().equals(e.getDatetime());
        }
    }
}

