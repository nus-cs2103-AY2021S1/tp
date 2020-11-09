package seedu.address.model.room;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class RoomTest {

    @Test
    public void isOccupied() {
        Room room1 = new Room(1);
        //default value of room1 is false -> returns false
        assertFalse(room1.isOccupied());

        //change value of isOccupied to true -> returns true
        room1.setOccupied(true);
        assertTrue(room1.isOccupied());
    }

    @Test
    void testEquals() {
        Room room1 = new Room(1);
        Room room2 = new Room(2);
        Room room1Copy = new Room(1);
        // same object -> returns true
        assertTrue(room1.equals(room1));

        // different object, same fields -> returns true
        assertTrue(room1.equals(room1Copy));

        // null -> returns false
        assertFalse(room1.equals(null));

        // different type -> returns false
        assertFalse(room1.equals(5));

        // different patient -> returns false
        assertFalse(room1.equals(room2));

        // different isOccupied value -> returns false
        room1Copy.setOccupied(true);
        assertFalse(room1.equals(room1Copy));

    }
}
