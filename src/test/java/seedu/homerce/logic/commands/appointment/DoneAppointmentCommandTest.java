package seedu.homerce.logic.commands.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.homerce.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.homerce.logic.commands.appointment.DoneAppointmentCommand.MESSAGE_ADD_REVENUE_SUCCESS;
import static seedu.homerce.testutil.appointment.TypicalAppointmentIndexes.INDEX_FIRST_APPOINTMENT;
import static seedu.homerce.testutil.appointment.TypicalAppointmentIndexes.INDEX_SECOND_APPOINTMENT;
import static seedu.homerce.testutil.appointment.TypicalAppointments.OCT_25_2020_2PM_HAIR_TREATMENT_ALICE;
import static seedu.homerce.testutil.appointment.TypicalAppointments.getTypicalAppointmentManager;

import org.junit.jupiter.api.Test;

import seedu.homerce.commons.core.Messages;
import seedu.homerce.commons.core.index.Index;
import seedu.homerce.model.Model;
import seedu.homerce.model.ModelManager;
import seedu.homerce.model.UserPrefs;
import seedu.homerce.model.appointment.Appointment;
import seedu.homerce.model.manager.ClientManager;
import seedu.homerce.model.manager.ExpenseTracker;
import seedu.homerce.model.manager.RevenueTracker;
import seedu.homerce.model.manager.ServiceManager;
import seedu.homerce.model.revenue.Revenue;
import seedu.homerce.testutil.appointment.AppointmentBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DoneAppointmentCommand}.
 */
public class DoneAppointmentCommandTest {
    private Model model = new ModelManager(new UserPrefs(), new ClientManager(), new ServiceManager(),
        new RevenueTracker(), new ExpenseTracker(), getTypicalAppointmentManager());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Appointment appointmentToMarkDone = model.getFilteredAppointmentList()
            .get(INDEX_FIRST_APPOINTMENT.getZeroBased());
        DoneAppointmentCommand doneAppointmentCommand = new DoneAppointmentCommand(INDEX_FIRST_APPOINTMENT);
        ModelManager expectedModel = new ModelManager(new UserPrefs(), new ClientManager(), new ServiceManager(),
            new RevenueTracker(), new ExpenseTracker(), getTypicalAppointmentManager());
        Appointment expectedAppointment = new AppointmentBuilder(OCT_25_2020_2PM_HAIR_TREATMENT_ALICE).build();
        expectedAppointment.markDone();
        expectedModel.setAppointment(
            new AppointmentBuilder(OCT_25_2020_2PM_HAIR_TREATMENT_ALICE).build(),
            expectedAppointment
        );
        expectedModel.refreshSchedule();
        Revenue revenueToAdd = new Revenue(
            appointmentToMarkDone.getService(),
            appointmentToMarkDone.getAppointmentDate()
        );
        expectedModel.addRevenue(revenueToAdd);
        String expectedMessage = String.format(
            DoneAppointmentCommand.MESSAGE_DONE_APPOINTMENT_SUCCESS,
            expectedAppointment
        ) + "\n" + String.format(MESSAGE_ADD_REVENUE_SUCCESS, revenueToAdd);
        assertCommandSuccess(doneAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAppointmentList().size() + 1);
        DoneAppointmentCommand doneAppointmentCommand = new DoneAppointmentCommand(outOfBoundIndex);
        assertCommandFailure(doneAppointmentCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DoneAppointmentCommand doneFirstCommand = new DoneAppointmentCommand(INDEX_FIRST_APPOINTMENT);
        DoneAppointmentCommand doneSecondCommand = new DoneAppointmentCommand(INDEX_SECOND_APPOINTMENT);

        // same object -> returns true
        assertTrue(doneFirstCommand.equals(doneFirstCommand));

        // same values -> returns true
        DoneAppointmentCommand doneFirstCommandCopy = new DoneAppointmentCommand(INDEX_FIRST_APPOINTMENT);
        assertTrue(doneFirstCommand.equals(doneFirstCommandCopy));

        // different types -> returns false
        assertFalse(doneFirstCommand.equals(1));

        // null -> returns false
        assertFalse(doneFirstCommand.equals(null));

        // different client -> returns false
        assertFalse(doneFirstCommand.equals(doneSecondCommand));
    }
}
