package seedu.resireg.model.room;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.resireg.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class RoomNumberTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RoomNumber(null));
    }

    @Test
    public void constructor_invalidRoomNumber_throwsIllegalArgumentException() {
        String invalidRoomNumber = "";
        assertThrows(IllegalArgumentException.class, () -> new RoomNumber(invalidRoomNumber));
    }

    @Test
    void isValidRoomNumber() {
        // null name
        assertThrows(NullPointerException.class, () -> RoomNumber.isValidRoomNumber(null));

        // invalid floor
        assertFalse(RoomNumber.isValidRoomNumber("")); // empty string
        assertFalse(RoomNumber.isValidRoomNumber(" ")); // spaces only
        assertFalse(RoomNumber.isValidRoomNumber("^")); // only non-numeric characters
        assertFalse(RoomNumber.isValidRoomNumber("1*")); // contains non-numeric characters
        assertFalse(RoomNumber.isValidRoomNumber("1")); // contains 1 digit
        assertFalse(RoomNumber.isValidRoomNumber("12")); // contains 2 digits
        assertFalse(RoomNumber.isValidRoomNumber("012")); // starts with 0

        // valid floor
        assertTrue(RoomNumber.isValidRoomNumber("123")); // small number
        assertTrue(RoomNumber.isValidRoomNumber("450")); // medium number
        assertTrue(RoomNumber.isValidRoomNumber("912")); // big number
    }
}
