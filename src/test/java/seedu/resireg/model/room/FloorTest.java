package seedu.resireg.model.room;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.resireg.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class FloorTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Floor(null));
    }

    @Test
    public void constructor_invalidFloor_throwsIllegalArgumentException() {
        String invalidFloor = "";
        assertThrows(IllegalArgumentException.class, () -> new Floor(invalidFloor));
    }

    @Test
    void isValidFloor() {
        // null name
        assertThrows(NullPointerException.class, () -> Floor.isValidFloor(null));

        // invalid floor
        assertFalse(Floor.isValidFloor("")); // empty string
        assertFalse(Floor.isValidFloor(" ")); // spaces only
        assertFalse(Floor.isValidFloor("^")); // only non-numeric characters
        assertFalse(Floor.isValidFloor("1*")); // contains non-numeric characters
        assertFalse(Floor.isValidFloor("123")); // contains 3 digits
        assertFalse(Floor.isValidFloor("01")); // starts with 0

        // valid floor
        assertTrue(Floor.isValidFloor("12")); // 2 digits
        assertTrue(Floor.isValidFloor("9")); // 1 digit
    }

}
