package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showClientAtIndex;
import static seedu.address.testutil.TestUtil.basicEqualsTests;
import static seedu.address.testutil.TypicalClients.getTypicalTbmManager;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CLIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CLIENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;

public class ClientViewCommandTest {

    private final Model model = new ModelManager(getTypicalTbmManager(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Client clientToView = model.getSortedFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());
        ClientViewCommand command = new ClientViewCommand(INDEX_FIRST_CLIENT);

        String expectedMsg = String.format(ClientViewCommand.MESSAGE_VIEW_CLIENT_SUCCESS, clientToView.getName());

        ModelManager expectedModel = new ModelManager(model.getTbmManager(), new UserPrefs());
        expectedModel.setWidgetClient(clientToView);

        assertCommandSuccess(command, model, expectedMsg, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getSortedFilteredClientList().size() + 1);
        ClientViewCommand clientViewCommand = new ClientViewCommand(outOfBoundIndex);

        assertCommandFailure(clientViewCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }
    @Test
    public void execute_validIndexFilteredList_success() {
        showClientAtIndex(model, INDEX_FIRST_CLIENT);

        Client clientToView = model.getSortedFilteredClientList().get(INDEX_FIRST_CLIENT.getZeroBased());
        ClientViewCommand clientViewCommand = new ClientViewCommand(INDEX_FIRST_CLIENT);

        String expectedMessage = String.format(ClientViewCommand.MESSAGE_VIEW_CLIENT_SUCCESS, clientToView.getName());

        Model expectedModel = new ModelManager(model.getTbmManager(), new UserPrefs());
        showClientAtIndex(expectedModel, INDEX_FIRST_CLIENT);
        expectedModel.setWidgetClient(clientToView);

        assertCommandSuccess(clientViewCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showClientAtIndex(model, INDEX_FIRST_CLIENT);

        Index outOfBoundIndex = INDEX_SECOND_CLIENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTbmManager().getClientList().size());

        ClientViewCommand clientViewCommand = new ClientViewCommand(outOfBoundIndex);

        assertCommandFailure(clientViewCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ClientViewCommand viewFirstCommand = new ClientViewCommand(INDEX_FIRST_CLIENT);
        ClientViewCommand viewSecondCommand = new ClientViewCommand(INDEX_SECOND_CLIENT);

        // basic equals tests
        basicEqualsTests(viewFirstCommand);

        // same values -> returns true
        ClientViewCommand viewFirstCommandCopy = new ClientViewCommand(INDEX_FIRST_CLIENT);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different client -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }

}
