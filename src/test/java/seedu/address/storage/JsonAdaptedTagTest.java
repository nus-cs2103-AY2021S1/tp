package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTag.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTags.BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.FileAddress;
import seedu.address.model.person.TagName;

public class JsonAdaptedTagTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_FILE_ADDRESS = " ";

    private static final String VALID_TAG_NAME = BENSON.getTagName().toString();

    private static final String VALID_FILE_ADDRESS = BENSON.getFileAddress().toString();

    @Test
    public void toModelType_validTagDetails_returnsPerson() throws Exception {
        JsonAdaptedTag person = new JsonAdaptedTag(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedTag person =
                new JsonAdaptedTag(INVALID_NAME, VALID_FILE_ADDRESS);
        String expectedMessage = TagName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedTag person = new JsonAdaptedTag(null, VALID_FILE_ADDRESS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TagName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }



    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedTag person =
                new JsonAdaptedTag(VALID_TAG_NAME, INVALID_FILE_ADDRESS);
        String expectedMessage = FileAddress.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedTag person = new JsonAdaptedTag(VALID_TAG_NAME, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, FileAddress.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
}
