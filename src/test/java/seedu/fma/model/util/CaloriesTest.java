package seedu.fma.model.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CaloriesTest {

    @Test
    void isValidCalories_validCalories_returnTrue() {
        assertTrue(Calories.isValidCalories("100"));
        assertTrue(Calories.isValidCalories("1"));
        assertTrue(Calories.isValidCalories("123029309209202"));
        assertTrue(Calories.isValidCalories("32332"));
    }

    @Test
    void isValidCalories_invalidCalories_returnFalse() {
        assertFalse(Calories.isValidCalories(""));
        assertFalse(Calories.isValidCalories("-19"));
        assertFalse(Calories.isValidCalories("-0.0.0"));
        assertFalse(Calories.isValidCalories("funny f"));
        assertFalse(Calories.isValidCalories("lalala"));
        assertFalse(Calories.isValidCalories("90.5"));
    }
}
