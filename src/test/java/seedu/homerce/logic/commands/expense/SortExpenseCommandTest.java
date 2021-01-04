package seedu.homerce.logic.commands.expense;

import static seedu.homerce.logic.commands.expense.ExpenseCommandTestUtil.assertCommandSuccessTab;
import static seedu.homerce.logic.commands.expense.SortExpenseCommand.MESSAGE_SUCCESS;
import static seedu.homerce.testutil.Assert.assertThrows;
import static seedu.homerce.testutil.expense.TypicalExpenses.getTypicalExpenseTracker;

import org.junit.jupiter.api.Test;

import seedu.homerce.model.Model;
import seedu.homerce.model.ModelManager;
import seedu.homerce.model.UserPrefs;
import seedu.homerce.model.manager.AppointmentManager;
import seedu.homerce.model.manager.ClientManager;
import seedu.homerce.model.manager.HistoryManager;
import seedu.homerce.model.manager.RevenueTracker;
import seedu.homerce.model.manager.ServiceManager;

public class SortExpenseCommandTest {

    private Model model = new ModelManager(new UserPrefs(), new ClientManager(), new ServiceManager(),
            new RevenueTracker(), getTypicalExpenseTracker(), new AppointmentManager());
    private Model expectedModel = new ModelManager(new UserPrefs(), new ClientManager(), new ServiceManager(),
            new RevenueTracker(), getTypicalExpenseTracker(), new AppointmentManager());

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        SortExpenseCommand command = new SortExpenseCommand(true);
        assertThrows(NullPointerException.class, () -> command.execute(null, HistoryManager.getInstance()));
    }

    @Test
    public void execute_ascending() {
        SortExpenseCommand command = new SortExpenseCommand(true);
        expectedModel.getExpenseTracker().sortExpenseList(true);
        assertCommandSuccessTab(command, model, MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_descending() {
        SortExpenseCommand command = new SortExpenseCommand(false);
        expectedModel.getExpenseTracker().sortExpenseList(false);
        assertCommandSuccessTab(command, model, MESSAGE_SUCCESS, expectedModel);
    }
}
