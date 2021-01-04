package seedu.homerce.logic.commands.expense;

import static seedu.homerce.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.homerce.testutil.expense.TypicalExpenses.getTypicalExpenseTracker;

import org.junit.jupiter.api.Test;

import seedu.homerce.model.Model;
import seedu.homerce.model.ModelManager;
import seedu.homerce.model.UserPrefs;
import seedu.homerce.model.manager.AppointmentManager;
import seedu.homerce.model.manager.ClientManager;
import seedu.homerce.model.manager.ExpenseTracker;
import seedu.homerce.model.manager.RevenueTracker;
import seedu.homerce.model.manager.ServiceManager;

public class ClearExpenseCommandTest {

    @Test
    public void execute_emptyExpenseTracker_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearExpenseCommand(), model, ClearExpenseCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyExpenseTracker_success() {
        Model model = new ModelManager(new UserPrefs(), new ClientManager(), new ServiceManager(),
                new RevenueTracker(), getTypicalExpenseTracker(), new AppointmentManager());
        Model expectedModel = new ModelManager(new UserPrefs(), new ClientManager(), new ServiceManager(),
                new RevenueTracker(), getTypicalExpenseTracker(), new AppointmentManager());
        expectedModel.setExpenseTracker(new ExpenseTracker());

        assertCommandSuccess(new ClearExpenseCommand(), model, ClearExpenseCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
