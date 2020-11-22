package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class LocationTest {
    private static final String LOCATION_WITH_CAROT = "^";
    private static final String LOCATION_WITH_AT = "Dining Hall@Eusoff";
    private static final String LOCATION_WITH_SLASH = "Dining Hall/Function Hall";
    private static final String LOCATION_WITH_TRAILING_SPACES = "Dining Hall!   ";
    private static final String LOCATION_WITH_LEADING_SPACES = "   Dining Hall!";
    private static final String VALID_LOCATION = "Dining Hall";

    @Test
    void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Location(null));
    }

    @Test
    void constructor_invalidLocation_throwsIllegalValueException() {
        // whitespace -> throws IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> new Location(""));
        assertThrows(IllegalArgumentException.class, () -> new Location("  "));
        assertThrows(IllegalArgumentException.class, () -> new Location("     "));

        // non-alphanumeric -> throws IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> new Location(LOCATION_WITH_CAROT));
        assertThrows(IllegalArgumentException.class, () -> new Location(LOCATION_WITH_AT));
        assertThrows(IllegalArgumentException.class, () -> new Location(LOCATION_WITH_SLASH));

        // inputs with leading spaces are rejected
        assertThrows(IllegalArgumentException.class, () -> new Location(LOCATION_WITH_LEADING_SPACES));
    }

    @Test
    void constructor_validLocation_success() {
        assertNotNull(new Location(VALID_LOCATION));
    }

    @Test
    void isValidLocation() {
        assertThrows(NullPointerException.class, () -> Location.isValidLocation(null));

        // whitespace -> returns false
        assertFalse(Location.isValidLocation(""));
        assertFalse(Location.isValidLocation("  "));
        assertFalse(Location.isValidLocation(LOCATION_WITH_LEADING_SPACES));
        assertFalse(Location.isValidLocation(LOCATION_WITH_TRAILING_SPACES));

        // non-alphanumeric -> returns false
        assertFalse(Location.isValidLocation(LOCATION_WITH_CAROT));
        assertFalse(Location.isValidLocation(LOCATION_WITH_AT));
        assertFalse(Location.isValidLocation(LOCATION_WITH_SLASH));

        assertTrue(Location.isValidLocation(VALID_LOCATION));
    }

    @Test
    void testEquals() {
        assertEquals(new Location(VALID_LOCATION), new Location(VALID_LOCATION.toUpperCase()));
        assertEquals(new Location(VALID_LOCATION), new Location(VALID_LOCATION.toLowerCase()));
    }

    @Test
    void testHashCode() {
        int testHashCode = new Location(VALID_LOCATION).hashCode();
        assertEquals(testHashCode, new Location(VALID_LOCATION).hashCode());
    }
}
