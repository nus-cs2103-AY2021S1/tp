package seedu.homerce.logic.commands.client;

import static seedu.homerce.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.homerce.testutil.client.TypicalClients.getTypicalClientManager;

import org.junit.jupiter.api.Test;

import seedu.homerce.model.Model;
import seedu.homerce.model.ModelManager;
import seedu.homerce.model.UserPrefs;
import seedu.homerce.model.manager.AppointmentManager;
import seedu.homerce.model.manager.ClientManager;
import seedu.homerce.model.manager.ExpenseTracker;
import seedu.homerce.model.manager.RevenueTracker;
import seedu.homerce.model.manager.ServiceManager;

public class ClearClientCommandTest {

    @Test
    public void execute_emptyClientManager_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearClientCommand(), model, ClearClientCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyClientManager_success() {
        Model model = new ModelManager(new UserPrefs(), getTypicalClientManager(), new ServiceManager(),
            new RevenueTracker(), new ExpenseTracker(), new AppointmentManager());
        Model expectedModel = new ModelManager(new UserPrefs(), getTypicalClientManager(), new ServiceManager(),
            new RevenueTracker(), new ExpenseTracker(), new AppointmentManager());
        expectedModel.setClientManager(new ClientManager());

        assertCommandSuccess(new ClearClientCommand(), model, ClearClientCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
