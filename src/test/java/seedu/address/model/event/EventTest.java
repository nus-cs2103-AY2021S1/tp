package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.event.EventUtil.VALID_DATE;
import static seedu.address.testutil.event.EventUtil.VALID_EVENT;
import static seedu.address.testutil.event.EventUtil.VALID_NAME;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.event.EventUtil;

public class EventTest {
    private Set<Tag> tags = new HashSet<Tag>();

    @Test
    public void constructor_success() {
        Event event = new Event(VALID_NAME, VALID_DATE, tags);
        assertEquals(event, VALID_EVENT);
    }

    @Test
    public void constructorMissing_name_failure() {
        assertThrows(AssertionError.class, () -> new Event(null, VALID_DATE, tags));
    }

    @Test
    public void constructorMissing_date_failure() {
        assertThrows(NullPointerException.class, () -> new Event(VALID_NAME, null, tags));
    }

    @Test
    public void getEventName() {
        Event event = new Event(VALID_NAME, VALID_DATE, tags);
        assertEquals(VALID_NAME, event.getName());
    }

    @Test
    public void getEventTime() {
        Event event = new Event(VALID_NAME, VALID_DATE, tags);
        assertEquals(VALID_DATE, event.getTime());
    }

    @Test
    public void getEventTags() {
        // TODO: Implement tagging for events
    }

    @Test
    public void isSameEvent_success() {
        Event event1 = new Event(VALID_NAME, VALID_DATE, tags);
        Event event2 = new Event(VALID_NAME, VALID_DATE, tags);
        assertTrue(event1.isSameEvent(event2));
    }

    @Test
    public void isSameEvent_failure() {
        Event event1 = new Event(VALID_NAME, VALID_DATE, tags);
        Event event2 = new Event(EventUtil.makeEventName("ES2660"), VALID_DATE, tags);
        assertFalse(event1.isSameEvent(event2));
    }
}
