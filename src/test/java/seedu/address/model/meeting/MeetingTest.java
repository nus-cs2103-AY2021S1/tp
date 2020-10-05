package seedu.address.model.meeting;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MeetingTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Meeting(null));
    }

    @Test
    public void constructor_invalidMeeting_throwsIllegalArgumentException() {
        assertThrows(java.time.format.DateTimeParseException.class, () -> new Meeting(""));

    }

}
