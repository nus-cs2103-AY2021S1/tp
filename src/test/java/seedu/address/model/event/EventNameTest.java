package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class EventNameTest {
    private static final String NAME_WITH_CAROT = "^";
    private static final String NAME_WITH_AT = "Dinner@Hall";
    private static final String NAME_WITH_SLASH = "Hello/World.";
    private static final String NAME_WITH_TRAILING_SPACES = "Hall Event!   ";
    private static final String NAME_WITH_LEADING_SPACES = "   Hall Event";
    private static final String VALID_NAME = "Hall Event";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventName(null));
    }

    @Test
    public void constructor_invalidEventName_throwsIllegalArgumentException() {
        // whitespace -> throws IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> new EventName(""));
        assertThrows(IllegalArgumentException.class, () -> new EventName(" "));
        assertThrows(IllegalArgumentException.class, () -> new EventName("   "));

        // non-alphanumeric -> throws IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> new EventName(NAME_WITH_CAROT));
        assertThrows(IllegalArgumentException.class, () -> new EventName(NAME_WITH_AT));
        assertThrows(IllegalArgumentException.class, () -> new EventName(NAME_WITH_SLASH));

        // inputs with leading spaces are rejected
        assertThrows(IllegalArgumentException.class, () -> new EventName(NAME_WITH_LEADING_SPACES));
    }

    @Test
    public void constructor_validEventName_success() {
        assertNotNull(new EventName(VALID_NAME));
    }

    @Test
    public void isValidEventName() {
        assertThrows(NullPointerException.class, () -> EventName.isValidEventName(null));

        // whitespace -> returns false
        assertFalse(EventName.isValidEventName(""));
        assertFalse(EventName.isValidEventName(" "));
        assertFalse(EventName.isValidEventName(NAME_WITH_LEADING_SPACES));
        assertFalse(EventName.isValidEventName(NAME_WITH_TRAILING_SPACES));

        // non-alphanumeric -> returns false
        assertFalse(EventName.isValidEventName(NAME_WITH_CAROT));
        assertFalse(EventName.isValidEventName(NAME_WITH_AT));
        assertFalse(EventName.isValidEventName(NAME_WITH_SLASH));

        // valid event name -> returns true
        assertTrue(EventName.isValidEventName(VALID_NAME));
    }

    @Test
    void testEquals() {
        assertEquals(new EventName(VALID_NAME), new EventName(VALID_NAME.toUpperCase()));
        assertEquals(new EventName(VALID_NAME), new EventName(VALID_NAME.toLowerCase()));
    }

    @Test
    void testHashCode() {
        int testHashCode = new EventName(VALID_NAME).hashCode();
        assertEquals(testHashCode, new EventName(VALID_NAME).hashCode());
    }

}
