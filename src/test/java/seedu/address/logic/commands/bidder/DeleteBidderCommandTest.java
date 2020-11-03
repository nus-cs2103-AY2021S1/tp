package seedu.address.logic.commands.bidder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.bidder.BidderCommandTestUtil.showBidderAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalMeeting.getTypicalMeetingAddressBook;
import static seedu.address.testutil.bidder.TypicalBidder.getTypicalBidderAddressBook;
import static seedu.address.testutil.seller.TypicalSeller.getTypicalSellerAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.biddercommands.DeleteBidderCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.bidbook.BidBook;
import seedu.address.model.person.bidder.Bidder;
import seedu.address.model.propertybook.PropertyBook;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteBidderCommandTest {

    private Model model = new ModelManager(
            new UserPrefs(),
            new BidBook(),
            new PropertyBook(),
            getTypicalBidderAddressBook(),
            getTypicalSellerAddressBook(),
            getTypicalMeetingAddressBook());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Bidder bidderToDelete = model.getFilteredBidderList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteBidderCommand deleteBidderCommand = new DeleteBidderCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteBidderCommand.MESSAGE_DELETE_BIDDER_SUCCESS, bidderToDelete);

        ModelManager expectedModel = new ModelManager(new UserPrefs(), model.getBidBook(),
                new PropertyBook(), model.getBidderAddressBook(),
                model.getSellerAddressBook(), model.getMeetingBook());
        expectedModel.deleteBidder(bidderToDelete);
        assertCommandSuccess(deleteBidderCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBidderList().size() + 1);
        DeleteBidderCommand deleteBidderCommand = new DeleteBidderCommand(outOfBoundIndex);
        assertCommandFailure(deleteBidderCommand, model, Messages.MESSAGE_INVALID_BIDDER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showBidderAtIndex(model, INDEX_FIRST_PERSON);

        Bidder bidderToDelete = model.getFilteredBidderList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteBidderCommand deleteBidderCommand = new DeleteBidderCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteBidderCommand.MESSAGE_DELETE_BIDDER_SUCCESS, bidderToDelete);
        ModelManager expectedModel = new ModelManager(new UserPrefs(), model.getBidBook(),
                new PropertyBook(), model.getBidderAddressBook(),
                model.getSellerAddressBook(), model.getMeetingBook());
        expectedModel.deleteBidder(bidderToDelete);
        showNoBidder(expectedModel);

        assertCommandSuccess(deleteBidderCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showBidderAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getBidderAddressBook().getBidderList().size());

        DeleteBidderCommand deleteBidderCommand = new DeleteBidderCommand(outOfBoundIndex);

        assertCommandFailure(deleteBidderCommand, model, Messages.MESSAGE_INVALID_BIDDER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteBidderCommand deleteFirstBidderCommand = new DeleteBidderCommand(INDEX_FIRST_PERSON);
        DeleteBidderCommand deleteSecondBidderCommand = new DeleteBidderCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstBidderCommand.equals(deleteFirstBidderCommand));

        // same values -> returns true
        DeleteBidderCommand deleteFirstBidderCommandCopy = new DeleteBidderCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstBidderCommand.equals(deleteFirstBidderCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstBidderCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstBidderCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstBidderCommand.equals(deleteSecondBidderCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoBidder(Model model) {
        model.updateFilteredBidderList(p -> false);

        assertTrue(model.getFilteredBidderList().isEmpty());
    }

}
