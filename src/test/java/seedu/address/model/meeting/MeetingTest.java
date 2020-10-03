package seedu.address.model.meeting;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.address.model.project.Email;

import java.time.LocalDateTime;

public class MeetingTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Meeting(null));
    }

    @Test
    public void constructor_invalidMeeting_throwsIllegalArgumentException() {
        String invalidMeetingTime = "";
        assertThrows(IllegalArgumentException.class, () -> new Email(invalidMeetingTime));

    }

}
