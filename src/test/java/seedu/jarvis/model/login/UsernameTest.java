package seedu.jarvis.model.login;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.jarvis.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class UsernameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Username(null));
    }

    @Test
    public void constructor_invalidUsername_throwsIllegalArgumentException() {
        String invalidUsername = "nusstutest";
        assertThrows(IllegalArgumentException.class, () -> new Username(invalidUsername));
    }

    @Test
    public void isValidUsername() {
        // null username
        assertThrows(NullPointerException.class, () -> Username.isValidUsername(null));

        // invalid username
        assertFalse(Username.isValidUsername("")); // empty string
        assertFalse(Username.isValidUsername(" ")); // spaces only
        assertFalse(Username.isValidUsername("^")); // only non-alphanumeric characters
        assertFalse(Username.isValidUsername("peter*")); // contains non-alphanumeric characters

        // valid username
        assertTrue(Username.isValidUsername("peter\\jack")); // alphabets only
        assertTrue(Username.isValidUsername("12345\\12345")); // numbers only
        assertTrue(Username.isValidUsername("12345\\jack")); // numbers and alphabets
        assertTrue(Username.isValidUsername("peter\\12345")); // alphabets and numbers
        assertTrue(Username.isValidUsername("nusstu\\e1234567")); // conventional luminus username
    }
}
