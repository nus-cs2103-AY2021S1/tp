package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalRooms.ROOM7_PATIENT_ALICE_NO_TASK;

import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.model.room.Room;

class RoomListTest {

    private final RoomList roomList = new RoomList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), roomList.getRoomObservableList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> roomList.resetData(null));
    }

    @Test
    public void numOfRoom_emptyRoomList() {
        assertEquals(0, roomList.getNumOfRooms());
    }

    //@@author w-yeehong
    @Test
    public void getRoomWithRoomNumber_roomNotInList() {
        RoomList roomList = new RoomList();

        // EP for room number: [MIN_INT...0], [1...MAX_INT]

        // EP [1...MAX_INT] -> returns empty optional
        Optional<Room> validRoom = roomList.getRoomWithRoomNumber(1);
        assertTrue(validRoom.isEmpty());

        // EP [MIN_INT...0] -> throws AssertionError
        assertThrows(AssertionError.class, () -> roomList.getRoomWithRoomNumber(0));

        // EP [MIN_INT...0] -> throws AssertionError
        assertThrows(AssertionError.class, () -> roomList.getRoomWithRoomNumber(-1));
    }

    @Test
    public void getRoomWithRoomNumber_roomInList_returnsRoom() {
        RoomList roomList = new RoomList();
        roomList.initRooms(ROOM7_PATIENT_ALICE_NO_TASK);
        Optional<Room> optionalRoom = roomList.getRoomWithRoomNumber(7);
        assertTrue(optionalRoom.map(room -> room.getRoomNumber() == 7).orElse(false));
    }
    //@@author w-yeehong
}
