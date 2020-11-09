package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.WORKSHOP;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.task.Event;
import seedu.address.model.task.MeetingLink;
import seedu.address.model.task.Recurrence;

public class JsonAdaptedEventTest {
    public static final String INVALID_LINK_TIME = "05-20-2020 0900";
    public static final String INVALID_LINK_URL = "invalid";
    public static final String INVALID_TAG = "#CS2100";
    public static final String BLANK = "";
    public static final String VALID_DESCRIPTION = WORKSHOP.getDescription();
    public static final LocalDateTime VALID_START_DATE_TIME = WORKSHOP.getStart();
    public static final LocalDateTime VALID_END_DATE_TIME = WORKSHOP.getEnd();
    public static final Boolean VALID_IS_DONE = true;
    public static final String VALID_LINK_DESCRIPTION = "Workshop meeting link";
    public static final String VALID_LINK_URL = "https://www.isvalidlink.com";
    public static final String VALID_LINK_TIME = "20-05-2020 0900";
    public static final MeetingLink VALID_MEETING_LINK = new MeetingLink(VALID_LINK_DESCRIPTION,
            VALID_LINK_URL, VALID_LINK_TIME);
    public static final Recurrence VALID_RECURRENCE = new Recurrence(1,
            "day");
    public static final JsonAdaptedRecurrence VALID_JSON_ADAPTED_RECURRENCE =
            new JsonAdaptedRecurrence(VALID_RECURRENCE);
    public static final List<JsonAdaptedTag> VALID_TAGS = WORKSHOP.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validEventDetails_returnsEvent() throws Exception {
        JsonAdaptedEvent event = new JsonAdaptedEvent(WORKSHOP);
        assertEquals(WORKSHOP, event.toModelType());
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(null, VALID_IS_DONE, VALID_START_DATE_TIME, VALID_END_DATE_TIME,
                VALID_LINK_DESCRIPTION,
                VALID_LINK_URL, VALID_LINK_TIME, VALID_JSON_ADAPTED_RECURRENCE, VALID_TAGS);
        assertThrows(IllegalValueException.class, event::toModelType);
    }
    @Test
    public void toModelType_nullIsDone_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_DESCRIPTION, null, VALID_START_DATE_TIME,
                VALID_END_DATE_TIME,
                VALID_LINK_DESCRIPTION,
                VALID_LINK_URL, VALID_LINK_TIME, VALID_JSON_ADAPTED_RECURRENCE, VALID_TAGS);
        assertThrows(IllegalValueException.class, event::toModelType);
    }
    @Test
    public void toModelType_nullStartDateTime_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_DESCRIPTION, VALID_IS_DONE, null,
                VALID_END_DATE_TIME,
                VALID_LINK_DESCRIPTION,
                VALID_LINK_URL, VALID_LINK_TIME, VALID_JSON_ADAPTED_RECURRENCE, VALID_TAGS);
        assertThrows(IllegalValueException.class, event::toModelType);
    }
    @Test
    public void toModelType_nullEndDateTime_throwsIllegalValueException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_DESCRIPTION, VALID_IS_DONE, VALID_START_DATE_TIME,
                null,
                VALID_LINK_DESCRIPTION,
                VALID_LINK_URL, VALID_LINK_TIME, VALID_JSON_ADAPTED_RECURRENCE, VALID_TAGS);
        assertThrows(IllegalValueException.class, event::toModelType);
    }
    @Test
    public void toModelType_nullLinkDescription_returnsEvent() throws IllegalValueException {
        Event modelEvent = new Event(VALID_IS_DONE, VALID_DESCRIPTION, VALID_START_DATE_TIME, VALID_END_DATE_TIME,
                VALID_RECURRENCE, WORKSHOP.getTags());
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_DESCRIPTION, VALID_IS_DONE, VALID_START_DATE_TIME,
                VALID_END_DATE_TIME,
                null,
                VALID_LINK_URL, VALID_LINK_TIME, VALID_JSON_ADAPTED_RECURRENCE, VALID_TAGS);
        assertEquals(modelEvent, event.toModelType());
    }
    @Test
    public void toModelType_nullLinkUrl_returnsEvent() throws IllegalValueException {
        Event modelEvent = new Event(VALID_IS_DONE, VALID_DESCRIPTION, VALID_START_DATE_TIME, VALID_END_DATE_TIME,
                VALID_RECURRENCE, WORKSHOP.getTags());
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_DESCRIPTION, VALID_IS_DONE, VALID_START_DATE_TIME,
                VALID_END_DATE_TIME,
                VALID_LINK_DESCRIPTION,
                null, VALID_LINK_TIME, VALID_JSON_ADAPTED_RECURRENCE, VALID_TAGS);
        assertEquals(modelEvent, event.toModelType());
    }
    @Test
    public void toModelType_blankLinkUrl_throwsIllegalArgumentException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_DESCRIPTION, VALID_IS_DONE, VALID_START_DATE_TIME,
                VALID_END_DATE_TIME,
                VALID_LINK_DESCRIPTION,
                BLANK, VALID_LINK_TIME, VALID_JSON_ADAPTED_RECURRENCE, VALID_TAGS);
        assertThrows(IllegalArgumentException.class, event::toModelType);
    }
    @Test
    public void toModelType_invalidLinkUrl_throwsIllegalArgumentException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_DESCRIPTION, VALID_IS_DONE, VALID_START_DATE_TIME,
                VALID_END_DATE_TIME,
                VALID_LINK_DESCRIPTION,
                INVALID_LINK_URL, VALID_LINK_TIME, VALID_JSON_ADAPTED_RECURRENCE, VALID_TAGS);
        assertThrows(IllegalArgumentException.class, event::toModelType);
    }
    @Test
    public void toModelType_invalidLinkTime_throwsIllegalArgumentException() {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_DESCRIPTION, VALID_IS_DONE, VALID_START_DATE_TIME,
                VALID_END_DATE_TIME,
                VALID_LINK_DESCRIPTION,
                INVALID_LINK_URL, INVALID_LINK_TIME, VALID_JSON_ADAPTED_RECURRENCE, VALID_TAGS);
        assertThrows(IllegalArgumentException.class, event::toModelType);
    }
    @Test
    public void toModelType_nullRecurrence_returnsEvent() throws IllegalValueException {
        Event modelEvent = new Event(VALID_IS_DONE, VALID_DESCRIPTION, VALID_START_DATE_TIME, VALID_END_DATE_TIME,
                VALID_MEETING_LINK, WORKSHOP.getTags());
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_DESCRIPTION, VALID_IS_DONE, VALID_START_DATE_TIME,
                VALID_END_DATE_TIME,
                VALID_LINK_DESCRIPTION,
                VALID_LINK_URL, VALID_LINK_TIME, null, VALID_TAGS);
        assertEquals(modelEvent, event.toModelType());
    }
    @Test
    public void toModelType_nullRecurrenceNullLink_returnsEvent() throws IllegalValueException {
        Event modelEvent = new Event(VALID_IS_DONE, VALID_DESCRIPTION, VALID_START_DATE_TIME, VALID_END_DATE_TIME,
                WORKSHOP.getTags());
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_DESCRIPTION, VALID_IS_DONE, VALID_START_DATE_TIME,
                VALID_END_DATE_TIME,
                VALID_LINK_DESCRIPTION,
                null, VALID_LINK_TIME, null, VALID_TAGS);
        assertEquals(modelEvent, event.toModelType());
    }
    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_DESCRIPTION, VALID_IS_DONE, VALID_START_DATE_TIME,
                VALID_END_DATE_TIME,
                VALID_LINK_DESCRIPTION,
                VALID_LINK_URL, VALID_LINK_TIME, VALID_JSON_ADAPTED_RECURRENCE, invalidTags);
        assertThrows(IllegalValueException.class, event::toModelType);
    }
}
