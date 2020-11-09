package seedu.address.storage.event;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedEvent.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.event.EventUtil.VALID_EVENT;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventTime;
import seedu.address.storage.JsonAdaptedEvent;
import seedu.address.storage.JsonAdaptedTag;

public class JsonAdaptedEventTest {

    private static final String INVALID_NAME = "@Assignments";
    private static final String INVALID_DATE = "234-12-2002 1200";

    private static final String VALID_NAME = "Assignment";
    private static final String VALID_DATE = "1-2-2002 1000";
    private static final JsonAdaptedTag VALID_TAG = new JsonAdaptedTag("Valid");
    private static final List<JsonAdaptedTag> LIST_OF_VALID_TAGS = List.of(VALID_TAG);

    @Test
    public void toModelType_validEventDetails_returnsEvent() throws Exception {
        JsonAdaptedEvent event = new JsonAdaptedEvent(VALID_EVENT);
        assertEquals(VALID_EVENT, event.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(INVALID_NAME, VALID_DATE, LIST_OF_VALID_TAGS);
        String expectedMessage = EventName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedEvent person = new JsonAdaptedEvent(null, VALID_DATE, LIST_OF_VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EventName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedEvent event =
                new JsonAdaptedEvent(VALID_NAME, INVALID_DATE, LIST_OF_VALID_TAGS);
        String expectedMessage = EventTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, event::toModelType);
    }

    //TODO: To be implemented when Events can handle Tags
    /*@Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedModule person =
                new JsonAdaptedModule(VALID_NAME, VALID_EMAIL, invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
    }*/
}
