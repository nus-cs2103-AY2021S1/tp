package seedu.address.logic.commands.seller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.seller.SellerCommandTestUtil.showSellerAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.bidder.TypicalBidder.getTypicalBidderAddressBook;
import static seedu.address.testutil.meeting.TypicalMeeting.getTypicalMeetingAddressBook;
import static seedu.address.testutil.seller.TypicalSeller.getTypicalSellerAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.sellercommands.DeleteSellerCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.bidbook.BidBook;
import seedu.address.model.person.seller.Seller;
import seedu.address.model.propertybook.PropertyBook;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteSellerCommandTest {

    private Model model = new ModelManager(
            new UserPrefs(),
            new BidBook(),
            new PropertyBook(),
            getTypicalBidderAddressBook(),
            getTypicalSellerAddressBook(),
            getTypicalMeetingAddressBook());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Seller sellerToDelete = model.getFilteredSellerList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteSellerCommand deleteSellerCommand = new DeleteSellerCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteSellerCommand.MESSAGE_DELETE_SELLER_SUCCESS, sellerToDelete);

        ModelManager expectedModel = new ModelManager(new UserPrefs(), model.getBidBook(),
                new PropertyBook(), model.getBidderAddressBook(),
                model.getSellerAddressBook(), model.getMeetingBook());
        expectedModel.deleteSeller(sellerToDelete);
        assertCommandSuccess(deleteSellerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredSellerList().size() + 1);
        DeleteSellerCommand deleteSellerCommand = new DeleteSellerCommand(outOfBoundIndex);

        assertCommandFailure(deleteSellerCommand, model, Messages.MESSAGE_INVALID_SELLER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showSellerAtIndex(model, INDEX_FIRST_PERSON);

        Seller sellerToDelete = model.getFilteredSellerList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteSellerCommand deleteSellerCommand = new DeleteSellerCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteSellerCommand.MESSAGE_DELETE_SELLER_SUCCESS, sellerToDelete);
        ModelManager expectedModel = new ModelManager(new UserPrefs(), model.getBidBook(),
                new PropertyBook(), model.getBidderAddressBook(),
                model.getSellerAddressBook(), model.getMeetingBook());
        expectedModel.deleteSeller(sellerToDelete);
        showNoSeller(expectedModel);

        assertCommandSuccess(deleteSellerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showSellerAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getSellerAddressBook().getSellerList().size());

        DeleteSellerCommand deleteSellerCommand = new DeleteSellerCommand(outOfBoundIndex);

        assertCommandFailure(deleteSellerCommand, model, Messages.MESSAGE_INVALID_SELLER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteSellerCommand deleteFirstSellerCommand = new DeleteSellerCommand(INDEX_FIRST_PERSON);
        DeleteSellerCommand deleteSecondSellerCommand = new DeleteSellerCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstSellerCommand.equals(deleteFirstSellerCommand));

        // same values -> returns true
        DeleteSellerCommand deleteFirstSellerCommandCopy = new DeleteSellerCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstSellerCommand.equals(deleteFirstSellerCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstSellerCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstSellerCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstSellerCommand.equals(deleteSecondSellerCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoSeller(Model model) {
        model.updateFilteredSellerList(p -> false);

        assertTrue(model.getFilteredSellerList().isEmpty());
    }

}
