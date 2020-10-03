package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        assertThrows(IllegalArgumentException.class, () -> new Description(invalidEmail));
    }

    @Test
    public void isValidEmail() {
        // null email
        assertThrows(NullPointerException.class, () -> Description.isValidEmail(null));

        // blank email
        assertFalse(Description.isValidEmail("")); // empty string
        assertFalse(Description.isValidEmail(" ")); // spaces only

        // missing parts
        assertFalse(Description.isValidEmail("@example.com")); // missing local part
        assertFalse(Description.isValidEmail("peterjackexample.com")); // missing '@' symbol
        assertFalse(Description.isValidEmail("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(Description.isValidEmail("peterjack@-")); // invalid domain name
        assertFalse(Description.isValidEmail("peterjack@exam_ple.com")); // underscore in domain name
        assertFalse(Description.isValidEmail("peter jack@example.com")); // spaces in local part
        assertFalse(Description.isValidEmail("peterjack@exam ple.com")); // spaces in domain name
        assertFalse(Description.isValidEmail(" peterjack@example.com")); // leading space
        assertFalse(Description.isValidEmail("peterjack@example.com ")); // trailing space
        assertFalse(Description.isValidEmail("peterjack@@example.com")); // double '@' symbol
        assertFalse(Description.isValidEmail("peter@jack@example.com")); // '@' symbol in local part
        assertFalse(Description.isValidEmail("peterjack@example@com")); // '@' symbol in domain name
        assertFalse(Description.isValidEmail("peterjack@.example.com")); // domain name starts with a period
        assertFalse(Description.isValidEmail("peterjack@example.com.")); // domain name ends with a period
        assertFalse(Description.isValidEmail("peterjack@-example.com")); // domain name starts with a hyphen
        assertFalse(Description.isValidEmail("peterjack@example.com-")); // domain name ends with a hyphen

        // valid email
        assertTrue(Description.isValidEmail("PeterJack_1190@example.com"));
        assertTrue(Description.isValidEmail("a@bc")); // minimal
        assertTrue(Description.isValidEmail("test@localhost")); // alphabets only
        assertTrue(Description.isValidEmail("!#$%&'*+/=?`{|}~^.-@example.org")); // special characters local part
        assertTrue(Description.isValidEmail("123@145")); // numeric local part and domain name
        assertTrue(Description.isValidEmail("a1+be!@example1.com")); // mixture of alphanumeric and special characters
        assertTrue(Description.isValidEmail("peter_jack@very-very-very-long-example.com")); // long domain name
        assertTrue(Description.isValidEmail("if.you.dream.it_you.can.do.it@example.com")); // long local part
    }
}
