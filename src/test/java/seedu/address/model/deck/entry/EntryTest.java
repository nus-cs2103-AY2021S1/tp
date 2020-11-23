package seedu.address.model.deck.entry;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRANSLATION_SPANISH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WORD_SPANISH;
import static seedu.address.testutil.entry.TypicalEntries.JAPANESE_1;
import static seedu.address.testutil.entry.TypicalEntries.SPANISH;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.entry.EntryBuilder;

public class EntryTest {

    @Test
    public void isSameEntry() {
        // same object -> returns true
        assertTrue(JAPANESE_1.isSameEntry(JAPANESE_1));

        // null -> returns false
        assertFalse(JAPANESE_1.isSameEntry(null));

        // different translation -> returns false
        Entry editedJapanese = new EntryBuilder(JAPANESE_1).withTranslation(VALID_TRANSLATION_SPANISH).build();
        assertFalse(JAPANESE_1.isSameEntry(editedJapanese));

        // different word, same translation -> returns true
        editedJapanese = new EntryBuilder(JAPANESE_1).withWord(VALID_WORD_SPANISH).build();
        assertTrue(JAPANESE_1.isSameEntry(editedJapanese));

        // same word, same translation -> returns true
        editedJapanese = new EntryBuilder(JAPANESE_1).build();
        assertTrue(JAPANESE_1.isSameEntry(editedJapanese));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Entry japaneseCopy = new EntryBuilder(JAPANESE_1).build();
        assertTrue(JAPANESE_1.equals(japaneseCopy));

        // different word -> returns true
        Entry editedJapanese = new EntryBuilder(JAPANESE_1).withWord(VALID_WORD_SPANISH).build();
        assertTrue(JAPANESE_1.equals(editedJapanese));

        // same object -> returns true
        assertTrue(JAPANESE_1.equals(JAPANESE_1));

        // null -> returns false
        assertFalse(JAPANESE_1.equals(null));

        // different type -> returns false
        assertFalse(JAPANESE_1.equals(5));

        // different entry -> returns false
        assertFalse(JAPANESE_1.equals(SPANISH));

        // different translation -> returns false
        editedJapanese = new EntryBuilder(JAPANESE_1).withTranslation(VALID_TRANSLATION_SPANISH).build();
        assertFalse(JAPANESE_1.equals(editedJapanese));
    }
}
