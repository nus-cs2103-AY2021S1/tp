package seedu.tr4cker.model.countdown;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.tr4cker.testutil.Assert.assertThrows;

import java.util.Objects;

import org.junit.jupiter.api.Test;

public class EventTest {
    private Event event00 = new Event(new EventName("Event Name Exclamation Mark"),
            new EventDate("25-Dec-2021", true));
    private Event event01 = new Event(new EventName("Event Name Exclamation Mark"),
            new EventDate("31-Oct-2021", true));
    private Event event10 = new Event(new EventName("Event Name Question Mark"),
            new EventDate("25-Dec-2021", true));
    private Event event11 = new Event(new EventName("Event Name Question Mark"),
            new EventDate("31-Oct-2021", true));

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Event(null, null));
    }

    @Test
    public void constructor_success() {
        EventName eventName = new EventName("Dear Diary I Believe Im A Good Person");
        EventDate eventDate = new EventDate("01-Sep-1989", false);
        assertDoesNotThrow(() -> new Event(eventName, eventDate));
    }

    @Test
    public void isSameEventTest() {
        // same object -> returns true
        assertTrue(event00.isSameEvent(event00));

        // null -> returns false
        assertFalse(event00.isSameEvent(null));

        // different name and date -> return false
        assertFalse(event00.isSameEvent(event11));

        // different name -> return false
        assertFalse(event00.isSameEvent(event10));

        // different date same name -> return true
        assertTrue(event00.isSameEvent(event01));
    }

    @Test
    public void testEquals() {
        // same object -> returns true
        assertEquals(event00, event00);

        // null -> returns false
        assertNotEquals(event00, null);

        // different name and date -> return false
        assertNotEquals(event11, event00);

        // different name -> return false
        assertNotEquals(event10, event00);

        // different date -> return false
        assertNotEquals(event01, event00);
    }

    @Test
    public void testHashcode() {
        EventName eventName = new EventName("Event Name Question Mark");
        EventDate eventDate = new EventDate("31-Oct-2021", true);
        assertEquals(event11.hashCode(), Objects.hash(eventName, eventDate));
    }

}
