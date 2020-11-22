package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.DINNER;
import static seedu.address.testutil.TypicalEvents.LUNCH;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.event.exceptions.DuplicateEventException;
import seedu.address.model.event.exceptions.EventNotFoundException;
import seedu.address.model.person.Person;
import seedu.address.testutil.EventBuilder;

class UniqueEventListTest {

    private UniqueEventList uniqueEventList;

    @BeforeEach
    void init() {
        List<Event> eventsList = Collections.singletonList(LUNCH);
        uniqueEventList = new UniqueEventList();
        uniqueEventList.setEvents(eventsList);
    }

    @Test
    void contains_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.contains(null));
    }

    @Test
    void contains_eventInList_returnsTrue() {
        assertTrue(uniqueEventList.contains(LUNCH));
    }

    @Test
    void contains_eventNotInList_returnsFalse() {
        assertFalse(uniqueEventList.contains(DINNER));
    }

    @Test
    void contains_eventWithUpdatedFields() {
        Event editedLunch = new EventBuilder(LUNCH).withEventName("Postponed Lunch").build();
        assertFalse(uniqueEventList.contains(editedLunch));

        Set<Person> attendees = new HashSet<>(Collections.singletonList(ALICE));
        Event lunchWithAttendees = new EventBuilder(LUNCH).withAttendees(attendees).build();
        assertTrue(uniqueEventList.contains(lunchWithAttendees));
    }

    @Test
    void add_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueEventList.add(null));
    }

    @Test
    void add_duplicateEvent_throwsDuplicateEventException() {
        assertThrows(DuplicateEventException.class, () ->
                uniqueEventList.add(LUNCH));
    }

    @Test
    void add_validInputs_containsNewEvent() {
        // expected
        UniqueEventList expected = new UniqueEventList();
        expected.add(LUNCH);
        expected.add(DINNER);

        uniqueEventList.add(DINNER);
        assertEquals(expected, uniqueEventList);
    }

    @Test
    void remove_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueEventList.remove(null));
    }

    @Test
    void remove_eventDoesNotExist_throwsEventNotFoundException() {
        assertThrows(EventNotFoundException.class, () ->
                uniqueEventList.remove(DINNER));
    }

    @Test
    void remove_existingEvent_removesEvent() {
        // expected
        UniqueEventList expected = new UniqueEventList();

        uniqueEventList.remove(LUNCH);
        assertEquals(expected, uniqueEventList);
    }

    @Test
    void setEvent_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueEventList.setEvent(null, LUNCH));
        assertThrows(NullPointerException.class, () ->
                uniqueEventList.setEvent(LUNCH, null));
    }

    @Test
    void setEvent_targetNotInList_throwsEventNotFoundException() {
        assertThrows(EventNotFoundException.class, () ->
                uniqueEventList.setEvent(DINNER, DINNER));
    }

    @Test
    void setEvent_editedEventEqualsTarget_success() {
        uniqueEventList.setEvent(LUNCH, LUNCH);
        assertTrue(uniqueEventList.contains(LUNCH));
    }

    @Test
    void setEvent_editedEventDifferentTarget_success() {
        // expected
        UniqueEventList expected = new UniqueEventList();
        expected.add(DINNER);

        uniqueEventList.setEvent(LUNCH, DINNER);
        assertEquals(expected, uniqueEventList);
    }

    @Test
    void setEvent_editedEventHasDifferentAttendees_success() {
        // expected
        Set<Person> attendees = new HashSet<>(Arrays.asList(ALICE, BENSON));
        Event editedLunch = new EventBuilder(LUNCH).withAttendees(attendees).build();
        UniqueEventList expected = new UniqueEventList();
        expected.add(editedLunch);

        uniqueEventList.setEvent(LUNCH, editedLunch);
        assertEquals(expected, uniqueEventList);
    }

    @Test
    void setEvent_setsExistingEventToDuplicate_throwsDuplicateEventException() {
        uniqueEventList.add(DINNER);

        // attempt to introduce 2 dinners into list
        assertThrows(DuplicateEventException.class, () ->
                uniqueEventList.setEvent(LUNCH, DINNER));
    }

    @Test
    void setEvents_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.setEvents(null));
    }

    @Test
    void setEvents_uniqueList_replacesOwnList() {
        // expected
        UniqueEventList expected = new UniqueEventList();
        expected.add(DINNER);

        List<Event> eventsList = Collections.singletonList(DINNER);
        uniqueEventList.setEvents(eventsList);
        assertEquals(expected, uniqueEventList);
    }

    @Test
    void setEvents_listWithDuplicates_throwsDuplicateEventException() {
        List<Event> eventsList = Arrays.asList(LUNCH, DINNER, LUNCH);
        assertThrows(DuplicateEventException.class, () ->
                uniqueEventList.setEvents(eventsList));
    }

    @Test
    void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueEventList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    void equals_validInput_success() {
        // expected
        UniqueEventList expected = new UniqueEventList();
        expected.add(LUNCH);

        assertEquals(expected, uniqueEventList);
    }

    @Test
    void hashCode_sameInput() {
        // expected
        UniqueEventList expected = new UniqueEventList();
        expected.add(LUNCH);
        int expectedHashCode = expected.hashCode();

        assertEquals(expectedHashCode, uniqueEventList.hashCode());
    }

    @Test
    void iterator_success() {
        for (Event e : uniqueEventList) {
            assertNotNull(e);
        }
    }
}
