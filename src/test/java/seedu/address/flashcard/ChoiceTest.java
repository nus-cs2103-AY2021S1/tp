package seedu.address.flashcard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


class ChoiceTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Choice(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        assertThrows(IllegalArgumentException.class, () -> new Choice(invalidEmail));
    }

    @Test
    public void IsValidChoice() {
        // null choice
        assertThrows(NullPointerException.class, () -> Choice.isValidChoice(null));

        // blank choice
        assertFalse(Choice.isValidChoice(""));
        assertFalse(Choice.isValidChoice(" "));

        // missing parts
        assertTrue(Choice.isValidChoice("Magnetic disk 12345!"));
        assertTrue(Choice.isValidChoice("Main memory!")); // with punctuation
        assertTrue(Choice.isValidChoice("Optical CDs and DVDs 12345")); // with numbers
    }

}