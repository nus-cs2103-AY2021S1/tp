package seedu.address.logic.commands.meetingcommands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.MeetingBook;
import seedu.address.model.ModelStub;
import seedu.address.model.ReadOnlyMeetingBook;
import seedu.address.model.meeting.Meeting;
import seedu.address.testutil.MeetingBuilder;



class AddMeetingCommandTest {

    @Test
    public void constructor_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddMeetingCommand(null));
    }

    @Test
    public void execute_meetingAcceptedByModel_addSuccessful() throws Exception {
        AddMeetingCommandTest.ModelStubAcceptingMeetingAdded modelStubAdmin =
                new AddMeetingCommandTest.ModelStubAcceptingMeetingAdded();
        Meeting validAdminMeeting = new MeetingBuilder().buildAdmin();

        CommandResult commandAdminResult = new AddMeetingCommand(validAdminMeeting).execute(modelStubAdmin);

        assertEquals(String.format(AddMeetingCommand.MESSAGE_SUCCESS, validAdminMeeting),
                commandAdminResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validAdminMeeting), modelStubAdmin.meetingsAdded);

        AddMeetingCommandTest.ModelStubAcceptingMeetingAdded modelStubViewing =
                new AddMeetingCommandTest.ModelStubAcceptingMeetingAdded();
        Meeting validViewingMeeting = new MeetingBuilder().buildViewing();

        CommandResult commandViewingResult = new AddMeetingCommand(validViewingMeeting).execute(modelStubViewing);

        assertEquals(String.format(AddMeetingCommand.MESSAGE_SUCCESS, validViewingMeeting),
                commandViewingResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validViewingMeeting), modelStubViewing.meetingsAdded);

        AddMeetingCommandTest.ModelStubAcceptingMeetingAdded modelStubPaperwork =
                new AddMeetingCommandTest.ModelStubAcceptingMeetingAdded();
        Meeting validPaperworkMeeting = new MeetingBuilder().buildPaperwork();

        CommandResult commandPaperworkResult = new AddMeetingCommand(validPaperworkMeeting).execute(modelStubPaperwork);

        assertEquals(String.format(AddMeetingCommand.MESSAGE_SUCCESS, validPaperworkMeeting),
                commandPaperworkResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPaperworkMeeting), modelStubPaperwork.meetingsAdded);
    }

    @Test
    public void execute_duplicateMeeting_throwsCommandException() {
        Meeting validAdmin = new MeetingBuilder().buildAdmin();
        AddMeetingCommand addMeetingAdminCommand = new AddMeetingCommand(validAdmin);
        ModelStub modelAdminStub = new ModelStubWithMeeting(validAdmin);

        assertThrows(CommandException.class,
                Messages.MESSAGE_DUPLICATE_MEETING, () -> addMeetingAdminCommand.execute(modelAdminStub));

        Meeting validViewing = new MeetingBuilder().buildViewing();
        AddMeetingCommand addMeetingViewingCommand = new AddMeetingCommand(validViewing);
        ModelStub modelViewingStub = new ModelStubWithMeeting(validViewing);

        assertThrows(CommandException.class,
                Messages.MESSAGE_DUPLICATE_MEETING, () -> addMeetingViewingCommand.execute(modelViewingStub));

        Meeting validPaperwork = new MeetingBuilder().buildPaperwork();
        AddMeetingCommand addMeetingPaperworkCommand = new AddMeetingCommand(validPaperwork);
        ModelStub modelPaperworkStub = new ModelStubWithMeeting(validPaperwork);

        assertThrows(CommandException.class,
                Messages.MESSAGE_DUPLICATE_MEETING, () -> addMeetingPaperworkCommand.execute(modelPaperworkStub));
    }

    @Test
    public void equals() {
        Meeting meetingAdminOne = new MeetingBuilder().withBidderId("B12").buildAdmin();
        Meeting meetingAdminTwo = new MeetingBuilder().withBidderId("B14").buildAdmin();
        AddMeetingCommand addAdminMeetingOneCommand = new AddMeetingCommand(meetingAdminOne);
        AddMeetingCommand addAdminMeetingTwoCommand = new AddMeetingCommand(meetingAdminTwo);

        // same object -> returns true
        assertTrue(addAdminMeetingOneCommand.equals(addAdminMeetingOneCommand));

        // same values -> returns true
        AddMeetingCommand addMeetingOneCommandCopy = new AddMeetingCommand(meetingAdminOne);
        assertTrue(addAdminMeetingOneCommand.equals(addMeetingOneCommandCopy));

        // different types -> returns false
        assertFalse(addAdminMeetingOneCommand.equals(1));

        // null -> returns false
        assertFalse(addAdminMeetingOneCommand.equals(null));

        // different meeting -> returns false
        assertFalse(addAdminMeetingOneCommand.equals(addAdminMeetingTwoCommand));


        Meeting meetingViewingOne = new MeetingBuilder().withBidderId("B12").buildViewing();
        Meeting meetingViewingTwo = new MeetingBuilder().withBidderId("B14").buildViewing();
        AddMeetingCommand addViewingMeetingOneCommand = new AddMeetingCommand(meetingViewingOne);
        AddMeetingCommand addViewingMeetingTwoCommand = new AddMeetingCommand(meetingViewingTwo);

        // same object -> returns true
        assertTrue(addViewingMeetingOneCommand.equals(addViewingMeetingOneCommand));

        // same values -> returns true
        AddMeetingCommand addViewingMeetingOneCommandCopy = new AddMeetingCommand(meetingViewingOne);
        assertTrue(addViewingMeetingOneCommand.equals(addViewingMeetingOneCommandCopy));

        // different types -> returns false
        assertFalse(addViewingMeetingOneCommand.equals(1));

        // null -> returns false
        assertFalse(addViewingMeetingOneCommand.equals(null));

        // different meeting -> returns false
        assertFalse(addViewingMeetingOneCommand.equals(addViewingMeetingTwoCommand));

        Meeting meetingPaperworkOne = new MeetingBuilder().withBidderId("B12").buildPaperwork();
        Meeting meetingPaperworkTwo = new MeetingBuilder().withBidderId("B14").buildPaperwork();
        AddMeetingCommand addPaperworkMeetingOneCommand = new AddMeetingCommand(meetingPaperworkOne);
        AddMeetingCommand addPaperworkMeetingTwoCommand = new AddMeetingCommand(meetingPaperworkTwo);

        // same object -> returns true
        assertTrue(addPaperworkMeetingOneCommand.equals(addPaperworkMeetingOneCommand));

        // same values -> returns true
        AddMeetingCommand addPaperworkMeetingOneCommandCopy = new AddMeetingCommand(meetingPaperworkOne);
        assertTrue(addPaperworkMeetingOneCommand.equals(addPaperworkMeetingOneCommandCopy));

        // different types -> returns false
        assertFalse(addPaperworkMeetingOneCommand.equals(1));

        // null -> returns false
        assertFalse(addPaperworkMeetingOneCommand.equals(null));

        // different meeting -> returns false
        assertFalse(addPaperworkMeetingOneCommand.equals(addPaperworkMeetingTwoCommand));
    }


    /**
     * A Model stub that contains a single admin meeting.
     */
    private class ModelStubWithMeeting extends ModelStub {

        private final Meeting meeting;

        ModelStubWithMeeting(Meeting meeting) {
            requireNonNull(meeting);
            this.meeting = meeting;
        }

        @Override
        public boolean hasMeeting(Meeting meeting) {
            requireNonNull(meeting);
            return this.meeting.equals(meeting);
        }
    }

    /**
     * A Model stub that always accept the meeting being added.
     */
    private class ModelStubAcceptingMeetingAdded extends ModelStub {

        final ArrayList<Meeting> meetingsAdded = new ArrayList<>();

        @Override
        public boolean hasMeeting(Meeting meeting) {
            requireNonNull(meeting);
            return meetingsAdded.stream().anyMatch(meeting::equals);
        }

        @Override
        public void addMeeting(Meeting meeting) {
            requireNonNull(meeting);
            meetingsAdded.add(meeting);
        }

        @Override
        public ReadOnlyMeetingBook getMeetingBook() {
            return new MeetingBook();
        }

    }

}
