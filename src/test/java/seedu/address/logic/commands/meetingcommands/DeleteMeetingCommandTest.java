package seedu.address.logic.commands.meetingcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showMeetingAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_SIXTH_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_MEETING;
import static seedu.address.testutil.TypicalMeeting.getTypicalMeetingAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.bidder.TypicalBidder.getTypicalBidderAddressBook;
import static seedu.address.testutil.bids.TypicalBid.getTypicalBidBook;
import static seedu.address.testutil.property.TypicalProperties.getTypicalPropertyBook;
import static seedu.address.testutil.seller.TypicalSeller.getTypicalSellerAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.sellercommands.DeleteSellerCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.meeting.Meeting;

class DeleteMeetingCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(),
            new UserPrefs(),
            getTypicalBidBook(),
            getTypicalPropertyBook(),
            getTypicalBidderAddressBook(),
            getTypicalSellerAddressBook(),
            getTypicalMeetingAddressBook());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Meeting adminMeetingToDelete = model.getFilteredMeetingList().get(INDEX_FIRST_MEETING.getZeroBased());
        DeleteMeetingCommand deleteAdminMeetingCommand = new DeleteMeetingCommand(INDEX_FIRST_MEETING);

        String expectedAdminMessage = String.format(DeleteMeetingCommand.MESSAGE_DELETE_MEETING_SUCCESS,
                adminMeetingToDelete);

        ModelManager expectedAdminModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getBidBook(),
                model.getPropertyBook(), model.getBidderAddressBook(),
                model.getSellerAddressBook(), model.getMeetingBook());
        expectedAdminModel.deleteMeeting(adminMeetingToDelete);
        assertCommandSuccess(deleteAdminMeetingCommand, model, expectedAdminMessage, expectedAdminModel);

        Meeting viewingMeetingToDelete = model.getFilteredMeetingList().get(INDEX_THIRD_MEETING.getZeroBased());
        DeleteMeetingCommand deleteViewingMeetingCommand = new DeleteMeetingCommand(INDEX_THIRD_MEETING);

        String expectedViewingMessage = String.format(DeleteMeetingCommand.MESSAGE_DELETE_MEETING_SUCCESS,
                viewingMeetingToDelete);

        ModelManager expectedViewingModel = new ModelManager(model.getAddressBook(), new UserPrefs(),
                model.getBidBook(),
                model.getPropertyBook(), model.getBidderAddressBook(),
                model.getSellerAddressBook(), model.getMeetingBook());
        expectedViewingModel.deleteMeeting(viewingMeetingToDelete);
        assertCommandSuccess(deleteViewingMeetingCommand, model, expectedViewingMessage, expectedViewingModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredMeetingList().size() + 1);
        DeleteMeetingCommand deleteMeetingCommand = new DeleteMeetingCommand(outOfBoundIndex);

        assertCommandFailure(deleteMeetingCommand, model, Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showMeetingAtIndex(model, INDEX_FIRST_MEETING);

        Meeting meetingToDelete = model.getFilteredMeetingList().get(INDEX_FIRST_MEETING.getZeroBased());
        DeleteMeetingCommand deleteMeetingCommand = new DeleteMeetingCommand(INDEX_FIRST_MEETING);

        String expectedMessage = String.format(DeleteMeetingCommand.MESSAGE_DELETE_MEETING_SUCCESS, meetingToDelete);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getBidBook(),
                model.getPropertyBook(), model.getBidderAddressBook(),
                model.getSellerAddressBook(), model.getMeetingBook());
        expectedModel.deleteMeeting(meetingToDelete);
        showNoMeeting(expectedModel);

        assertCommandSuccess(deleteMeetingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showMeetingAtIndex(model, INDEX_FIRST_MEETING);

        Index outOfBoundIndex = INDEX_SECOND_MEETING;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getMeetingBook().getMeetingList().size());

        DeleteMeetingCommand deleteMeetingCommand = new DeleteMeetingCommand(outOfBoundIndex);

        assertCommandFailure(deleteMeetingCommand, model, Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteMeetingCommand deleteFirstMeetingCommand = new DeleteMeetingCommand(INDEX_FIRST_MEETING);
        DeleteSellerCommand deleteSecondMeetingCommand = new DeleteSellerCommand(INDEX_SECOND_MEETING);

        // same object -> returns true
        assertTrue(deleteFirstMeetingCommand.equals(deleteFirstMeetingCommand));

        // same values -> returns true
        DeleteMeetingCommand deleteFirstMeetingCommandCopy = new DeleteMeetingCommand(INDEX_FIRST_MEETING);
        assertTrue(deleteFirstMeetingCommand.equals(deleteFirstMeetingCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstMeetingCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstMeetingCommand.equals(null));

        // different meeting -> returns false
        assertFalse(deleteFirstMeetingCommand.equals(deleteSecondMeetingCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoMeeting(Model model) {
        model.updateFilteredMeetingList(p -> false);

        assertTrue(model.getFilteredMeetingList().isEmpty());
    }

}
