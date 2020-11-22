package seedu.forgetfulnus.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.forgetfulnus.storage.JsonAdaptedFlashCard.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.forgetfulnus.testutil.Assert.assertThrows;
import static seedu.forgetfulnus.testutil.TypicalFlashCards.FLASHCARD_2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.forgetfulnus.commons.exceptions.IllegalValueException;
import seedu.forgetfulnus.model.flashcard.EnglishPhrase;
import seedu.forgetfulnus.model.flashcard.GermanPhrase;
import seedu.forgetfulnus.model.flashcard.Order;
import seedu.forgetfulnus.model.tag.DifficultyTag;
import seedu.forgetfulnus.model.tag.GenderTag;

public class JsonAdaptedFlashCardTest {
    private static final String INVALID_GERMAN_PHRASE = "Deutschl@nd";
    private static final String INVALID_ENGLISH_PHRASE = "+Germany";
    private static final String INVALID_DIFFICULTY_TAG = "easy peasy";
    private static final String INVALID_GENDER_TAG = "IT";
    private static final String INVALID_TAG = "#country";
    private static final String INVALID_ORDER = "-100";

    private static final String VALID_GERMAN_PHRASE = FLASHCARD_2.getGermanPhrase().toString();
    private static final String VALID_ENGLISH_PHRASE = FLASHCARD_2.getEnglishPhrase().toString();
    private static final String VALID_DIFFICULTY_TAG = FLASHCARD_2.getDifficultyTag().toString();
    private static final String VALID_GENDER_TAG = FLASHCARD_2.getGenderTag().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = FLASHCARD_2.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final String VALID_ORDER = "2";

    @Test
    public void toModelType_validFlashcardDetails_returnsFlashcard() throws Exception {
        JsonAdaptedFlashCard flashcard = new JsonAdaptedFlashCard(FLASHCARD_2);
        assertEquals(FLASHCARD_2, flashcard.toModelType());
    }

    @Test
    public void toModelType_invalidGermanPhrase_throwsIllegalValueException() {
        JsonAdaptedFlashCard flashcard =
                new JsonAdaptedFlashCard(INVALID_GERMAN_PHRASE, VALID_ENGLISH_PHRASE,
                        VALID_DIFFICULTY_TAG, VALID_GENDER_TAG, VALID_TAGS, VALID_ORDER);

        String expectedMessage = GermanPhrase.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_nullGermanPhrase_throwsIllegalValueException() {
        JsonAdaptedFlashCard flashcard = new JsonAdaptedFlashCard(null, VALID_ENGLISH_PHRASE,
                VALID_DIFFICULTY_TAG, VALID_GENDER_TAG, VALID_TAGS, VALID_ORDER);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, GermanPhrase.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_invalidEnglishPhrase_throwsIllegalValueException() {
        JsonAdaptedFlashCard flashcard =
                new JsonAdaptedFlashCard(VALID_GERMAN_PHRASE, INVALID_ENGLISH_PHRASE,
                        VALID_DIFFICULTY_TAG, VALID_GENDER_TAG, VALID_TAGS, VALID_ORDER);

        String expectedMessage = EnglishPhrase.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_nullEnglishPhrase_throwsIllegalValueException() {
        JsonAdaptedFlashCard flashcard = new JsonAdaptedFlashCard(VALID_GERMAN_PHRASE, null,
                VALID_DIFFICULTY_TAG, VALID_GENDER_TAG, VALID_TAGS, VALID_ORDER);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, EnglishPhrase.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_invalidDifficultyTag_throwsIllegalValueException() {
        JsonAdaptedFlashCard flashcard =
                new JsonAdaptedFlashCard(VALID_GERMAN_PHRASE, VALID_ENGLISH_PHRASE,
                        INVALID_DIFFICULTY_TAG, VALID_GENDER_TAG, VALID_TAGS, VALID_ORDER);

        String expectedMessage = DifficultyTag.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_nullDifficultyTag_throwsIllegalValueException() {
        JsonAdaptedFlashCard flashcard = new JsonAdaptedFlashCard(VALID_GERMAN_PHRASE, VALID_ENGLISH_PHRASE,
                null, VALID_GENDER_TAG, VALID_TAGS, VALID_ORDER);

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, DifficultyTag.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_invalidGenderTag_throwsIllegalValueException() {
        JsonAdaptedFlashCard flashcard =
                new JsonAdaptedFlashCard(VALID_GERMAN_PHRASE, VALID_ENGLISH_PHRASE,
                        VALID_DIFFICULTY_TAG, INVALID_GENDER_TAG, VALID_TAGS, VALID_ORDER);
        String expectedMessage = GenderTag.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_nullGenderTag_throwsIllegalValueException() {
        JsonAdaptedFlashCard flashcard = new JsonAdaptedFlashCard(VALID_GERMAN_PHRASE, VALID_ENGLISH_PHRASE,
                VALID_DIFFICULTY_TAG, null, VALID_TAGS, VALID_ORDER);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, GenderTag.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_invalidOrder_throwsIllegalValueException() {
        JsonAdaptedFlashCard flashcard =
                new JsonAdaptedFlashCard(VALID_GERMAN_PHRASE, VALID_ENGLISH_PHRASE, VALID_DIFFICULTY_TAG,
                        VALID_GENDER_TAG, VALID_TAGS, INVALID_ORDER);
        String expectedMessage = Order.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_nullOrder_throwsIllegalValueException() {
        JsonAdaptedFlashCard flashcard = new JsonAdaptedFlashCard(VALID_GERMAN_PHRASE, VALID_ENGLISH_PHRASE,
                VALID_DIFFICULTY_TAG, VALID_GENDER_TAG, VALID_TAGS, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Order.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, flashcard::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedFlashCard flashcard =
                new JsonAdaptedFlashCard(VALID_GERMAN_PHRASE, VALID_ENGLISH_PHRASE,
                        VALID_DIFFICULTY_TAG, VALID_GENDER_TAG, invalidTags, VALID_ORDER);
        assertThrows(IllegalValueException.class, flashcard::toModelType);
    }

}
