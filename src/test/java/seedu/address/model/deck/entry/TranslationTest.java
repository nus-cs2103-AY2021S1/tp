package seedu.address.model.deck.entry;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TranslationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Translation(null));
    }

    @Test
    public void constructor_invalidTranslation_throwsIllegalArgumentException() {
        String invalidTranslation = "";
        assertThrows(IllegalArgumentException.class, () -> new Translation(invalidTranslation));
    }

    @Test
    public void isValidTranslation() {
        // null translation
        assertThrows(NullPointerException.class, () -> Translation.isValidTranslation(null));

        // blank translation
        assertFalse(Translation.isValidTranslation("")); // empty string
        assertFalse(Translation.isValidTranslation(" ")); // spaces only

        // valid translation
        assertTrue(Translation.isValidTranslation("PeterJack_1190@example.com"));
        assertTrue(Translation.isValidTranslation("a@bc")); // minimal
        assertTrue(Translation.isValidTranslation("test@localhost")); // alphabets only
        assertTrue(Translation.isValidTranslation("!#$%&'*+/=?`{|}~^.-@example.org"));
        // special characters local part
        assertTrue(Translation.isValidTranslation("123@145")); // numeric local part and domain name
        assertTrue(Translation.isValidTranslation("a1+be!@example1.com"));
        // mixture of alphanumeric and special characters
        assertTrue(Translation.isValidTranslation("peter_jack@very-very-very-long-example.com")); // long domain name
        assertTrue(Translation.isValidTranslation("if.you.dream.it_you.can.do.it@example.com")); // long local part
    }

    @Test
    public void equals() {
        Translation validTranslation = new Translation("I like green tea ice cream");
        Object object = (Object) new Translation("I like green tea ice cream");

        assertTrue(validTranslation.equals(validTranslation)); //same object
        assertFalse(validTranslation.equals(null)); //null object
        assertTrue(validTranslation.equals(object));
    }

}
