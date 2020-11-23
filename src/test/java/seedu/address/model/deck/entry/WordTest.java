package seedu.address.model.deck.entry;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class WordTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Word(null));
    }

    @Test
    public void constructor_invalidWord_throwsIllegalArgumentException() {
        String invalidWord = "";
        assertThrows(IllegalArgumentException.class, () -> new Word(invalidWord));
    }

    @Test
    public void isValidWord() {
        // null word
        assertThrows(NullPointerException.class, () -> Word.isValidWord(null));

        // invalid word
        assertFalse(Word.isValidWord("")); // empty string
        assertFalse(Word.isValidWord(" ")); // spaces only

        // valid word
        assertTrue(Word.isValidWord("peter jack")); // alphabets only
        assertTrue(Word.isValidWord("12345")); // numbers only
        assertTrue(Word.isValidWord("peter the 2nd")); // alphanumeric characters
        assertTrue(Word.isValidWord("Capital Tan")); // with capital letters
        assertTrue(Word.isValidWord("David Roger Jackson Ray Jr 2nd")); // long words
    }
}
