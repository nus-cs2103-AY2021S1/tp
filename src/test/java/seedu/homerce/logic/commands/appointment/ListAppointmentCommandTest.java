package seedu.homerce.logic.commands.appointment;

import static seedu.homerce.logic.commands.expense.ExpenseCommandTestUtil.assertCommandSuccessTab;
import static seedu.homerce.testutil.appointment.TypicalAppointments.getTypicalAppointmentManager;

import org.junit.jupiter.api.Test;

import seedu.homerce.model.Model;
import seedu.homerce.model.ModelManager;
import seedu.homerce.model.UserPrefs;
import seedu.homerce.model.manager.ClientManager;
import seedu.homerce.model.manager.ExpenseTracker;
import seedu.homerce.model.manager.RevenueTracker;
import seedu.homerce.model.manager.ServiceManager;

public class ListAppointmentCommandTest {
    private Model model = new ModelManager(new UserPrefs(), new ClientManager(), new ServiceManager(),
        new RevenueTracker(), new ExpenseTracker(), getTypicalAppointmentManager());
    private Model expectedModel = new ModelManager(new UserPrefs(), new ClientManager(), new ServiceManager(),
        new RevenueTracker(), new ExpenseTracker(), getTypicalAppointmentManager());

    @Test
    public void execute() {
        assertCommandSuccessTab(
            new ListAppointmentCommand(), model,
            ListAppointmentCommand.MESSAGE_LIST_APPOINTMENT_SUCCESS, expectedModel
        );
    }
}
