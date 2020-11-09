package seedu.medibook.model.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.medibook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class HeightTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Height(null));
    }

    @Test
    public void constructor_invalidHeight_throwsIllegalArgumentException() {
        String invalidHeight = " 160";
        assertThrows(IllegalArgumentException.class, () -> new Height(invalidHeight));
    }

    @Test
    public void isValidHeight() {
        // invalid height
        assertFalse(Height.isValidHeight(null)); // null input
        assertFalse(Height.isValidHeight("")); // empty string
        assertFalse(Height.isValidHeight("-10")); // negative height
        assertFalse(Height.isValidHeight("300")); // height larger than 299cm
        assertFalse(Height.isValidHeight("17i")); // non-numeric character

        // valid height
        assertTrue(Height.isValidHeight("50"));
        assertTrue(Height.isValidHeight("195"));
        assertTrue(Height.isValidHeight("169"));
    }
}
