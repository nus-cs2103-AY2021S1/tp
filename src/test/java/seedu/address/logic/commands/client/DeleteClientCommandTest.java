package seedu.address.logic.commands.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showClientWithId;
import static seedu.address.testutil.TypicalIds.ID_FIRST_CLIENT;
import static seedu.address.testutil.TypicalIds.ID_SECOND_CLIENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.IdCounter;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.client.Client;
import seedu.address.model.person.client.ClientId;
import seedu.address.testutil.TypicalClients;

public class DeleteClientCommandTest {
    @Test
    public void execute_validIndexUnfilteredList_success() {
        IdCounter.reset();
        Model model = new ModelManager(TypicalClients.getTypicalHairStyleX(), new UserPrefs());
        Client personToDelete = model.getClientById(ID_FIRST_CLIENT);
        DeleteClientCommand deleteCommand = new DeleteClientCommand(ID_FIRST_CLIENT);

        String expectedMessage = String.format(DeleteClientCommand.MESSAGE_DELETE_CLIENT_SUCCESS,
                personToDelete);

        IdCounter.reset();
        ModelManager expectedModel = new ModelManager(TypicalClients.getTypicalHairStyleX(), new UserPrefs());
        expectedModel.deleteClient(personToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        IdCounter.reset();
        Model model = new ModelManager(TypicalClients.getTypicalHairStyleX(), new UserPrefs());
        ClientId outOfBoundIndex = new ClientId(String.valueOf(model
                .getFilteredClientList().size() + 1));
        DeleteClientCommand deleteCommand = new DeleteClientCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_ID);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        IdCounter.reset();
        Model model = new ModelManager(TypicalClients.getTypicalHairStyleX(), new UserPrefs());
        showClientWithId(model, ID_FIRST_CLIENT);

        Client personToDelete = model.getClientById(ID_FIRST_CLIENT);
        DeleteClientCommand deleteCommand = new DeleteClientCommand(ID_FIRST_CLIENT);

        String expectedMessage = String.format(DeleteClientCommand.MESSAGE_DELETE_CLIENT_SUCCESS,
                personToDelete);

        IdCounter.reset();
        Model expectedModel = new ModelManager(TypicalClients.getTypicalHairStyleX(), new UserPrefs());
        expectedModel.deleteClient(personToDelete);
        showNoClient(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        DeleteClientCommand deleteFirstCommand = new DeleteClientCommand(ID_FIRST_CLIENT);
        DeleteClientCommand deleteSecondCommand = new DeleteClientCommand(ID_SECOND_CLIENT);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteClientCommand deleteFirstCommandCopy = new DeleteClientCommand(ID_FIRST_CLIENT);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoClient(Model model) {
        model.updateFilteredClientList(p -> false);

        assertTrue(model.getFilteredClientList().isEmpty());
    }
}
