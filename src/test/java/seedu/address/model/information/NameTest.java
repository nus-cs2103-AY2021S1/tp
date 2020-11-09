package seedu.address.model.information;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

    public static final String HUNDRED_CHARACTER_STRING = "jG9r9MzdNRybTFQVBZLDQWVWu3IyWgV0ui oG Qmy8m02ocP346"
            + "hcysxt2Pn4opfJB8IUwiaQ6hEvgqtsaXBFMUNfFbPvUmTvVJG";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // invalid name
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidName("peter*")); // contains non-alphanumeric characters
        assertFalse(Name.isValidName(HUNDRED_CHARACTER_STRING + "a")); // 101 character alphanumeric

        // valid name
        assertTrue(Name.isValidName("peter jack")); // alphabets only; person name
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(Name.isValidName("Capital Tan")); // with capital letters
        assertTrue(Name.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
        assertTrue(Name.isValidName("Google")); // alphabets only; company name
        assertTrue(Name.isValidName("Software engineer")); // alphabets only; job title
        assertTrue(Name.isValidName(HUNDRED_CHARACTER_STRING)); // 100 character alphanumeric
    }
}
