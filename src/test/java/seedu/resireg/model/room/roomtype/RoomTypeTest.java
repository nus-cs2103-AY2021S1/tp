package seedu.resireg.model.room.roomtype;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.resireg.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RoomTypeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RoomType(null));
    }

    @Test
    public void constructor_invalidRoomType_throwsIllegalArgumentException() {
        String invalidRoomType = "";
        assertThrows(IllegalArgumentException.class, () -> new RoomType(invalidRoomType));
    }

    @Test
    public void isValidRoomType() {
        // null roomtype
        assertThrows(NullPointerException.class, () -> RoomType.isValidRoomType(null));

        // invalid roomtypes
        assertFalse(RoomType.isValidRoomType("")); // empty string
        assertFalse(RoomType.isValidRoomType(" ")); // spaces only
        assertFalse(RoomType.isValidRoomType("dnfj")); // invalid roomtype abbreviation

        // valid roomtypes
        assertTrue(RoomType.isValidRoomType("CA"));
        assertTrue(RoomType.isValidRoomType("CN"));
        assertTrue(RoomType.isValidRoomType("NA"));
        assertTrue(RoomType.isValidRoomType("NN"));
    }
}
