package ay2021s1_cs2103_w16_3.finesse.logic.commands.bookmark;

import static ay2021s1_cs2103_w16_3.finesse.commons.core.Messages.MESSAGE_INVALID_BOOKMARK_EXPENSE_DISPLAYED_INDEX;
import static ay2021s1_cs2103_w16_3.finesse.model.Model.PREDICATE_SHOW_ALL_BOOKMARK_EXPENSES;
import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import ay2021s1_cs2103_w16_3.finesse.logic.commands.CommandResult;
import ay2021s1_cs2103_w16_3.finesse.logic.commands.exceptions.CommandException;
import ay2021s1_cs2103_w16_3.finesse.model.Model;
import ay2021s1_cs2103_w16_3.finesse.model.bookmark.BookmarkExpense;
import ay2021s1_cs2103_w16_3.finesse.model.bookmark.exceptions.DuplicateBookmarkTransactionException;
import ay2021s1_cs2103_w16_3.finesse.model.category.Category;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Amount;
import ay2021s1_cs2103_w16_3.finesse.model.transaction.Title;

/**
 * Edits the details of an existing bookmark expense using its displayed index from the bookmark expense list
 * in the expense tab.
 */
public class EditBookmarkExpenseCommand extends EditBookmarkCommand {

    public static final String MESSAGE_EDIT_BOOKMARK_EXPENSE_SUCCESS = "Edited Bookmark Expense: %1$s";

    public EditBookmarkExpenseCommand(EditBookmarkCommand superCommand) {
        super(superCommand.getTargetIndex(), superCommand.getEditBookmarkTransactionDescriptor());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<BookmarkExpense> lastShownList = model.getFilteredBookmarkExpenseList();

        if (getTargetIndex().getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_BOOKMARK_EXPENSE_DISPLAYED_INDEX);
        }

        BookmarkExpense bookmarkExpenseToEdit = lastShownList.get(getTargetIndex().getZeroBased());
        BookmarkExpense editedBookmarkExpense = createEditedBookmarkExpense(bookmarkExpenseToEdit,
                getEditBookmarkTransactionDescriptor());
        try {
            model.setBookmarkExpense(bookmarkExpenseToEdit, editedBookmarkExpense);
        } catch (DuplicateBookmarkTransactionException e) {
            throw new CommandException(e.getMessage());
        }
        model.updateFilteredBookmarkExpenseList(PREDICATE_SHOW_ALL_BOOKMARK_EXPENSES);
        return new CommandResult(String.format(MESSAGE_EDIT_BOOKMARK_EXPENSE_SUCCESS, editedBookmarkExpense));
    }

    /**
     * Creates and returns a {@code BookmarkExpense} with the details of {@code bookmarkExpenseToEdit}
     * edited with {@code editBookmarkExpenseDescriptor}.
     */
    private static BookmarkExpense createEditedBookmarkExpense(BookmarkExpense bookmarkExpenseToEdit,
                                                               EditBookmarkTransactionDescriptor
                                                       editBookmarkExpenseDescriptor) {
        assert bookmarkExpenseToEdit != null;

        Title updatedTitle = editBookmarkExpenseDescriptor.getTitle().orElse(bookmarkExpenseToEdit.getTitle());
        Amount updatedAmount = editBookmarkExpenseDescriptor.getAmount().orElse(bookmarkExpenseToEdit.getAmount());
        Set<Category> updatedCategories = editBookmarkExpenseDescriptor.getCategories()
                .orElse(bookmarkExpenseToEdit.getCategories());

        return new BookmarkExpense(updatedTitle, updatedAmount, updatedCategories);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditBookmarkExpenseCommand)) {
            return false;
        }

        // state check
        EditBookmarkExpenseCommand otherEditBookmarkExpenseCommand = (EditBookmarkExpenseCommand) other;
        return getTargetIndex().equals(otherEditBookmarkExpenseCommand.getTargetIndex())
                && getEditBookmarkTransactionDescriptor()
                .equals(otherEditBookmarkExpenseCommand.getEditBookmarkTransactionDescriptor());
    }

}
