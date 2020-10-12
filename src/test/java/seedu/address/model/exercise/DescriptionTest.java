package seedu.address.model.exercise;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DescriptionTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new seedu.address.model.exercise.Description(null));
    }

    @Test
    public void isValidDescription() {
        // null description
        assertThrows(NullPointerException.class, () -> seedu.address.model.exercise.Description.isValidDescription(null));

        // invalid description
        assertFalse(seedu.address.model.exercise.Description.isValidDescription("")); // empty string

        // valid description
        assertTrue(seedu.address.model.exercise.Description.isValidDescription(" ")); // spaces only
        assertTrue(seedu.address.model.exercise.Description.isValidDescription("finished today")); // alphabets only
        assertTrue(seedu.address.model.exercise.Description.isValidDescription("12345")); // numbers only
        assertTrue(seedu.address.model.exercise.Description.isValidDescription("2 times")); // alphanumeric characters
        assertTrue(seedu.address.model.exercise.Description.isValidDescription("2 Times")); // with capital letters
    }
}
