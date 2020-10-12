package seedu.stock.model.stock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.stock.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SourceTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Source(null));
    }

    @Test
    public void constructor_invalidSource_throwsIllegalArgumentException() {
        String invalidSource = "";
        assertThrows(IllegalArgumentException.class, () -> new Source(invalidSource));
    }

    @Test
    public void isValidSource() {
        // null source
        assertThrows(NullPointerException.class, () -> Source.isValidSource(null));

        // invalid source
        assertFalse(Source.isValidSource("")); // empty string
        assertFalse(Source.isValidSource("  ")); // spaces only

        // valid source
        assertTrue(Source.isValidSource("Kc Company"));
        assertTrue(Source.isValidSource("-")); // one character
        assertTrue(Source.isValidSource(
                "kc Company and amy company combine@ptd limited at")); // long Source
    }
}
