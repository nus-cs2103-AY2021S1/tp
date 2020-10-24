package seedu.address.logic.commands.bids;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showBidAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_BID;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_BID;
import static seedu.address.testutil.TypicalMeeting.getTypicalMeetingAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.bidder.TypicalBidder.getTypicalBidderAddressBook;
import static seedu.address.testutil.bids.TypicalBid.getTypicalBidBook;
import static seedu.address.testutil.seller.TypicalSeller.getTypicalSellerAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.bidcommands.DeleteBidCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.bid.Bid;
import seedu.address.model.propertybook.PropertyBook;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteBidCommand}.
 */
public class DeleteBidCommandTest {


    private Model model = new ModelManager(getTypicalAddressBook(),
            new UserPrefs(),
            getTypicalBidBook(),
            new PropertyBook(),
            getTypicalBidderAddressBook(),
            getTypicalSellerAddressBook(),
            getTypicalMeetingAddressBook());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Bid bidToDelete = model.getFilteredBidList().get(INDEX_FIRST_BID.getZeroBased());
        DeleteBidCommand deleteBidCommand = new DeleteBidCommand(INDEX_FIRST_BID);

        String expectedMessage = String.format(DeleteBidCommand.MESSAGE_DELETE_BID_SUCCESS, bidToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getBidBook(),
                new PropertyBook(), model.getBidderAddressBook(),
                model.getSellerAddressBook(), model.getMeetingBook());
        expectedModel.deleteBid(bidToDelete);
        assertCommandSuccess(deleteBidCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBidList().size() + 1);
        DeleteBidCommand deleteBidCommand = new DeleteBidCommand(outOfBoundIndex);

        assertCommandFailure(deleteBidCommand, model, Messages.MESSAGE_INVALID_BID_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showBidAtIndex(model, INDEX_FIRST_BID);

        Bid bidToDelete = model.getFilteredBidList().get(INDEX_FIRST_BID.getZeroBased());
        DeleteBidCommand deleteBidCommand = new DeleteBidCommand(INDEX_FIRST_BID);

        String expectedMessage = String.format(DeleteBidCommand.MESSAGE_DELETE_BID_SUCCESS, bidToDelete);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getBidBook(),
                new PropertyBook(), model.getBidderAddressBook(),
                model.getSellerAddressBook(), model.getMeetingBook());
        expectedModel.deleteBid(bidToDelete);
        showNoBid(expectedModel);

        assertCommandSuccess(deleteBidCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showBidAtIndex(model, INDEX_FIRST_BID);

        Index outOfBoundIndex = INDEX_SECOND_BID;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getBidBook().getBidList().size());

        DeleteBidCommand deleteBidCommand = new DeleteBidCommand(outOfBoundIndex);

        assertCommandFailure(deleteBidCommand, model, Messages.MESSAGE_INVALID_BID_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteBidCommand deleteFirstCommand = new DeleteBidCommand(INDEX_FIRST_BID);
        DeleteBidCommand deleteSecondCommand = new DeleteBidCommand(INDEX_SECOND_BID);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteBidCommand deleteFirstCommandCopy = new DeleteBidCommand(INDEX_FIRST_BID);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different bid -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoBid(Model model) {
        model.updateFilteredBidList(p -> false);

        assertTrue(model.getFilteredBidList().isEmpty());
    }
}
