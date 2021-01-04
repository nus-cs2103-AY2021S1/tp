package seedu.homerce.logic.commands.expense;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.commons.core.Messages.MESSAGE_NOT_EDITED;
import static seedu.homerce.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.homerce.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.homerce.logic.commands.expense.ExpenseCommandTestUtil.DESC_AIRCON;
import static seedu.homerce.logic.commands.expense.ExpenseCommandTestUtil.DESC_CONDITIONER;
import static seedu.homerce.logic.commands.expense.ExpenseCommandTestUtil.VALID_DATE_DECEMBER;
import static seedu.homerce.logic.commands.expense.ExpenseCommandTestUtil.VALID_DESCRIPTION_CONDITIONER;
import static seedu.homerce.logic.commands.expense.ExpenseCommandTestUtil.VALID_ISFIXED_CONDITIONER;
import static seedu.homerce.logic.commands.expense.ExpenseCommandTestUtil.VALID_TAG_HAIRSUPPLIES;
import static seedu.homerce.logic.commands.expense.ExpenseCommandTestUtil.VALID_VALUE_CONDITIONER;
import static seedu.homerce.testutil.expense.TypicalExpenseIndexes.INDEX_FIRST_EXPENSE;
import static seedu.homerce.testutil.expense.TypicalExpenseIndexes.INDEX_SECOND_EXPENSE;
import static seedu.homerce.testutil.expense.TypicalExpenses.getTypicalExpenseTracker;

import org.junit.jupiter.api.Test;

import seedu.homerce.commons.core.index.Index;
import seedu.homerce.model.Model;
import seedu.homerce.model.ModelManager;
import seedu.homerce.model.UserPrefs;
import seedu.homerce.model.expense.Expense;
import seedu.homerce.model.manager.AppointmentManager;
import seedu.homerce.model.manager.ClientManager;
import seedu.homerce.model.manager.ExpenseTracker;
import seedu.homerce.model.manager.RevenueTracker;
import seedu.homerce.model.manager.ServiceManager;
import seedu.homerce.testutil.expense.EditExpenseDescriptorBuilder;
import seedu.homerce.testutil.expense.ExpenseBuilder;

public class EditExpenseCommandTest {

    private Model model = new ModelManager(new UserPrefs(), new ClientManager(), new ServiceManager(),
            new RevenueTracker(), getTypicalExpenseTracker(), new AppointmentManager());

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastExpense = Index.fromOneBased(model.getFilteredExpenseList().size());
        Expense lastExpense = model.getFilteredExpenseList().get(indexLastExpense.getZeroBased());

        ExpenseBuilder expenseInList = new ExpenseBuilder(lastExpense);
        Expense editedExpense = expenseInList.withDescription(VALID_DESCRIPTION_CONDITIONER)
                .withIsFixed(VALID_ISFIXED_CONDITIONER).withAmount(VALID_VALUE_CONDITIONER)
                .withDate(VALID_DATE_DECEMBER).withTag(VALID_TAG_HAIRSUPPLIES).build();

        EditExpenseCommand.EditExpenseDescriptor descriptor = new EditExpenseDescriptorBuilder()
                .withDescription(VALID_DESCRIPTION_CONDITIONER).withIsFixed(VALID_ISFIXED_CONDITIONER)
                .withAmount(VALID_VALUE_CONDITIONER).withDate(VALID_DATE_DECEMBER)
                .withTag(VALID_TAG_HAIRSUPPLIES).build();
        EditExpenseCommand editExpenseCommand = new EditExpenseCommand(indexLastExpense, descriptor);

        String expectedMessage = String.format(EditExpenseCommand.MESSAGE_EDIT_EXPENSE_SUCCESS, editedExpense);

        Model expectedModel = new ModelManager(new UserPrefs(), new ClientManager(),
                new ServiceManager(), new RevenueTracker(), new ExpenseTracker(model.getExpenseTracker()),
                new AppointmentManager());
        expectedModel.setExpense(lastExpense, editedExpense);

        assertCommandSuccess(editExpenseCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        final EditExpenseCommand standardCommand = new EditExpenseCommand(INDEX_FIRST_EXPENSE, DESC_CONDITIONER);

        // same values -> returns true
        EditExpenseCommand.EditExpenseDescriptor copyDescriptor =
                new EditExpenseCommand.EditExpenseDescriptor(DESC_CONDITIONER);
        EditExpenseCommand commandWithSameValues = new EditExpenseCommand(INDEX_FIRST_EXPENSE, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearExpenseCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditExpenseCommand(INDEX_SECOND_EXPENSE, DESC_AIRCON)));
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditExpenseCommand editExpenseCommand = new EditExpenseCommand(INDEX_FIRST_EXPENSE,
            new EditExpenseCommand.EditExpenseDescriptor());
        assertCommandFailure(editExpenseCommand, model, MESSAGE_NOT_EDITED);
    }
}
