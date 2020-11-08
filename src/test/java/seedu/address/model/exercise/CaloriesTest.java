package seedu.address.model.exercise;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CaloriesTest {
    @Test
    public void constructor_invalidCalories_throwsIllegalArgumentException() {
        String invalidCalories = "";
        assertThrows(IllegalArgumentException.class, () -> new Calories(invalidCalories));
    }

    @Test
    public void isValidCalories() {
        // Boundary Testing
        assertTrue(Calories.isValidCalories("0"));
        assertTrue(Calories.isValidCalories("2147483647"));

        //Partition Testing
        assertFalse(Calories.isValidCalories("-1")); // long number
        assertFalse(Calories.isValidCalories("2147483648")); // long number
        assertFalse(Calories.isValidCalories("phone")); // non-numeric
        assertFalse(Calories.isValidCalories("")); // empty string
        assertFalse(Calories.isValidCalories(" ")); // spaces only
        assertFalse(Calories.isValidCalories("9011p041")); // alphabets within digits
        assertFalse(Calories.isValidCalories("9312 1534")); // spaces within digits
    }

}
