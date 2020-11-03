package seedu.resireg.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.resireg.logic.commands.CommandTestUtil.DESC_ROOM_A;
import static seedu.resireg.logic.commands.CommandTestUtil.DESC_ROOM_B;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_FLOOR_A;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_ROOM_NUMBER_A;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_TAG_RENOVATED;
import static seedu.resireg.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.resireg.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.resireg.logic.commands.CommandTestUtil.showRoomAtIndex;
import static seedu.resireg.logic.commands.EditRoomCommand.MESSAGE_DUPLICATE_ROOM;
import static seedu.resireg.logic.commands.EditRoomCommand.MESSAGE_EDIT_ROOM_SUCCESS;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_FIRST_ROOM;
import static seedu.resireg.testutil.TypicalIndexes.INDEX_SECOND_ROOM;
import static seedu.resireg.testutil.TypicalRooms.getTypicalResiReg;

import org.junit.jupiter.api.Test;

import seedu.resireg.commons.core.Messages;
import seedu.resireg.commons.core.index.Index;
import seedu.resireg.logic.CommandHistory;
import seedu.resireg.logic.commands.EditRoomCommand.EditRoomDescriptor;
import seedu.resireg.model.Model;
import seedu.resireg.model.ModelManager;
import seedu.resireg.model.ResiReg;
import seedu.resireg.model.UserPrefs;
import seedu.resireg.model.room.Room;
import seedu.resireg.storage.Storage;
import seedu.resireg.storage.StorageManager;
import seedu.resireg.testutil.EditRoomDescriptorBuilder;
import seedu.resireg.testutil.RoomBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 */
public class EditRoomCommandTest {
    private CommandHistory history = new CommandHistory();

    private Model model = new ModelManager(getTypicalResiReg(), new UserPrefs());
    private Storage storage = new StorageManager(null, null);

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Room editedRoom = new RoomBuilder().build();
        EditRoomDescriptor descriptor = new EditRoomDescriptorBuilder(editedRoom).build();
        EditRoomCommand editRoomCommand = new EditRoomCommand(INDEX_FIRST_ROOM, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_ROOM_SUCCESS,
                editedRoom.toString());

        Model expectedModel = new ModelManager(new ResiReg(model.getResiReg()), model.getUserPrefs());
        expectedModel.setRoom(model.getFilteredRoomList().get(0), editedRoom);
        expectedModel.saveStateResiReg();

