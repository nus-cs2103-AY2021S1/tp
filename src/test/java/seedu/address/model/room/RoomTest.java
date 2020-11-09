package seedu.address.model.room;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalRooms.DEFAULT_ROOM;
import static seedu.address.testutil.TypicalRooms.DOUBLE_1;
import static seedu.address.testutil.TypicalRooms.SINGLE_1;
import static seedu.address.testutil.TypicalRooms.SUITE_1;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.RoomBuilder;

public class RoomTest {

    @Test
    public void isSameRoom() {
        // same object -> return true
        assertTrue(DEFAULT_ROOM.equals(DEFAULT_ROOM));

        // null -> returns false
        assertFalse(DEFAULT_ROOM.equals(null));

        // different roomID and price -> return false
        Room editedRoom1 = new RoomBuilder(SINGLE_1)
                .withRoomID(DOUBLE_1.getRoomID())
                .withPrice(SUITE_1.getPrice()).build();
        assertFalse(SINGLE_1.equals(editedRoom1));

        // different roomID only -> return false
        editedRoom1 = new RoomBuilder(SINGLE_1).withRoomID(DOUBLE_1.getRoomID()).build();
        assertFalse(SINGLE_1.equals(editedRoom1));

        // different price only -> return false
        editedRoom1 = new RoomBuilder(SINGLE_1).withPrice(DOUBLE_1.getPrice()).build();
        assertFalse(SINGLE_1.equals(editedRoom1));

        // same roomID and price -> return true
        editedRoom1 = new RoomBuilder(DOUBLE_1).withRoomID(SINGLE_1.getRoomID()).withPrice(SINGLE_1.getPrice()).build();
        assertTrue(SINGLE_1.equals(editedRoom1));
    }

    @Test
    public void equals() {
        // same value -> return true
        Room room1Copy = new RoomBuilder(SINGLE_1).build();
        assertTrue(SINGLE_1.equals(room1Copy));

        // same object -> return true
        assertTrue(SINGLE_1.equals(SINGLE_1));

        //null -> return false
        assertFalse(SINGLE_1.equals(null));

        // different type -> returns false
        assertFalse(SINGLE_1.equals(5));

        // different room -> return false
        assertFalse((SINGLE_1.equals(DOUBLE_1)));

        // different price -> returns false
        Room editedRoom1 = new RoomBuilder(SINGLE_1).withPrice(200).build();
        assertFalse(SINGLE_1.equals(editedRoom1));

        //different room ID -> return false
        editedRoom1 = new RoomBuilder(SINGLE_1).withRoomID(DOUBLE_1.getRoomID()).build();
        assertFalse(SINGLE_1.equals(editedRoom1));
    }
}
