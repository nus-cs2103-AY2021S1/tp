package seedu.flashcard.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.flashcard.storage.JsonAdaptedFlashcard.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.flashcard.testutil.Assert.assertThrows;
import static seedu.flashcard.testutil.TypicalFlashcards.FLASHCARD_1;

import org.junit.jupiter.api.Test;

import seedu.flashcard.commons.exceptions.IllegalValueException;
import seedu.flashcard.model.flashcard.Answer;
import seedu.flashcard.model.flashcard.Category;
import seedu.flashcard.model.flashcard.Question;

public class JsonAdaptedFlashcardTest {
    private static final String INVALID_QUESTION = "";
    private static final String INVALID_ANSWER = "";
    private static final String INVALID_CATEGORY = "";
    private static final String INVALID_FAVOURITE_STATUS = "";
    private static final String VALID_QUESTION = FLASHCARD_1.getQuestion().toString();
    private static final String VALID_ANSWER = FLASHCARD_1.getAnswer().toString();
    private static final String VALID_CATEGORY = FLASHCARD_1.getCategory().toString();
    private static final String VALID_NOTE = FLASHCARD_1.getNote().toString();
    private static final String VALID_RATING = FLASHCARD_1.getRating().toString();
    private static final String VALID_TAG = FLASHCARD_1.getTag().getTagName();
    private static final String VALID_FAVOURITE_STATUS = Boolean.toString(FLASHCARD_1.isFavourite());

    @Test
    public void toModelType_validFlashcardDetails_returnsFlashcard() throws Exception {
        JsonAdaptedFlashcard flashcard = new JsonAdaptedFlashcard(FLASHCARD_1);
        assertEquals(FLASHCARD_1, flashcard.toModelType());
    }

    @Test
    public void toModelType_invalidQuestion_throwsIllegalValueException() {
        JsonAdaptedFlashcard flashcard =
                new JsonAdaptedFlashcard(INVALID_QUESTION, VALID_ANSWER, VALID_CATEGORY, VALID_NOTE,
                        VALID_RATING, VALID_TAG, VALID_FAVOURITE_STATUS);
        String expectedMessage = Question.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_nullQuestion_throwsIllegalValueException() {
        JsonAdaptedFlashcard flashcard = new JsonAdaptedFlashcard(null, VALID_ANSWER, VALID_CATEGORY,
                VALID_NOTE, VALID_RATING, VALID_TAG, VALID_FAVOURITE_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Question.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_invalidAnswer_throwsIllegalValueException() {
        JsonAdaptedFlashcard flashcard =
                new JsonAdaptedFlashcard(VALID_QUESTION, INVALID_ANSWER, VALID_CATEGORY, VALID_NOTE, VALID_RATING,
                        VALID_TAG, VALID_FAVOURITE_STATUS);
        String expectedMessage = Answer.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_nullAnswer_throwsIllegalValueException() {
        JsonAdaptedFlashcard flashcard = new JsonAdaptedFlashcard(VALID_QUESTION, null, VALID_CATEGORY,
                VALID_NOTE, VALID_RATING, VALID_TAG, VALID_FAVOURITE_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Answer.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_invalidCategory_throwsIllegalValueException() {
        JsonAdaptedFlashcard flashcard =
                new JsonAdaptedFlashcard(VALID_QUESTION, VALID_ANSWER, INVALID_CATEGORY, VALID_NOTE, VALID_RATING,
                        VALID_TAG, VALID_FAVOURITE_STATUS);
        String expectedMessage = Category.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_nullCategory_throwsIllegalValueException() {
        JsonAdaptedFlashcard flashcard = new JsonAdaptedFlashcard(VALID_QUESTION, VALID_ANSWER,
                null, VALID_NOTE, VALID_RATING, VALID_TAG, VALID_FAVOURITE_STATUS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Category.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

}
