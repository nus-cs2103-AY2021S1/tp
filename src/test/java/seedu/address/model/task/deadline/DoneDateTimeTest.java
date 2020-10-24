package seedu.address.model.task.deadline;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DoneDateTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DoneDateTime(null));
    }

    @Test
    public void constructor_invalidDateTime_throwsIllegalArgumentException() {
        String invalidDoneDateTime = "12-30-2020 12:12";
        assertThrows(IllegalArgumentException.class, () -> new DoneDateTime(invalidDoneDateTime));
    }

    @Test
    public void isValidDateTime() {
        // null dateTime number
        assertThrows(NullPointerException.class, () -> DoneDateTime.isValidDoneDateTime(null));

        // invalid dateTime numbers
        assertFalse(DoneDateTime.isValidDoneDateTime("")); // empty string
        assertFalse(DoneDateTime.isValidDoneDateTime(" ")); // spaces only
        assertFalse(DoneDateTime.isValidDoneDateTime("12-13-2000 12:00")); // month greater than 12
        assertFalse(DoneDateTime.isValidDoneDateTime("date")); // non-numeric
        assertFalse(DoneDateTime.isValidDoneDateTime("32-01-1200")); // day greater than 31
        assertFalse(DoneDateTime.isValidDoneDateTime("12-12-2020")); // no time
        assertFalse(DoneDateTime.isValidDoneDateTime("5-6-2020 12:00")); // no leading zero for day and month

        // valid dateTime numbers
        assertTrue(DoneDateTime.isValidDoneDateTime("05-09-2020 18:00")); // date and month with leading zero
        assertTrue(DoneDateTime.isValidDoneDateTime("12-12-2020 12:00"));
    }
}
