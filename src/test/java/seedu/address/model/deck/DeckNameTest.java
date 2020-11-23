package seedu.address.model.deck;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DeckNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeckName(null));
    }

    @Test
    public void constructor_invalidDeckName_throwsIllegalArgumentException() {
        String invalidDeckName = "";
        assertThrows(IllegalArgumentException.class, () -> new DeckName(invalidDeckName));
    }

    @Test
    public void constructor_invalidDeckNameLength_throwsIllegalArgumentException() {
        //Deck name of length 101 characters. Maximum deck name length is 100 characters.
        String invalidDeckNameLength = "01234567890123456789012345678901234567890123456789"
                + "0123456789012345678901234567890123456789012345678910";
        assertThrows(IllegalArgumentException.class, () -> new DeckName(invalidDeckNameLength));
    }

    @Test
    public void getDeckNameTest_validDeckName_success() {
        String test = "test";
        DeckName deckName = new DeckName(test);
        assertEquals(deckName.getDeckName(), test);
    }

    @Test
    public void isValidDeckNameLength() {
        // maximum deck name length
        assertTrue(DeckName.isValidLength("012345678901234567890123456789012345678901234567890123456789"
                + "0123456789012345678901234567890123456789"));
        // exceed maximum deck name length
        assertFalse(DeckName.isValidLength("012345678901234567890123456789012345678901234567890123456789"
                + "01234567890123456789012345678901234567890"));
        // below maximum deck name length
        assertTrue(DeckName.isValidLength("012345678901234567890123456789012345678901234567890123456789"
                + "012345678901234567890123456789012345678"));
    }

    @Test
    public void isValidDeckName() {
        // null deck name
        assertThrows(NullPointerException.class, () -> DeckName.isValidDeckName(null));

        // invalid deck name
        assertFalse(DeckName.isValidDeckName("")); // empty string
        assertFalse(DeckName.isValidDeckName(" ")); // spaces only

        // valid deck name
        assertTrue(DeckName.isValidDeckName("French")); // allows alphabets
        assertTrue(DeckName.isValidDeckName("Español")); // allows special characters
        assertTrue(DeckName.isValidDeckName("日本語")); // allows special characters
        assertTrue(DeckName.isValidDeckName("Korean 1")); // allows numbers
        assertTrue(DeckName.isValidDeckName("Japanese Intermediate 2 Lesson 5")); // long names
    }
}
