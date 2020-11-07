package seedu.address.model.timetable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class DurationTest {

    public static final Duration DURATION_1600_1800 =
            new Duration(LocalTime.of(16, 0), LocalTime.of(18, 0));
    public static final Duration DURATION_1600_1800_COPY =
            new Duration(LocalTime.of(16, 0), LocalTime.of(18, 0));
    public static final Duration DURATION_1600_1700 =
            new Duration(LocalTime.of(16, 0), LocalTime.of(17, 0));
    public static final Duration DURATION_1700_1800 =
            new Duration(LocalTime.of(17, 0), LocalTime.of(18, 0));
    public static final Duration DURATION_1630_1730 =
            new Duration(LocalTime.of(16, 30), LocalTime.of(17, 30));
    public static final Duration DURATION_1600_1759 =
            new Duration(LocalTime.of(16, 0), LocalTime.of(17, 59));
    public static final Duration DURATION_1600_1801 =
            new Duration(LocalTime.of(16, 0), LocalTime.of(18, 1));
    public static final Duration DURATION_1800_2000 =
            new Duration(LocalTime.of(18, 0), LocalTime.of(20, 0));

    @Test
    public void isValidDuration() {
        // format tests
        assertTrue(Duration.isValidDuration("1800-1900"));

        // incorrect format -> returns false
        assertFalse(Duration.isValidDuration("8-9"));
        assertFalse(Duration.isValidDuration("800-900"));
        assertFalse(Duration.isValidDuration("1800 - 1900"));
        assertFalse(Duration.isValidDuration("18:00-19:00"));
        assertFalse(Duration.isValidDuration("6 to 7"));
        assertFalse(Duration.isValidDuration("6pm to 7pm"));

        // valid boundary values -> returns true
        assertTrue(Duration.isValidDuration("0000-0001"));
        assertTrue(Duration.isValidDuration("2358-2359"));

        // invalid boundary values -> returns false
        assertFalse(Duration.isValidDuration("2300-2400"));
        assertFalse(Duration.isValidDuration("2300-2401"));
        assertFalse(Duration.isValidDuration("2400-2401"));


        // order tests

        // valid order -> returns true
        assertTrue(Duration.isValidDuration(LocalTime.of(16, 0), LocalTime.of(18, 0)));
        assertTrue(Duration.isValidDuration(LocalTime.of(16, 0), LocalTime.of(16, 1)));
        assertTrue(Duration.isValidDuration(LocalTime.of(23, 59), LocalTime.MAX));
        assertTrue(Duration.isValidDuration(LocalTime.MIN, LocalTime.of(0, 1)));

        // invalid order -> returns false
        assertFalse(Duration.isValidDuration(LocalTime.of(18, 0), LocalTime.of(16, 0)));
        assertFalse(Duration.isValidDuration(LocalTime.of(18, 0), LocalTime.of(17, 59)));
        assertFalse(Duration.isValidDuration(LocalTime.MAX, LocalTime.of(23, 59)));
        assertFalse(Duration.isValidDuration(LocalTime.MAX, LocalTime.MIN));

        // same timing -> returns false
        assertFalse(Duration.isValidDuration(LocalTime.of(18, 0), LocalTime.of(18, 0)));
        assertFalse(Duration.isValidDuration(LocalTime.MAX, LocalTime.MAX));
    }

    @Test
    public void isSameDuration() {
        // same object -> returns true
        assertTrue(DURATION_1600_1800.isSameDuration(DURATION_1600_1800));

        // different object, same duration -> returns true
        assertTrue(DURATION_1600_1800.isSameDuration(DURATION_1600_1800_COPY));

        // contains null -> returns false
        assertFalse(DURATION_1600_1800.isSameDuration(null));

        // different durations -> returns false
        assertFalse(DURATION_1600_1800.isSameDuration(DURATION_1800_2000));
    }

    @Test
    public void hasOverlapDuration() {
        // same object -> returns true
        assertTrue(DURATION_1600_1800.hasOverlapDuration(DURATION_1600_1800));

        // different object, same duration -> returns true
        assertTrue(DURATION_1600_1800.hasOverlapDuration(DURATION_1600_1800_COPY));

        // same start time -> returns true
        assertTrue(DURATION_1600_1800.hasOverlapDuration(DURATION_1600_1700));

        // same end time -> returns true
        assertTrue(DURATION_1600_1800.hasOverlapDuration(DURATION_1700_1800));

        // complete overlap -> returns true
        assertTrue(DURATION_1600_1800.hasOverlapDuration(DURATION_1630_1730));
        assertTrue(DURATION_1630_1730.hasOverlapDuration(DURATION_1600_1800));

        // partial overlap -> returns true
        assertTrue(DURATION_1630_1730.hasOverlapDuration(DURATION_1600_1700));
        assertTrue(DURATION_1630_1730.hasOverlapDuration(DURATION_1700_1800));

        // boundary values, overlap -> returns true
        assertTrue(DURATION_1600_1800.hasOverlapDuration(DURATION_1600_1759));
        assertTrue(DURATION_1600_1800.hasOverlapDuration(DURATION_1600_1801));

        // end time of first duration equal start time of second duration -> returns false
        assertFalse(DURATION_1600_1800.hasOverlapDuration(DURATION_1800_2000));

        // completely no overlap -> returns false
        assertFalse(DURATION_1600_1700.hasOverlapDuration(DURATION_1800_2000));
    }

    @Test
    public void toStringTest() {
        assertEquals("1600-1800", DURATION_1600_1800.toString());
    }
}
