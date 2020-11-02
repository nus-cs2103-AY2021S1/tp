package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_NAME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMeetings.CS2100_MEETING;
import static seedu.address.testutil.TypicalMeetings.CS2101_MEETING;
import static seedu.address.testutil.TypicalModules.CS2101;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.MeetingBuilder;

public class MeetingTest {
    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Meeting meeting = new MeetingBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> meeting.getParticipants().remove(0));
    }

    @Test
    public void isSameMeeting() {
        // same object -> returns true
        assertTrue(CS2100_MEETING.isSameMeeting(CS2100_MEETING));

        // null -> returns false
        assertFalse(CS2100_MEETING.isSameMeeting(null));

        // different date and time but same module and meeting name -> returns true
        Meeting editedCS2100Meeting = new MeetingBuilder(CS2100_MEETING)
                .withDate(VALID_DATE)
                .withTime(VALID_TIME)
                .build();
        assertTrue(CS2100_MEETING.isSameMeeting(editedCS2100Meeting));

        // different name -> returns false
        editedCS2100Meeting = new MeetingBuilder(CS2100_MEETING).withName(VALID_MEETING_NAME).build();
        assertFalse(CS2100_MEETING.isSameMeeting(editedCS2100Meeting));

        // different module -> returns false
        editedCS2100Meeting = new MeetingBuilder(CS2100_MEETING).withModule(CS2101).build();
        assertFalse(CS2100_MEETING.isSameMeeting(editedCS2100Meeting));

        // same name, same module, same date, different attributes -> returns true
        editedCS2100Meeting = new MeetingBuilder(CS2100_MEETING).withDate(VALID_DATE)
                .withMembers(CS2101_MEETING.getParticipants()).build();
        assertTrue(CS2100_MEETING.isSameMeeting(editedCS2100Meeting));

        // same name, same module, same time, different attributes -> returns true
        editedCS2100Meeting = new MeetingBuilder(CS2100_MEETING).withTime(VALID_TIME)
                .withMembers(CS2101_MEETING.getParticipants()).build();
        assertTrue(CS2100_MEETING.isSameMeeting(editedCS2100Meeting));

        // same name, same module, same date, same time, different attributes -> returns true
        editedCS2100Meeting = new MeetingBuilder(CS2100_MEETING)
                .withMembers(CS2101_MEETING.getParticipants()).build();
        assertTrue(CS2100_MEETING.isSameMeeting(editedCS2100Meeting));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Meeting cs2100MeetingCopy = new MeetingBuilder(CS2100_MEETING).build();
        assertTrue(CS2100_MEETING.equals(cs2100MeetingCopy));

        // same object -> returns true
        assertTrue(CS2100_MEETING.equals(CS2100_MEETING));

        // null -> returns false
        assertFalse(CS2100_MEETING.equals(null));

        // different type -> returns false
        assertFalse(CS2100_MEETING.equals(5));

        // different meeting -> returns false
        assertFalse(CS2100_MEETING.equals(CS2101_MEETING));

        // different name -> returns false
        Meeting editedCS2100Meeting = new MeetingBuilder(CS2100_MEETING).withName(VALID_MEETING_NAME).build();
        assertFalse(CS2100_MEETING.equals(editedCS2100Meeting));

        // different module -> returns false
        editedCS2100Meeting = new MeetingBuilder(CS2100_MEETING).withModule(CS2101).build();
        assertFalse(CS2100_MEETING.equals(editedCS2100Meeting));

        // different date -> returns false
        editedCS2100Meeting = new MeetingBuilder(CS2100_MEETING).withDate(VALID_DATE).build();
        assertFalse(CS2100_MEETING.equals(editedCS2100Meeting));

        // different time -> returns false
        editedCS2100Meeting = new MeetingBuilder(CS2100_MEETING).withTime(VALID_TIME).build();
        assertFalse(CS2100_MEETING.equals(editedCS2100Meeting));

        // different participants -> returns false
        editedCS2100Meeting = new MeetingBuilder(CS2100_MEETING).withMembers(CS2101.getClassmates()).build();
        assertFalse(CS2100_MEETING.equals(editedCS2100Meeting));
    }
}
