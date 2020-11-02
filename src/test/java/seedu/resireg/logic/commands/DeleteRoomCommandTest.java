package seedu.resireg.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.resireg.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.resireg.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.resireg.logic.commands.CommandTestUtil.showRoomAtIndex;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_FIRST_ROOM;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_SECOND_ROOM;
import static seedu.resireg.testutil.TypicalRooms.getTypicalResiReg;

import org.junit.jupiter.api.Test;

import seedu.resireg.commons.core.Messages;
import seedu.resireg.commons.core.index.Index;
import seedu.resireg.logic.CommandHistory;
import seedu.resireg.model.Model;
import seedu.resireg.model.ModelManager;
import seedu.resireg.model.UserPrefs;
import seedu.resireg.model.bin.BinItem;
import seedu.resireg.model.room.Room;
import seedu.resireg.storage.Storage;

public class DeleteRoomCommandTest {

    private CommandHistory history = new CommandHistory();

    private Model model = new ModelManager(getTypicalResiReg(), new UserPrefs());
    private Storage storage = null;

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Room roomToDelete = model.getFilteredRoomList().get(INDEX_FIRST_ROOM.getZeroBased());
        DeleteRoomCommand deleteRoomCommand = new DeleteRoomCommand(INDEX_FIRST_ROOM);

        String expectedMessage = String.format(DeleteRoomCommand.MESSAGE_DELETE_ROOM_SUCCESS,
                roomToDelete.toString());

        ModelManager expectedModel = new ModelManager(model.getResiReg(), model.getUserPrefs());
        expectedModel.deleteRoom(roomToDelete);
        expectedModel.addBinItem(new BinItem(roomToDelete));
        expectedModel.saveStateResiReg();

        assertCommandSuccess(deleteRoomCommand, model, history, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredRoomList().size() + 1);
        DeleteRoomCommand deleteRoomCommand = new DeleteRoomCommand(outOfBoundIndex);

        assertCommandFailure(deleteRoomCommand, model, history, Messages.MESSAGE_INVALID_ROOM_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showRoomAtIndex(model, INDEX_FIRST_ROOM);

        Room roomToDelete = model.getFilteredRoomList().get(INDEX_FIRST_ROOM.getZeroBased());
        DeleteRoomCommand deleteRoomCommand = new DeleteRoomCommand(INDEX_FIRST_ROOM);

        String expectedMessage = String.format(DeleteRoomCommand.MESSAGE_DELETE_ROOM_SUCCESS,
                roomToDelete.toString());

        Model expectedModel = new ModelManager(model.getResiReg(), model.getUserPrefs());
        expectedModel.deleteRoom(roomToDelete);
        expectedModel.addBinItem(new BinItem(roomToDelete));
        expectedModel.saveStateResiReg();
        showNoRoom(expectedModel);

        assertCommandSuccess(deleteRoomCommand, model, history, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showRoomAtIndex(model, INDEX_FIRST_ROOM);

        Index outOfBoundIndex = INDEX_SECOND_ROOM;
        // ensures that outOfBoundIndex is still in bounds of rooms list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getResiReg().getRoomList().size());

        DeleteRoomCommand deleteRoomCommand = new DeleteRoomCommand(outOfBoundIndex);

        assertCommandFailure(deleteRoomCommand, model, history, Messages.MESSAGE_INVALID_ROOM_DISPLAYED_INDEX);
    }

    @Test
    public void executeUndoRedo_validIndexUnfilteredList_success() throws Exception {
        Room toDelete = model.getFilteredRoomList().get(INDEX_FIRST_ROOM.getZeroBased());
        DeleteRoomCommand deleteRoomCommand = new DeleteRoomCommand(INDEX_FIRST_ROOM);
        Model expectedModel = new ModelManager(model.getResiReg(), model.getUserPrefs());
        expectedModel.deleteRoom(toDelete);
        expectedModel.addBinItem(new BinItem(toDelete));
        expectedModel.saveStateResiReg();

        // delete -> first room deleted
        deleteRoomCommand.execute(model, storage, history);

        // undo -> reverts resireg back to previous state
        expectedModel.undoResiReg();
        assertCommandSuccess(new UndoCommand(), model, history, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // redo -> same first room deleted again
        expectedModel.redoResiReg();
        assertCommandSuccess(new RedoCommand(), model, history, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void executeUndoRedo_invalidIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredRoomList().size() + 1);
        DeleteRoomCommand deleteRoomCommand = new DeleteRoomCommand(outOfBoundIndex);

        // execution failed -> state not added to model
        assertCommandFailure(deleteRoomCommand, model, history, Messages.MESSAGE_INVALID_ROOM_DISPLAYED_INDEX);

        // single resireg state in model -> undo and redo failures
        assertCommandFailure(new UndoCommand(), model, history, UndoCommand.MESSAGE_FAILURE);
        assertCommandFailure(new RedoCommand(), model, history, RedoCommand.MESSAGE_FAILURE);
    }

    /**
     * 1. Deletes a {@code Room} from a filtered list.
     * 2. Undo deletion.
     * 3. The list should have the same filtering as before.
     * 4. Remove list filtering. Verify the index of the deleted room has changed.
     * 5. Redo deletion, ensuring {@code RedoCommand} deletes the room
     * regardless of indexing.
     */
    @Test
    public void executeUndoRedo_validIndexFilteredList_sameRoomDeleted() throws Exception {
        DeleteRoomCommand deleteRoomCommand = new DeleteRoomCommand(INDEX_FIRST_ROOM);
        Model expectedModel = new ModelManager(model.getResiReg(), model.getUserPrefs());

        showRoomAtIndex(model, INDEX_SECOND_ROOM);
        Room toDelete = model.getFilteredRoomList().get(INDEX_FIRST_ROOM.getZeroBased());
        expectedModel.deleteRoom(toDelete);
        expectedModel.addBinItem(new BinItem(toDelete));
        expectedModel.saveStateResiReg();

        // delete -> deletes second room in unfiltered room list, first room in filtered room list
        deleteRoomCommand.execute(model, storage, history);

        // undo -> reverts resireg back to previous state, keeps filtering
        expectedModel.undoResiReg();
        showRoomAtIndex(expectedModel, INDEX_SECOND_ROOM);
        assertCommandSuccess(new UndoCommand(), model, history, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // remove filtering
        model.updateFilteredRoomList(Model.PREDICATE_SHOW_ALL_ROOMS);
        expectedModel.updateFilteredRoomList(Model.PREDICATE_SHOW_ALL_ROOMS);

        assertNotEquals(toDelete, model.getFilteredRoomList().get(INDEX_FIRST_ROOM.getZeroBased()));
        // redo -> delete same second room in unfiltered room list
        expectedModel.redoResiReg();
        assertCommandSuccess(new RedoCommand(), model, history, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        DeleteRoomCommand deleteFirstCommand = new DeleteRoomCommand(INDEX_FIRST_ROOM);
        DeleteRoomCommand deleteSecondCommand = new DeleteRoomCommand(INDEX_SECOND_ROOM);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteRoomCommand deleteFirstCommandCopy = new DeleteRoomCommand(INDEX_FIRST_ROOM);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different room -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoRoom(Model model) {
        model.updateFilteredRoomList(p -> false);

        assertTrue(model.getFilteredRoomList().isEmpty());
    }
}
