package atas.model.student;

import static atas.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class EmailTest {

    public static final Email VALID_EMAIL = new Email("test@u.nus.edu");
    public static final Email VALID_EMAIL_COPY = new Email(VALID_EMAIL.value);
    public static final Email DIFFERENT_VALID_EMAIL = new Email("different@u.nus.edu");

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Email(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        assertThrows(IllegalArgumentException.class, () -> new Email(invalidEmail));
    }

    @Test
    public void isValidEmail() {
        // null email
        assertThrows(NullPointerException.class, () -> Email.isValidEmail(null));

        // blank email
        assertFalse(Email.isValidEmail("")); // empty string
        assertFalse(Email.isValidEmail(" ")); // spaces only

        // missing parts
        assertFalse(Email.isValidEmail("@u.nus.edu")); // missing local part
        assertFalse(Email.isValidEmail("peterjacku.nus.edu")); // missing '@' symbol
        assertFalse(Email.isValidEmail("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(Email.isValidEmail("peterjack@-")); // invalid domain name
        assertFalse(Email.isValidEmail("peterjack@exam_ple.com")); // underscore in domain name
        assertFalse(Email.isValidEmail("peter jack@example.com")); // spaces in local part
        assertFalse(Email.isValidEmail("peterjack@exam ple.com")); // spaces in domain name
        assertFalse(Email.isValidEmail(" peterjack@example.com")); // leading space
        assertFalse(Email.isValidEmail("peterjack@example.com ")); // trailing space
        assertFalse(Email.isValidEmail("peterjack@@example.com")); // double '@' symbol
        assertFalse(Email.isValidEmail("peter@jack@example.com")); // '@' symbol in local part
        assertFalse(Email.isValidEmail("peterjack@example@com")); // '@' symbol in domain name
        assertFalse(Email.isValidEmail("peterjack@.example.com")); // domain name starts with a period
        assertFalse(Email.isValidEmail("peterjack@example.com.")); // domain name ends with a period
        assertFalse(Email.isValidEmail("peterjack@-example.com")); // domain name starts with a hyphen
        assertFalse(Email.isValidEmail("peterjack@example.com-")); // domain name ends with a hyphen

        // valid email
        assertTrue(Email.isValidEmail("PeterJack_1190@u.nus.edu"));
        assertTrue(Email.isValidEmail("a@u.nus.edu")); // minimal
        assertTrue(Email.isValidEmail("test@u.nus.edu")); // alphabets only
        assertTrue(Email.isValidEmail("!#$%&'*+/=?`{|}~^._-@u.nus.edu")); // special characters local part
        assertTrue(Email.isValidEmail("123@u.nus.edu")); // numeric local part and domain name
        assertTrue(Email.isValidEmail("a1+be!@u.nus.edu")); // mixture of alphanumeric and special characters
        assertTrue(Email.isValidEmail("if.you.dream.it_you.can.do.it@u.nus.edu")); // long local part
        assertTrue(Email.isValidEmail(VALID_EMAIL.value));
        assertTrue(Email.isValidEmail(VALID_EMAIL_COPY.value));
        assertTrue(Email.isValidEmail(DIFFERENT_VALID_EMAIL.value));
    }

    @Test
    public void equals() {
        // this == other
        assertEquals(VALID_EMAIL, VALID_EMAIL);
        assertEquals(VALID_EMAIL_COPY, VALID_EMAIL_COPY);
        assertEquals(DIFFERENT_VALID_EMAIL, DIFFERENT_VALID_EMAIL);

        // same email value
        assertEquals(VALID_EMAIL_COPY, VALID_EMAIL);

        // different email value
        assertNotEquals(DIFFERENT_VALID_EMAIL, VALID_EMAIL);
        assertNotEquals(DIFFERENT_VALID_EMAIL, VALID_EMAIL_COPY);
    }
}
