package seedu.tr4cker.model.countdown;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tr4cker.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EventDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventDate(null, false));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new EventDate(invalidDate, false));
    }

    @Test
    public void constructor_pastDateNew_throwsIllegalArgumentException() {
        String pastDate1 = "01-09-1989";
        String pastDate2 = "01-Sep-1989";
        assertThrows(IllegalArgumentException.class, () -> new EventDate(pastDate1, true));
        assertThrows(IllegalArgumentException.class, () -> new EventDate(pastDate2, true));
    }

    @Test
    public void constructor_success() {
        String pastDate1 = "01-09-1989";
        String pastDate2 = "01-Sep-1989";
        assertDoesNotThrow(() -> new EventDate(pastDate1, false));
        assertDoesNotThrow(() -> new EventDate(pastDate2, false));

        String futureDate1 = "01-09-2021";
        String futureDate2 = "01-Sep-2021";
        assertDoesNotThrow(() -> new EventDate(futureDate1, false));
        assertDoesNotThrow(() -> new EventDate(futureDate2, false));

        assertDoesNotThrow(() -> new EventDate(futureDate1, true));
        assertDoesNotThrow(() -> new EventDate(futureDate2, true));
    }

    @Test
    void isValidDateTest() {
        // null date
        assertThrows(NullPointerException.class, () -> EventDate.isValidDate(null));

        // invalid date
        assertFalse(EventDate.isValidDate("hehe"));
        assertFalse(EventDate.isValidDate("12345678"));
        assertFalse(EventDate.isValidDate("September 1st, 1989"));
        assertFalse(EventDate.isValidDate("Sep-01-1989"));
        assertFalse(EventDate.isValidDate("01-September-1989"));

        // valid date
        assertTrue(EventDate.isValidDate("01-Sep-1989"));
        assertTrue(EventDate.isValidDate("01-09-1989"));
    }

    @Test
    public void isDateTest() {
        // invalid date
        assertFalse(EventDate.isDate("29-Feb-2021"));
        assertFalse(EventDate.isDate("31-11-2021"));

        // valid date
        assertTrue(EventDate.isDate("31-Oct-2021"));
    }

    @Test
    void isFutureDateTest() {
        // null date
        assertThrows(NullPointerException.class, () -> EventDate.isFutureDate(null));

        // past date
        assertFalse(EventDate.isFutureDate("01-Sep-1989"));
        assertFalse(EventDate.isFutureDate("01-09-1989"));

        // future date
        assertTrue(EventDate.isFutureDate("01-Sep-2021"));
        assertTrue(EventDate.isFutureDate("01-09-2021"));
    }

    @Test
    void testEquals() {
        EventDate eventDate1 = new EventDate("01-09-2021", false);
        EventDate eventDate2 = new EventDate("01-Sep-2021", false);
        EventDate eventDate3 = new EventDate("01-Sep-2020", false);

        // same
        assertEquals(eventDate2, eventDate1);

        // same object
        assertEquals(eventDate1, eventDate1);

        // different
        assertNotEquals(eventDate3, eventDate1);

        EventName eventName = new EventName("Help");
        // different type
        assertFalse(eventDate1.equals(eventName));
    }

    @Test
    void testHashCode() {
        EventDate eventDate1 = new EventDate("01-09-2021", true);
        EventDate eventDate2 = new EventDate("01-Sep-2021", true);
        assertEquals(eventDate1.hashCode(), eventDate2.hashCode());
        assertEquals(eventDate1.hashCode(), eventDate1.getDate().hashCode());
    }
}
