package seedu.homerce.logic.commands.expense;

import static seedu.homerce.logic.commands.expense.ExpenseCommandTestUtil.assertCommandSuccessTab;
import static seedu.homerce.testutil.expense.TypicalExpenses.getTypicalExpenseTracker;

import org.junit.jupiter.api.Test;

import seedu.homerce.model.Model;
import seedu.homerce.model.ModelManager;
import seedu.homerce.model.UserPrefs;
import seedu.homerce.model.manager.AppointmentManager;
import seedu.homerce.model.manager.ClientManager;
import seedu.homerce.model.manager.RevenueTracker;
import seedu.homerce.model.manager.ServiceManager;

public class ListExpenseCommandTest {

    private Model model = new ModelManager(new UserPrefs(), new ClientManager(), new ServiceManager(),
            new RevenueTracker(), getTypicalExpenseTracker(), new AppointmentManager());
    private Model expectedModel = new ModelManager(new UserPrefs(), new ClientManager(), new ServiceManager(),
            new RevenueTracker(), getTypicalExpenseTracker(), new AppointmentManager());

    @Test
    public void execute() {
        expectedModel.getExpenseTracker().sortDefaultExpenseList();
        assertCommandSuccessTab(new ListExpenseCommand(), model, ListExpenseCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
