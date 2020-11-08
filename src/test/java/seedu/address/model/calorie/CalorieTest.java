package seedu.address.model.calorie;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CalorieTest {

    @Test
    public void isValidCalorie() {
        // invalid format checks -> returns false
        assertFalse(Calorie.isValidCalorie("-1"));
        assertFalse(Calorie.isValidCalorie("100.0"));
        assertFalse(Calorie.isValidCalorie("100.0.0"));

        // valid format checks -> returns true
        assertTrue(Calorie.isValidCalorie("100"));

        // invalid range checks -> returns false
        assertFalse(Calorie.isValidCalorie(-1));
        assertFalse(Calorie.isValidCalorie(0));

        // valid range checks -> returns true
        assertTrue(Calorie.isValidCalorie(1));
        assertTrue(Calorie.isValidCalorie(100));
    }
}
