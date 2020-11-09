package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedFlashcard.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFlashcards.HEAPING;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Definition;
import seedu.address.model.person.Title;

public class JsonAdaptedFlashcardTest {
    private static final String INVALID_NAME = "H@eaping";
    private static final String INVALID_DEFINITION = " ";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_PRIORITY = "Higher";

    private static final String VALID_NAME = HEAPING.getTitle().toString();
    private static final String VALID_DEFINITION = HEAPING.getDefinition().toString();
    private static final String VALID_PRIORITY = HEAPING.getDefinition().toString();

    private static final List<JsonAdaptedTag> VALID_TAGS = HEAPING.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedFlashcard flashcard = new JsonAdaptedFlashcard(HEAPING);
        assertEquals(HEAPING, flashcard.toModelType());
    }

    @Test
    public void toModelType_invalidTitle_throwsIllegalValueException() {
        JsonAdaptedFlashcard flashcard =
                new JsonAdaptedFlashcard(INVALID_NAME, VALID_DEFINITION, VALID_PRIORITY, VALID_TAGS);
        String expectedMessage = Title.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_nullTitle_throwsIllegalValueException() {
        JsonAdaptedFlashcard flashcard = new JsonAdaptedFlashcard(null, VALID_DEFINITION, VALID_PRIORITY, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedFlashcard flashcard =
                new JsonAdaptedFlashcard(VALID_NAME, INVALID_DEFINITION, VALID_PRIORITY, VALID_TAGS);
        String expectedMessage = Definition.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedFlashcard flashcard = new JsonAdaptedFlashcard(VALID_NAME, null, VALID_PRIORITY, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Definition.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedFlashcard flashcard =
                new JsonAdaptedFlashcard(VALID_NAME, VALID_DEFINITION, VALID_PRIORITY, invalidTags);
        assertThrows(IllegalValueException.class, flashcard::toModelType);
    }

}
