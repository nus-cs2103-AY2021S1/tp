package seedu.forgetfulnus.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.forgetfulnus.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GermanPhraseTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GermanPhrase(null));
    }

    @Test
    public void constructor_invalidGermanPhrase_throwsIllegalArgumentException() {
        String invalidGermanPhrase = "";
        assertThrows(IllegalArgumentException.class, () -> new GermanPhrase(invalidGermanPhrase));
    }

    //TODO change constraints to english alphabets only
    //change examples
    @Test
    public void isValidGermanPhrase() {
        // null name
        assertThrows(NullPointerException.class, () -> GermanPhrase.isValidGermanPhrase(null));

        // invalid German Phrase
        assertFalse(EnglishPhrase.isValidEnglishPhrase("")); // empty string
        assertFalse(EnglishPhrase.isValidEnglishPhrase(" ")); // spaces only
        assertFalse(EnglishPhrase.isValidEnglishPhrase("^")); // only non-alphabet characters
        assertFalse(EnglishPhrase.isValidEnglishPhrase("forgetfulness*")); // contains non-alphabet characters
        assertFalse(EnglishPhrase.isValidEnglishPhrase("forgetfulness1")); // contains numbers

        // valid German Phrase
        assertTrue(EnglishPhrase.isValidEnglishPhrase("forgetfulness me")); // alphabets only
        assertTrue(EnglishPhrase.isValidEnglishPhrase("Forgetful Ness")); // with capital letters
        assertTrue(EnglishPhrase.isValidEnglishPhrase("For get ful ness")); // long English phrases


    }
}
