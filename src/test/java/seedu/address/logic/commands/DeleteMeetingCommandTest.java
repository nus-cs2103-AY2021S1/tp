package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMeetings.CS2100_MEETING;
import static seedu.address.testutil.TypicalMeetings.CS2101_MEETING;
import static seedu.address.testutil.TypicalMeetings.CS2102_MEETING;
import static seedu.address.testutil.TypicalMeetings.getTypicalMeetingBook;
import static seedu.address.testutil.TypicalModules.CS2105;
import static seedu.address.testutil.TypicalModules.getTypicalModuleBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.MeetingBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ModuleBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;
import seedu.address.testutil.MeetingBuilder;

public class DeleteMeetingCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalMeetingBook(), getTypicalModuleBook(),
            new UserPrefs());

    @Test
    public void execute_validMeetingUnfilteredList_success() {
        Meeting deletedMeeting = new MeetingBuilder(CS2100_MEETING).build();
        DeleteMeetingCommand deleteMeetingCommand = new DeleteMeetingCommand(
                CS2100_MEETING.getModule().getModuleName(),
                CS2100_MEETING.getMeetingName());

        String expectedMessage = String.format(DeleteMeetingCommand.MESSAGE_DELETE_MEETING_SUCCESS, deletedMeeting);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new MeetingBook(model.getMeetingBook()),
                new ModuleBook(model.getModuleBook()),
                new UserPrefs());
        expectedModel.deleteMeeting(model.getFilteredMeetingList().get(0));

        assertCommandSuccess(deleteMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_meetingWithNonExistentModule_throwsCommandException() {
        Meeting meetingWithNonExistentModule = new MeetingBuilder(CS2100_MEETING)
                .withModule(CS2105)
                .build();
        DeleteMeetingCommand deleteMeetingCommand = new DeleteMeetingCommand(meetingWithNonExistentModule);
        String expectedMessage = String.format(
                Messages.MESSAGE_INVALID_MODULE_DISPLAYED,
                meetingWithNonExistentModule.getModule().getModuleName());

        assertThrows(CommandException.class, expectedMessage, () ->
                deleteMeetingCommand.execute(model));
    }

    @Test
    public void execute_nonExistentMeeting_throwsCommandException() {
        Meeting meetingWithNonExistentModule = new MeetingBuilder(CS2100_MEETING)
                .withName(CS2102_MEETING.getMeetingName().toString())
                .build();
        DeleteMeetingCommand deleteMeetingCommand = new DeleteMeetingCommand(meetingWithNonExistentModule);
        String expectedMessage = Messages.MESSAGE_INVALID_MEETING_DISPLAYED;

        assertThrows(CommandException.class, expectedMessage, () ->
                deleteMeetingCommand.execute(model));
    }

    @Test
    public void execute_deleteSelectedMeeting_updatesSelectedMeeting() throws CommandException {
        Meeting selectedMeeting = model.getSelectedMeeting();
        new DeleteMeetingCommand(selectedMeeting.getModule().getModuleName(), selectedMeeting.getMeetingName())
                .execute(model);

        assertNull(model.getSelectedMeeting());
    }

    @Test
    public void equals() {
        DeleteMeetingCommand meetingCommand1 = new DeleteMeetingCommand(CS2100_MEETING);
        DeleteMeetingCommand meetingCommand2 = new DeleteMeetingCommand(CS2101_MEETING);

        // same object -> returns true
        assertTrue(meetingCommand2.equals(meetingCommand2));

        // same values -> returns true
        DeleteMeetingCommand meetingCommand1Copy = new DeleteMeetingCommand(CS2100_MEETING);
        assertTrue(meetingCommand1.equals(meetingCommand1Copy));

        // different types -> returns false
        assertFalse(meetingCommand2.equals(1));

        // null -> returns false
        assertFalse(meetingCommand2.equals(null));

        // different meeting -> returns false
        assertFalse(meetingCommand2.equals(meetingCommand1));
    }
}
