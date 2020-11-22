package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TestUtil.basicEqualsTests;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalClients.getTypicalTbmManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.client.Client;
import seedu.address.model.note.Note;
import seedu.address.testutil.ClientBuilder;

class ClientNoteDeleteCommandTest {

    private static final String NOTE_CONTENT_1 = "client note content 1";
    private static final String NOTE_CONTENT_2 = "client note content 2";

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTbmManager(), new UserPrefs());
    }

    @Test
    public void constructor_nulls_throwsNullPointerException() {
        Index clientIdx = Index.fromOneBased(1);
        Index clientNoteIdx = Index.fromOneBased(1);
        // make sure args are valid before testing
        assertDoesNotThrow(() -> new ClientNoteDeleteCommand(clientIdx, clientNoteIdx));
        // change args to null one by one
        assertThrows(NullPointerException.class, () -> new ClientNoteDeleteCommand(null, clientNoteIdx));
        assertThrows(NullPointerException.class, () -> new ClientNoteDeleteCommand(clientIdx, null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        Index clientIdx = Index.fromOneBased(1);
        Index clientNoteIdx = Index.fromOneBased(1);
        ClientNoteDeleteCommand clientNoteDeleteCommand = new ClientNoteDeleteCommand(clientIdx, clientNoteIdx);
        assertThrows(NullPointerException.class, () -> clientNoteDeleteCommand.execute(null));
    }

    @Test
    public void execute_validClientIdxValidNoteIdx_generatesClientNoteDeleteCommandSuccessfully() {
        Index client2Idx = Index.fromOneBased(2);
        Index clientNoteIdx = Index.fromOneBased(1);
        Note clientNote1 = new Note(NOTE_CONTENT_1);
        Note clientNote2 = new Note(NOTE_CONTENT_2);
        Model newModel = new ModelManager();
        Client client1 = new ClientBuilder().withName("client1").build();
        Client client2 = new ClientBuilder().withName("client2").build();
        newModel.addClient(client1);
        newModel.addClient(client2);
        newModel.addClientNote(client2, clientNote1);
        newModel.addClientNote(client2, clientNote2);

        Model expectedModel = new ModelManager();
        Client client1Copy = new ClientBuilder().withName("client1").build();
        Client client2Copy = new ClientBuilder().withName("client2").build();
        expectedModel.addClient(client1Copy);
        expectedModel.addClient(client2Copy);
        expectedModel.addClientNote(client2Copy, clientNote2);

        CommandResult expectedResult = new CommandResult(ClientNoteDeleteCommand.MESSAGE_DELETED_CLIENT_NOTE_SUCCESS);
        ClientNoteDeleteCommand clientNoteDeleteCommand = new ClientNoteDeleteCommand(client2Idx, clientNoteIdx);
        assertCommandSuccess(clientNoteDeleteCommand, newModel, expectedResult, expectedModel);
    }

    @Test
    public void execute_invalidClientIndex_throwsCommandException() {
        Index invalidClientIndex = Index.fromOneBased(model.getSortedFilteredClientList().size() + 1);
        ClientNoteDeleteCommand clientNoteDeleteCommand =
                new ClientNoteDeleteCommand(invalidClientIndex, Index.fromOneBased(1));
        assertThrows(CommandException.class, () -> clientNoteDeleteCommand.execute(model));
    }

    @Test
    public void execute_validClientIndexInvalidClientNoteIndex_throwsCommandException() {
        Client client = new ClientBuilder(ALICE).build();
        Note clientNote = new Note(NOTE_CONTENT_1);
        Index idx = Index.fromOneBased(1);
        model.addClientNote(client, clientNote);
        Index invalidClientNoteIndex = Index.fromOneBased(model.getSortedFilteredClientNotesList().size() + 1);
        ClientNoteDeleteCommand clientNoteDeleteCommand =
                new ClientNoteDeleteCommand(idx, invalidClientNoteIndex);
        assertThrows(CommandException.class, () -> clientNoteDeleteCommand.execute(model));
    }

    @Test
    public void testEquals() {
        ClientNoteDeleteCommand clientNoteDeleteCommand1 =
                new ClientNoteDeleteCommand(Index.fromOneBased(1), Index.fromOneBased(1));
        ClientNoteDeleteCommand clientNoteDeleteCommand1Duplicate =
                new ClientNoteDeleteCommand(Index.fromOneBased(1), Index.fromOneBased(1));
        ClientNoteDeleteCommand clientNoteDeleteCommandClient2 =
                new ClientNoteDeleteCommand(Index.fromOneBased(2), Index.fromOneBased(1));
        ClientNoteDeleteCommand clientNoteDeleteCommand1ClientNote2 =
                new ClientNoteDeleteCommand(Index.fromOneBased(1), Index.fromOneBased(2));

        // basic equals tests
        basicEqualsTests(clientNoteDeleteCommand1);

        // same values -> returns true
        assertTrue(clientNoteDeleteCommand1.equals(clientNoteDeleteCommand1Duplicate));

        // diff values (diff client) --> returns false
        assertFalse(clientNoteDeleteCommand1.equals(clientNoteDeleteCommandClient2));

        // diff values (diff client note) --> returns false
        assertFalse(clientNoteDeleteCommand1.equals(clientNoteDeleteCommand1ClientNote2));
    }

}
