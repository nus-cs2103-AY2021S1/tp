package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MeetingNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MeetingName(null));
    }

    @Test
    public void constructor_invalidMeetingName_throwsIllegalArgumentException() {
        String invalidMeetingName = "";
        assertThrows(IllegalArgumentException.class, () -> new MeetingName(invalidMeetingName));
    }

    @Test
    public void isValidMeetingName() {
        // null name
        assertThrows(NullPointerException.class, () -> MeetingName.isValidMeetingName(null));

        // invalid name
        assertFalse(MeetingName.isValidMeetingName("")); // empty string
        assertFalse(MeetingName.isValidMeetingName(" ")); // spaces only
        assertFalse(MeetingName.isValidMeetingName("^")); // only non-alphanumeric characters
        assertFalse(MeetingName.isValidMeetingName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(MeetingName.isValidMeetingName("meeting abcde")); // alphabets only
        assertTrue(MeetingName.isValidMeetingName("12345")); // numbers only
        assertTrue(MeetingName.isValidMeetingName("meeting 12345")); // alphanumeric characters
        assertTrue(MeetingName.isValidMeetingName("Meeting ABCDE")); // with capital letters
        assertTrue(MeetingName.isValidMeetingName("Meeting With Group Mates To Discuss Project")); // long names
    }
}
