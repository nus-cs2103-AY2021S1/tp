package seedu.forgetfulnus.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.forgetfulnus.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EnglishPhraseTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EnglishPhrase(null));
    }

    @Test
    public void constructor_invalidEnglishPhrase_throwsIllegalArgumentException() {
        String invalidEnglishPhrase = "";
        assertThrows(IllegalArgumentException.class, () -> new EnglishPhrase(invalidEnglishPhrase));
    }

    @Test
    public void isValidEnglishPhrase() {
        // null English phrase
        assertThrows(NullPointerException.class, () -> EnglishPhrase.isValidEnglishPhrase(null));

        // invalid English Phrase
        assertFalse(EnglishPhrase.isValidEnglishPhrase("")); // empty string
        assertFalse(EnglishPhrase.isValidEnglishPhrase(" ")); // spaces only
        assertFalse(EnglishPhrase.isValidEnglishPhrase("^")); // only non-alphabet characters
        assertFalse(EnglishPhrase.isValidEnglishPhrase("forgetfulness*")); // contains non-alphabet characters
        assertFalse(EnglishPhrase.isValidEnglishPhrase("forgetfulness1")); // contains numbers


        // valid English Phrase
        assertTrue(EnglishPhrase.isValidEnglishPhrase("forgetfulness me")); // alphabets only
        assertTrue(EnglishPhrase.isValidEnglishPhrase("Forgetful Ness")); // with capital letters
        assertTrue(EnglishPhrase.isValidEnglishPhrase("For get ful ness")); // long English phrases
    }

    @Test
    public void isCorrectAttempt() {
        assertTrue(new EnglishPhrase("test test").isCorrectAttempt("test test"));
        assertTrue(new EnglishPhrase("test").isCorrectAttempt("TEST"));
        assertTrue(new EnglishPhrase("test test").isCorrectAttempt("   test test  "));
        assertTrue(new EnglishPhrase("test test").isCorrectAttempt("   test    test  "));
        assertFalse(new EnglishPhrase("test").isCorrectAttempt("ashdha"));
    }
}
