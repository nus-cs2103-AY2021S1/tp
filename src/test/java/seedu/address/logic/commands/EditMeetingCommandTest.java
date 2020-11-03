package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditMeetingCommand.EditMeetingDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.MeetingBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ModuleBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditMeetingDescriptorBuilder;
import seedu.address.testutil.EditMeetingDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.MeetingBuilder;
import seedu.address.testutil.MeetingBuilder;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEETING_NAME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalMeetings.CS2100_MEETING;
import static seedu.address.testutil.TypicalMeetings.CS2102_MEETING;
import static seedu.address.testutil.TypicalMeetings.CS2102_MEETING_DUPLICATE_NAME;
import static seedu.address.testutil.TypicalMeetings.CS2104_MEETING;
import static seedu.address.testutil.TypicalMeetings.getTypicalMeetingBook;
import static seedu.address.testutil.TypicalMeetings.getTypicalMeetingBookWithDuplicateName;
import static seedu.address.testutil.TypicalModules.CS2100;
import static seedu.address.testutil.TypicalModules.CS2102;
import static seedu.address.testutil.TypicalModules.CS2104;
import static seedu.address.testutil.TypicalModules.getTypicalModuleBook;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

public class EditMeetingCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalMeetingBook(), getTypicalModuleBook(),
            new UserPrefs());

    private Model modelWithDuplicatedMeetingName = new ModelManager(getTypicalAddressBook(),
            getTypicalMeetingBookWithDuplicateName(), getTypicalModuleBook(), new UserPrefs());

    @Test
    public void constructor_nullParams_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditMeetingCommand(null, null, null));
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Meeting editedMeeting = new MeetingBuilder(CS2100_MEETING)
                .withName(VALID_MEETING_NAME)
                .withDate(VALID_DATE)
                .withTime(VALID_TIME)
                .withMembers(CS2100.getClassmates())
                .build();
        EditMeetingCommand.EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder(editedMeeting).build();
        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(
                CS2100_MEETING.getModule().getModuleName(),
                CS2100_MEETING.getMeetingName(),
                descriptor);

        String expectedMessage = String.format(EditMeetingCommand.MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new MeetingBook(model.getMeetingBook()),
                new ModuleBook(model.getModuleBook()),
                new UserPrefs());
        expectedModel.setMeeting(model.getFilteredMeetingList().get(1), editedMeeting);

        assertCommandSuccess(editMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastMeeting = Index.fromOneBased(model.getFilteredMeetingList().size());
        Meeting lastMeeting = model.getFilteredMeetingList().get(indexLastMeeting.getZeroBased());
        MeetingBuilder meetingInList = new MeetingBuilder(lastMeeting);
        Meeting editedMeeting = meetingInList.withName(VALID_MEETING_NAME).withDate(VALID_DATE).build();

        EditMeetingCommand.EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder()
                .withMeetingName(VALID_MEETING_NAME)
                .withDate(VALID_DATE).build();
        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(
                CS2102_MEETING.getModule().getModuleName(),
                CS2102_MEETING.getMeetingName(),
                descriptor);

        String expectedMessage = String.format(EditMeetingCommand.MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new MeetingBook(model.getMeetingBook()),
                new ModuleBook(model.getModuleBook()),
                new UserPrefs());
        expectedModel.setMeeting(lastMeeting, editedMeeting);

        assertCommandSuccess(editMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(
                CS2100_MEETING.getModule().getModuleName(),
                CS2100_MEETING.getMeetingName(),
                new EditMeetingCommand.EditMeetingDescriptor());
        Meeting editedMeeting = new MeetingBuilder(CS2100_MEETING).build();

        String expectedMessage = String.format(EditMeetingCommand.MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new MeetingBook(model.getMeetingBook()),
                new ModuleBook(model.getModuleBook()),
                new UserPrefs());

        assertCommandSuccess(editMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateMeetingUnfilteredList_failure() {
        Meeting editedMeeting = CS2102_MEETING_DUPLICATE_NAME;
        EditMeetingCommand.EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder(editedMeeting).build();
        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(
                CS2102_MEETING.getModule().getModuleName(),
                CS2102_MEETING.getMeetingName(),
                descriptor);
        String expectedMessage = String.format(EditMeetingCommand.MESSAGE_DUPLICATE_MEETING,
                CS2102_MEETING.getModule().getModuleName(),
                CS2102_MEETING.getMeetingName());

        assertCommandFailure(editMeetingCommand, modelWithDuplicatedMeetingName, expectedMessage);
    }

    @Test
    public void execute_duplicateMeetingDateAndTime_throwsCommandException() {
        Meeting editedMeeting = new MeetingBuilder(CS2102_MEETING)
                .withDate("2020-02-02")
                .withTime("10:00")
                .build();
        EditMeetingCommand.EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder(editedMeeting).build();
        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(
                CS2102_MEETING.getModule().getModuleName(),
                CS2102_MEETING.getMeetingName(),
                descriptor);
        String expectedMessage = String.format(EditMeetingCommand.MESSAGE_CONFLICTING_MEETING_TIMES,
                CS2100_MEETING.getModule().getModuleName(),
                CS2100_MEETING.getMeetingName());

        assertCommandFailure(editMeetingCommand, model, expectedMessage);
    }

    @Test
    public void execute_personNotInModule_throwsCommandException() {
        EditMeetingCommand.EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder(CS2102_MEETING)
                .withMembers(
                        CS2100_MEETING.getParticipants()
                                .stream()
                                .map(person -> person.getName())
                                .map(name -> name.fullName)
                                .toArray(String[]::new))
                .build();
        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(
                CS2102_MEETING.getModule().getModuleName(),
                CS2102_MEETING.getMeetingName(),
                descriptor);
        String expectedMessage = String.format(
                EditMeetingCommand.MESSAGE_NONEXISTENT_PERSON, BENSON.getName().toString(), CS2102.getModuleName());

        assertCommandFailure(editMeetingCommand, model, expectedMessage);
    }
}
