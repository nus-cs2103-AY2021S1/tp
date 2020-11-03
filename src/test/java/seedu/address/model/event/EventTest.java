package seedu.address.model.event;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.EventUtil;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.EventUtil.*;

public class EventTest {

    @Test
    public void constructor_success() {
        Event event = new Event(VALID_NAME, VALID_DATE);
        assertEquals(event, VALID_EVENT);
    }

    @Test
    public void constructorMissing_name_failure() {
        assertThrows(AssertionError.class, () -> new Event(null, VALID_DATE));
    }

    @Test
    public void constructorMissing_date_failure() {
        assertThrows(NullPointerException.class, () -> new Event(VALID_NAME, null));
    }

    @Test
    public void getEventName() {
        Event event = new Event(VALID_NAME, VALID_DATE);
        assertEquals(VALID_NAME, event.getName());
    }

    @Test
    public void getEventTime() {
        Event event = new Event(VALID_NAME, VALID_DATE);
        assertEquals(VALID_DATE, event.getTime());
    }

    @Test
    public void getEventTags() {
        // TODO: Implement tagging for events
    }

    @Test
    public void isSameEvent_success() {
        Event event1 = new Event(VALID_NAME, VALID_DATE);
        Event event2 = new Event(VALID_NAME, VALID_DATE);
        assertTrue(event1.isSameEvent(event2));
    }

    @Test
    public void isSameEvent_failure() {
        Event event1 = new Event(VALID_NAME, VALID_DATE);
        Event event2 = new Event(EventUtil.makeEventName("ES2660"), VALID_DATE);
        assertFalse(event1.isSameEvent(event2));
    }
}
