package seedu.homerce.logic.commands.expense;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.commons.core.Messages.MESSAGE_EXPENSES_LISTED_OVERVIEW;
import static seedu.homerce.logic.commands.expense.ExpenseCommandTestUtil.assertCommandSuccessTab;
import static seedu.homerce.testutil.expense.TypicalExpenses.CONDITIONER;
import static seedu.homerce.testutil.expense.TypicalExpenses.getTypicalExpenseTracker;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.homerce.model.Model;
import seedu.homerce.model.ModelManager;
import seedu.homerce.model.UserPrefs;
import seedu.homerce.model.expense.predicate.ExpenseDescriptionPredicate;
import seedu.homerce.model.manager.AppointmentManager;
import seedu.homerce.model.manager.ClientManager;
import seedu.homerce.model.manager.RevenueTracker;
import seedu.homerce.model.manager.ServiceManager;
import seedu.homerce.model.util.attributes.Description;

public class FindExpenseCommandTest {

    private Model model = new ModelManager(new UserPrefs(), new ClientManager(), new ServiceManager(),
            new RevenueTracker(), getTypicalExpenseTracker(), new AppointmentManager());
    private Model expectedModel = new ModelManager(new UserPrefs(), new ClientManager(), new ServiceManager(),
            new RevenueTracker(), getTypicalExpenseTracker(), new AppointmentManager());

    @Test
    public void equals() {
        Description testDescriptionOne = new Description("test1");
        ExpenseDescriptionPredicate testPredicateOne = new ExpenseDescriptionPredicate(testDescriptionOne);
        FindExpenseCommand testFindCommandOne = new FindExpenseCommand(testPredicateOne);
        Description testDescriptionTwo = new Description("test2");
        ExpenseDescriptionPredicate testPredicateTwo = new ExpenseDescriptionPredicate(testDescriptionTwo);
        FindExpenseCommand testFindCommandTwo = new FindExpenseCommand(testPredicateTwo);

        // same object -> return true
        assertTrue(testFindCommandOne.equals(testFindCommandOne));

        // same values -> return true
        FindExpenseCommand testFindCommandOneCopy = new FindExpenseCommand(testPredicateOne);
        assertTrue(testFindCommandOne.equals(testFindCommandOneCopy));

        // different types -> return false
        assertFalse(testFindCommandOne.equals(1));

        // different values -> return false
        assertFalse(testFindCommandOne.equals(testFindCommandTwo));

        // null -> return false
        assertFalse(testFindCommandOne.equals(null));
    }

    @Test
    public void execute_parameterDoesNotExist_success() {
        String expectedMessage = String.format(MESSAGE_EXPENSES_LISTED_OVERVIEW, 0);
        ExpenseDescriptionPredicate predicate = new ExpenseDescriptionPredicate(new Description("test"));
        FindExpenseCommand command = new FindExpenseCommand(predicate);
        expectedModel.updateFilteredExpenseList(predicate);
        assertCommandSuccessTab(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(), model.getFilteredExpenseList());
    }

    @Test
    public void execute_parameterExists_success() {
        String expectedMessage = String.format(MESSAGE_EXPENSES_LISTED_OVERVIEW, 1);
        ExpenseDescriptionPredicate predicate = new ExpenseDescriptionPredicate(new Description("Conditioner"));
        FindExpenseCommand command = new FindExpenseCommand(predicate);
        expectedModel.updateFilteredExpenseList(predicate);
        assertCommandSuccessTab(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CONDITIONER), model.getFilteredExpenseList());
    }
}

