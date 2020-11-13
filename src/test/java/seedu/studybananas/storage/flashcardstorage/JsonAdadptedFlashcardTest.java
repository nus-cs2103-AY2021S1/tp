package seedu.studybananas.storage.flashcardstorage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.studybananas.storage.flashcardstorage.JsonAdaptedFlashcard.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.studybananas.testutil.Assert.assertThrows;
import static seedu.studybananas.testutil.TypicalFlashcards.NEWTONS_SECOND_LAW;

import org.junit.jupiter.api.Test;

import seedu.studybananas.commons.exceptions.IllegalValueException;
import seedu.studybananas.model.flashcard.Answer;
import seedu.studybananas.model.flashcard.Question;

public class JsonAdadptedFlashcardTest {
    private static final String VALID_QUESTION = NEWTONS_SECOND_LAW.getQuestion().toString();
    private static final String VALID_ANSWER = NEWTONS_SECOND_LAW.getAnswer().toString();

    @Test
    public void toModelType_validFlashcardDetails_returnsFlashcard() throws Exception {
        JsonAdaptedFlashcard flashcard = new JsonAdaptedFlashcard(NEWTONS_SECOND_LAW);
        assertEquals(NEWTONS_SECOND_LAW, flashcard.toModelType());
    }

    @Test
    public void toModelType_nullQuestion_throwsIllegalValueException() {
        JsonAdaptedFlashcard flashcard = new JsonAdaptedFlashcard(null, VALID_ANSWER);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Question.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_nullAnswer_throwsIllegalValueException() {
        JsonAdaptedFlashcard flashcard = new JsonAdaptedFlashcard(VALID_QUESTION, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Answer.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }
}
