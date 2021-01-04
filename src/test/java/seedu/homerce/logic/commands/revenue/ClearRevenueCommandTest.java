package seedu.homerce.logic.commands.revenue;

import static seedu.homerce.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.homerce.testutil.revenue.TypicalRevenues.getTypicalRevenueTracker;

import org.junit.jupiter.api.Test;

import seedu.homerce.model.Model;
import seedu.homerce.model.ModelManager;
import seedu.homerce.model.UserPrefs;
import seedu.homerce.model.manager.AppointmentManager;
import seedu.homerce.model.manager.ClientManager;
import seedu.homerce.model.manager.ExpenseTracker;
import seedu.homerce.model.manager.RevenueTracker;
import seedu.homerce.model.manager.ServiceManager;

public class ClearRevenueCommandTest {

    @Test
    public void execute_emptyRevenueTracker_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearRevenueCommand(), model, ClearRevenueCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyRevenueTracker_success() {
        Model model = new ModelManager(new UserPrefs(), new ClientManager(), new ServiceManager(),
            getTypicalRevenueTracker(), new ExpenseTracker(), new AppointmentManager());
        Model expectedModel = new ModelManager(new UserPrefs(), new ClientManager(), new ServiceManager(),
            getTypicalRevenueTracker(), new ExpenseTracker(), new AppointmentManager());
        expectedModel.setRevenueTracker(new RevenueTracker());

        assertCommandSuccess(new ClearRevenueCommand(), model, ClearRevenueCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
