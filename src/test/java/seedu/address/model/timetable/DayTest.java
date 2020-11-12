package seedu.address.model.timetable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DayTest {

    private static final Day MONDAY = Day.MONDAY;
    private static final Day TUESDAY = Day.TUESDAY;
    private static final Day WEDNESDAY = Day.WEDNESDAY;
    private static final Day THURSDAY = Day.THURSDAY;
    private static final Day FRIDAY = Day.FRIDAY;
    private static final Day INVALID_DAY = Day.UNKNOWN;

    @Test
    public void getDayEnum() {

        // valid days
        assertEquals(MONDAY, Day.getDayEnum("monday"));
        assertEquals(TUESDAY, Day.getDayEnum("tuesday"));
        assertEquals(WEDNESDAY, Day.getDayEnum("wednesday"));
        assertEquals(THURSDAY, Day.getDayEnum("thursday"));
        assertEquals(FRIDAY, Day.getDayEnum("friday"));

        // invalid day
        assertEquals(INVALID_DAY, Day.getDayEnum("saturday"));
        assertEquals(INVALID_DAY, Day.getDayEnum("random day"));

        // valid but different days
        assertNotEquals(MONDAY, Day.getDayEnum("tuesday"));
        assertNotEquals(TUESDAY, Day.getDayEnum("wednesday"));
        assertNotEquals(WEDNESDAY, Day.getDayEnum("thursday"));
        assertNotEquals(THURSDAY, Day.getDayEnum("friday"));
        assertNotEquals(FRIDAY, Day.getDayEnum("monday"));
    }

    @Test
    public void isSameDay() {
        // valid days, same day -> returns true
        assertTrue(MONDAY.isSameDay(MONDAY));

        // valid days, different day -> returns false
        assertFalse(MONDAY.isSameDay(TUESDAY));

        // 1 invalid day -> returns false
        assertFalse(INVALID_DAY.isSameDay(WEDNESDAY));

        // 2 invalid days -> returns true (vacuously)
        assertTrue(Day.getDayEnum("saturday").isSameDay(Day.getDayEnum("random")));
    }

    @Test
    public void isUnknownDay() {
        // valid day -> returns false
        assertFalse(Day.isUnknownDay(MONDAY));

        // invalid day -> returns true
        assertTrue(Day.isUnknownDay(INVALID_DAY));
    }

    @Test
    public void toStringTest() {
        assertEquals("Monday", MONDAY.toString());
        assertEquals("Wednesday", WEDNESDAY.toString());

        assertNotEquals("FrIdAy", FRIDAY.toString());
    }
}
