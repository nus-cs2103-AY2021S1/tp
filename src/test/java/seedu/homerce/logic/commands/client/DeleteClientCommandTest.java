package seedu.homerce.logic.commands.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.homerce.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.homerce.logic.commands.CommandTestUtil.showClientAtIndex;
import static seedu.homerce.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.homerce.testutil.TypicalIndexes.INDEX_SECOND_CLIENT;
import static seedu.homerce.testutil.appointment.TypicalAppointments.NOV_11_2020_12PM_LASH_LIFT_BENSON;
import static seedu.homerce.testutil.client.TypicalClients.getTypicalClientManager;

import org.junit.jupiter.api.Test;

import seedu.homerce.commons.core.Messages;
import seedu.homerce.commons.core.index.Index;
import seedu.homerce.model.Model;
import seedu.homerce.model.ModelManager;
import seedu.homerce.model.UserPrefs;
import seedu.homerce.model.client.Client;
import seedu.homerce.model.manager.AppointmentManager;
import seedu.homerce.model.manager.ExpenseTracker;
import seedu.homerce.model.manager.RevenueTracker;
import seedu.homerce.model.manager.ServiceManager;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteClientCommand}.
 */
public class DeleteClientCommandTest {

    private Model model = new ModelManager(new UserPrefs(), getTypicalClientManager(), new ServiceManager(),
        new RevenueTracker(), new ExpenseTracker(), new AppointmentManager());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Client clientToDelete = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());
        DeleteClientCommand deleteClientCommand = new DeleteClientCommand(INDEX_FIRST_CLIENT);

        String expectedMessage = String.format(DeleteClientCommand.MESSAGE_DELETE_CLIENT_SUCCESS, clientToDelete);

        ModelManager expectedModel = new ModelManager(new UserPrefs(), model.getClientManager(), new ServiceManager(),
                new RevenueTracker(), new ExpenseTracker(), new AppointmentManager());
        expectedModel.deleteClient(clientToDelete);

        assertCommandSuccess(deleteClientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredClientList().size() + 1);
        DeleteClientCommand deleteClientCommand = new DeleteClientCommand(outOfBoundIndex);

        assertCommandFailure(deleteClientCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showClientAtIndex(model, INDEX_FIRST_CLIENT);

        Client clientToDelete = model.getFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());
        DeleteClientCommand deleteClientCommand = new DeleteClientCommand(INDEX_FIRST_CLIENT);

        String expectedMessage = String.format(DeleteClientCommand.MESSAGE_DELETE_CLIENT_SUCCESS, clientToDelete);

        Model expectedModel = new ModelManager(new UserPrefs(), model.getClientManager(), new ServiceManager(),
                new RevenueTracker(), new ExpenseTracker(), new AppointmentManager());
        expectedModel.deleteClient(clientToDelete);
        showNoClient(expectedModel);

        assertCommandSuccess(deleteClientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showClientAtIndex(model, INDEX_FIRST_CLIENT);

        Index outOfBoundIndex = INDEX_SECOND_CLIENT;
        // ensures that outOfBoundIndex is still in bounds of homerce list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getClientManager().getClientList().size());

        DeleteClientCommand deleteClientCommand = new DeleteClientCommand(outOfBoundIndex);

        assertCommandFailure(deleteClientCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_deleteClientScheduledForAppointment_throwsCommandException() {
        model.addAppointment(NOV_11_2020_12PM_LASH_LIFT_BENSON);

        DeleteClientCommand deleteClientCommand = new DeleteClientCommand(INDEX_SECOND_CLIENT);

        assertCommandFailure(deleteClientCommand, model, Messages.MESSAGE_CLIENT_INVALID_DELETION);
    }

    @Test
    public void equals() {
        DeleteClientCommand deleteFirstCommand = new DeleteClientCommand(INDEX_FIRST_CLIENT);
        DeleteClientCommand deleteSecondCommand = new DeleteClientCommand(INDEX_SECOND_CLIENT);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteClientCommand deleteFirstCommandCopy = new DeleteClientCommand(INDEX_FIRST_CLIENT);
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
    private void showNoClient(Model model) {
        model.updateFilteredClientList(p -> false);

        assertTrue(model.getFilteredClientList().isEmpty());
    }
}
