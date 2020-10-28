package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.ALICE_CLASS_EVENT;
import static seedu.address.testutil.TypicalEvents.TODO_EVENT;
import static seedu.address.testutil.TypicalEvents.dateTimeBeforeAliceEndTime;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.model.student.Name;
import seedu.address.testutil.EventBuilder;

public class EventTest {
    public static final String VALID_EVENT_NAME = "Valid event name";
    public static final LocalDateTime VALID_EVENT_START_DATE_TIME = LocalDateTime.of(2020, 1, 27, 0, 0);
    public static final LocalDateTime VALID_EVENT_END_DATE_TIME = LocalDateTime.of(2020, 1, 28, 0, 1);
    public static final String VALID_UNIQUE_IDENTIFIER = "This is a valid identifier";
    public static final EventRecurrence VALID_EVENT_RECURRENCE = EventRecurrence.DAILY;


    @Test
    public void constructor_nullEventName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Event(null,
                VALID_EVENT_START_DATE_TIME, VALID_EVENT_END_DATE_TIME,
                VALID_UNIQUE_IDENTIFIER, VALID_EVENT_RECURRENCE));
    }

    @Test
    public void constructor_nullEventStartDateTime_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Event(VALID_EVENT_NAME,
                null, VALID_EVENT_END_DATE_TIME,
                VALID_UNIQUE_IDENTIFIER, VALID_EVENT_RECURRENCE));
    }

    @Test
    public void constructor_nullEventEndDateTime_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Event(VALID_EVENT_NAME,
                VALID_EVENT_START_DATE_TIME, null,
                VALID_UNIQUE_IDENTIFIER, VALID_EVENT_RECURRENCE));
    }

    @Test
    public void constructor_nullIdentifier_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Event(VALID_EVENT_NAME,
                VALID_EVENT_START_DATE_TIME, VALID_EVENT_END_DATE_TIME,
                null, VALID_EVENT_RECURRENCE));
    }

    @Test
    public void constructor_nullRecurrence_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Event(VALID_EVENT_NAME,
                VALID_EVENT_START_DATE_TIME, VALID_EVENT_END_DATE_TIME,
                VALID_UNIQUE_IDENTIFIER, null));
    }

    @Test
    public void constructor_invalidEventDateTimeRange_throwsIllegalArgumentException() {
        LocalDateTime invalidStartTime = LocalDateTime.of(2020, 1, 29 , 0, 0);
        LocalDateTime invalidEndTime = LocalDateTime.of(2020, 1, 28 , 23, 59);

        assertThrows(IllegalArgumentException.class, () -> new Event(VALID_EVENT_NAME,
                invalidStartTime, invalidEndTime,
                VALID_UNIQUE_IDENTIFIER, VALID_EVENT_RECURRENCE));
    }

    @Test
    public void constructor_blankEventName_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Event("   ", VALID_EVENT_START_DATE_TIME,
                VALID_EVENT_END_DATE_TIME,
                VALID_UNIQUE_IDENTIFIER, VALID_EVENT_RECURRENCE));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Event aliceClassEventCopy = new EventBuilder(ALICE_CLASS_EVENT).build();
        assertTrue(ALICE_CLASS_EVENT.equals(aliceClassEventCopy));

        // same object -> returns true
        assertTrue(ALICE_CLASS_EVENT.equals(ALICE_CLASS_EVENT));

        // null -> returns false
        assertFalse(ALICE_CLASS_EVENT.equals(null));

        // different type -> returns false
        assertFalse(ALICE_CLASS_EVENT.equals(5));

        // different event -> returns false
        assertFalse(ALICE_CLASS_EVENT.equals(TODO_EVENT));

        // different event name -> returns false
        Event editedAliceClassEvent = new EventBuilder(ALICE_CLASS_EVENT)
                .withEventName(TODO_EVENT.getEventName()).build();
        assertFalse(ALICE_CLASS_EVENT.equals(editedAliceClassEvent));

        // different startTime -> returns false
        editedAliceClassEvent = new EventBuilder(ALICE_CLASS_EVENT)
                .withEventStartDateTime(dateTimeBeforeAliceEndTime).build();
        assertFalse(ALICE_CLASS_EVENT.equals(editedAliceClassEvent));

        // different endTime -> returns false
        editedAliceClassEvent =
                new EventBuilder(ALICE_CLASS_EVENT).withEventEndDateTime(dateTimeBeforeAliceEndTime).build();
        assertFalse(ALICE_CLASS_EVENT.equals(editedAliceClassEvent));

        // different recurrence -> return false
        editedAliceClassEvent = new EventBuilder(ALICE_CLASS_EVENT)
                .withRecurrence(TODO_EVENT.getRecurrence()).build();
        assertFalse(ALICE_CLASS_EVENT.equals(editedAliceClassEvent));

        // different uniqueIdentifier -> return false
        editedAliceClassEvent = new EventBuilder(ALICE_CLASS_EVENT)
                .withUniqueIdentifier(TODO_EVENT.getUniqueIdentifier()).build();
        assertFalse(ALICE_CLASS_EVENT.equals(editedAliceClassEvent));
    }

    @Test
    public void isValidEventName() {
        // null name
        assertThrows(NullPointerException.class, () -> Event.isValidEventName(null));

        // invalid name
        assertFalse(Event.isValidEventName("")); // empty string
        assertFalse(Event.isValidEventName(" ")); // spaces only

        // valid name
        assertTrue(Name.isValidName("peter jack")); // alphabets only
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(Name.isValidName("Capital Tan")); // with capital letters
        assertTrue(Name.isValidName("David Roger Jackson Ray Jr 2nd"));
        assertTrue(Event.isValidEventName("^")); // only non-alphanumeric characters
        assertTrue(Event.isValidEventName("peter*")); // contains non-alphanumeric characters
    }

    @Test
    public void isValidEventStartEndTime() {
        LocalDateTime earlierDateTime = LocalDateTime.of(2020, 10, 29, 23, 0);
        LocalDateTime laterDateTime = LocalDateTime.of(2020, 10, 29, 23, 30);

        // null test
        assertThrows(NullPointerException.class, () -> Event.isValidEventStartAndEndTime(null, laterDateTime));
        assertThrows(NullPointerException.class, () -> Event.isValidEventStartAndEndTime(earlierDateTime, null));

        assertTrue(Event.isValidEventStartAndEndTime(earlierDateTime, laterDateTime)); // valid start and end

        assertFalse(Event.isValidEventStartAndEndTime(laterDateTime, earlierDateTime)); // start later than end

        assertFalse(Event.isValidEventStartAndEndTime(earlierDateTime, earlierDateTime)); // same time

        assertTrue(Event.isValidEventStartAndEndTime(earlierDateTime, earlierDateTime.plusSeconds(1))); // 1 sec

    }

    @Test
    public void isSameEvent() {
        Event event1 = new EventBuilder(ALICE_CLASS_EVENT).build();

        // same event return true
        assertTrue(event1.isSameEvent(ALICE_CLASS_EVENT));

        // event with different unique id return true
        Event differentUniqueIdentifier = new EventBuilder(ALICE_CLASS_EVENT)
                .withUniqueIdentifier("randomUid").build();
        assertTrue(event1.isSameEvent(differentUniqueIdentifier));

        // different name return false
        Event differentNameEvent = new EventBuilder(ALICE_CLASS_EVENT)
                .withEventName(ALICE_CLASS_EVENT.getEventName() + "abc").build();
        assertFalse(event1.isSameEvent(differentNameEvent));

        // different start date time return false
        Event differentStartEvent =
                new EventBuilder(ALICE_CLASS_EVENT)
                        .withEventStartDateTime(ALICE_CLASS_EVENT.getEventStartDateTime().minusDays(2))
                        .build();
        assertFalse(event1.isSameEvent(differentStartEvent));

        // different end date time return false
        Event differentEndEvent = new EventBuilder(ALICE_CLASS_EVENT)
                .withEventEndDateTime(ALICE_CLASS_EVENT.getEventEndDateTime().plusMinutes(60))
                .build();
        assertFalse(event1.isSameEvent(differentEndEvent));
    }

}
