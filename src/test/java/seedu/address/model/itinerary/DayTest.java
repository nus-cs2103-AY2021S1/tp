package seedu.address.model.itinerary;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class DayTest {

    @Test
    public void constructor_nullDayNumber_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Day(null));
    }

    @Test
    public void constructor_invalidDayNumber_throwsIllegalArgumentException() {
        int invalidDayNumber = -1;
        assertThrows(IllegalArgumentException.class, () -> new Day(-1));
    }

    @Test
    public void isValidDayNumber() {
        // null day
        assertThrows(NullPointerException.class, () -> Day.isValidDayNumber(null));

        // invalid day number
        assertFalse(Day.isValidDayNumber(0)); // zero
        assertFalse(Day.isValidDayNumber(-1)); // negative number

        // valid day number
        assertTrue(Day.isValidDayNumber(2));
    }
}
