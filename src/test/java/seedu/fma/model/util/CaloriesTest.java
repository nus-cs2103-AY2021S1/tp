package seedu.fma.model.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CaloriesTest {

    @Test
    void isValidCalories_validCalories_returnTrue() {
        assertTrue(Calories.isValidCalories(1000));
        assertTrue(Calories.isValidCalories(1));
        assertTrue(Calories.isValidCalories(50));
        assertTrue(Calories.isValidCalories(501));
    }

    @Test
    void isValidCalories_invalidCalories_returnFalse() {
        assertFalse(Calories.isValidCalories(0));
        assertFalse(Calories.isValidCalories(-1));
        assertFalse(Calories.isValidCalories(1001));
        assertFalse(Calories.isValidCalories(Integer.MAX_VALUE));
    }
}
