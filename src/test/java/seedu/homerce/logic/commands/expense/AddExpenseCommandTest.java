package seedu.homerce.logic.commands.expense;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.homerce.logic.commands.CommandResult;
import seedu.homerce.model.expense.Expense;
import seedu.homerce.model.manager.ExpenseTracker;
import seedu.homerce.model.manager.HistoryManager;
import seedu.homerce.model.manager.ReadOnlyExpenseTracker;
import seedu.homerce.testutil.ModelStub;
import seedu.homerce.testutil.expense.ExpenseBuilder;

public class AddExpenseCommandTest {

    @Test
    public void constructor_nullExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddExpenseCommand(null));
    }

    @Test
    public void constructor_nullModel_throwsNullPointerException() {
        Expense conditioner = new ExpenseBuilder().withDescription("Conditioner").build();
        AddExpenseCommand addConditionerCommand = new AddExpenseCommand(conditioner);
        assertThrows(NullPointerException.class, () -> addConditionerCommand.execute(null, null));
    }

    @Test
    public void execute_expenseAcceptedByModel_addSuccessful() {
        AddExpenseCommandTest.ModelStubAcceptingExpenseAdded modelStub =
            new ModelStubAcceptingExpenseAdded();
        Expense validExpense = new ExpenseBuilder().build();

        CommandResult commandResult = new AddExpenseCommand(validExpense).execute(modelStub,
                HistoryManager.getInstance());

        assertEquals(String.format(AddExpenseCommand.MESSAGE_SUCCESS, validExpense), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validExpense), modelStub.expensesAdded);
    }

    @Test
    public void equals() {
        Expense conditioner = new ExpenseBuilder().withDescription("Conditioner").build();
        Expense chair = new ExpenseBuilder().withDescription("Chair").build();
        AddExpenseCommand addConditionerCommand = new AddExpenseCommand(conditioner);
        AddExpenseCommand addChairCommand = new AddExpenseCommand(chair);

        // same object -> returns true
        assertTrue(addChairCommand.equals(addChairCommand));

        // different types -> return false
        assertFalse(addConditionerCommand.equals(1));

        // null -> returns false
        assertFalse(addConditionerCommand.equals(null));

        // different expense -> returns false
        assertFalse(addConditionerCommand.equals(addChairCommand));
    }

    /**
     * A Model stub that always accepts the expense being added.
     */
    private static class ModelStubAcceptingExpenseAdded extends ModelStub {

        final ArrayList<Expense> expensesAdded = new ArrayList<>();

        @Override
        public void addExpense(Expense expense) {
            requireNonNull(expense);
            expensesAdded.add(expense);
        }

        @Override
        public ReadOnlyExpenseTracker getExpenseTracker() {
            return new ExpenseTracker();
        }
    }
}

