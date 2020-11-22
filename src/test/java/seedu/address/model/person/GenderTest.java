package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GENDER;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GenderTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Gender(null));
    }

    @Test
    public void constructor_invalidGender_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Gender(INVALID_GENDER));
    }

    @Test
    public void isValidGender() {
        // valid male gender
        assertTrue(Gender.isValidGender("M"));

        // valid female gender
        assertTrue(Gender.isValidGender("F"));

        // invalid gender
        assertFalse(Email.isValidEmail("A"));
        assertFalse(Email.isValidEmail("G"));
        assertFalse(Email.isValidEmail("Z"));

    }
}
