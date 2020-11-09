package seedu.address.model.quiz;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class McqTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Mcq(null, 1, Arrays.asList("yes", "no")));
        assertThrows(NullPointerException.class, () -> new Mcq("yes?", null, Arrays.asList("yes", "no")));
        assertThrows(NullPointerException.class, () -> new Mcq("yes?", 1, null));
    }

    @Test
    public void constructor_invalidPrompt_throwsIllegalArgumentException() {
        String invalidPrompt = "";
        assertThrows(IllegalArgumentException.class, () -> new Mcq(invalidPrompt, 1, Arrays.asList("yes", "no")));
    }

    @Test
    public void isValidPrompt() {
        // null address
        assertThrows(NullPointerException.class, () -> Mcq.isValidPrompt(null));

        // invalid addresses
        assertFalse(Mcq.isValidPrompt("")); // empty string
        assertFalse(Mcq.isValidPrompt(" ")); // spaces only

        // valid addresses
        assertTrue(Mcq.isValidPrompt("OOP stands for Object-Oriented Programming."));
        assertTrue(Mcq.isValidPrompt("-")); // one character
        assertTrue(Mcq
                .isValidPrompt("A dictionary is a collection of (key,value) pairs.")); // long definition
    }
}
