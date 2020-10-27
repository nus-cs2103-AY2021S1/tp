package seedu.address.logic.commands.bids;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showBidAtIndex;
import static seedu.address.logic.commands.bids.BidCommandTestUtil.VALID_BID_A;
import static seedu.address.logic.commands.bids.BidCommandTestUtil.VALID_BID_B;
import static seedu.address.logic.commands.bids.BidCommandTestUtil.VALID_PROPERTY_ID_BID_A;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_BID;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_BID;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.bidder.TypicalBidder.getTypicalBidderAddressBook;
import static seedu.address.testutil.bids.TypicalBid.getTypicalBidBook;
import static seedu.address.testutil.property.TypicalProperties.getTypicalPropertyBook;
import static seedu.address.testutil.seller.TypicalSeller.getTypicalSellerAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.bidcommands.EditBidCommand;
import seedu.address.logic.commands.bidcommands.EditBidCommand.EditBidDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.MeetingBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.bid.Bid;
import seedu.address.testutil.bids.BidBuilder;
import seedu.address.testutil.bids.EditBidDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for EditCommand.
 */
public class EditBidCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalBidBook(),
            getTypicalPropertyBook(), getTypicalBidderAddressBook(),
            getTypicalSellerAddressBook(), new MeetingBook());


    @Test
    public void execute_allFieldsSpecifiedUnfilteredBidList_success() throws CommandException {
        Bid editedBid = new BidBuilder().build();
        EditBidDescriptor descriptor = new EditBidDescriptorBuilder(editedBid).build();
        EditBidCommand editBidCommand = new EditBidCommand(INDEX_FIRST_BID, descriptor);

        String expectedMessage = String.format(EditBidCommand.MESSAGE_EDIT_BID_SUCCESS,
                model.getFilteredBidList().get(0), editedBid);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getBidBook(),
                model.getPropertyBook(), model.getBidderAddressBook(), model.getSellerAddressBook(), new MeetingBook());

        expectedModel.setBid(model.getFilteredBidList().get(0), editedBid);

        assertCommandSuccess(editBidCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_failure() {
        EditBidCommand editBidCommand = new EditBidCommand(INDEX_FIRST_BID, new EditBidDescriptor());
        Bid editedBid = model.getFilteredBidList().get(INDEX_FIRST_BID.getZeroBased());

        String expectedMessage = String.format(EditBidCommand.MESSAGE_NOT_EDITED,
                model.getFilteredBidList().get(0), editedBid);
        assertCommandFailure(editBidCommand, model, expectedMessage);
    }

    @Test
    public void execute_duplicateBidUnfilteredList_failure() {
        Bid firstBid = model.getFilteredBidList().get(INDEX_FIRST_BID.getZeroBased());
        EditBidDescriptor descriptor = new EditBidDescriptorBuilder(firstBid).build();
        EditBidCommand editBidCommand = new EditBidCommand(INDEX_SECOND_BID, descriptor);

        assertCommandFailure(editBidCommand, model, EditBidCommand.MESSAGE_DUPLICATE_BID);
    }

    @Test
    public void execute_duplicateBidFilteredList_failure() {
        showBidAtIndex(model, INDEX_FIRST_BID);

        // edit Bid in filtered list into a duplicate in address book
        Bid bidInList = model.getBidBook().getBidList().get(INDEX_SECOND_BID.getZeroBased());
        EditBidCommand editBidCommand = new EditBidCommand(INDEX_FIRST_BID,
                new EditBidDescriptorBuilder(bidInList).build());

        assertCommandFailure(editBidCommand, model, EditBidCommand.MESSAGE_DUPLICATE_BID);
    }

    @Test
    public void execute_invalidBidIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBidList().size() + 1);
        EditBidDescriptor descriptor = new EditBidDescriptorBuilder().withPropertyId(VALID_PROPERTY_ID_BID_A).build();
        EditBidCommand editBidCommand = new EditBidCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editBidCommand, model, Messages.MESSAGE_INVALID_BID_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidBidIndexFilteredList_failure() {
        showBidAtIndex(model, INDEX_FIRST_BID);
        Index outOfBoundIndex = INDEX_SECOND_BID;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getBidBook().getBidList().size());

        EditBidCommand editBidCommand = new EditBidCommand(outOfBoundIndex,
                new EditBidDescriptorBuilder().withPropertyId(VALID_PROPERTY_ID_BID_A).build());

        assertCommandFailure(editBidCommand, model, Messages.MESSAGE_INVALID_BID_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditBidCommand standardCommand = new EditBidCommand(INDEX_FIRST_BID, VALID_BID_A);

        // same values -> returns true
        EditBidDescriptor copyDescriptor = new EditBidDescriptor(VALID_BID_A);
        EditBidCommand commandWithSameValues = new EditBidCommand(INDEX_FIRST_BID, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditBidCommand(INDEX_SECOND_BID, VALID_BID_A)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditBidCommand(INDEX_FIRST_BID, VALID_BID_B)));
    }

}
