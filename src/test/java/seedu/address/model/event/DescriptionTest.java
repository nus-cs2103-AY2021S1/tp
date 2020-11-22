package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class DescriptionTest {
    private static final String DESC_WITH_CAROT = "^";
    private static final String DESC_WITH_AT = "Dinner@Hall";
    private static final String DESC_WITH_SLASH = "Hello/World.";
    private static final String DESC_WITH_TRAILING_SPACES = "Hall Event!   ";
    private static final String DESC_WITH_LEADING_SPACES = "   Hall Event";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Description(""));
        assertThrows(IllegalArgumentException.class, () -> new Description(" "));

        // inputs with leading spaces are rejected
        assertThrows(IllegalArgumentException.class, () -> new Description(DESC_WITH_LEADING_SPACES));
    }

    @Test
    public void constructor_validDescription_success() {
        assertNotNull(new Description(DESC_WITH_CAROT));
        assertNotNull(new Description(DESC_WITH_AT));
        assertNotNull(new Description(DESC_WITH_SLASH));
        assertNotNull(new Description(DESC_WITH_TRAILING_SPACES));
    }

    @Test
    public void isDescription() {
        assertThrows(NullPointerException.class, () -> Description.isValidDescription(null));

        assertFalse(Description.isValidDescription(""));
        assertFalse(Description.isValidDescription(" "));
        assertFalse(Description.isValidDescription(DESC_WITH_LEADING_SPACES));

        assertTrue(Description.isValidDescription(DESC_WITH_CAROT));
        assertTrue(Description.isValidDescription(DESC_WITH_AT));
        assertTrue(Description.isValidDescription(DESC_WITH_SLASH));
        assertTrue(Description.isValidDescription(DESC_WITH_TRAILING_SPACES));
    }
}
