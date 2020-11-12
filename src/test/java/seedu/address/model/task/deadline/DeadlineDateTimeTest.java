package seedu.address.model.task.deadline;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DeadlineDateTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeadlineDateTime(null));
    }

    @Test
    public void constructor_invalidDateTime_throwsIllegalArgumentException() {
        String invalidDeadlineDateTime = "12-30-2020 12:12";
        assertThrows(IllegalArgumentException.class, () -> new DeadlineDateTime(invalidDeadlineDateTime));
    }

    @Test
    public void isValidDateTime() {
        // null dateTime number
        assertThrows(NullPointerException.class, () -> DeadlineDateTime.isValidDateTime(null));

        // invalid dateTime numbers
        assertFalse(DeadlineDateTime.isValidDateTime("")); // empty string
        assertFalse(DeadlineDateTime.isValidDateTime(" ")); // spaces only
        assertFalse(DeadlineDateTime.isValidDateTime("12-13-2000 12:00")); // month greater than 12
        assertFalse(DeadlineDateTime.isValidDateTime("date")); // non-numeric
        assertFalse(DeadlineDateTime.isValidDateTime("32-01-1200")); // day greater than 31
        assertFalse(DeadlineDateTime.isValidDateTime("12-12-2020")); // no time
        assertFalse(DeadlineDateTime.isValidDateTime("5-6-2020 12:00")); // no leading zero for day and month

        // valid dateTime numbers
        assertTrue(DeadlineDateTime.isValidDateTime("05-09-2020 18:00")); // date and month with leading zero
        assertTrue(DeadlineDateTime.isValidDateTime("12-12-2020 12:00"));
    }
}
