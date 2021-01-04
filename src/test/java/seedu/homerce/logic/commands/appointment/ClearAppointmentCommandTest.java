package seedu.homerce.logic.commands.appointment;

import static seedu.homerce.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.homerce.testutil.appointment.TypicalAppointments.getTypicalAppointmentManager;

import org.junit.jupiter.api.Test;

import seedu.homerce.model.Model;
import seedu.homerce.model.ModelManager;
import seedu.homerce.model.UserPrefs;
import seedu.homerce.model.manager.AppointmentManager;
import seedu.homerce.model.manager.ClientManager;
import seedu.homerce.model.manager.ExpenseTracker;
import seedu.homerce.model.manager.RevenueTracker;
import seedu.homerce.model.manager.ServiceManager;

public class ClearAppointmentCommandTest {
    @Test
    public void execute_emptyAppointmentManager_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        assertCommandSuccess(
            new ClearAppointmentCommand(), model,
            ClearAppointmentCommand.MESSAGE_CLEAR_APPOINTMENT_SUCCESS, expectedModel
        );
    }

    @Test
    public void execute_nonEmptyAppointmentManager_success() {
        Model model = new ModelManager(new UserPrefs(), new ClientManager(), new ServiceManager(),
            new RevenueTracker(), new ExpenseTracker(), getTypicalAppointmentManager());
        Model expectedModel = new ModelManager(new UserPrefs(), new ClientManager(), new ServiceManager(),
            new RevenueTracker(), new ExpenseTracker(), new AppointmentManager());
        assertCommandSuccess(
            new ClearAppointmentCommand(), model,
            ClearAppointmentCommand.MESSAGE_CLEAR_APPOINTMENT_SUCCESS, expectedModel
        );
    }
}
