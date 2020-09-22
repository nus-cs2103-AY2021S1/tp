package seedu.address.model.stock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SourceTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Source(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        assertThrows(IllegalArgumentException.class, () -> new Source(invalidEmail));
    }

    @Test
    public void isValidEmail() {
        // null email
        assertThrows(NullPointerException.class, () -> Source.isValidEmail(null));

        // blank email
        assertFalse(Source.isValidEmail("")); // empty string
        assertFalse(Source.isValidEmail(" ")); // spaces only

        // missing parts
        assertFalse(Source.isValidEmail("@example.com")); // missing local part
        assertFalse(Source.isValidEmail("peterjackexample.com")); // missing '@' symbol
        assertFalse(Source.isValidEmail("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(Source.isValidEmail("peterjack@-")); // invalid domain name
        assertFalse(Source.isValidEmail("peterjack@exam_ple.com")); // underscore in domain name
        assertFalse(Source.isValidEmail("peter jack@example.com")); // spaces in local part
        assertFalse(Source.isValidEmail("peterjack@exam ple.com")); // spaces in domain name
        assertFalse(Source.isValidEmail(" peterjack@example.com")); // leading space
        assertFalse(Source.isValidEmail("peterjack@example.com ")); // trailing space
        assertFalse(Source.isValidEmail("peterjack@@example.com")); // double '@' symbol
        assertFalse(Source.isValidEmail("peter@jack@example.com")); // '@' symbol in local part
        assertFalse(Source.isValidEmail("peterjack@example@com")); // '@' symbol in domain name
        assertFalse(Source.isValidEmail("peterjack@.example.com")); // domain name starts with a period
        assertFalse(Source.isValidEmail("peterjack@example.com.")); // domain name ends with a period
        assertFalse(Source.isValidEmail("peterjack@-example.com")); // domain name starts with a hyphen
        assertFalse(Source.isValidEmail("peterjack@example.com-")); // domain name ends with a hyphen

        // valid email
        assertTrue(Source.isValidEmail("PeterJack_1190@example.com"));
        assertTrue(Source.isValidEmail("a@bc")); // minimal
        assertTrue(Source.isValidEmail("test@localhost")); // alphabets only
        assertTrue(Source.isValidEmail("!#$%&'*+/=?`{|}~^.-@example.org")); // special characters local part
        assertTrue(Source.isValidEmail("123@145")); // numeric local part and domain name
        assertTrue(Source.isValidEmail("a1+be!@example1.com")); // mixture of alphanumeric and special characters
        assertTrue(Source.isValidEmail("peter_jack@very-very-very-long-example.com")); // long domain name
        assertTrue(Source.isValidEmail("if.you.dream.it_you.can.do.it@example.com")); // long local part
    }
}
