package seedu.address.model.meeting.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.meeting.CommandTestUtil.VALID_CONTACTS_B;
import static seedu.address.logic.commands.meeting.CommandTestUtil.VALID_DESCRIPTION_B;
import static seedu.address.logic.commands.meeting.CommandTestUtil.VALID_FROM_A;
import static seedu.address.logic.commands.meeting.CommandTestUtil.VALID_FROM_B;
import static seedu.address.logic.commands.meeting.CommandTestUtil.VALID_TITLE_B;
import static seedu.address.logic.commands.meeting.CommandTestUtil.VALID_TO_A;
import static seedu.address.logic.commands.meeting.CommandTestUtil.VALID_TO_B;
import static seedu.address.testutil.TypicalMeetings.MEETING_A;
import static seedu.address.testutil.TypicalMeetings.MEETING_B;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.MeetingBuilder;

class MeetingTest {

    @Test
    public void isSameMeeting() {
        // same object -> returns true
        assertTrue(MEETING_A.isSameMeeting(MEETING_A));

        // null-> returns false
        assertFalse(MEETING_A.isSameMeeting(null));

        // different from -> return false
        Meeting editedMeeting = new MeetingBuilder(MEETING_A)
                .withFrom(VALID_FROM_B).build();
        assertFalse(MEETING_A.isSameMeeting(editedMeeting));

        // different to -> returns false
        editedMeeting = new MeetingBuilder(MEETING_A)
                .withTo("16:00").build();
        assertFalse(MEETING_A.isSameMeeting(editedMeeting));

        // different title -> return false
        editedMeeting = new MeetingBuilder(MEETING_A)
                .withTitle(VALID_TITLE_B).build();
        assertFalse(MEETING_A.isSameMeeting(editedMeeting));

        // different from  -> returns false
        editedMeeting = new MeetingBuilder(MEETING_A)
                .withFrom(VALID_FROM_B).build();
        assertFalse(MEETING_A.isSameMeeting(editedMeeting));

        //        // TODO:
        //        // same name, same from, same to, different attributes -> returns true
        //        editedMeeting = new MeetingBuilder(MEETING_A)
        //                .withDescription(VALID_DESCRIPTION_B)
        //                .withContacts(VALID_CONTACTS_B)
        //                .withLocation(VALID_LOCATION_B).build();
        //        assertFalse(MEETING_A.isSameMeeting(editedMeeting));

    }

    @Test
    public void isValidFromAndTo() {

        // FROM < TO -> returns true
        assertTrue(Meeting.isValidFromAndTo(new From(VALID_FROM_A), new To(VALID_TO_A)));

        // FROM = TO -> returns false
        assertFalse(Meeting.isValidFromAndTo(new From(VALID_FROM_B), new To("12:00")));

        // From > To -> returns false
        assertFalse(Meeting.isValidFromAndTo(new From(VALID_FROM_A), new To(VALID_TO_B)));

    }

    @Test
    public void equals() {
        Meeting meetingACopy = new MeetingBuilder(MEETING_A).build();
        assertTrue(MEETING_A.equals(meetingACopy));

        // same object -> returns true
        assertTrue(MEETING_A.equals(MEETING_A));

        // null -> returns false
        assertFalse(meetingACopy.equals(null));

        // different type -> returns false
        assertFalse(meetingACopy.equals(5));

        // null -> returns false
        assertFalse(meetingACopy.equals(MEETING_B));

        // different name -> returns false
        Meeting editedMeeting = new MeetingBuilder(MEETING_A)
                .withTitle(VALID_TITLE_B).build();
        assertFalse(meetingACopy.equals(editedMeeting));

        // different optional -> returns false
        editedMeeting = new MeetingBuilder(MEETING_A)
                .withDescription(VALID_DESCRIPTION_B).build();
        assertFalse(meetingACopy.equals(editedMeeting));

        // different from -> returns false
        editedMeeting = new MeetingBuilder(MEETING_A)
                .withFrom(VALID_FROM_B).build();
        assertFalse(meetingACopy.equals(editedMeeting));

        // different to -> returns false
        editedMeeting = new MeetingBuilder(MEETING_A)
                .withTitle(VALID_TO_B).build();
        assertFalse(meetingACopy.equals(editedMeeting));

        // different contacts -> returns false
        editedMeeting = new MeetingBuilder(MEETING_A)
                .withTitle(VALID_CONTACTS_B).build();
        assertFalse(meetingACopy.equals(editedMeeting));

        // different location -> returns false
        editedMeeting = new MeetingBuilder(MEETING_A)
                .withTitle(VALID_TITLE_B).build();
        assertFalse(meetingACopy.equals(editedMeeting));


    }

}
