package seedu.expense.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.expense.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.expense.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.expense.logic.commands.CommandTestUtil.showExpenseAtIndex;
import static seedu.expense.testutil.TypicalExpenses.getTypicalExpenseBook;
import static seedu.expense.testutil.TypicalIndexes.INDEX_FIRST_EXPENSE;
import static seedu.expense.testutil.TypicalIndexes.INDEX_SECOND_EXPENSE;

import org.junit.jupiter.api.Test;

import seedu.expense.commons.core.Messages;
import seedu.expense.commons.core.index.Index;
import seedu.expense.model.Model;
import seedu.expense.model.ModelManager;
import seedu.expense.model.UserPrefs;
import seedu.expense.model.alias.AliasMap;
import seedu.expense.model.expense.Expense;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalExpenseBook(), new UserPrefs(), new AliasMap());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Expense expenseToDelete = model.getFilteredExpenseList().get(INDEX_FIRST_EXPENSE.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_EXPENSE);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_EXPENSE_SUCCESS, expenseToDelete);

        ModelManager expectedModel = new ModelManager(model.getExpenseBook(), new UserPrefs(), new AliasMap());
        expectedModel.deleteExpense(expenseToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredExpenseList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_EXPENSE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showExpenseAtIndex(model, INDEX_FIRST_EXPENSE);

        Expense expenseToDelete = model.getFilteredExpenseList().get(INDEX_FIRST_EXPENSE.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_EXPENSE);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_EXPENSE_SUCCESS, expenseToDelete);

        Model expectedModel = new ModelManager(model.getExpenseBook(), new UserPrefs(), new AliasMap());
        expectedModel.deleteExpense(expenseToDelete);
        showNoExpense(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showExpenseAtIndex(model, INDEX_FIRST_EXPENSE);

        Index outOfBoundIndex = INDEX_SECOND_EXPENSE;
        // ensures that outOfBoundIndex is still in bounds of expense book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getExpenseBook().getExpenseList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_EXPENSE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_EXPENSE);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_EXPENSE);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_EXPENSE);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different expense -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoExpense(Model model) {
        model.updateFilteredExpenseList(p -> false);

        assertTrue(model.getFilteredExpenseList().isEmpty());
    }
}
