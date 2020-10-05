package seedu.address.model.vendor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EmailTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new seedu.address.model.vendor.Email(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        assertThrows(IllegalArgumentException.class, () -> new seedu.address.model.vendor.Email(invalidEmail));
    }

    @Test
    public void isValidEmail() {
        // null email
        assertThrows(NullPointerException.class, () -> seedu.address.model.vendor.Email.isValidEmail(null));

        // blank email
        assertFalse(seedu.address.model.vendor.Email.isValidEmail("")); // empty string
        assertFalse(seedu.address.model.vendor.Email.isValidEmail(" ")); // spaces only

        // missing parts
        assertFalse(seedu.address.model.vendor.Email.isValidEmail("@example.com")); // missing local part
        assertFalse(seedu.address.model.vendor.Email.isValidEmail("peterjackexample.com")); // missing '@' symbol
        assertFalse(seedu.address.model.vendor.Email.isValidEmail("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(seedu.address.model.vendor.Email.isValidEmail("peterjack@-")); // invalid domain name
        assertFalse(seedu.address.model.vendor.Email.isValidEmail("peterjack@exam_ple.com")); // _ in domain name
        assertFalse(seedu.address.model.vendor.Email.isValidEmail("peter jack@example.com")); // " "in local part
        assertFalse(seedu.address.model.vendor.Email.isValidEmail("peterjack@exam ple.com")); // " " in domain name
        assertFalse(seedu.address.model.vendor.Email.isValidEmail(" peterjack@example.com")); // leading space
        assertFalse(seedu.address.model.vendor.Email.isValidEmail("peterjack@example.com ")); // trailing space
        assertFalse(seedu.address.model.vendor.Email.isValidEmail("peterjack@@example.com")); // double '@' symbol
        assertFalse(seedu.address.model.vendor.Email.isValidEmail("peter@jack@example.com")); // wrong '@' symbol
        assertFalse(seedu.address.model.vendor.Email.isValidEmail("peterjack@example@com")); // wrong'@' symbol
        assertFalse(seedu.address.model.vendor.Email.isValidEmail("peterjack@.example.com")); // starts with "."
        assertFalse(seedu.address.model.vendor.Email.isValidEmail("peterjack@example.com.")); // starts with "."
        assertFalse(seedu.address.model.vendor.Email.isValidEmail("peterjack@-example.com")); // starts with "-"
        assertFalse(seedu.address.model.vendor.Email.isValidEmail("peterjack@example.com-")); // starts with "-"

        // valid email
        assertTrue(seedu.address.model.vendor.Email.isValidEmail("PeterJack_1190@example.com"));
        assertTrue(seedu.address.model.vendor.Email.isValidEmail("a@bc")); // minimal
        assertTrue(seedu.address.model.vendor.Email.isValidEmail("test@localhost")); // alphabets only
        assertTrue(seedu.address.model.vendor.Email.isValidEmail("!#$%&'*+/=?`{|}~^.-@example.org")); // special
        assertTrue(seedu.address.model.vendor.Email.isValidEmail("123@145")); // numeric local part and domain name
        assertTrue(seedu.address.model.vendor.Email.isValidEmail("a1+be!@example1.com"));
        // mixture of alphanumeric and special characters
        assertTrue(seedu.address.model.vendor.Email.isValidEmail("peter_jack@very-very-very-long-example.com"));
        // long domain name
        assertTrue(Email.isValidEmail("if.you.dream.it_you.can.do.it@example.com")); // long local part
    }
}
