package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedEntry.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.entry.TypicalEntries.JAPANESE_2;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.deck.entry.Translation;
import seedu.address.model.deck.entry.Word;


public class JsonAdaptedEntryTest {
    private static final JsonAdaptedWord INVALID_JsonAdaptedWord = new JsonAdaptedWord("");
    private static final JsonAdaptedTranslation INVALID_JsonAdaptedTranslation = new JsonAdaptedTranslation("      ");

    private static final JsonAdaptedWord VALID_JsonAdaptedWord = new JsonAdaptedWord(JAPANESE_2.getWord());
    private static final JsonAdaptedTranslation VALID_JsonAdaptedTranslation =
            new JsonAdaptedTranslation(JAPANESE_2.getTranslation());

    @Test
    public void toModelType_validEntryDetails_returnsEntry() throws Exception {
        JsonAdaptedEntry entry = new JsonAdaptedEntry(JAPANESE_2);
        assertEquals(JAPANESE_2, entry.toModelType());
    }

    @Test
    public void toModelType_invalidJsonAdaptedWord_throwsIllegalValueException() {
        JsonAdaptedEntry entry =
                new JsonAdaptedEntry(INVALID_JsonAdaptedWord, VALID_JsonAdaptedTranslation);
        String expectedMessage = Word.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, entry::toModelType);
    }

    @Test
    public void toModelType_nullJsonAdaptedWord_throwsIllegalValueException() {
        JsonAdaptedEntry entry = new JsonAdaptedEntry(null, VALID_JsonAdaptedTranslation);
        String expectedMessage = String.format(
                MISSING_FIELD_MESSAGE_FORMAT, JsonAdaptedWord.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, entry::toModelType);
    }

    @Test
    public void toModelType_invalidJsonAdaptedTranslation_throwsIllegalValueException() {
        JsonAdaptedEntry entry =
                new JsonAdaptedEntry(VALID_JsonAdaptedWord, INVALID_JsonAdaptedTranslation);
        String expectedMessage = Translation.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, entry::toModelType);
    }

    @Test
    public void toModelType_nullJsonAdaptedTranslation_throwsIllegalValueException() {
        JsonAdaptedEntry entry = new JsonAdaptedEntry(VALID_JsonAdaptedWord, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                JsonAdaptedTranslation.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, entry::toModelType);
    }
}
