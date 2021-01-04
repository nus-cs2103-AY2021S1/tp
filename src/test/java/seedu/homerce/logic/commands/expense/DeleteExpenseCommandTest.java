package seedu.homerce.logic.commands.expense;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.homerce.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.homerce.testutil.expense.TypicalExpenseIndexes.INDEX_FIRST_EXPENSE;
import static seedu.homerce.testutil.expense.TypicalExpenseIndexes.INDEX_SECOND_EXPENSE;
import static seedu.homerce.testutil.expense.TypicalExpenses.getTypicalExpenseTracker;

import org.junit.jupiter.api.Test;

import seedu.homerce.commons.core.Messages;
import seedu.homerce.commons.core.index.Index;
import seedu.homerce.model.Model;
import seedu.homerce.model.ModelManager;
import seedu.homerce.model.UserPrefs;
import seedu.homerce.model.expense.Expense;
import seedu.homerce.model.manager.AppointmentManager;
import seedu.homerce.model.manager.ClientManager;
import seedu.homerce.model.manager.RevenueTracker;
import seedu.homerce.model.manager.ServiceManager;

public class DeleteExpenseCommandTest {
    private Model model = new ModelManager(new UserPrefs(), new ClientManager(), new ServiceManager(),
            new RevenueTracker(), getTypicalExpenseTracker(), new AppointmentManager());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Expense expenseToDelete = model.getFilteredExpenseList().get(INDEX_FIRST_EXPENSE.getZeroBased());
        DeleteExpenseCommand deleteExpenseCommand = new DeleteExpenseCommand(INDEX_FIRST_EXPENSE);

        String expectedMessage = String.format(DeleteExpenseCommand.MESSAGE_DELETE_EXPENSE_SUCCESS, expenseToDelete);

        ModelManager expectedModel = new ModelManager(new UserPrefs(), new ClientManager(), new ServiceManager(),
                new RevenueTracker(), model.getExpenseTracker(), new AppointmentManager());
        expectedModel.deleteExpense(expenseToDelete);

        assertCommandSuccess(deleteExpenseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredExpenseList().size() + 1);
        DeleteExpenseCommand deleteExpenseCommand = new DeleteExpenseCommand(outOfBoundIndex);

        assertCommandFailure(deleteExpenseCommand, model, Messages.MESSAGE_INVALID_EXPENSE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteExpenseCommand deleteFirstCommand = new DeleteExpenseCommand(INDEX_FIRST_EXPENSE);
        DeleteExpenseCommand deleteSecondCommand = new DeleteExpenseCommand(INDEX_SECOND_EXPENSE);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteExpenseCommand deleteFirstCommandCopy = new DeleteExpenseCommand(INDEX_FIRST_EXPENSE);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different client -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
}
