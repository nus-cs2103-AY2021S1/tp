package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTags.CS1101S;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.MeetingLink;
import seedu.address.model.tag.TagName;

public class JsonAdaptedTagTest {
    private static final String INVALID_TAG_NAME = " ";
    private static final String INVALID_MEETING_LINK = "meeting_link";

    private static final String VALID_TAG_NAME = CS1101S.getTagName().tagName;
    private static final String VALID_MEETING_LINK = CS1101S.getMeetingLink().toString();

    @Test
    public void toModelType_validTagDetails_returnsTag() throws Exception {
        JsonAdaptedTag tag = new JsonAdaptedTag(CS1101S);
        assertEquals(CS1101S, tag.toModelType());
    }

    @Test
    public void toModelType_invalidTagName_throwsIllegalValueException() {
        JsonAdaptedTag tag =
                new JsonAdaptedTag(INVALID_TAG_NAME, null, VALID_MEETING_LINK);
        String expectedMessage = TagName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tag::toModelType);
    }

    @Test
    public void toModelType_nullTagName_throwsIllegalValueException() {
        JsonAdaptedTag tag =
                new JsonAdaptedTag(null, null, VALID_MEETING_LINK);
        String expectedMessage = String.format(JsonAdaptedTag.MISSING_FIELD_MESSAGE_FORMAT,
                TagName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tag::toModelType);;
    }

    @Test
    public void toModelType_invalidMeetingLink_throwsIllegalValueException() {
        JsonAdaptedTag tag =
                new JsonAdaptedTag(VALID_TAG_NAME, null, INVALID_MEETING_LINK);
        String expectedMessage = MeetingLink.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tag::toModelType);
    }

    @Test
    public void toModelType_nullMeetingLink_throwsIllegalValueException() {
        JsonAdaptedTag tag =
                new JsonAdaptedTag(VALID_TAG_NAME, null, null);
        String expectedMessage = String.format(JsonAdaptedTag.MISSING_FIELD_MESSAGE_FORMAT,
                MeetingLink.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tag::toModelType);;
    }
}
