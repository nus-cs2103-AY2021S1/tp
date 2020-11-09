package seedu.address.model.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TemperatureRangeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Temperature(null));
    }

    @Test
    public void constructor_invalidTemperature_throwsIllegalArgumentException() {
        String invalidTemperatureRange = "";
        assertThrows(IllegalArgumentException.class, () -> new TemperatureRange(invalidTemperatureRange));
    }

    @Test
    public void isValidTemperatureRange() {
        // null temperatureRange
        assertThrows(NullPointerException.class, () -> TemperatureRange.isValidTemperatureRange(null));

        // invalid temperatureRange
        assertFalse(TemperatureRange.isValidTemperatureRange("")); //empty string
        assertFalse(TemperatureRange.isValidTemperatureRange(" ")); // spaces only
        assertFalse(TemperatureRange.isValidTemperatureRange("37.0")); //only 1 temperature
        assertFalse(TemperatureRange.isValidTemperatureRange("37.8-37")); //second temperature invalid

        // valid temperatureRange
        assertTrue(TemperatureRange.isValidTemperatureRange("36.7-37.0"));
        assertTrue(TemperatureRange.isValidTemperatureRange("36.0-38.0"));
    }
}
