package seedu.cc.logic.commands.entrylevel;

import static java.util.Objects.requireNonNull;
import static seedu.cc.logic.parser.util.CliSyntax.PREFIX_AMOUNT;
import static seedu.cc.logic.parser.util.CliSyntax.PREFIX_CATEGORY;
import static seedu.cc.logic.parser.util.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.cc.logic.parser.util.CliSyntax.PREFIX_TAG;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.cc.commons.core.Messages;
import seedu.cc.commons.core.category.Category;
import seedu.cc.commons.core.index.Index;
import seedu.cc.commons.util.CollectionUtil;
import seedu.cc.logic.commands.Command;
import seedu.cc.logic.commands.CommandResult;
import seedu.cc.logic.commands.CommandResultFactory;
import seedu.cc.logic.commands.exceptions.CommandException;
import seedu.cc.model.Model;
import seedu.cc.model.account.ActiveAccount;
import seedu.cc.model.account.entry.Amount;
import seedu.cc.model.account.entry.Description;
import seedu.cc.model.account.entry.Entry;
import seedu.cc.model.account.entry.Expense;
import seedu.cc.model.account.entry.Revenue;
import seedu.cc.model.tag.Tag;

/**
 * Edits an entry in Common Cents.
 */
public class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the entry identified "
            + "by the index number used in the expense/revenue list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_CATEGORY + "CATEGORY "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + PREFIX_AMOUNT + "AMOUNT] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_CATEGORY + "revenue "
            + PREFIX_DESCRIPTION + "selling clothes "
            + PREFIX_AMOUNT + "20";

    public static final String PREFIXES = PREFIX_CATEGORY + "CATEGORY\n"
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION]\n"
            + "[" + PREFIX_AMOUNT + "AMOUNT]\n";
    public static final String MESSAGE_SUCCESS = "Entry edited!\n%1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    private Index index;
    private EditEntryDescriptor editEntryDescriptor;

    /**
     * Creates an EditAccountCommand to edit the current {@code ActiveAccount}
     */
    public EditCommand(Index index, EditEntryDescriptor editEntryDescriptor) {
        requireNonNull(index);
        this.index = index;
        this.editEntryDescriptor = editEntryDescriptor;
    }

    @Override
    public CommandResult execute(Model model, ActiveAccount activeAccount) throws CommandException {
        requireNonNull(model);
        requireNonNull(activeAccount);

        List<? extends Entry> listToAccess;
        if (editEntryDescriptor.isEntryExpense()) {
            listToAccess = activeAccount.getFilteredExpenseList();

        } else {
            assert editEntryDescriptor.isEntryRevenue();
            listToAccess = activeAccount.getFilteredRevenueList();
        }

        if (index.getZeroBased() >= listToAccess.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DISPLAYED_INDEX);
        }

        Entry entryToEdit = activeAccount.getEntryAtIndex(this.index, listToAccess);
        Entry editedEntry = createEditedEntry(entryToEdit, editEntryDescriptor);

        // Set previous state for undo before entry is edited
        activeAccount.setPreviousState();
        if (entryToEdit instanceof Expense) {
            activeAccount.setExpense((Expense) entryToEdit, (Expense) editedEntry);
        } else {
            assert editedEntry instanceof Revenue;
            activeAccount.setRevenue((Revenue) entryToEdit, (Revenue) editedEntry);
        }

        model.setAccount(activeAccount.getAccount());

        return CommandResultFactory
            .createCommandResultForEntryListChangingCommand(String.format(MESSAGE_SUCCESS, editedEntry));
    }

    private static Entry createEditedEntry(Entry entryToEdit, EditEntryDescriptor editEntryDescriptor) {
        assert entryToEdit != null;

        Description updatedDescription = editEntryDescriptor.getDescription()
                .orElse(entryToEdit.getDescription());
        Amount updatedAmount = editEntryDescriptor.getAmount().orElse(entryToEdit.getAmount());
        Set<Tag> updatedTags = editEntryDescriptor.getTags().orElse(entryToEdit.getTags());

        if (entryToEdit instanceof Revenue) {
            return new Revenue(updatedDescription, updatedAmount, updatedTags);
        } else {
            assert(entryToEdit instanceof Expense);
            return new Expense(updatedDescription, updatedAmount, updatedTags);
        }

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editEntryDescriptor.equals(e.editEntryDescriptor);
    }

    /**
     * Stores the details to edit the entry with. Each non-empty field value will replace the
     * corresponding field value of the entry.
     */
    public static class EditEntryDescriptor {
        private Category category;
        private Description description;
        private Amount amount;
        private Set<Tag> tags;

        public EditEntryDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditEntryDescriptor(EditEntryDescriptor toCopy) {
            setCategory(toCopy.category);
            setDescription(toCopy.description);
            setAmount(toCopy.amount);
            setTags(toCopy.tags);
        }

        public boolean isEntryExpense() {
            return getCategory().isExpense();
        }

        public boolean isEntryRevenue() {
            return getCategory().isRevenue();
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(description, amount, tags);
        }

        public void setCategory(Category category) {
            this.category = category;
        }

        public Category getCategory() {
            return category;
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setAmount(Amount amount) {
            this.amount = amount;
        }

        public Optional<Amount> getAmount() {
            return Optional.ofNullable(amount);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditEntryDescriptor)) {
                return false;
            }

            // state check
            EditEntryDescriptor e = (EditEntryDescriptor) other;

            return getCategory().equals(e.getCategory())
                    && getDescription().equals(e.getDescription())
                    && getAmount().equals(e.getAmount())
                    && getTags().equals(e.getTags());
        }
    }

}


