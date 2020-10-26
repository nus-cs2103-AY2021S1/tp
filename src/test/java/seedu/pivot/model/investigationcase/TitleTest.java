package seedu.pivot.model.investigationcase;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.pivot.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TitleTest {
    private static final String BLANK = " ";
    private static final String EMPTY = "";

    // Title is an Alphanumeric that cannot be blank.
    @Test
    public void constructor_blank_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Title(BLANK));
        assertThrows(IllegalArgumentException.class, () -> new Title(EMPTY));
    }

    @Test
    public void isValidTitle_blank_false() {
        assertFalse(Title.isValidTitle(BLANK));
        assertFalse(Title.isValidTitle(EMPTY));
    }
}
