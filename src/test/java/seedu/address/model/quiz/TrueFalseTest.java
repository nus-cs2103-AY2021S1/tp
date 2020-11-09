package seedu.address.model.quiz;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TrueFalseTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TrueFalse(null, true));
        assertThrows(NullPointerException.class, () -> new TrueFalse("yes?", null));
    }

    @Test
    public void constructor_invalidPrompt_throwsIllegalArgumentException() {
        String invalidPrompt = "";
        assertThrows(IllegalArgumentException.class, () -> new TrueFalse(invalidPrompt, true));
    }

    @Test
    public void isValidPrompt() {
        // null address
        assertThrows(NullPointerException.class, () -> TrueFalse.isValidPrompt(null));

        // invalid addresses
        assertFalse(TrueFalse.isValidPrompt("")); // empty string
        assertFalse(TrueFalse.isValidPrompt(" ")); // spaces only

        // valid addresses
        assertTrue(TrueFalse.isValidPrompt("OOP stands for Object-Oriented Programming."));
        assertTrue(TrueFalse.isValidPrompt("-")); // one character
        assertTrue(TrueFalse
                .isValidPrompt("A dictionary is a collection of (key,value) pairs.")); // long definition
    }
}
