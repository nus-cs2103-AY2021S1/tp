package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RoomTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Room(null));
    }

    @Test
    public void constructor_invalidRoom_throwsIllegalArgumentException() {
        String invalidRoom = "";
        assertThrows(IllegalArgumentException.class, () -> new Room(invalidRoom));
    }

    @Test
    public void isValidRoom() {
        // null Room
        assertThrows(NullPointerException.class, () -> Room.isValidRoom(null));

        // blank Room
        assertFalse(Room.isValidRoom("")); // empty string
        assertFalse(Room.isValidRoom(" ")); // spaces only

        // incorrect length
        assertFalse(Room.isValidRoom("1234")); // incorrect length
        assertFalse(Room.isValidRoom("54000")); // incorrect length
        assertFalse(Room.isValidRoom("10")); // incorrect length

        // contains invalid characters
        assertFalse(Room.isValidRoom("4b2"));
        assertFalse(Room.isValidRoom("50b"));
        assertFalse(Room.isValidRoom("90a"));
        assertFalse(Room.isValidRoom("---"));
        assertFalse(Room.isValidRoom("+z/"));

        // trailing space
        assertFalse(Room.isValidRoom("405 ")); // trailing space
        assertFalse(Room.isValidRoom("320 ")); // trailing space
        assertFalse(Room.isValidRoom("105 ")); // trailing space

        // room number 0
        assertFalse(Room.isValidRoom("400")); // Room number 0
        assertFalse(Room.isValidRoom("200")); // Room number 0
        assertFalse(Room.isValidRoom("100")); // Room number 0

        // valid Room
        assertTrue(Room.isValidRoom("405"));
        assertTrue(Room.isValidRoom("120"));
        assertTrue(Room.isValidRoom("301"));
        assertTrue(Room.isValidRoom("212"));
    }
}
