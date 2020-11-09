package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.tutorialgroup.DayOfWeek;
import seedu.address.model.tutorialgroup.TimeOfDay;

public class TimeOfDayTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TimeOfDay(null));
    }

    @Test
    public void constructor_invalidTimeOfDay_throwsIllegalArgumentException() {
        String invalidTimeOfDayString = "1800";
        assertThrows(IllegalArgumentException.class, () -> new DayOfWeek(invalidTimeOfDayString));
    }

    @Test
    public void isValidTimeOfDay() {
        // null name
        assertThrows(NullPointerException.class, () -> TimeOfDay.isValidTimeOfDay(null));

        // invalid DayOfWeek Input
        assertFalse(TimeOfDay.isValidTimeOfDay(""));
        assertFalse(TimeOfDay.isValidTimeOfDay(" "));
        assertFalse(TimeOfDay.isValidTimeOfDay("^"));
        assertFalse(TimeOfDay.isValidTimeOfDay("peter*"));
        assertFalse(TimeOfDay.isValidTimeOfDay("23:60"));
        assertFalse(TimeOfDay.isValidTimeOfDay("-1"));

        // valid TimeOfDay input
        assertTrue(TimeOfDay.isValidTimeOfDay("23:59"));
        assertTrue(TimeOfDay.isValidTimeOfDay("00:00"));
    }
}
