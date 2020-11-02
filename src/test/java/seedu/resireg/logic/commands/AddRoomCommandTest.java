package seedu.resireg.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_FLOOR_A;
import static seedu.resireg.logic.commands.CommandTestUtil.VALID_FLOOR_B;
import static seedu.resireg.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.resireg.logic.CommandHistory;
import seedu.resireg.logic.commands.exceptions.CommandException;
import seedu.resireg.model.ReadOnlyResiReg;
import seedu.resireg.model.ResiReg;
import seedu.resireg.model.room.Room;
import seedu.resireg.testutil.ModelStub;
import seedu.resireg.testutil.RoomBuilder;
import seedu.resireg.testutil.StorageStub;

public class AddRoomCommandTest {
    private CommandHistory history = new CommandHistory();

    @Test
    public void constructor_nullRoom_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddRoomCommand(null));
    }

    @Test
    public void execute_roomAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingRoomAdded modelStub = new ModelStubAcceptingRoomAdded();
        StorageStub storageStub = new StorageStub();
        Room validRoom = new RoomBuilder().build();

        CommandResult commandResult = new AddRoomCommand(validRoom).execute(modelStub, storageStub, history);

        assertEquals(String.format(AddRoomCommand.MESSAGE_SUCCESS, validRoom.toString()),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validRoom), modelStub.roomsAdded);
    }

    @Test
    public void execute_duplicateRoom_throwsCommandException() {
        Room validRoom = new RoomBuilder().build();
        AddRoomCommand addRoomCommand = new AddRoomCommand(validRoom);
        ModelStub modelStub = new ModelStubWithRoom(validRoom);
        StorageStub storageStub = new StorageStub();

        assertThrows(CommandException.class, AddRoomCommand.MESSAGE_DUPLICATE_ROOM, () ->
                addRoomCommand.execute(modelStub, storageStub, history));
    }

    @Test
    public void equals() {
        Room roomA = new RoomBuilder().withFloor(VALID_FLOOR_A).build();
        Room roomB = new RoomBuilder().withFloor(VALID_FLOOR_B).build();
        AddRoomCommand addRoomACommand = new AddRoomCommand(roomA);
        AddRoomCommand addRoomBCommand = new AddRoomCommand(roomB);

        // same object -> returns true
        assertTrue(addRoomACommand.equals(addRoomACommand));

        // same values -> returns true
        AddRoomCommand addAliceCommandCopy = new AddRoomCommand(roomA);
        assertTrue(addRoomACommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addRoomACommand.equals(1));

        // null -> returns false
        assertFalse(addRoomACommand.equals(null));

        // different room -> returns false
        assertFalse(addRoomACommand.equals(addRoomBCommand));
    }

    /**
     * A Model stub that contains a single room.
     */
    private class ModelStubWithRoom extends ModelStub {
        private final Room room;

        ModelStubWithRoom(Room room) {
            requireNonNull(room);
            this.room = room;
        }

        @Override
        public boolean hasRoom(Room room) {
            requireNonNull(room);
            return this.room.isSameRoom(room);
        }
    }

    /**
     * A Model stub that always accept the room being added.
     */
    private class ModelStubAcceptingRoomAdded extends ModelStub {
        final ArrayList<Room> roomsAdded = new ArrayList<>();

        @Override
        public boolean hasRoom(Room room) {
            requireNonNull(room);
            return roomsAdded.stream().anyMatch(room::isSameRoom);
        }

        @Override
        public void addRoom(Room room) {
            requireNonNull(room);
            roomsAdded.add(room);
        }

        @Override
        public void saveStateResiReg() {
            // called by {@code AddRoomCommand#execute()}
        }

        @Override
        public ReadOnlyResiReg getResiReg() {
            return new ResiReg();
        }
    }
}
