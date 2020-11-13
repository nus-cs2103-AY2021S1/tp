package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTag.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTags.MYFILE;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.FileAddress;
import seedu.address.model.tag.TagName;

public class JsonAdaptedTagTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_FILE_ADDRESS = " ";
    private static final String INVALID_LABEL = "#project";

    private static final String VALID_TAG_NAME = MYFILE.getTagName().toString();

    private static final String VALID_FILE_ADDRESS = MYFILE.getFileAddress().toString();

    private static final Set<JsonAdaptedLabel> VALID_LABELS = MYFILE.getLabels().stream()
            .map(JsonAdaptedLabel::new)
            .collect(Collectors.toSet());

    @Test
    public void toModelType_validTagDetails_returnsPerson() throws Exception {
        JsonAdaptedTag tag = new JsonAdaptedTag(MYFILE);
        assertEquals(MYFILE, tag.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedTag tag =
                new JsonAdaptedTag(INVALID_NAME, VALID_FILE_ADDRESS, VALID_LABELS);
        String expectedMessage = TagName.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tag::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedTag tag = new JsonAdaptedTag(null, VALID_FILE_ADDRESS, VALID_LABELS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TagName.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tag::toModelType);
    }



    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedTag tag =
                new JsonAdaptedTag(VALID_TAG_NAME, INVALID_FILE_ADDRESS, VALID_LABELS);
        String expectedMessage = FileAddress.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, tag::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedTag tag = new JsonAdaptedTag(VALID_TAG_NAME, null, VALID_LABELS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, FileAddress.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, tag::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        Set<JsonAdaptedLabel> invalidLabels = new HashSet<>(VALID_LABELS);
        invalidLabels.add(new JsonAdaptedLabel(INVALID_LABEL));
        JsonAdaptedTag tag =
                new JsonAdaptedTag(VALID_TAG_NAME, VALID_FILE_ADDRESS, invalidLabels);
        assertThrows(IllegalValueException.class, tag::toModelType);
    }
}
