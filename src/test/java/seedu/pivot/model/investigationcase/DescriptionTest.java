package seedu.pivot.model.investigationcase;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pivot.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DescriptionTest {
    private static final String SYMBOLS = "!@#$%^&*()_<>,./;'[]";
    private static final String ALPHA = "ABC";
    private static final String NUMERIC = "123";
    private static final String SYMBOLS_ALPHA_NUMERIC = "ASdsa14@#$%^";
    private static final String EMPTY = "";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }


    // Description can be initialized to empty OR blank but it they are not valid values to Add or Edit
    @Test
    public void isValidDescription() {

        // null description
        assertThrows(NullPointerException.class, () -> Description.isValidDescriptionToAdd(null));

        // valid values
        assertTrue(Description.isValidDescriptionToAdd(SYMBOLS));
        assertTrue(Description.isValidDescriptionToAdd(ALPHA));
        assertTrue(Description.isValidDescriptionToAdd(NUMERIC));
        assertTrue(Description.isValidDescriptionToAdd(SYMBOLS_ALPHA_NUMERIC));

        // empty -> false
        assertFalse(Description.isValidDescriptionToAdd(EMPTY));
    }

    @Test
    public void hasDescription() {

        // Description object with a description
        Description filledDescription = new Description(SYMBOLS_ALPHA_NUMERIC);

        // Description object with a no description
        Description emptyDescription = new Description(EMPTY);

        assertTrue(filledDescription.hasDescription());

        assertFalse(emptyDescription.hasDescription());
    }

    @Test
    public void equals() {

        Description description = new Description(SYMBOLS);
        // same values -> returns true
        assertTrue(description.equals(new Description(SYMBOLS)));

        // same object -> returns true
        assertTrue(description.equals(description));

        // null -> returns false
        assertFalse(description.equals(null));

        // different type -> returns false
        assertFalse(description.equals(5));

        // different String -> returns false
        assertFalse(description.equals(new Description(ALPHA)));
    }
}
