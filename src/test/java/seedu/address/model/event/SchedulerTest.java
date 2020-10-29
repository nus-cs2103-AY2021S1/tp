package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.ALICE_CLASS_EVENT;
import static seedu.address.testutil.TypicalEvents.getTypicalScheduler;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jfxtras.icalendarfx.components.VEvent;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.ReadOnlyVEvent;
import seedu.address.model.event.exceptions.DuplicateEventException;
import seedu.address.model.event.exceptions.EventNotFoundException;
import seedu.address.testutil.EventBuilder;

public class SchedulerTest {
    private final Scheduler scheduler = new Scheduler();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), scheduler.getEventsList());
        assertEquals(Collections.emptyList(), scheduler.getVEvents());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        Scheduler newData = getTypicalScheduler();
        scheduler.resetData(newData);
        assertEquals(scheduler, newData);
    }

    @Test
    public void resetData_withDuplicateEvents_throwsDuplicateEventException() {
        // Two events with the same identity fields
        Event editedAliceClassEvent = new EventBuilder(ALICE_CLASS_EVENT).build();
        List<Event> newEvents = Arrays.asList(ALICE_CLASS_EVENT, editedAliceClassEvent);
        SchedulerStub newData = new SchedulerStub(newEvents);
        assertThrows(DuplicateEventException.class, () -> scheduler.resetData(newData));
    }

    @Test
    public void hasEvent_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> scheduler.hasEvent(null));
    }

    @Test
    public void hasEvent_eventNotInScheduler_returnsFalse() {
        assertFalse(scheduler.hasEvent(ALICE_CLASS_EVENT));
    }

    @Test
    public void hasEvent_vEventInScheduler_returnsTrue() {
        scheduler.addEvent(ALICE_CLASS_EVENT);
        assertTrue(scheduler.hasEvent(ALICE_CLASS_EVENT));
    }

    @Test
    public void hasEvent_eventWithSameIdentityFieldsInScheduler_returnsTrue() {
        scheduler.addEvent(ALICE_CLASS_EVENT);
        Event editedAliceClassEvent = new EventBuilder(ALICE_CLASS_EVENT).build();
        assertTrue(scheduler.hasEvent(editedAliceClassEvent));
    }

    @Test
    public void getVEvents_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> scheduler.getVEvents().remove(0));
    }

    // event list test
    @Test
    public void add_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> scheduler.addEvent(null));
    }

    @Test
    public void add_duplicateEvent_throwsDuplicateEventException() {
        scheduler.addEvent(ALICE_CLASS_EVENT);
        assertThrows(DuplicateEventException.class, () -> scheduler.addEvent(ALICE_CLASS_EVENT));
    }

    @Test
    public void removeEvent_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> scheduler.removeEvent(null));
    }

    @Test
    public void removeEvent_eventDoesNotExist_throwsEventNotFoundException() {
        assertThrows(EventNotFoundException.class, () -> scheduler.removeEvent(ALICE_CLASS_EVENT));
    }

    @Test
    public void removeEvent_existingEvent_removesEvent() {
        scheduler.addEvent(ALICE_CLASS_EVENT);
        scheduler.removeEvent(ALICE_CLASS_EVENT);
        Scheduler expectedScheduler = new Scheduler();
        assertEquals(expectedScheduler, scheduler);
    }

    private static class SchedulerStub implements ReadOnlyEvent, ReadOnlyVEvent {

        private final ObservableList<Event> internalList = FXCollections.observableArrayList();

        SchedulerStub(List<Event> eventList) {
            this.internalList.setAll(eventList);
        }

        @Override
        public ObservableList<VEvent> getVEvents() {
            return null;
        }

        @Override
        public List<Event> getEventsList() {
            return internalList;
        }
    }

}
