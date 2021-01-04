package seedu.homerce.logic.commands.revenue;

import static seedu.homerce.logic.commands.expense.ExpenseCommandTestUtil.assertCommandSuccessTab;
import static seedu.homerce.logic.commands.revenue.SortRevenueCommand.MESSAGE_SUCCESS;
import static seedu.homerce.testutil.Assert.assertThrows;
import static seedu.homerce.testutil.revenue.TypicalRevenues.getTypicalRevenueTracker;

import org.junit.jupiter.api.Test;

import seedu.homerce.model.Model;
import seedu.homerce.model.ModelManager;
import seedu.homerce.model.UserPrefs;
import seedu.homerce.model.manager.AppointmentManager;
import seedu.homerce.model.manager.ClientManager;
import seedu.homerce.model.manager.ExpenseTracker;
import seedu.homerce.model.manager.HistoryManager;
import seedu.homerce.model.manager.ServiceManager;

public class SortRevenueCommandTest {

    private Model model = new ModelManager(new UserPrefs(), new ClientManager(), new ServiceManager(),
            getTypicalRevenueTracker(), new ExpenseTracker(), new AppointmentManager());
    private Model expectedModel = new ModelManager(new UserPrefs(), new ClientManager(), new ServiceManager(),
            getTypicalRevenueTracker(), new ExpenseTracker(), new AppointmentManager());

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        SortRevenueCommand command = new SortRevenueCommand(true);
        assertThrows(NullPointerException.class, () -> command.execute(null, HistoryManager.getInstance()));
    }

    @Test
    public void execute_ascending() {
        SortRevenueCommand command = new SortRevenueCommand(true);
        expectedModel.getRevenueTracker().sortRevenueList(true);
        assertCommandSuccessTab(command, model, MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_descending() {
        SortRevenueCommand command = new SortRevenueCommand(false);
        expectedModel.getRevenueTracker().sortRevenueList(false);
        assertCommandSuccessTab(command, model, MESSAGE_SUCCESS, expectedModel);
    }
}
