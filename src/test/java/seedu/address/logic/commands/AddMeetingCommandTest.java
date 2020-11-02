package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMeetings.CS2100_MEETING;
import static seedu.address.testutil.TypicalMeetings.CS2101_MEETING;
import static seedu.address.testutil.TypicalMeetings.CS2104_MEETING;
import static seedu.address.testutil.TypicalMeetings.getTypicalMeetingBook;
import static seedu.address.testutil.TypicalModules.CS2104;
import static seedu.address.testutil.TypicalModules.getTypicalModuleBook;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;
import seedu.address.testutil.MeetingBuilder;

public class AddMeetingCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalMeetingBook(), getTypicalModuleBook(),
            new UserPrefs());

    @Test
    public void constructor_nullParams_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AddMeetingCommand(null, null, null, null, null,
                        null, null));
    }

    @Test
    public void execute_meetingAcceptedByModel_addSuccessful() throws Exception {
        Meeting validMeeting = new MeetingBuilder(CS2104_MEETING).build();

        CommandResult commandResult = new AddMeetingCommand(validMeeting).execute(model);

        assertEquals(String.format(AddMeetingCommand.MESSAGE_SUCCESS, validMeeting), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_sameNameDifferentModule_addSuccessful() throws Exception {
        Meeting meetingSameNameDifferentModule = new MeetingBuilder(CS2104_MEETING)
                .withModule(CS2100_MEETING.getModule())
                .withMembers(CS2100_MEETING.getParticipants())
                .build();

        CommandResult commandResult = new AddMeetingCommand(meetingSameNameDifferentModule).execute(model);

        assertEquals(String.format(AddMeetingCommand.MESSAGE_SUCCESS, meetingSameNameDifferentModule), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_sameModuleDifferentName_addSuccessful() throws Exception {
        Meeting meetingSameModuleDifferentName = new MeetingBuilder(CS2104_MEETING)
                .withName(CS2100_MEETING.getMeetingName().toString())
                .build();

        CommandResult commandResult = new AddMeetingCommand(meetingSameModuleDifferentName).execute(model);

        assertEquals(String.format(AddMeetingCommand.MESSAGE_SUCCESS, meetingSameModuleDifferentName), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_sameTimeDifferentDate_addSuccessful() throws Exception {
        Meeting meetingSameDateDifferentTime = new MeetingBuilder(CS2104_MEETING)
                .withTime("10:00")
                .build();

        CommandResult commandResult = new AddMeetingCommand(meetingSameDateDifferentTime).execute(model);

        assertEquals(String.format(AddMeetingCommand.MESSAGE_SUCCESS, meetingSameDateDifferentTime), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_sameDateDifferentTime_addSuccessful() throws Exception {
        Meeting meetingSameDateDifferentTime = new MeetingBuilder(CS2104_MEETING)
                .withDate("2020-01-02")
                .build();

        CommandResult commandResult = new AddMeetingCommand(meetingSameDateDifferentTime).execute(model);

        assertEquals(String.format(AddMeetingCommand.MESSAGE_SUCCESS, meetingSameDateDifferentTime), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_duplicateMeeting_throwsCommandException() {
        Meeting duplicateMeeting = new MeetingBuilder(CS2100_MEETING).build();
        AddMeetingCommand addMeetingCommand = new AddMeetingCommand(duplicateMeeting);
        String expectedMessage = String.format(
                AddMeetingCommand.MESSAGE_DUPLICATE_MEETING,
                duplicateMeeting.getModule().getModuleName(),
                duplicateMeeting.getMeetingName());

        assertThrows(CommandException.class, expectedMessage, () ->
                addMeetingCommand.execute(model));
    }

    @Test
    public void execute_duplicateMeetingDateAndTime_throwsCommandException() {
        Meeting meetingWithDuplicateDateTime = new MeetingBuilder(CS2104_MEETING)
                .withDate("2020-01-02")
                .withTime("10:00")
                .build();
        AddMeetingCommand addMeetingCommand = new AddMeetingCommand(meetingWithDuplicateDateTime);
        String expectedMessage = String.format(
                AddMeetingCommand.MESSAGE_CONFLICTING_MEETING_TIMES,
                CS2100_MEETING.getModule().getModuleName(),
                CS2100_MEETING.getMeetingName());

        assertThrows(CommandException.class, expectedMessage, () ->
                addMeetingCommand.execute(model));
    }

    @Test
    public void execute_personNotInModule_throwsCommandException() {
        Meeting meetingWithPersonNotInModule = new MeetingBuilder(CS2104_MEETING)
                .withMembers(CS2100_MEETING.getParticipants())
                .build();
        AddMeetingCommand addMeetingCommand = new AddMeetingCommand(meetingWithPersonNotInModule);
        String expectedMessage = String.format(
                AddMeetingCommand.MESSAGE_NONEXISTENT_PERSON, BENSON.getName().toString(), CS2104.getModuleName());

        assertThrows(CommandException.class, expectedMessage, () ->
                addMeetingCommand.execute(model));
    }

    @Test
    public void equals() {
        AddMeetingCommand meetingCommand1 = new AddMeetingCommand(CS2100_MEETING);
        AddMeetingCommand meetingCommand2 = new AddMeetingCommand(CS2101_MEETING);

        // same object -> returns true
        assertTrue(meetingCommand2.equals(meetingCommand2));

        // same values -> returns true
        AddMeetingCommand meetingCommand1Copy = new AddMeetingCommand(CS2100_MEETING);
        assertTrue(meetingCommand1.equals(meetingCommand1Copy));

        // different types -> returns false
        assertFalse(meetingCommand2.equals(1));

        // null -> returns false
        assertFalse(meetingCommand2.equals(null));

        // different meeting -> returns false
        assertFalse(meetingCommand2.equals(meetingCommand1));
    }
}
