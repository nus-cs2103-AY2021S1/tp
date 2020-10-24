package seedu.address.model.task.event;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EndDateTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EndDateTime(null));
    }

    @Test
    public void constructor_invalidDateTime_throwsIllegalArgumentException() {
        String invalidEndDateTime = "12-30-2020 12:12";
        assertThrows(IllegalArgumentException.class, () -> new EndDateTime(invalidEndDateTime));
    }

    @Test
    public void isValidDateTime() {
        // null dateTime number
        assertThrows(NullPointerException.class, () -> EndDateTime.isValidEndDateTime(null));

        // invalid dateTime numbers
        assertFalse(EndDateTime.isValidEndDateTime("")); // empty string
        assertFalse(EndDateTime.isValidEndDateTime(" ")); // spaces only
        assertFalse(EndDateTime.isValidEndDateTime("12-13-2000 12:00")); // month greater than 12
        assertFalse(EndDateTime.isValidEndDateTime("date")); // non-numeric
        assertFalse(EndDateTime.isValidEndDateTime("32-01-1200")); // day greater than 31
        assertFalse(EndDateTime.isValidEndDateTime("12-12-2020")); // no time
        assertFalse(EndDateTime.isValidEndDateTime("5-6-2020 12:00")); // no leading zero for day and month

        // valid dateTime numbers
        assertTrue(EndDateTime.isValidEndDateTime("05-09-2020 18:00")); // date and month with leading zero
        assertTrue(EndDateTime.isValidEndDateTime("12-12-2020 12:00"));
    }
}