        assertCommandSuccess(editRoomCommand, model, history, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastRoom = Index.fromOneBased(model.getFilteredRoomList().size());
        Room lastRoom = model.getFilteredRoomList().get(indexLastRoom.getZeroBased());

        RoomBuilder roomInList = new RoomBuilder(lastRoom);
        Room editedRoom = roomInList.withFloor(VALID_FLOOR_A).withRoomNumber(VALID_ROOM_NUMBER_A)
                .withTags(VALID_TAG_RENOVATED).build();

        EditRoomDescriptor descriptor = new EditRoomDescriptorBuilder().withFloor(VALID_FLOOR_A)
                .withRoomNumber(VALID_ROOM_NUMBER_A).withTags(VALID_TAG_RENOVATED).build();
        EditRoomCommand editRoomCommand = new EditRoomCommand(indexLastRoom, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_ROOM_SUCCESS, editedRoom.toString());

        Model expectedModel = new ModelManager(new ResiReg(model.getResiReg()), new UserPrefs());
        expectedModel.setRoom(lastRoom, editedRoom);
        expectedModel.saveStateResiReg();

        assertCommandSuccess(editRoomCommand, model, history, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditRoomCommand editRoomCommand = new EditRoomCommand(INDEX_FIRST_ROOM, new EditRoomDescriptor());
        Room editedRoom = model.getFilteredRoomList().get(INDEX_FIRST_ROOM.getZeroBased());

        String expectedMessage = String.format(MESSAGE_EDIT_ROOM_SUCCESS, editedRoom.toString());

        Model expectedModel = new ModelManager(new ResiReg(model.getResiReg()), new UserPrefs());
        expectedModel.saveStateResiReg();

        assertCommandSuccess(editRoomCommand, model, history, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showRoomAtIndex(model, INDEX_FIRST_ROOM);

        Room roomInFilteredList = model.getFilteredRoomList().get(INDEX_FIRST_ROOM.getZeroBased());
        Room editedRoom = new RoomBuilder(roomInFilteredList).withFloor(VALID_FLOOR_A).build();
        EditRoomCommand editRoomCommand = new EditRoomCommand(INDEX_FIRST_ROOM,
                new EditRoomDescriptorBuilder().withFloor(VALID_FLOOR_A).build());

        String expectedMessage = String.format(MESSAGE_EDIT_ROOM_SUCCESS,
                editedRoom.toString());

        Model expectedModel = new ModelManager(new ResiReg(model.getResiReg()), new UserPrefs());
        showRoomAtIndex(expectedModel, INDEX_FIRST_ROOM);
        expectedModel.setRoom(model.getFilteredRoomList().get(0), editedRoom);
        expectedModel.saveStateResiReg();

        assertCommandSuccess(editRoomCommand, model, history, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateRoomUnfilteredList_failure() {
        Room firstRoom = model.getFilteredRoomList().get(INDEX_FIRST_ROOM.getZeroBased());
        EditRoomDescriptor descriptor = new EditRoomDescriptorBuilder(firstRoom).build();
        EditRoomCommand editRoomCommand = new EditRoomCommand(INDEX_SECOND_ROOM, descriptor);

        assertCommandFailure(editRoomCommand, model, history, MESSAGE_DUPLICATE_ROOM);
    }

    @Test
    public void execute_duplicateRoomFilteredList_failure() {
        showRoomAtIndex(model, INDEX_FIRST_ROOM);

        // edit room in filtered list into a duplicate in ResiReg
        Room roomInList = model.getResiReg().getRoomList().get(INDEX_SECOND_ROOM.getZeroBased());
        EditRoomCommand editRoomCommand = new EditRoomCommand(INDEX_FIRST_ROOM,
                new EditRoomDescriptorBuilder(roomInList).build());

        assertCommandFailure(editRoomCommand, model, history, MESSAGE_DUPLICATE_ROOM);
    }

    @Test
    public void execute_invalidRoomIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredRoomList().size() + 1);
        EditRoomDescriptor descriptor = new EditRoomDescriptorBuilder().withFloor(VALID_FLOOR_A).build();
        EditRoomCommand editRoomCommand = new EditRoomCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editRoomCommand, model, history, Messages.MESSAGE_INVALID_ROOM_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of ResiReg
     */
    @Test
    public void execute_invalidRoomIndexFilteredList_failure() {
        showRoomAtIndex(model, INDEX_FIRST_ROOM);
        Index outOfBoundIndex = INDEX_SECOND_ROOM;
        // ensures that outOfBoundIndex is still in bounds of ResiReg's room list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getResiReg().getRoomList().size());

        EditRoomCommand editRoomCommand = new EditRoomCommand(outOfBoundIndex,
                new EditRoomDescriptorBuilder().withFloor(VALID_FLOOR_A).build());

        assertCommandFailure(editRoomCommand, model, history, Messages.MESSAGE_INVALID_ROOM_DISPLAYED_INDEX);
    }

    @Test
    public void executeUndoRedo_validIndexUnfilteredList_success() throws Exception {
        Room editedRoom = new RoomBuilder().build();
        Room roomToEdit = model.getFilteredRoomList().get(INDEX_FIRST_ROOM.getZeroBased());
        EditRoomDescriptor desc = new EditRoomDescriptorBuilder(editedRoom).build();
        EditRoomCommand editRoomCommand = new EditRoomCommand(INDEX_FIRST_ROOM, desc);
        Model expectedModel = new ModelManager(new ResiReg(model.getResiReg()), new UserPrefs());
        expectedModel.setRoom(roomToEdit, editedRoom);
        expectedModel.saveStateResiReg();

        // edit -> first room edited
        editRoomCommand.execute(model, storage, history);

        // undo -> reverts resireg back to prev state and filtered room list to show all rooms
        expectedModel.undoResiReg();
        assertCommandSuccess(new UndoCommand(), model, history, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // redo -> same first room edited again
        expectedModel.redoResiReg();
        assertCommandSuccess(new RedoCommand(), model, history, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void executeUndoRedo_invalidIndexUnfilteredList_failure() {
        Index outofBounds = Index.fromOneBased(model.getFilteredRoomList().size() + 1);
        EditRoomDescriptor desc = new EditRoomDescriptorBuilder().withFloor(VALID_FLOOR_A).build();
        EditRoomCommand editRoomCommand = new EditRoomCommand(outofBounds, desc);

        // execution failed -> resireg state not added into model
        assertCommandFailure(editRoomCommand, model, history, Messages.MESSAGE_INVALID_ROOM_DISPLAYED_INDEX);

        // single resireg state in mode -> undo and redo fails
        assertCommandFailure(new UndoCommand(), model, history, UndoCommand.MESSAGE_FAILURE);
        assertCommandFailure(new RedoCommand(), model, history, RedoCommand.MESSAGE_FAILURE);
    }

    /**
     * 1. Edits a {@code Room} from a filtered list.
     * 2. Undo the edit.
     * 3. The list should have the same filtering as before.
     * 4. Remove list filtering. Verify the index of the edited room has changed.
     * 4. Redo the edit. This ensures {@code RedoCommand} edits the room object
     * regardless of indexing.
     */
    @Test
    public void executeUndoRedo_validIndexFilteredList_sameRoomEdited() throws Exception {
        Room editedRoom = new RoomBuilder().build();
        EditRoomDescriptor desc = new EditRoomDescriptorBuilder(editedRoom).build();
        EditRoomCommand editRoomCommand = new EditRoomCommand(INDEX_FIRST_ROOM, desc);
        Model expectedModel = new ModelManager(new ResiReg(model.getResiReg()), new UserPrefs());

        showRoomAtIndex(model, INDEX_SECOND_ROOM);
        Room roomToEdit = model.getFilteredRoomList().get(INDEX_FIRST_ROOM.getZeroBased());
        expectedModel.setRoom(roomToEdit, editedRoom);
        expectedModel.saveStateResiReg();

        // edit -> edits second room in unfiltered list / first room in filtered room list
        editRoomCommand.execute(model, storage, history);

        // undo -> reverts resireg back to prev state, keeps filtering
        expectedModel.undoResiReg();
        showRoomAtIndex(expectedModel, INDEX_SECOND_ROOM);
        assertCommandSuccess(new UndoCommand(), model, history, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // remove filtering
        model.updateFilteredRoomList(Model.PREDICATE_SHOW_ALL_ROOMS);
        expectedModel.updateFilteredRoomList(Model.PREDICATE_SHOW_ALL_ROOMS);

        assertNotEquals(model.getFilteredRoomList().get(INDEX_FIRST_ROOM.getZeroBased()), roomToEdit);
        // redo -> edits same second room in unfiltered room list
        expectedModel.redoResiReg();
        assertCommandSuccess(new RedoCommand(), model, history, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        final EditRoomCommand standardCommand = new EditRoomCommand(INDEX_FIRST_ROOM, DESC_ROOM_A);

        // same values -> returns true
        EditRoomDescriptor copyDescriptor = new EditRoomDescriptor(DESC_ROOM_A);
        EditRoomCommand commandWithSameValues = new EditRoomCommand(INDEX_FIRST_ROOM, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditRoomCommand(INDEX_SECOND_ROOM, DESC_ROOM_A)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditRoomCommand(INDEX_FIRST_ROOM, DESC_ROOM_B)));
    }
}
