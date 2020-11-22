package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class EventDateTest {
    private static final String VALID_DATE = "01/01/2020 15:00";
    private static final String INVALID_DATE = "2020/01/01 15:00";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventDate(null));
    }

    @Test
    public void constructor_invalidEventDate_throwsIllegalValueException() {
        assertThrows(IllegalArgumentException.class, () -> new EventDate(""));
        assertThrows(IllegalArgumentException.class, () -> new EventDate(INVALID_DATE));
    }

    @Test
    public void constructor_validEventDate_success() {
        assertNotNull(new EventDate(VALID_DATE));
    }

    @Test
    void isValidEventDate() {
        assertThrows(NullPointerException.class, () -> EventDate.isValidEventDate(null));

        assertFalse(EventDate.isValidEventDate(INVALID_DATE));
        assertFalse(EventDate.isValidEventDate(""));

        assertTrue(EventDate.isValidEventDate(VALID_DATE));
    }

    @Test
    void testHashCode() {
        int testHashCode = new EventDate(VALID_DATE).hashCode();
        assertEquals(testHashCode, new EventDate(VALID_DATE).hashCode());
    }
}
