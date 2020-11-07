package seedu.address.logic.commands.bidder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.bidder.BidderCommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.bidder.BidderCommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.bidder.BidderCommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.bidder.BidderCommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.bidder.BidderCommandTestUtil.assertBidderCommandFailure;
import static seedu.address.logic.commands.bidder.BidderCommandTestUtil.assertBidderCommandSuccess;
import static seedu.address.logic.commands.bidder.BidderCommandTestUtil.showBidderAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.bidder.TypicalBidder.getTypicalBidderAddressBook;
import static seedu.address.testutil.seller.TypicalSeller.getTypicalSellerAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.biddercommands.EditBidderCommand;
import seedu.address.logic.commands.biddercommands.EditBidderCommand.EditBidderDescriptor;
import seedu.address.logic.commands.biddercommands.ListBidderCommand;
import seedu.address.model.MeetingBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.bidbook.BidBook;
import seedu.address.model.person.bidder.Bidder;
import seedu.address.model.propertybook.PropertyBook;
import seedu.address.testutil.bidder.BidderBuilder;
import seedu.address.testutil.bidder.EditBidderDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model,
 * UndoCommand and RedoCommand) and unit tests for EditBidderCommand.
 */
public class EditBidderCommandTest {

    private Model model = new ModelManager(new UserPrefs(), new BidBook(),
            new PropertyBook(), getTypicalBidderAddressBook(),
            getTypicalSellerAddressBook(), new MeetingBook());


    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Bidder editedBidder = model.getBidderAddressBook().getBidderList().get(0);
        EditBidderDescriptor bidderDescriptor = new EditBidderDescriptorBuilder(editedBidder).build();
        EditBidderCommand editBidderCommand = new EditBidderCommand(INDEX_FIRST_PERSON, bidderDescriptor);

        String expectedMessage = String.format(EditBidderCommand.MESSAGE_EDIT_BIDDER_SUCCESS, editedBidder);
        ModelManager expectedModel = new ModelManager(new UserPrefs(), model.getBidBook(),
                new PropertyBook(), model.getBidderAddressBook(), model.getSellerAddressBook(), new MeetingBook());

        expectedModel.setBidder(model.getFilteredBidderList().get(0), editedBidder);

        assertBidderCommandSuccess(editBidderCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastBidder = Index.fromOneBased(model.getFilteredBidderList().size());
        Bidder lastBidder = model.getFilteredBidderList().get(indexLastBidder.getZeroBased());

        BidderBuilder bidderInList = new BidderBuilder(lastBidder);

        Bidder editedBidder = bidderInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB).build();

        EditBidderDescriptor bidderDescriptor = new EditBidderDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).build();

        EditBidderCommand editBidderCommand = new EditBidderCommand(indexLastBidder, bidderDescriptor);

        String expectedMessage = String.format(EditBidderCommand.MESSAGE_EDIT_BIDDER_SUCCESS, editedBidder);

        ModelManager expectedModel = new ModelManager(new UserPrefs(), model.getBidBook(),
                new PropertyBook(), model.getBidderAddressBook(), model.getSellerAddressBook(), new MeetingBook());

        expectedModel.setBidder(lastBidder, editedBidder);

        assertBidderCommandSuccess(editBidderCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditBidderCommand editBidderCommand = new EditBidderCommand(INDEX_FIRST_PERSON, new EditBidderDescriptor());
        Bidder editedBidder = model.getFilteredBidderList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditBidderCommand.MESSAGE_EDIT_BIDDER_SUCCESS, editedBidder);

        ModelManager expectedModel = new ModelManager(new UserPrefs(), model.getBidBook(),
                new PropertyBook(), model.getBidderAddressBook(), model.getSellerAddressBook(), new MeetingBook());

        assertBidderCommandSuccess(editBidderCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showBidderAtIndex(model, INDEX_FIRST_PERSON);

        Bidder bidderInFilteredList = model.getFilteredBidderList().get(INDEX_FIRST_PERSON.getZeroBased());
        Bidder editedBidder = new BidderBuilder(bidderInFilteredList)
                .withName(VALID_NAME_BOB).build();
        EditBidderCommand editBidderCommand = new EditBidderCommand(INDEX_FIRST_PERSON,
                new EditBidderDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditBidderCommand.MESSAGE_EDIT_BIDDER_SUCCESS, editedBidder);

        ModelManager expectedModel = new ModelManager(new UserPrefs(), model.getBidBook(),
                new PropertyBook(), model.getBidderAddressBook(), model.getSellerAddressBook(), new MeetingBook());

        expectedModel.setBidder(model.getFilteredBidderList().get(0), editedBidder);

        assertBidderCommandSuccess(editBidderCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateBidderUnfilteredList_failure() {
        Bidder firstBidder = model.getFilteredBidderList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditBidderDescriptor bidderDescriptor = new EditBidderDescriptorBuilder(firstBidder).build();
        EditBidderCommand editBidderCommand = new EditBidderCommand(INDEX_SECOND_PERSON, bidderDescriptor);
        assertBidderCommandFailure(editBidderCommand, model, EditBidderCommand.MESSAGE_DUPLICATE_BIDDER);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showBidderAtIndex(model, INDEX_FIRST_PERSON);

        // edit bidder in filtered list into a duplicate in address book
        Bidder bidderInList = model.getBidderAddressBook().getBidderList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditBidderCommand editBidderCommand = new EditBidderCommand(INDEX_FIRST_PERSON,
                new EditBidderDescriptorBuilder(bidderInList).build());
        assertBidderCommandFailure(editBidderCommand, model, EditBidderCommand.MESSAGE_DUPLICATE_BIDDER);
    }


    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBidderList().size() + 1);
        EditBidderDescriptor bidderDescriptor = new EditBidderDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditBidderCommand editBidderCommand = new EditBidderCommand(outOfBoundIndex, bidderDescriptor);

        assertBidderCommandFailure(editBidderCommand, model, Messages.MESSAGE_INVALID_BIDDER_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showBidderAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getBidderAddressBook().getBidderList().size());

        EditBidderCommand editBidderCommand = new EditBidderCommand(outOfBoundIndex,
                new EditBidderDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertBidderCommandFailure(editBidderCommand, model, Messages.MESSAGE_INVALID_BIDDER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditBidderCommand standardBidderCommand = new EditBidderCommand(INDEX_FIRST_PERSON, DESC_AMY);

        // same values -> returns true
        EditBidderDescriptor copyDescriptor = new EditBidderDescriptor(DESC_AMY);
        EditBidderCommand commandWithSameValues = new EditBidderCommand(INDEX_FIRST_PERSON, copyDescriptor);

        System.out.print((standardBidderCommand.equals(commandWithSameValues)));
        assertTrue(standardBidderCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardBidderCommand.equals(standardBidderCommand));

        // null -> returns false
        assertFalse(standardBidderCommand.equals(null));

        // different types -> returns false
        assertFalse(standardBidderCommand.equals(new ListBidderCommand()));

        // different index -> returns false
        assertFalse(standardBidderCommand.equals(new EditBidderCommand(INDEX_SECOND_PERSON, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardBidderCommand.equals(new EditBidderCommand(INDEX_FIRST_PERSON, DESC_BOB)));
    }
}
