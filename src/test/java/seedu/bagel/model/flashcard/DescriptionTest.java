package seedu.bagel.model.flashcard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.bagel.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new Description(invalidDescription));
    }

    @Test
    public void isValidFlashcardDesc() {
        // null address
        assertThrows(NullPointerException.class, () -> Description.isValidDescription(null));

        // invalid addresses
        assertFalse(Description.isValidDescription("")); // empty string
        assertFalse(Description.isValidDescription(" ")); // spaces only

        // valid addresses
        assertTrue(Description.isValidDescription("help me for ger pls"));
        assertTrue(Description.isValidDescription("-")); // one character
        assertTrue(Description.isValidDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
                + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, "
                + "quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
                + "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla "
                + "pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt "
                + "mollit anim id est laborum.")); // long address
    }

}
