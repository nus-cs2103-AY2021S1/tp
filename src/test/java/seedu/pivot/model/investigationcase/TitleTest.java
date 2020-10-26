package seedu.pivot.model.investigationcase;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pivot.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TitleTest {
    private static final String ALPHANUMERIC = "ABC123";
    private static final String ALPHA = "ABC";
    private static final String NUMERIC = "123";
    private static final String NOT_ALPHANUMERIC = "ASdsa14@#$%^";
    private static final String BLANK = " ";
    private static final String EMPTY = "";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Title(null));
    }

    @Test
    public void constructor_notAlphanum_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Title(NOT_ALPHANUMERIC));
    }

    // Title is an Alphanumeric that cannot be blank.
    @Test
    public void constructor_blank_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Title(BLANK));
        assertThrows(IllegalArgumentException.class, () -> new Title(EMPTY));
    }

    @Test
    public void isValidTitle() {
        // null title
        assertThrows(NullPointerException.class, () -> Title.isValidTitle(null));

        // valid values
        assertTrue(Title.isValidTitle(ALPHANUMERIC));
        assertTrue(Title.isValidTitle(ALPHA));
        assertTrue(Title.isValidTitle(NUMERIC));

        // invalid values
        assertFalse(Title.isValidTitle(NOT_ALPHANUMERIC));

        // blank -> false
        assertFalse(Title.isValidTitle(BLANK));
        assertFalse(Title.isValidTitle(EMPTY));
    }
}
