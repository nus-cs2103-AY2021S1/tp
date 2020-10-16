package quickcache.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static quickcache.testutil.Assert.assertThrows;

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
    public void isValidChoice() {
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

    @Test
    public void equals() {
        Choice choice = new Choice("Main memory!");
        Choice choiceCopy = new Choice("Main memory!");
        Choice choiceDifferent = new Choice("Not main memory!");
        assertTrue(choice.equals(choiceCopy));

        // same object -> returns true
        assertTrue(choice.equals(choice));

        // null -> returns false
        assertFalse(choice.equals(null));

        // different type -> returns false
        assertFalse(choice.equals(5));

        // different choice -> returns false
        assertFalse(choice.equals(choiceDifferent));
    }

}
