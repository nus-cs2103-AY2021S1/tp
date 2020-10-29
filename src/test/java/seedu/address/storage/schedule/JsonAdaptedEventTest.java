package seedu.address.storage.schedule;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.Messages;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventRecurrence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.ALICE_CLASS_EVENT;
import static seedu.address.testutil.TypicalEvents.TODO_EVENT;

public class JsonAdaptedEventTest {

    private static final String INVALID_EVENT_NAME = " "; // blank
    private static final String INVALID_START_DATE_TIME = "2020-12-21";
    private static final String INVALID_END_DATE_TIME = "19:00";
    private static final String INVALID_EVENT_RECURRENCE = "!@#";

    private static final String VALID_EVENT_NAME = TODO_EVENT.getEventName();
    private static final String VALID_START_DATE_TIME = TODO_EVENT.getEventStartDateTime().toString();
    private static final String VALID_END_DATE_TIME = TODO_EVENT.getEventEndDateTime().toString();
    private static final String VALID_UNIQUE_IDENTIFIER = TODO_EVENT.getUniqueIdentifier();
    private static final String VALID_EVENT_RECURRENCE = TODO_EVENT.getRecurrence().getRecurrenceString();


    @Test
    public void toModelType_validEvent_returnsEvent() throws Exception {
        JsonAdaptedEvent jsonAdaptedEvent = new JsonAdaptedEvent(ALICE_CLASS_EVENT);
        assertEquals(ALICE_CLASS_EVENT, jsonAdaptedEvent.toModelType());
    }

    @Test
    public void toModelType_invalidEventName_throwsNullPointerException() {
        JsonAdaptedEvent jsonAdaptedEvent = new JsonAdaptedEvent(INVALID_EVENT_NAME, VALID_START_DATE_TIME,
                VALID_END_DATE_TIME, VALID_UNIQUE_IDENTIFIER, VALID_EVENT_RECURRENCE);
        String expectedMessage = Event.INVALID_EVENT_NAME_MSG;
        assertThrows(IllegalValueException.class, expectedMessage, jsonAdaptedEvent::toModelType);
    }

    @Test
    public void toModelType_nullEventName_throwsNullPointerException() {
        JsonAdaptedEvent jsonAdaptedEvent = new JsonAdaptedEvent(null, VALID_START_DATE_TIME,
                VALID_END_DATE_TIME, VALID_UNIQUE_IDENTIFIER, VALID_EVENT_RECURRENCE);
        String expectedMessage = String.format(JsonAdaptedEvent.MISSING_FIELD_MESSAGE_FORMAT, "eventName");
        assertThrows(IllegalValueException.class, expectedMessage, jsonAdaptedEvent::toModelType);
    }

    @Test
    public void toModelType_nullStartDateTime_throwsNullPointerException() {
        JsonAdaptedEvent jsonAdaptedEvent = new JsonAdaptedEvent(VALID_EVENT_NAME, null,
                VALID_END_DATE_TIME, VALID_UNIQUE_IDENTIFIER, VALID_EVENT_RECURRENCE);
        String expectedMessage = String.format(JsonAdaptedEvent.MISSING_FIELD_MESSAGE_FORMAT, "eventStartDateTime");
        assertThrows(IllegalValueException.class, expectedMessage, jsonAdaptedEvent::toModelType);
    }

    @Test
    public void toModelType_invalidStartDateTime_throwsNullPointerException() {
        JsonAdaptedEvent jsonAdaptedEvent = new JsonAdaptedEvent(VALID_EVENT_NAME, INVALID_START_DATE_TIME,
                VALID_END_DATE_TIME, VALID_UNIQUE_IDENTIFIER, VALID_EVENT_RECURRENCE);
        String expectedMessage = Event.INCORRECT_DATE_TIME_MESSAGE;
        assertThrows(IllegalValueException.class, expectedMessage, jsonAdaptedEvent::toModelType);
    }

    @Test
    public void toModelType_nullEndDateTime_throwsNullPointerException() {
        JsonAdaptedEvent jsonAdaptedEvent = new JsonAdaptedEvent(VALID_EVENT_NAME, VALID_START_DATE_TIME,
                null, VALID_UNIQUE_IDENTIFIER, VALID_EVENT_RECURRENCE);
        String expectedMessage = String.format(JsonAdaptedEvent.MISSING_FIELD_MESSAGE_FORMAT, "eventEndDateTime");
        assertThrows(IllegalValueException.class, expectedMessage, jsonAdaptedEvent::toModelType);
    }

    @Test
    public void toModelType_invalidEndDateTime_throwsNullPointerException() {
        JsonAdaptedEvent jsonAdaptedEvent = new JsonAdaptedEvent(VALID_EVENT_NAME, VALID_START_DATE_TIME,
                INVALID_END_DATE_TIME, VALID_UNIQUE_IDENTIFIER, VALID_EVENT_RECURRENCE);
        String expectedMessage = Event.INCORRECT_DATE_TIME_MESSAGE;
        assertThrows(IllegalValueException.class, expectedMessage, jsonAdaptedEvent::toModelType);
    }

    @Test
    public void toModelType_nullUniqueIdentifier_throwsNullPointerException() {
        JsonAdaptedEvent jsonAdaptedEvent = new JsonAdaptedEvent(VALID_EVENT_NAME, VALID_START_DATE_TIME,
                VALID_END_DATE_TIME, null, VALID_EVENT_RECURRENCE);
        String expectedMessage = String.format(JsonAdaptedEvent.MISSING_FIELD_MESSAGE_FORMAT, "uniqueIdentifier");
        assertThrows(IllegalValueException.class, expectedMessage, jsonAdaptedEvent::toModelType);
    }

    @Test
    public void toModelType_nullEventRecurrence_throwsNullPointerException() {
        JsonAdaptedEvent jsonAdaptedEvent = new JsonAdaptedEvent(VALID_EVENT_NAME, VALID_START_DATE_TIME,
                VALID_END_DATE_TIME, VALID_UNIQUE_IDENTIFIER, null);
        String expectedMessage = String.format(JsonAdaptedEvent.MISSING_FIELD_MESSAGE_FORMAT, "eventRecurrence");
        assertThrows(IllegalValueException.class, expectedMessage, jsonAdaptedEvent::toModelType);
    }

    @Test
    public void toModelType_invalidEventRecurrence_throwsNullPointerException() {
        JsonAdaptedEvent jsonAdaptedEvent = new JsonAdaptedEvent(VALID_EVENT_NAME, INVALID_START_DATE_TIME,
                VALID_END_DATE_TIME, VALID_UNIQUE_IDENTIFIER, INVALID_EVENT_RECURRENCE);
        String expectedMessage = EventRecurrence.INCORRECT_EVENT_RECURRENCE_MESSAGE;
        assertThrows(IllegalValueException.class, expectedMessage, jsonAdaptedEvent::toModelType);
    }
}
