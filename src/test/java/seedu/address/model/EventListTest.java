package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.event.EventUtil.VALID_EVENT;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.event.Event;
import seedu.address.testutil.event.EventUtil;

public class EventListTest {
    private static final EventList eventList = new EventList();
    private static final EventList emptyList = new EventList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), eventList.getEventList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> eventList.resetData(null));
    }

    @Test
    public void copyData_from_existingList() {
        eventList.resetData(emptyList);
        eventList.addEvent(VALID_EVENT);
        EventList copy = new EventList(eventList);
        assertEquals(eventList, copy);
    }

    @Test
    public void hasEvent_from_existingList() {
        eventList.resetData(emptyList);
        eventList.addEvent(VALID_EVENT);
        assertTrue(eventList.hasEvent(VALID_EVENT));
    }

    @Test
    public void clearList_from_existingList() {
        eventList.resetData(emptyList);
        EventList empty = new EventList();
        eventList.resetData(empty);
        assertEquals(eventList, empty);
    }

    @Test
    public void addEvent_to_existingList() {
        eventList.resetData(emptyList);
        eventList.addEvent(VALID_EVENT);
        assertTrue(eventList.hasEvent(VALID_EVENT));
    }

    @Test
    public void setEvent_from_existingList() {
        eventList.resetData(emptyList);
        eventList.addEvent(VALID_EVENT);
        Event newEvent = EventUtil.makeEvent(EventUtil.makeEventName("Hello"),
                EventUtil.makeEventTime("2-3-2021 1300"));
        eventList.setEvent(VALID_EVENT, newEvent);
        assertTrue(eventList.hasEvent(newEvent));
        assertFalse(eventList.hasEvent(VALID_EVENT));
    }

    @Test
    public void removeEvent_from_existingList() {
        eventList.resetData(emptyList);
        eventList.addEvent(VALID_EVENT);
        eventList.removeEvent(VALID_EVENT);
        assertFalse(eventList.hasEvent(VALID_EVENT));
    }
}
