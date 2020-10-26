package seedu.pivot.model.investigationcase;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pivot.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.pivot.model.investigationcase.caseperson.Name;

public class NameTest {
    private static final String ALPHANUMERIC = "ABC123";
    private static final String ALPHA = "ABC";
    private static final String NUMERIC = "123";
    private static final String NOT_ALPHANUMERIC = "ASdsa14@#$%^";
    private static final String BLANK = " ";
    private static final String EMPTY = "";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_notAlphanum_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Name(NOT_ALPHANUMERIC));
    }

    @Test
    public void constructor_blank_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Name(BLANK));
        assertThrows(IllegalArgumentException.class, () -> new Name(EMPTY));
    }

    @Test
    public void isValidName_blank_false() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // valid values
        assertTrue(Name.isValidName(ALPHANUMERIC));
        assertTrue(Name.isValidName(ALPHA));
        assertTrue(Name.isValidName(NUMERIC));

        // invalid values
        assertFalse(Name.isValidName(NOT_ALPHANUMERIC));

        // blank -> false
        assertFalse(Name.isValidName(BLANK));
        assertFalse(Name.isValidName(EMPTY));
    }
}
