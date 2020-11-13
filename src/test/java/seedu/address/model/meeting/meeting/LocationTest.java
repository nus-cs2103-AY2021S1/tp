package seedu.address.model.meeting.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.Assert;

class LocationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Location((String) null));
    }

    @Test
    public void constructor_invalidLocation_throwsIllegalArgumentException() {
        String invalidLocation = " ";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Location(invalidLocation));
    }

    @Test
    public void isValidLocation() {
        // null Location
        assertThrows(NullPointerException.class, () -> Location.isValidLocation(null));

        // Empty location
        assertTrue(Location.isValidLocation(""));

        // Blank location
        assertFalse(Location.isValidLocation(" "));
        assertFalse(Location.isValidLocation("   "));

        // Invalid input
        assertFalse(Location.isValidLocation(" Serangoon"));

        // Valid location
        assertTrue(Location.isValidLocation("Serangoon"));
        assertTrue(Location.isValidLocation("Serangoon North"));
        assertTrue(Location.isValidLocation("Serangoon North Blk 452 #01-03"));
        assertTrue(Location.isValidLocation("S e r a n g o o n"));
        assertTrue(Location.isValidLocation("S1rango3on"));
        assertTrue(Location.isValidLocation("123&"));

    }
}
