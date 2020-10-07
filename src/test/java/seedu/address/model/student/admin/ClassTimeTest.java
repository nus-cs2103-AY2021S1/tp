package seedu.address.model.student.admin;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ClassTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ClassTime(null));
    }

    @Test
    public void constructor_invalidClassTime_throwsIllegalArgumentException() {
        // Completely empty input
        String emptyClassTime = "";
        assertThrows(IllegalArgumentException.class, () -> new ClassTime(emptyClassTime));

        // Invalid input
        String invalidInput = "2 hey sup";
        assertThrows(IllegalArgumentException.class, () -> new ClassTime(invalidInput));

        // Invalid Day of week (must be 1 - 7)
        String invalidDayOfWeek_1 = "0 1000-1230";
        assertThrows(IllegalArgumentException.class, () -> new ClassTime(invalidDayOfWeek_1));

        String invalidDayOfWeek_2 = "9 1000-1200";
        assertThrows(IllegalArgumentException.class, () -> new ClassTime(invalidDayOfWeek_2));

        // Invalid Start time
        String invalidStartTime = "0 2500-1230";
        assertThrows(IllegalArgumentException.class, () -> new ClassTime(invalidStartTime));

        // Invalid End time
        String invalidEndTime = "9 1000-0090";
        assertThrows(IllegalArgumentException.class, () -> new ClassTime(invalidEndTime));
    }

    @Test
    public void isValidClassTime() {
        // null class time
        assertThrows(NullPointerException.class, () -> ClassTime.isValidClassTime(null));

        // invalid class times
        assertFalse(ClassTime.isValidClassTime("")); // empty string
        assertFalse(ClassTime.isValidClassTime(" ")); // spaces only
        assertFalse(ClassTime.isValidClassTime("0 1000-1230")); // invalid day of week
        assertFalse(ClassTime.isValidClassTime("10 1000-1230")); // invalid day of week
        assertFalse(ClassTime.isValidClassTime("1 1090-1230")); // invalid start time
        assertFalse(ClassTime.isValidClassTime("2 1000-2930")); // invalid day of week

        // valid class times
        assertTrue(ClassTime.isValidClassTime("2 1000-2230"));
        assertTrue(ClassTime.isValidClassTime("7 0000-2359"));
    }
}
