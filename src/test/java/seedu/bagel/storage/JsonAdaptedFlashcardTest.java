package seedu.bagel.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.bagel.storage.JsonAdaptedFlashcard.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.bagel.testutil.Assert.assertThrows;
import static seedu.bagel.testutil.TypicalFlashcards.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.bagel.commons.exceptions.IllegalValueException;
import seedu.bagel.model.flashcard.Description;
import seedu.bagel.model.flashcard.FlashcardSet;
import seedu.bagel.model.flashcard.Link;
import seedu.bagel.model.flashcard.Title;


public class JsonAdaptedFlashcardTest {

    private static final String INVALID_TITLE = "";
    private static final String INVALID_DESC = "";
    private static final String INVALID_LINK = "h";
    private static final String INVALID_SET = "101";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_TITLE = BENSON.getTitle().toString();
    private static final String VALID_DESC = BENSON.getDescription().toString();
    private static final String VALID_LINK = "";
    private static final String VALID_SET = "1";
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validFlashcardDetails_returnsFlashcard() throws Exception {
        JsonAdaptedFlashcard flashcard = new JsonAdaptedFlashcard(BENSON);
        assertEquals(BENSON, flashcard.toModelType());
    }

    @Test
    public void toModelType_invalidTitle_throwsIllegalValueException() {
        JsonAdaptedFlashcard flashcard =
                new JsonAdaptedFlashcard(INVALID_TITLE, VALID_DESC, null, VALID_SET, VALID_TAGS);
        String expectedMessage = Title.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_nullTitle_throwsIllegalValueException() {
        JsonAdaptedFlashcard flashcard = new JsonAdaptedFlashcard(null, VALID_DESC, null, VALID_SET,
                VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedFlashcard flashcard =
                new JsonAdaptedFlashcard(VALID_TITLE, INVALID_DESC, null, VALID_SET, VALID_TAGS);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedFlashcard flashcard = new JsonAdaptedFlashcard(VALID_TITLE, null, null, VALID_SET, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_invalidLink_throwsIllegalValueException() {
        JsonAdaptedFlashcard flashcard =
                new JsonAdaptedFlashcard(VALID_TITLE, VALID_DESC, INVALID_LINK, VALID_SET, VALID_TAGS);
        String expectedMessage = Link.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_nullLink_throwsIllegalValueException() {
        JsonAdaptedFlashcard flashcard =
                new JsonAdaptedFlashcard(VALID_TITLE, VALID_DESC, null, VALID_SET, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Link.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_invalidSet_throwsIllegalValueException() {
        JsonAdaptedFlashcard flashcard =
                new JsonAdaptedFlashcard(VALID_TITLE, VALID_DESC, VALID_LINK, INVALID_SET, VALID_TAGS);
        String expectedMessage = FlashcardSet.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_nullSet_throwsIllegalValueException() {
        JsonAdaptedFlashcard flashcard =
                new JsonAdaptedFlashcard(VALID_TITLE, VALID_DESC, VALID_LINK, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, FlashcardSet.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedFlashcard flashcard = new JsonAdaptedFlashcard(VALID_TITLE, VALID_DESC, null, VALID_SET,
                invalidTags);
        assertThrows(IllegalValueException.class, flashcard::toModelType);
    }
}
