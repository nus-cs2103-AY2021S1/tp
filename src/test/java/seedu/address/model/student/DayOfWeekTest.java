package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.tutorialgroup.DayOfWeek;

public class DayOfWeekTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DayOfWeek(null));
    }

    @Test
    public void constructor_invalidDayOfWeek_throwsIllegalArgumentException() {
        String invalidDayOfWeekString = "MONDAY";
        assertThrows(IllegalArgumentException.class, () -> new DayOfWeek(invalidDayOfWeekString));
    }

    @Test
    public void isValidDayOfWeek() {
        // null name
        assertThrows(NullPointerException.class, () -> DayOfWeek.isValidDayOfWeek(null));

        // invalid DayOfWeek Input
        assertFalse(DayOfWeek.isValidDayOfWeek(""));
        assertFalse(DayOfWeek.isValidDayOfWeek(" "));
        assertFalse(DayOfWeek.isValidDayOfWeek("^"));
        assertFalse(DayOfWeek.isValidDayOfWeek("peter*"));
        assertFalse(DayOfWeek.isValidDayOfWeek("Wednesday"));
        assertFalse(DayOfWeek.isValidDayOfWeek("tuesday"));

        // valid DayOfWeek Input
        assertTrue(DayOfWeek.isValidDayOfWeek("MON"));
        assertTrue(DayOfWeek.isValidDayOfWeek("TUE"));
        assertTrue(DayOfWeek.isValidDayOfWeek("WED"));
        assertTrue(DayOfWeek.isValidDayOfWeek("THU"));
        assertTrue(DayOfWeek.isValidDayOfWeek("FRI"));
    }
}
