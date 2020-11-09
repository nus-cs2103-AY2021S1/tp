package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_TIME_TUTOR_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_TUTOR_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_URL_TUTOR_MEETING;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalLinks.PROJECT_DOCUMENT;
import static seedu.address.testutil.TypicalLinks.PROJECT_MEETING;
import static seedu.address.testutil.TypicalLinks.TUTOR_MEETING;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.MeetingLinkBuilder;



public class MeetingLinkTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MeetingLink(null, null, (String) null));
        assertThrows(NullPointerException.class, () -> new MeetingLink(null, null, (LocalDateTime) null));
    }

    @Test
    public void constructor_invalidUrl_throwsIllegalArgumentException() {
        String invalidUrl = "example.com";
        assertThrows(IllegalArgumentException.class, () -> new MeetingLink(VALID_DESCRIPTION_TUTOR_MEETING,
                invalidUrl, VALID_DATE_TIME_TUTOR_MEETING));
    }

    @Test
    public void snooze() {
        MeetingLink projectMeetingCopy = new MeetingLinkBuilder(PROJECT_MEETING).build();
        String snoozeTime = "12-12-3030 2359";

        // Test bad input
        assertThrows(DateTimeParseException.class, () -> projectMeetingCopy.snooze(""));
        assertThrows(DateTimeParseException.class, () -> projectMeetingCopy.snooze(" "));
        assertThrows(DateTimeParseException.class, () -> projectMeetingCopy.snooze("abc"));
        assertThrows(DateTimeParseException.class, () -> projectMeetingCopy.snooze("123"));
        assertThrows(DateTimeParseException.class, () -> projectMeetingCopy.snooze("22-22-2222 2222"));

        MeetingLink projectMeetingSnooze = new MeetingLinkBuilder(PROJECT_MEETING).build();
        projectMeetingSnooze.snooze(snoozeTime);
        assertFalse(projectMeetingSnooze.getDateTime().equals(PROJECT_MEETING.getDateTime()));

        MeetingLink editedProjectMeeting = new MeetingLinkBuilder(PROJECT_MEETING).withDatetime(snoozeTime).build();
        assertTrue(projectMeetingSnooze.getDateTime().equals(editedProjectMeeting.getDateTime()));

    }

    @Test
    public void equals() {

        // same values -> returns true
        MeetingLink projectMeetingCopy = new MeetingLinkBuilder(PROJECT_MEETING).build();
        assertTrue(PROJECT_MEETING.equals(projectMeetingCopy));

        // same object -> returns true
        assertTrue(PROJECT_MEETING.equals(PROJECT_MEETING));

        // null -> returns false
        assertFalse(PROJECT_MEETING.equals(null));

        // different type -> returns false
        assertFalse(PROJECT_MEETING.equals(5));

        assertFalse(PROJECT_MEETING.equals(PROJECT_DOCUMENT));

        // different person -> returns false
        assertFalse(PROJECT_MEETING.equals(TUTOR_MEETING));

        // different description -> returns false
        MeetingLink editedProjectMeeting = new MeetingLinkBuilder(PROJECT_MEETING)
                .withDescription(VALID_DESCRIPTION_TUTOR_MEETING).build();
        assertFalse(PROJECT_MEETING.equals(editedProjectMeeting));

        // different datetime -> returns false
        editedProjectMeeting = new MeetingLinkBuilder(PROJECT_MEETING)
                .withDatetime(VALID_DATE_TIME_TUTOR_MEETING).build();
        assertFalse(PROJECT_MEETING.equals(editedProjectMeeting));

        // different URL -> returns false
        editedProjectMeeting = new MeetingLinkBuilder(PROJECT_MEETING)
                .withUrl(VALID_URL_TUTOR_MEETING).build();
        assertFalse(PROJECT_MEETING.equals(editedProjectMeeting));
    }
}
