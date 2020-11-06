package seedu.address.model.recipe;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CaloriesTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Calories(null));
    }

    @Test
    public void isValidCalories() {
        // null name
        assertThrows(NullPointerException.class, () -> Calories.isValidCalories(null));

        // invalid calories String
        assertFalse(Calories.isValidCalories("")); // empty value
        assertFalse(Calories.isValidCalories("1 2")); // space
        assertFalse(Calories.isValidCalories("-1")); // negative integer
        assertFalse(Calories.isValidCalories("0"));
        assertFalse(Calories.isValidCalories("-214783647")); // min_integer

        // valid calories String
        assertTrue(Calories.isValidCalories("1")); // positive number
        assertTrue(Calories.isValidCalories("214783647")); // max_integer

        // valid calories integer
        assertTrue(Calories.isValidCalories(0));
        assertTrue(Calories.isValidCalories(1));
        assertTrue(Calories.isValidCalories(214783647));

        // invalid calories integer
        assertFalse(Calories.isValidCalories(-1));
        assertFalse(Calories.isValidCalories(-214783647));

    }

    @Test
    public void equals() {
        Calories calories1 = new Calories(1);
        Calories calories2 = new Calories(2);

        // same object -> returns true
        assertTrue(calories1.equals(calories1));

        // same values -> returns true
        Calories calories1Copy = new Calories(1);
        assertTrue(calories1.equals(calories1Copy));

        // different types -> returns false
        assertFalse(calories1.equals(1));

        // null -> returns false
        assertFalse(calories1.equals(null));

        // different calories -> returns false
        assertFalse(calories1.equals(calories2));
    }
}
