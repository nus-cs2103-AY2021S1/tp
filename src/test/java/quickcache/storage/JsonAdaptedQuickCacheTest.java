package quickcache.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static quickcache.storage.JsonAdaptedQuickCache.MISSING_FIELD_MESSAGE_FORMAT;
import static quickcache.testutil.Assert.assertThrows;
import static quickcache.testutil.TypicalFlashcards.RANDOM1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import quickcache.commons.exceptions.IllegalValueException;
import quickcache.model.flashcard.Answer;
import quickcache.model.flashcard.OpenEndedQuestion;
import quickcache.model.flashcard.Statistics;


public class JsonAdaptedQuickCacheTest {
    private static final String INVALID_ANSWER = " ";
    private static final String INVALID_QUESTION = " ";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_TYPE = OpenEndedQuestion.TYPE;
    private static final String VALID_QUESTION = RANDOM1.getQuestion().getValue();
    private static final List<String> VALID_CHOICES = Collections.emptyList();
    private static final String VALID_ANSWER = RANDOM1.getAnswer().getValue();
    private static final List<JsonAdaptedTag> VALID_TAGS = RANDOM1.getTags()
            .stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final String VALID_DIFFICULTY = RANDOM1.getDifficulty().getValue();
    private static final Statistics VALID_STATISTICS = RANDOM1.getStatistics();

    @Test
    public void toModelType_validFlashcardDetails_returnsFlashcard() throws Exception {
        JsonAdaptedQuickCache flashcard = new JsonAdaptedQuickCache(RANDOM1);
        assertEquals(RANDOM1, flashcard.toModelType());
    }

    @Test
    public void toModelType_invalidAQuestion_throwsIllegalValueException() {
        JsonAdaptedQuickCache flashcard =
                new JsonAdaptedQuickCache(VALID_TYPE, INVALID_QUESTION, VALID_CHOICES,
                        VALID_ANSWER, VALID_TAGS, VALID_DIFFICULTY, VALID_STATISTICS);
        String expectedMessage = Answer.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_nullQuestion_throwsIllegalValueException() {
        JsonAdaptedQuickCache flashcard =
                new JsonAdaptedQuickCache(VALID_TYPE, null, VALID_CHOICES,
                        VALID_ANSWER, VALID_TAGS, VALID_DIFFICULTY, VALID_STATISTICS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, String.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_invalidAnswer_throwsIllegalValueException() {
        JsonAdaptedQuickCache flashcard =
                new JsonAdaptedQuickCache(VALID_TYPE, VALID_QUESTION, VALID_CHOICES,
                        INVALID_ANSWER, VALID_TAGS, VALID_DIFFICULTY, VALID_STATISTICS);
        String expectedMessage = Answer.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_nullAnswer_throwsIllegalValueException() {
        JsonAdaptedQuickCache flashcard =
                new JsonAdaptedQuickCache(VALID_TYPE, VALID_QUESTION, VALID_CHOICES,
                        null, VALID_TAGS, VALID_DIFFICULTY, VALID_STATISTICS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Answer.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_nullType_throwsIllegalValueException() {
        JsonAdaptedQuickCache flashcard = new JsonAdaptedQuickCache(null, VALID_QUESTION, VALID_CHOICES,
                VALID_ANSWER, VALID_TAGS, VALID_DIFFICULTY, VALID_STATISTICS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, String.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_nullStats_throwsIllegalValueException() {
        JsonAdaptedQuickCache flashcard = new JsonAdaptedQuickCache(VALID_TYPE, VALID_QUESTION, VALID_CHOICES,
                VALID_ANSWER, VALID_TAGS, VALID_DIFFICULTY, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Integer.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedQuickCache flashcard =
                new JsonAdaptedQuickCache(VALID_TYPE, VALID_QUESTION, VALID_CHOICES,
                        VALID_ANSWER, invalidTags, VALID_DIFFICULTY, VALID_STATISTICS);
        assertThrows(IllegalValueException.class, flashcard::toModelType);
    }
}
