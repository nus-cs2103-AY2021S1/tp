package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.LUNCH;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.EventBuilder;

class EventTest {
    private EventName eventName = new EventName("Hall dinner");
    private EventDate date = new EventDate("01/01/2020 15:00");
    private Location location = new Location("Dining Hall");
    private Description description = new Description("Have dinner together");
    private HashSet<Person> attendees = new HashSet<>(Arrays.asList(ALICE, BENSON, CARL));

    @Test
    public void constructor_withoutAttendees() {
        // null
        assertThrows(NullPointerException.class, () -> new Event(null, date, location, description));
        assertThrows(NullPointerException.class, () -> new Event(eventName, null, location, description));
        assertThrows(NullPointerException.class, () -> new Event(eventName, date, null, description));
        assertThrows(NullPointerException.class, () -> new Event(eventName, date, location, null));

        assertNotNull(new Event(eventName, date, location, description));
    }

    @Test
    public void constructor_withAttendees() {
        // null
        assertThrows(NullPointerException.class, () -> new Event(null, date, location, description, attendees));
        assertThrows(NullPointerException.class, () -> new Event(eventName, null, location, description, attendees));
        assertThrows(NullPointerException.class, () -> new Event(eventName, date, null, description, attendees));
        assertThrows(NullPointerException.class, () -> new Event(eventName, date, location, null, attendees));
        assertThrows(NullPointerException.class, () -> new Event(eventName, date, location, description, null));

        assertNotNull(new Event(eventName, date, location, description, attendees));
    }

    @Test
    public void isSameEvent_sameEvent_returnTrue() {
        Event lunchCopy = new EventBuilder(LUNCH).build();
        assertTrue(LUNCH.isSameEvent(lunchCopy));

        // different attendees -> return true
        Set<Person> attendees = new HashSet<>();
        Event editedLunch = new EventBuilder(LUNCH).withAttendees(attendees).build();
        assertTrue(LUNCH.isSameEvent(editedLunch));
    }

    @Test
    public void isSameEvent_null_returnsFalse() {
        assertFalse(LUNCH.isSameEvent(null));
    }

    @Test
    public void isSameEvent_differentEvent_returnFalse() {
        Event editedLunch = new EventBuilder(LUNCH).build();
        assert(LUNCH.isSameEvent(editedLunch)) : "Lunch event is not the same to begin with";

        editedLunch = new EventBuilder(LUNCH).withEventName("Postponed Lunch").build();
        assertFalse(LUNCH.isSameEvent(editedLunch));

        editedLunch = new EventBuilder(LUNCH).withDate("12/12/2022 00:00").build();
        assertFalse(LUNCH.isSameEvent(editedLunch));

        editedLunch = new EventBuilder(LUNCH).withLocation("Cool Spot").build();
        assertFalse(LUNCH.isSameEvent(editedLunch));
    }

    @Test
    public void equals_sameEvent_returnTrue() {
        Event editedLunch = new EventBuilder(LUNCH).build();
        assertTrue(LUNCH.equals(editedLunch));

        editedLunch = new EventBuilder(LUNCH).withDescription("Blah blah blah").build();
        assertTrue(LUNCH.isSameEvent(editedLunch));
    }

    @Test
    public void equals_null_returnsFalse() {
        assertFalse(LUNCH.equals(null));
    }

    @Test
    public void equals_differentEvent_returnFalse() {
        Event editedLunch = new EventBuilder(LUNCH).build();
        assert(LUNCH.equals(editedLunch)) : "Lunch event was not equals to begin with";

        editedLunch = new EventBuilder(LUNCH).withEventName("Postponed lunhc").build();
        assertFalse(LUNCH.equals(editedLunch));

        editedLunch = new EventBuilder(LUNCH).withDate("12/12/2030 00:00").build();
        assertFalse(LUNCH.equals(editedLunch));

        editedLunch = new EventBuilder(LUNCH).withDescription("Blah blah blah.").build();
        assertFalse(LUNCH.equals(editedLunch));

        editedLunch = new EventBuilder(LUNCH).withLocation("Far far away").build();
        assertFalse(LUNCH.equals(editedLunch));

        // different attendees -> return false

        editedLunch = new EventBuilder(LUNCH).withAttendees(attendees).build();
        assertFalse(LUNCH.equals(editedLunch));
    }

    @Test
    public void hashCode_sameInput() {
        int testHashCode = new EventBuilder(LUNCH).build().hashCode();
        assertEquals(testHashCode, LUNCH.hashCode());
    }

    @Test
    public void toString_sameInput() {
        String testString = "Hall Lunch\nEvent Date: 01/01/2020 15:00\n"
            + "Location: Dining Hall\nDescription: Eat lunch together";
        System.out.println(LUNCH.toString());
        assertEquals(testString, LUNCH.toString());
    }
}
