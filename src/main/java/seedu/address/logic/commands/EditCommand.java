package seedu.address.logic.commands;
import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EXPENSES;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.expense.Amount;
import seedu.address.model.expense.Category;
import seedu.address.model.expense.Date;
import seedu.address.model.expense.Description;
import seedu.address.model.expense.Expense;

public class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the expense identified by the index number.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_EXPENSE = "This expense already exists in the expense book.";
    public static final String SUCCESSFUL_MESSAGE = "Expense edited successfully.";

    private final Index index;
    private final EditExpenseDescriptor editExpenseDescriptor;

    /**
     * @param index of the expense in the filtered expense list to edit
     * @param editExpenseDescriptor details to edit the expense with
     */
    public EditCommand(Index index, EditExpenseDescriptor editExpenseDescriptor) {
        requireNonNull(index);
        requireNonNull(editExpenseDescriptor);

        this.index = index;
        this.editExpenseDescriptor = new EditExpenseDescriptor(editExpenseDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Expense> lastShownList = model.getFilteredExpenseList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EXPENSE_DISPLAYED_INDEX);
        }

        Expense expenseToEdit = lastShownList.get(index.getZeroBased());
        Expense editedExpense = createEditedExpense(expenseToEdit, editExpenseDescriptor);

        if (!expenseToEdit.isSameExpense(editedExpense) && model.hasExpense(editedExpense)) {
            throw new CommandException(MESSAGE_DUPLICATE_EXPENSE);
        }

        model.setExpense(expenseToEdit, editedExpense);
        model.updateFilteredExpenseList(PREDICATE_SHOW_ALL_EXPENSES);
        return new CommandResult(String.format(SUCCESSFUL_MESSAGE, editedExpense));
    }

    @Override
    public String toString() {
        return editExpenseDescriptor.toString();
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Expense createEditedExpense(Expense expenseToEdit, EditExpenseDescriptor editExpenseDescriptor) {
        assert expenseToEdit != null;

        Amount updatedAmount = editExpenseDescriptor.getAmount().orElse(expenseToEdit.getAmount());
        Category updatedCategory = editExpenseDescriptor.getCategory().orElse(expenseToEdit.getCategory());
        Date updatedDate = editExpenseDescriptor.getDate().orElse(expenseToEdit.getDate());
        Description updatedDescription = editExpenseDescriptor.getDescription().orElse(expenseToEdit.getDescription());

        return new Expense(updatedAmount, updatedDate, updatedCategory, updatedDescription);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditExpenseDescriptor {
        private Amount amount;
        private Category category;
        private Date date;
        private Description description;

        public EditExpenseDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditExpenseDescriptor(EditExpenseDescriptor toCopy) {
            setAmount(toCopy.amount);
            setCategory(toCopy.category);
            setDate(toCopy.date);
            setDescription(toCopy.description);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(amount, category, date, description);
        }

        public void setAmount(Amount amount) {
            this.amount = amount;
        }

        public Optional<Amount> getAmount() {
            return Optional.ofNullable(amount);
        }

        public void setCategory(Category category) {
            this.category = category;
        }

        public Optional<Category> getCategory() {
            return Optional.ofNullable(category);
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Optional<Date> getDate() {
            return Optional.ofNullable(date);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditExpenseDescriptor)) {
                return false;
            }

            // state check
            EditExpenseDescriptor e = (EditExpenseDescriptor) other;

            return getAmount().equals(e.getAmount())
                    && getCategory().equals(e.getCategory())
                    && getDate().equals(e.getDate())
                    && getDescription().equals(e.getDescription());
        }

        @Override
        public String toString() {
            return "The amount is: " + this.amount + "\n"
                    + "The Date is: " + this.date + "\n"
                    + "The Category is: " + this.category + "\n"
                    + "The Description is: " + this.description + "\n";
        }
    }
}
