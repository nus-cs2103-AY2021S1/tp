package seedu.address.model.book;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LanguageTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Language(null));
    }

    @Test
    public void constructor_invalidLanguage_throwsIllegalArgumentException() {
        String invalidLanguage = "";
        assertThrows(IllegalArgumentException.class, () -> new Language(invalidLanguage));
    }

    @Test
    public void isValidLanguage() {
        // null language
        assertThrows(NullPointerException.class, () -> Language.isValidLanguage(null));

        // invalid languages
        assertFalse(Language.isValidLanguage("")); // empty string
        assertFalse(Language.isValidLanguage(" ")); // spaces only
        assertFalse(Language.isValidLanguage("123")); // numbers

        // valid languages
        assertTrue(Language.isValidLanguage("English"));
        assertTrue(Language.isValidLanguage("chinese")); // one character
        assertTrue(Language.isValidLanguage("thisisalanguage")); // long language
    }
}
