package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EmailTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Email(null));
    }

    @Test
    public void constructor_invalidProjectDescription_throwsIllegalArgumentException() {
        String email = "";
        assertThrows(IllegalArgumentException.class, () -> new Email(email));
    }

    @Test
    public void isValidEmail() {
        // null email
        assertThrows(NullPointerException.class, () -> Email.isValidEmail(null));

        // invalid email
        assertFalse(Email.isValidEmail("")); // emprty string
        assertFalse(Email.isValidEmail(" ")); // spaces only
        assertFalse(Email.isValidEmail(" jack@gmail.com")); // space before email
        assertFalse(Email.isValidEmail("test)@gmail.com")); // parenthesis in email
        assertFalse(Email.isValidEmail("@gmail.com")); // nothing before the @ symbol
        assertFalse(Email.isValidEmail("test$gmail.com")); // @ symbol not present
        assertFalse(Email.isValidEmail("test@y")); // domain less than 2 letters long
        assertFalse(Email.isValidEmail("hellofresh@hello.")); // end email with '.'
        assertFalse(Email.isValidEmail("hellofresh@h/lo.ga")); // email with unusual domain

        // valid email
        assertTrue(Email.isValidEmail("hellofresh@gmail.com")); // only letters
        assertTrue(Email.isValidEmail("hello_$fresh@gmail.com")); // email with special characters
        assertTrue(Email.isValidEmail("hellofresh@helo.ga")); // email with unusual domain
    }
}
