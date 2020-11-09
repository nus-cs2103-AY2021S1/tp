package seedu.address.logic.commands.meetingcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showMeetingAtIndex;
import static seedu.address.logic.commands.MeetingCommandTestUtil.VALID_MEETING_A;
import static seedu.address.logic.commands.MeetingCommandTestUtil.VALID_MEETING_B;
import static seedu.address.logic.commands.MeetingCommandTestUtil.VALID_VENUE_A;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MEETING;
import static seedu.address.testutil.bidder.TypicalBidder.getTypicalBidderAddressBook;
import static seedu.address.testutil.meeting.TypicalMeeting.getTypicalMeetingAddressBook;
import static seedu.address.testutil.property.TypicalProperties.getTypicalPropertyBook;
import static seedu.address.testutil.seller.TypicalSeller.getTypicalSellerAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.bidbook.BidBook;
import seedu.address.model.meeting.Meeting;
import seedu.address.testutil.MeetingBuilder;
import seedu.address.testutil.meeting.EditMeetingDescriptorBuilder;

class EditMeetingCommandTest {
    private Model model = new ModelManager(new UserPrefs(), new BidBook(),
            getTypicalPropertyBook(), getTypicalBidderAddressBook(),
            getTypicalSellerAddressBook(), getTypicalMeetingAddressBook());


    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() throws CommandException {
        Meeting editedAdminMeeting = new MeetingBuilder().buildAdmin();
        EditMeetingCommand.EditMeetingDescriptor adminMeetingDescriptor =
                new EditMeetingDescriptorBuilder(editedAdminMeeting).build();
        EditMeetingCommand editAdminMeetingCommand =
                new EditMeetingCommand(INDEX_FIRST_MEETING, adminMeetingDescriptor);

        String expectedMessage = String.format(EditMeetingCommand.MESSAGE_EDIT_MEETING_SUCCESS, editedAdminMeeting);
        ModelManager expectedModel = new ModelManager(new UserPrefs(), model.getBidBook(),
                model.getPropertyBook(), model.getBidderAddressBook(), model.getSellerAddressBook(),
                model.getMeetingBook());

        expectedModel.setMeeting(model.getFilteredMeetingList().get(0), editedAdminMeeting);

        assertCommandSuccess(editAdminMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() throws CommandException {
        Index indexLastMeeting = Index.fromOneBased(model.getFilteredMeetingList().size());
        Meeting lastMeeting = model.getFilteredMeetingList().get(indexLastMeeting.getZeroBased());

        MeetingBuilder meetingInList = new MeetingBuilder(lastMeeting);
        Meeting editedMeeting = meetingInList.withBidderId("B1").withDate("12-10-2021").withEndTime("14:00")
                .withPropertyId("P1").withStartTime("12:00").withVenue("bedok").buildPaperwork();

        EditMeetingCommand.EditMeetingDescriptor meetingDescriptor = new EditMeetingDescriptorBuilder()
                .withBidderId("B1").withDate("12-10-2021").withEndTime("14:00")
                .withPropertyId("P1").withStartTime("12:00").withVenue("bedok").build();

        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(indexLastMeeting, meetingDescriptor);

        String expectedMessage = String.format(EditMeetingCommand.MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting);

        ModelManager expectedModel = new ModelManager(new UserPrefs(), model.getBidBook(),
                getTypicalPropertyBook(), model.getBidderAddressBook(), model.getSellerAddressBook(),
                model.getMeetingBook());

        expectedModel.setMeeting(lastMeeting, editedMeeting);

        assertCommandSuccess(editMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(INDEX_FIRST_MEETING,
                new EditMeetingCommand.EditMeetingDescriptor());
        Meeting editedMeeting = model.getFilteredMeetingList().get(INDEX_FIRST_MEETING.getZeroBased());

        String expectedMessage = String.format(EditMeetingCommand.MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting);

        ModelManager expectedModel = new ModelManager(new UserPrefs(), model.getBidBook(),
                getTypicalPropertyBook(), model.getBidderAddressBook(), model.getSellerAddressBook(),
                model.getMeetingBook());

        assertCommandSuccess(editMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() throws CommandException {
        showMeetingAtIndex(model, INDEX_FIRST_MEETING);

        Meeting meetingInFilteredList = model.getFilteredMeetingList().get(INDEX_FIRST_MEETING.getZeroBased());
        Meeting editedMeeting = new MeetingBuilder(meetingInFilteredList).withVenue(VALID_VENUE_A).buildAdmin();
        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(INDEX_FIRST_MEETING,
                new EditMeetingDescriptorBuilder().withVenue(VALID_VENUE_A).build());

        String expectedMessage = String.format(EditMeetingCommand.MESSAGE_EDIT_MEETING_SUCCESS, editedMeeting);

        ModelManager expectedModel = new ModelManager(new UserPrefs(), model.getBidBook(),
                getTypicalPropertyBook(), model.getBidderAddressBook(), model.getSellerAddressBook(),
                model.getMeetingBook());

        expectedModel.setMeeting(model.getFilteredMeetingList().get(0), editedMeeting);

        assertCommandSuccess(editMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateMeetingUnfilteredList_failure() {
        Meeting firstMeeting = model.getFilteredMeetingList().get(INDEX_FIRST_MEETING.getZeroBased());
        EditMeetingCommand.EditMeetingDescriptor meetingDescriptor =
                new EditMeetingDescriptorBuilder(firstMeeting).build();
        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(INDEX_SECOND_MEETING, meetingDescriptor);

        assertCommandFailure(editMeetingCommand, model, EditMeetingCommand.MESSAGE_DUPLICATE_MEETING);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showMeetingAtIndex(model, INDEX_FIRST_MEETING);

        // edit seller in filtered list into a duplicate in address book
        Meeting meetingInList = model.getMeetingBook().getMeetingList().get(INDEX_SECOND_MEETING.getZeroBased());
        EditMeetingCommand editMeetingCommand = new EditMeetingCommand(INDEX_FIRST_MEETING,
                new EditMeetingDescriptorBuilder(meetingInList).build());
        assertCommandFailure(editMeetingCommand, model, EditMeetingCommand.MESSAGE_DUPLICATE_MEETING);
    }

    @Test
    public void equals() {
        final EditMeetingCommand standardMeetingCommand = new EditMeetingCommand(INDEX_FIRST_MEETING, VALID_MEETING_A);

        // same values -> returns true
        EditMeetingCommand.EditMeetingDescriptor copyDescriptor =
                new EditMeetingCommand.EditMeetingDescriptor(VALID_MEETING_A);
        EditMeetingCommand commandWithSameValues = new EditMeetingCommand(INDEX_FIRST_MEETING, copyDescriptor);
        assertTrue(standardMeetingCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardMeetingCommand.equals(standardMeetingCommand));

        // null -> returns false
        assertFalse(standardMeetingCommand.equals(null));

        // different types -> returns false
        assertFalse(standardMeetingCommand.equals(new ListMeetingCommand()));

        // different index -> returns false
        assertFalse(standardMeetingCommand.equals(new EditMeetingCommand(INDEX_SECOND_MEETING, VALID_MEETING_A)));

        // different descriptor -> returns false
        assertFalse(standardMeetingCommand.equals(new EditMeetingCommand(INDEX_FIRST_MEETING, VALID_MEETING_B)));
    }

}
