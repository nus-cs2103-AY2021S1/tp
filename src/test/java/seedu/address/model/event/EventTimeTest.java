package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.event.EventUtil;

public class EventTimeTest {

    public static final String VALID_DATE_STRING_1 = "2-2-2022 1000";
    public static final String VALID_DATE_STRING_2 = "1-2-2020 1200";
    public static final String INVALID_DATE_STRING = "32-2-2020 1000";
    public static final String INVALID_DATE_MISSING_TIME_STRING = "21-2-2020";

    @Test
    public void constructor_one_variable() {
        EventTime time = EventUtil.makeEventTime(VALID_DATE_STRING_1);
    }

    @Test
    public void constructor_two_variable() {
        EventTime time = EventUtil.makeEventTime(VALID_DATE_STRING_1, VALID_DATE_STRING_2);
    }

    @Test
    public void constructorNull_time_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> EventUtil.makeEventTime(null));
    }

    @Test
    public void constructorInvalid_timeFormat_throwsDateTimeException() {
        assertThrows(DateTimeException.class, () -> EventUtil.makeEventTime(INVALID_DATE_STRING));
        assertThrows(DateTimeException.class, () -> EventUtil.makeEventTime(INVALID_DATE_MISSING_TIME_STRING));
    }

    @Test
    public void validDate() {
        assertTrue(EventTime.isValidDateTime("2020-02-02T12:00"));
    }

    @Test
    public void invalidDate() {
        assertFalse(EventTime.isValidDateTime(INVALID_DATE_STRING));
        assertFalse(EventTime.isValidDateTime(INVALID_DATE_MISSING_TIME_STRING));
    }

    @Test
    public void getStart() {
        EventTime time = EventUtil.makeEventTime(VALID_DATE_STRING_1, VALID_DATE_STRING_2);
        LocalDateTime timeOne = EventUtil.makeLocalDateTime(VALID_DATE_STRING_1);
        assertEquals(time.getStart(), timeOne);
    }

    @Test
    public void getEnd() {
        EventTime time = EventUtil.makeEventTime(VALID_DATE_STRING_1, VALID_DATE_STRING_2);
        LocalDateTime timeTwo = EventUtil.makeLocalDateTime(VALID_DATE_STRING_2);
        assertEquals(time.getEnd(), timeTwo);
    }

    @Test
    public void isEqual() {
        EventTime timeOne = EventUtil.makeEventTime(VALID_DATE_STRING_1, VALID_DATE_STRING_2);
        EventTime timeTwo = EventUtil.makeEventTime(VALID_DATE_STRING_1, VALID_DATE_STRING_2);
        assertTrue(timeOne.equals(timeTwo));
    }
}
