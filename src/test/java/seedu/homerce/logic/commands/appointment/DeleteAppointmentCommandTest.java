package seedu.homerce.logic.commands.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.homerce.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.homerce.testutil.appointment.TypicalAppointmentIndexes.INDEX_FIRST_APPOINTMENT;
import static seedu.homerce.testutil.appointment.TypicalAppointmentIndexes.INDEX_SECOND_APPOINTMENT;
import static seedu.homerce.testutil.appointment.TypicalAppointments.NOV_11_2020_12PM_LASH_LIFT_BENSON;
import static seedu.homerce.testutil.appointment.TypicalAppointments.OCT_26_2020_11AM_MANICURE_CARL;
import static seedu.homerce.testutil.appointment.TypicalAppointments.getTypicalAppointmentManager;

import org.junit.jupiter.api.Test;

import seedu.homerce.commons.core.Messages;
import seedu.homerce.commons.core.index.Index;
import seedu.homerce.model.Model;
import seedu.homerce.model.ModelManager;
import seedu.homerce.model.UserPrefs;
import seedu.homerce.model.appointment.Appointment;
import seedu.homerce.model.manager.AppointmentManager;
import seedu.homerce.model.manager.ClientManager;
import seedu.homerce.model.manager.ExpenseTracker;
import seedu.homerce.model.manager.RevenueTracker;
import seedu.homerce.model.manager.ServiceManager;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteAppointmentCommand}.
 */
public class DeleteAppointmentCommandTest {
    private Model model = new ModelManager(new UserPrefs(), new ClientManager(), new ServiceManager(),
        new RevenueTracker(), new ExpenseTracker(), getTypicalAppointmentManager());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Appointment appointmentToDelete = model.getFilteredAppointmentList()
            .get(INDEX_FIRST_APPOINTMENT.getZeroBased());
        DeleteAppointmentCommand deleteAppointmentCommand = new DeleteAppointmentCommand(INDEX_FIRST_APPOINTMENT);
        String expectedMessage = String.format(
            DeleteAppointmentCommand.MESSAGE_DELETE_APPOINTMENT_SUCCESS,
            appointmentToDelete
        );
        ModelManager expectedModel = new ModelManager(new UserPrefs(), new ClientManager(), new ServiceManager(),
            new RevenueTracker(), new ExpenseTracker(), new AppointmentManager());
        expectedModel.addAppointment(OCT_26_2020_11AM_MANICURE_CARL);
        expectedModel.addAppointment(NOV_11_2020_12PM_LASH_LIFT_BENSON);
        expectedModel.refreshSchedule();
        assertCommandSuccess(deleteAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAppointmentList().size() + 1);
        DeleteAppointmentCommand deleteAppointmentCommand = new DeleteAppointmentCommand(outOfBoundIndex);
        assertCommandFailure(deleteAppointmentCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteAppointmentCommand deleteFirstCommand = new DeleteAppointmentCommand(INDEX_FIRST_APPOINTMENT);
        DeleteAppointmentCommand deleteSecondCommand = new DeleteAppointmentCommand(INDEX_SECOND_APPOINTMENT);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteAppointmentCommand deleteFirstCommandCopy = new DeleteAppointmentCommand(INDEX_FIRST_APPOINTMENT);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different client -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    @Test
    public void showNoAppointment() {
        model.updateFilteredAppointmentList(p -> false);
        assertTrue(model.getFilteredAppointmentList().isEmpty());
    }
}
