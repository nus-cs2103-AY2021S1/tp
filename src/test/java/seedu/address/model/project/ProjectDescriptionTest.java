package seedu.address.model.project;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ProjectDescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ProjectDescription(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        assertThrows(IllegalArgumentException.class, () -> new ProjectDescription(invalidEmail));
    }

    @Test
    public void isValidEmail() {
        // null email
        assertThrows(NullPointerException.class, () -> ProjectDescription.isValidProjectDescription(null));

        // blank email
        assertFalse(ProjectDescription.isValidProjectDescription("")); // empty string
        assertFalse(ProjectDescription.isValidProjectDescription(" ")); // spaces only

        // missing parts
        assertFalse(ProjectDescription.isValidProjectDescription("@example.com")); // missing local part
        assertFalse(ProjectDescription.isValidProjectDescription("peterjackexample.com")); // missing '@' symbol
        assertFalse(ProjectDescription.isValidProjectDescription("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(ProjectDescription.isValidProjectDescription("peterjack@-")); // invalid domain name
        assertFalse(ProjectDescription.isValidProjectDescription("peterjack@exam_ple.com")); // underscore in domain name
        assertFalse(ProjectDescription.isValidProjectDescription("peter jack@example.com")); // spaces in local part
        assertFalse(ProjectDescription.isValidProjectDescription("peterjack@exam ple.com")); // spaces in domain name
        assertFalse(ProjectDescription.isValidProjectDescription(" peterjack@example.com")); // leading space
        assertFalse(ProjectDescription.isValidProjectDescription("peterjack@example.com ")); // trailing space
        assertFalse(ProjectDescription.isValidProjectDescription("peterjack@@example.com")); // double '@' symbol
        assertFalse(ProjectDescription.isValidProjectDescription("peter@jack@example.com")); // '@' symbol in local part
        assertFalse(ProjectDescription.isValidProjectDescription("peterjack@example@com")); // '@' symbol in domain name
        assertFalse(ProjectDescription.isValidProjectDescription("peterjack@.example.com")); // domain name starts with a period
        assertFalse(ProjectDescription.isValidProjectDescription("peterjack@example.com.")); // domain name ends with a period
        assertFalse(ProjectDescription.isValidProjectDescription("peterjack@-example.com")); // domain name starts with a hyphen
        assertFalse(ProjectDescription.isValidProjectDescription("peterjack@example.com-")); // domain name ends with a hyphen

        // valid email
        assertTrue(ProjectDescription.isValidProjectDescription("PeterJack_1190@example.com"));
        assertTrue(ProjectDescription.isValidProjectDescription("a@bc")); // minimal
        assertTrue(ProjectDescription.isValidProjectDescription("test@localhost")); // alphabets only
        assertTrue(ProjectDescription.isValidProjectDescription("!#$%&'*+/=?`{|}~^.-@example.org")); // special characters local part
        assertTrue(ProjectDescription.isValidProjectDescription("123@145")); // numeric local part and domain name
        assertTrue(ProjectDescription.isValidProjectDescription("a1+be!@example1.com")); // mixture of alphanumeric and special characters
        assertTrue(ProjectDescription.isValidProjectDescription("peter_jack@very-very-very-long-example.com")); // long domain name
        assertTrue(ProjectDescription.isValidProjectDescription("if.you.dream.it_you.can.do.it@example.com")); // long local part
    }
}
