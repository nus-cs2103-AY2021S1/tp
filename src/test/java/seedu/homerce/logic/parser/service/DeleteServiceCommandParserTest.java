package seedu.homerce.logic.parser.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.homerce.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.homerce.logic.commands.CommandTestUtil.showServiceAtIndex;
import static seedu.homerce.testutil.TypicalIndexes.INDEX_FIRST_SERVICE;
import static seedu.homerce.testutil.TypicalIndexes.INDEX_SECOND_SERVICE;
import static seedu.homerce.testutil.service.TypicalServices.getTypicalServiceManager;

import org.junit.jupiter.api.Test;

import seedu.homerce.commons.core.Messages;
import seedu.homerce.commons.core.index.Index;
import seedu.homerce.logic.commands.service.DeleteServiceCommand;
import seedu.homerce.model.Model;
import seedu.homerce.model.ModelManager;
import seedu.homerce.model.UserPrefs;
import seedu.homerce.model.manager.AppointmentManager;
import seedu.homerce.model.manager.ClientManager;
import seedu.homerce.model.manager.ExpenseTracker;
import seedu.homerce.model.manager.RevenueTracker;
import seedu.homerce.model.service.Service;

public class DeleteServiceCommandParserTest {

    private Model model = new ModelManager(new UserPrefs(), new ClientManager(), getTypicalServiceManager(),
        new RevenueTracker(), new ExpenseTracker(), new AppointmentManager());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Service serviceToDelete = model.getFilteredServiceList().get(INDEX_FIRST_SERVICE.getZeroBased());
        DeleteServiceCommand deleteServiceCommand = new DeleteServiceCommand(INDEX_FIRST_SERVICE);

        String expectedMessage = String.format(DeleteServiceCommand.MESSAGE_DELETE_SERVICE_SUCCESS, serviceToDelete);

        ModelManager expectedModel = new ModelManager(new UserPrefs(), new ClientManager(), model.getServiceManager(),
            new RevenueTracker(), new ExpenseTracker(), new AppointmentManager());
        expectedModel.deleteService(serviceToDelete);

        assertCommandSuccess(deleteServiceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredServiceList().size() + 1);
        DeleteServiceCommand deleteServiceCommand = new DeleteServiceCommand(outOfBoundIndex);

        assertCommandFailure(deleteServiceCommand, model, Messages.MESSAGE_INVALID_SERVICE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showServiceAtIndex(model, INDEX_FIRST_SERVICE);

        Service serviceToDelete = model.getFilteredServiceList().get(INDEX_FIRST_SERVICE.getZeroBased());
        DeleteServiceCommand deleteServiceCommand = new DeleteServiceCommand(INDEX_FIRST_SERVICE);

        String expectedMessage = String.format(DeleteServiceCommand.MESSAGE_DELETE_SERVICE_SUCCESS, serviceToDelete);

        ModelManager expectedModel = new ModelManager(new UserPrefs(), new ClientManager(), model.getServiceManager(),
            new RevenueTracker(), new ExpenseTracker(), new AppointmentManager());
        expectedModel.deleteService(serviceToDelete);
        showNoService(expectedModel);

        assertCommandSuccess(deleteServiceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showServiceAtIndex(model, INDEX_FIRST_SERVICE);

        Index outOfBoundIndex = INDEX_SECOND_SERVICE;
        // ensures that outOfBoundIndex is still in bounds of homerce list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getServiceManager().getServiceList().size());

        DeleteServiceCommand deleteServiceCommand = new DeleteServiceCommand(outOfBoundIndex);

        assertCommandFailure(deleteServiceCommand, model, Messages.MESSAGE_INVALID_SERVICE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteServiceCommand deleteFirstCommand = new DeleteServiceCommand(INDEX_FIRST_SERVICE);
        DeleteServiceCommand deleteSecondCommand = new DeleteServiceCommand(INDEX_SECOND_SERVICE);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteServiceCommand deleteFirstCommandCopy = new DeleteServiceCommand(INDEX_FIRST_SERVICE);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different service -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoService(Model model) {
        model.updateFilteredServiceList(p -> false);

        assertTrue(model.getFilteredServiceList().isEmpty());
    }
}
