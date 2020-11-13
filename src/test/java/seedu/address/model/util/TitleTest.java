package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TitleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Title(null));
    }

    @Test
    public void constructor_emptyTitle_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Title(""));
    }

    @Test
    public void isValidTitle() {
        // Empty -> false
        assertFalse(Title.isValidTitle(""));

        // Alphabets -> true
        assertTrue(Title.isValidTitle("Hello"));

        // Numbers -> true
        assertTrue(Title.isValidTitle("1234"));

        // Special characters -> true
        assertTrue(Title.isValidTitle("!@#$%^&*()-,./?"));
    }
}
