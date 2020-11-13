package seedu.meeting.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMeetings.MEETING_A;
import static seedu.address.testutil.TypicalMeetings.getTypicalMeetingBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.meeting.MeetingBook;
import seedu.address.model.meeting.ReadOnlyMeetingBook;
import seedu.address.model.meeting.meeting.Meeting;
import seedu.address.model.meeting.meeting.exceptions.DuplicateMeetingException;
import seedu.address.testutil.MeetingBuilder;

public class MeetingBookTest {

    private final MeetingBook meetingBook = new MeetingBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), meetingBook.getMeetingList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> meetingBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyMeetingBook_replacesData() {
        MeetingBook newData = getTypicalMeetingBook();
        meetingBook.resetData(newData);
        assertEquals(newData, meetingBook);
    }

    @Test
    public void resetData_withDuplicateMeetings_throwsDuplicateMeetingException() {
        // Two meetings with the same identity fields
        Meeting editedMeetingA = new MeetingBuilder(MEETING_A)
                .build();
        List<Meeting> newMeetings = Arrays.asList(MEETING_A, editedMeetingA);
        MeetingBookStub newData = new MeetingBookStub(newMeetings);

        assertThrows(DuplicateMeetingException.class, () -> meetingBook.resetData(newData));
    }

    @Test
    public void hasMeeting_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> meetingBook.hasMeeting(null));
    }

    @Test
    public void hasMeeting_meetingNotInMeetingBook_returnsFalse() {
        assertFalse(meetingBook.hasMeeting(MEETING_A));
    }

    @Test
    public void hasMeeting_meetingInMeetingBook_returnsTrue() {
        meetingBook.addMeeting(MEETING_A);
        assertTrue(meetingBook.hasMeeting(MEETING_A));
    }

    @Test
    public void hasMeeting_meetingWithSameIdentityFieldsInMeetingBook_returnsTrue() {
        meetingBook.addMeeting(MEETING_A);
        Meeting editedAlice = new MeetingBuilder(MEETING_A)
                .build();
        assertTrue(meetingBook.hasMeeting(editedAlice));
    }

    @Test
    public void getMeetingList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> meetingBook.getMeetingList().remove(0));
    }

    /**
     * A stub ReadOnlyMeetingBook whose meetings list can violate interface constraints.
     */
    private static class MeetingBookStub implements ReadOnlyMeetingBook {
        private final ObservableList<Meeting> meetings = FXCollections.observableArrayList();

        MeetingBookStub(Collection<Meeting> meetings) {
            this.meetings.setAll(meetings);
        }

        @Override
        public ObservableList<Meeting> getMeetingList() {
            return meetings;
        }
    }

}
