package seedu.homerce.logic.commands.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.homerce.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.homerce.testutil.appointment.TypicalAppointmentIndexes.INDEX_FIRST_APPOINTMENT;
import static seedu.homerce.testutil.appointment.TypicalAppointmentIndexes.INDEX_SECOND_APPOINTMENT;
import static seedu.homerce.testutil.appointment.TypicalAppointments.getTypicalAppointmentManager;
import static seedu.homerce.testutil.client.TypicalClients.getTypicalClientManager;
import static seedu.homerce.testutil.service.TypicalServices.getTypicalServiceManager;

import org.junit.jupiter.api.Test;

import seedu.homerce.commons.core.Messages;
import seedu.homerce.commons.core.index.Index;
import seedu.homerce.model.Model;
import seedu.homerce.model.ModelManager;
import seedu.homerce.model.UserPrefs;
import seedu.homerce.model.appointment.Appointment;
import seedu.homerce.model.manager.AppointmentManager;
import seedu.homerce.model.manager.ExpenseTracker;
import seedu.homerce.model.manager.RevenueTracker;
import seedu.homerce.testutil.appointment.AppointmentBuilder;
import seedu.homerce.testutil.appointment.EditAppointmentDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests
 * for EditAppointmentCommand.
 */
public class EditAppointmentCommandTest {
    private Model model = new ModelManager(new UserPrefs(), getTypicalClientManager(), getTypicalServiceManager(),
        new RevenueTracker(), new ExpenseTracker(), getTypicalAppointmentManager());

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastAppointment = Index.fromOneBased(model.getFilteredAppointmentList().size());
        Appointment lastAppointment = model.getFilteredAppointmentList().get(indexLastAppointment.getZeroBased());

        AppointmentBuilder appointmentInList = new AppointmentBuilder(lastAppointment);
        Appointment editedAppointment = appointmentInList
            .withDate("22-11-2021")
            .build();

        EditAppointmentCommand.EditAppointmentDescriptor descriptor = new EditAppointmentDescriptorBuilder()
            .withDate("22-11-2021")
            .build();
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(indexLastAppointment, descriptor);

        String expectedMessage = String.format(
            EditAppointmentCommand.MESSAGE_EDIT_APPOINTMENT_SUCCESS,
            editedAppointment
        );

        Model expectedModel = new ModelManager(new UserPrefs(), getTypicalClientManager(),
            getTypicalServiceManager(), new RevenueTracker(), new ExpenseTracker(),
            new AppointmentManager(model.getAppointmentManager()));
        expectedModel.setAppointment(lastAppointment, editedAppointment);
        expectedModel.refreshSchedule();
        assertCommandSuccess(editAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        EditAppointmentCommand.EditAppointmentDescriptor descriptor =
            new EditAppointmentDescriptorBuilder()
                .withServiceCode("SC010")
                .build();
        final EditAppointmentCommand standardCommand = new EditAppointmentCommand(INDEX_FIRST_APPOINTMENT, descriptor);

        // same values -> returns true
        EditAppointmentCommand.EditAppointmentDescriptor copyDescriptor =
            new EditAppointmentDescriptorBuilder()
                .withServiceCode("SC010")
                .build();
        EditAppointmentCommand commandWithSameValues =
            new EditAppointmentCommand(INDEX_FIRST_APPOINTMENT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearAppointmentCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditAppointmentCommand(INDEX_SECOND_APPOINTMENT, descriptor)));
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditAppointmentCommand editAppointmentCommand = new EditAppointmentCommand(INDEX_FIRST_APPOINTMENT,
            new EditAppointmentCommand.EditAppointmentDescriptor());
        assertCommandFailure(editAppointmentCommand, model, Messages.MESSAGE_NOT_EDITED);
    }
}
