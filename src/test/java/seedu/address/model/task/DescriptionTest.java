package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

//@@author w-yeehong
public class DescriptionTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void isValidDescription() {
        // EP for description: [null], [string length < 1], [string length > 4000]
        //                     [1 <= string length <= 4000]

        // EP [null] -> throws NullPointerException
        assertThrows(NullPointerException.class, () -> Description.isValidDescription(null));

        // EP [string length < 1] -> returns false
        assertFalse(Description.isValidDescription(""));

        // EP [string length > 4000] -> returns false
        String invalidString = ".".repeat(4001);
        assertTrue(invalidString.length() > 4000);
        assertFalse(Description.isValidDescription(invalidString));

        // EP [1 <= string length <= 4000], BV 1 -> returns true
        assertTrue(Description.isValidDescription("."));

        // EP [1 <= string length <= 4000], BV 4000 -> returns true
        String validString = ".".repeat(4000);
        assertTrue(validString.length() == 4000);
        assertTrue(Description.isValidDescription(validString));
    }
}
