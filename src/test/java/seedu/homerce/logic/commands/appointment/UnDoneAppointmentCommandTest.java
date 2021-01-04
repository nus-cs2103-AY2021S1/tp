package seedu.homerce.logic.commands.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.homerce.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.homerce.logic.commands.appointment.UnDoneAppointmentCommand.MESSAGE_DELETE_REVENUE_SUCCESS;
import static seedu.homerce.testutil.appointment.TypicalAppointmentIndexes.INDEX_FIRST_APPOINTMENT;
import static seedu.homerce.testutil.appointment.TypicalAppointmentIndexes.INDEX_SECOND_APPOINTMENT;
import static seedu.homerce.testutil.appointment.TypicalAppointments.OCT_25_2020_2PM_HAIR_TREATMENT_ALICE;
import static seedu.homerce.testutil.appointment.TypicalAppointments.getTypicalAppointmentManager;
import static seedu.homerce.testutil.client.TypicalClients.getTypicalClientManager;
import static seedu.homerce.testutil.service.TypicalServices.getTypicalServiceManager;

import org.junit.jupiter.api.Test;

import seedu.homerce.commons.core.Messages;
import seedu.homerce.commons.core.index.Index;
import seedu.homerce.logic.commands.exceptions.CommandException;
import seedu.homerce.model.Model;
import seedu.homerce.model.ModelManager;
import seedu.homerce.model.UserPrefs;
import seedu.homerce.model.appointment.Appointment;
import seedu.homerce.model.manager.ExpenseTracker;
import seedu.homerce.model.manager.HistoryManager;
import seedu.homerce.model.manager.RevenueTracker;
import seedu.homerce.model.revenue.Revenue;
import seedu.homerce.testutil.appointment.AppointmentBuilder;

public class UnDoneAppointmentCommandTest {
    private Model model = new ModelManager(new UserPrefs(), getTypicalClientManager(), getTypicalServiceManager(),
        new RevenueTracker(), new ExpenseTracker(), getTypicalAppointmentManager());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        DoneAppointmentCommand doneAppointmentCommand = new DoneAppointmentCommand(INDEX_FIRST_APPOINTMENT);
        // Add in the revenue and mark done for original model.
        try {
            doneAppointmentCommand.execute(model, HistoryManager.getInstance());
        } catch (CommandException e) {
            e.printStackTrace();
        }
        Appointment appointmentToMarkUnDone = new AppointmentBuilder(OCT_25_2020_2PM_HAIR_TREATMENT_ALICE).build();
        UnDoneAppointmentCommand unDoneAppointmentCommand = new UnDoneAppointmentCommand(INDEX_FIRST_APPOINTMENT);
        ModelManager expectedModel = new ModelManager(new UserPrefs(), getTypicalClientManager(),
            getTypicalServiceManager(), new RevenueTracker(), new ExpenseTracker(), getTypicalAppointmentManager());
        Revenue revenueToRemove = new Revenue(
            appointmentToMarkUnDone.getService(),
            appointmentToMarkUnDone.getAppointmentDate()
        );
        String expectedMessage = String.format(
            UnDoneAppointmentCommand.MESSAGE_UNDONE_APPOINTMENT_SUCCESS,
            appointmentToMarkUnDone
        ) + "\n" + String.format(MESSAGE_DELETE_REVENUE_SUCCESS, revenueToRemove);
        expectedModel.refreshSchedule();
        model.refreshSchedule();
        assertCommandSuccess(unDoneAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAppointmentList().size() + 1);
        UnDoneAppointmentCommand unDoneAppointmentCommand = new UnDoneAppointmentCommand(outOfBoundIndex);
        assertCommandFailure(unDoneAppointmentCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        UnDoneAppointmentCommand unDoneFirstCommand = new UnDoneAppointmentCommand(INDEX_FIRST_APPOINTMENT);
        UnDoneAppointmentCommand unDoneSecondCommand = new UnDoneAppointmentCommand(INDEX_SECOND_APPOINTMENT);

        // same object -> returns true
        assertTrue(unDoneFirstCommand.equals(unDoneFirstCommand));

        // same values -> returns true
        UnDoneAppointmentCommand doneFirstCommandCopy = new UnDoneAppointmentCommand(INDEX_FIRST_APPOINTMENT);
        assertTrue(unDoneFirstCommand.equals(doneFirstCommandCopy));

        // different types -> returns false
        assertFalse(unDoneFirstCommand.equals(1));

        // null -> returns false
        assertFalse(unDoneFirstCommand.equals(null));

        // different client -> returns false
        assertFalse(unDoneFirstCommand.equals(unDoneSecondCommand));
    }
}
