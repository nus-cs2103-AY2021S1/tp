package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.account.ActiveAccount;
import seedu.address.model.account.entry.Entry;
import seedu.address.model.account.entry.Expense;
import seedu.address.model.account.entry.Revenue;
import seedu.address.testutil.ActiveAccountStub;
import seedu.address.testutil.ExpenseBuilder;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.RevenueBuilder;


public class AddCommandTest {

    private final ExpenseBuilder expenseBuilder = new ExpenseBuilder();
    private final RevenueBuilder revenueBuilder = new RevenueBuilder();

    @Test
    public void constructor_nullEntry_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_expenseAcceptedByModel_addSuccessful() {

        Expense expenseStub = expenseBuilder.build();
        Model modelStub = new ModelStub();
        ActiveAccountStubAcceptingEntry activeAccountStub = new ActiveAccountStubAcceptingEntry();
        CommandResult commandResult = new AddCommand(expenseStub).execute(modelStub, activeAccountStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, expenseStub),
                commandResult.getFeedbackToUser());
        assertTrue(activeAccountStub.getEntries().contains(expenseStub));
    }

    @Test
    public void execute_revenueAcceptedByModel_addSuccessful() {

        Revenue revenueStub = revenueBuilder.build();
        Model modelStub = new ModelStub();
        ActiveAccountStubAcceptingEntry activeAccountStub = new ActiveAccountStubAcceptingEntry();
        CommandResult commandResult = new AddCommand(revenueStub).execute(modelStub, activeAccountStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, revenueStub),
                commandResult.getFeedbackToUser());
        assertTrue(activeAccountStub.getEntries().contains(revenueStub));
    }

    @Test
    public void execute_typicalExpense() {
        Expense expenseStub = expenseBuilder.build();
        Model modelStub = new ModelStub();
        ActiveAccount activeAccountStub = new ActiveAccountStub();

        assertEquals("New entry added! Expense: buying paint supplies Amount: 131.73",
                new AddCommand(expenseStub).execute(modelStub, activeAccountStub).getFeedbackToUser());
    }

    @Test
    public void execute_typicalRevenue() {
        Revenue revenueStub = revenueBuilder.build();
        Model modelStub = new ModelStub();
        ActiveAccount activeAccountStub = new ActiveAccountStub();

        assertEquals("New entry added! Revenue: buying paint supplies Amount: 131.73",
                new AddCommand(revenueStub).execute(modelStub, activeAccountStub).getFeedbackToUser());
    }

    @Test
    public void execute_typicalRevenue_withTags() {
        //to maintain immutability of revenueBuilder
        RevenueBuilder revenueBuilderStub = new RevenueBuilder(revenueBuilder.build());
        revenueBuilderStub.withTags("bar", "foo");
        Revenue revenueStub = revenueBuilderStub.build();
        Model modelStub = new ModelStub();
        ActiveAccount activeAccountStub = new ActiveAccountStub();

        assertEquals("New entry added! Revenue: buying paint supplies Amount: 131.73 Tags: [bar][foo]",
                new AddCommand(revenueStub).execute(modelStub, activeAccountStub).getFeedbackToUser());
    }

    @Test
    public void execute_typicalExpense_withTags() {
        //to maintain immutability of expenseBuilder
        ExpenseBuilder expenseBuilderStub = new ExpenseBuilder(expenseBuilder.build());
        expenseBuilderStub.withTags("bar", "foo");
        Expense expenseStub = expenseBuilderStub.build();

        Model modelStub = new ModelStub();
        ActiveAccount activeAccountStub = new ActiveAccountStub();

        assertEquals("New entry added! Expense: buying paint supplies Amount: 131.73 Tags: [bar][foo]",
                new AddCommand(expenseStub).execute(modelStub, activeAccountStub).getFeedbackToUser());
    }

    @Test
    public void equals() {
        Expense expenseStub = expenseBuilder.build();
        Revenue revenueStub = revenueBuilder.build();
        AddCommand addExpenseCommand = new AddCommand(expenseStub);
        AddCommand addRevenueCommand = new AddCommand(revenueStub);

        // same object -> returns true
        assertTrue(addExpenseCommand.equals(addExpenseCommand));
        assertTrue(addRevenueCommand.equals(addRevenueCommand));

        // same values -> returns true
        AddCommand addExpenseCommandCopy = new AddCommand(expenseStub);
        assertTrue(addExpenseCommand.equals(addExpenseCommandCopy));

        AddCommand addRevenueCommandCopy = new AddCommand(revenueStub);
        assertTrue(addRevenueCommand.equals(addRevenueCommandCopy));

        // different types -> returns false
        assertFalse(addExpenseCommand.equals(1));
        assertFalse(addRevenueCommand.equals(1));

        // null -> returns false
        assertFalse(addExpenseCommand.equals(null));
        assertFalse(addRevenueCommand.equals(null));

        // different person -> returns false
        assertFalse(addRevenueCommand.equals(addExpenseCommand));
    }

    public static class ActiveAccountStubAcceptingEntry extends ActiveAccountStub {
        private final ArrayList<Entry> entries = new ArrayList<>();

        @Override
        public void addExpense(Expense expense) {
            entries.add(expense);
        }

        @Override
        public void addRevenue(Revenue revenue) {
            entries.add(revenue);
        }

        public ArrayList<Entry> getEntries() {
            return entries;
        }
    }
}


