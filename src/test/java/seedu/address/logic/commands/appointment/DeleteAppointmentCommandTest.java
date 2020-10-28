package seedu.address.logic.commands.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.getTypicalCliniCalWithAppointments;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPOINTMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_APPOINTMENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;

public class DeleteAppointmentCommandTest {

    private Model model = new ModelManager(getTypicalCliniCalWithAppointments(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Appointment appointmentToDelete = model.getFilteredAppointmentList()
                .get(INDEX_FIRST_APPOINTMENT.getZeroBased());
        DeleteAppointmentCommand deleteAppointmentCommand = new DeleteAppointmentCommand(INDEX_FIRST_APPOINTMENT);

        String expectedMessage = String.format(DeleteAppointmentCommand.MESSAGE_SUCCESS, appointmentToDelete);

        ModelManager expectedModel = new ModelManager(model.getCliniCal(), new UserPrefs());
        expectedModel.deleteAppointment(appointmentToDelete);

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

        // different patient -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
}
