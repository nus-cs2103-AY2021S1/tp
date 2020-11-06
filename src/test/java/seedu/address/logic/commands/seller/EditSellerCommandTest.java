package seedu.address.logic.commands.seller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.seller.SellerCommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.seller.SellerCommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.seller.SellerCommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.seller.SellerCommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.seller.SellerCommandTestUtil.assertSellerCommandFailure;
import static seedu.address.logic.commands.seller.SellerCommandTestUtil.assertSellerCommandSuccess;
import static seedu.address.logic.commands.seller.SellerCommandTestUtil.showSellerAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.bidder.TypicalBidder.getTypicalBidderAddressBook;
import static seedu.address.testutil.seller.TypicalSeller.getTypicalSellerAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.sellercommands.EditSellerCommand;
import seedu.address.logic.commands.sellercommands.EditSellerCommand.EditSellerDescriptor;
import seedu.address.logic.commands.sellercommands.ListSellerCommand;
import seedu.address.model.MeetingBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.bidbook.BidBook;
import seedu.address.model.person.seller.Seller;
import seedu.address.model.propertybook.PropertyBook;
import seedu.address.testutil.seller.EditSellerDescriptorBuilder;
import seedu.address.testutil.seller.SellerBuilder;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand)
 * and unit tests for EditSellerCommand.
 */
public class EditSellerCommandTest {

    private Model model = new ModelManager(new UserPrefs(), new BidBook(),
            new PropertyBook(), getTypicalBidderAddressBook(),
            getTypicalSellerAddressBook(), new MeetingBook());


    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Seller editedSeller = model.getSellerAddressBook().getSellerList().get(0);
        EditSellerDescriptor sellerDescriptor = new EditSellerDescriptorBuilder(editedSeller).build();
        EditSellerCommand editSellerCommand = new EditSellerCommand(INDEX_FIRST_PERSON, sellerDescriptor);

        String expectedMessage = String.format(EditSellerCommand.MESSAGE_EDIT_SELLER_SUCCESS, editedSeller);
        ModelManager expectedModel = new ModelManager(new UserPrefs(), model.getBidBook(),
                new PropertyBook(), model.getBidderAddressBook(), model.getSellerAddressBook(), new MeetingBook());

        expectedModel.setSeller(model.getFilteredSellerList().get(0), editedSeller);

        assertSellerCommandSuccess(editSellerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastSeller = Index.fromOneBased(model.getFilteredSellerList().size());
        Seller lastSeller = model.getFilteredSellerList().get(indexLastSeller.getZeroBased());

        SellerBuilder sellerInList = new SellerBuilder(lastSeller);
        Seller editedSeller = sellerInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .build();

        EditSellerDescriptor sellerDescriptor = new EditSellerDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).build();

        EditSellerCommand editSellerCommand = new EditSellerCommand(indexLastSeller, sellerDescriptor);

        String expectedMessage = String.format(EditSellerCommand.MESSAGE_EDIT_SELLER_SUCCESS, editedSeller);

        ModelManager expectedModel = new ModelManager(new UserPrefs(), model.getBidBook(),
                new PropertyBook(), model.getBidderAddressBook(), model.getSellerAddressBook(), new MeetingBook());

        expectedModel.setSeller(lastSeller, editedSeller);

        assertSellerCommandSuccess(editSellerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditSellerCommand editSellerCommand = new EditSellerCommand(INDEX_FIRST_PERSON, new EditSellerDescriptor());
        Seller editedSeller = model.getFilteredSellerList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditSellerCommand.MESSAGE_EDIT_SELLER_SUCCESS, editedSeller);

        ModelManager expectedModel = new ModelManager(new UserPrefs(), model.getBidBook(),
                new PropertyBook(), model.getBidderAddressBook(), model.getSellerAddressBook(), new MeetingBook());

        assertSellerCommandSuccess(editSellerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showSellerAtIndex(model, INDEX_FIRST_PERSON);

        Seller sellerInFilteredList = model.getFilteredSellerList().get(INDEX_FIRST_PERSON.getZeroBased());
        Seller editedSeller = new SellerBuilder(sellerInFilteredList).withName(VALID_NAME_BOB).build();
        EditSellerCommand editSellerCommand = new EditSellerCommand(INDEX_FIRST_PERSON,
                new EditSellerDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditSellerCommand.MESSAGE_EDIT_SELLER_SUCCESS, editedSeller);

        ModelManager expectedModel = new ModelManager(new UserPrefs(), model.getBidBook(),
                new PropertyBook(), model.getBidderAddressBook(), model.getSellerAddressBook(), new MeetingBook());

        expectedModel.setSeller(model.getFilteredSellerList().get(0), editedSeller);

        assertSellerCommandSuccess(editSellerCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateSellerUnfilteredList_failure() {
        Seller firstSeller = model.getFilteredSellerList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditSellerDescriptor sellerDescriptor = new EditSellerDescriptorBuilder(firstSeller).build();
        EditSellerCommand editSellerCommand = new EditSellerCommand(INDEX_SECOND_PERSON, sellerDescriptor);

        assertSellerCommandFailure(editSellerCommand, model, EditSellerCommand.MESSAGE_DUPLICATE_SELLER);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showSellerAtIndex(model, INDEX_FIRST_PERSON);

        // edit seller in filtered list into a duplicate in address book
        Seller sellerInList = model.getSellerAddressBook().getSellerList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditSellerCommand editSellerCommand = new EditSellerCommand(INDEX_FIRST_PERSON,
                new EditSellerDescriptorBuilder(sellerInList).build());
        assertSellerCommandFailure(editSellerCommand, model, EditSellerCommand.MESSAGE_DUPLICATE_SELLER);
    }


    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredSellerList().size() + 1);
        EditSellerDescriptor sellerDescriptor = new EditSellerDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditSellerCommand editSellerCommand = new EditSellerCommand(outOfBoundIndex, sellerDescriptor);

        assertSellerCommandFailure(editSellerCommand, model, Messages.MESSAGE_INVALID_SELLER_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showSellerAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getSellerAddressBook().getSellerList().size());

        EditSellerCommand editSellerCommand = new EditSellerCommand(outOfBoundIndex,
                new EditSellerDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertSellerCommandFailure(editSellerCommand, model, Messages.MESSAGE_INVALID_SELLER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditSellerCommand standardSellerCommand = new EditSellerCommand(INDEX_FIRST_PERSON, DESC_AMY);

        // same values -> returns true
        EditSellerDescriptor copyDescriptor = new EditSellerDescriptor(DESC_AMY);
        EditSellerCommand commandWithSameValues = new EditSellerCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardSellerCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardSellerCommand.equals(standardSellerCommand));

        // null -> returns false
        assertFalse(standardSellerCommand.equals(null));

        // different types -> returns false
        assertFalse(standardSellerCommand.equals(new ListSellerCommand()));

        // different index -> returns false
        assertFalse(standardSellerCommand.equals(new EditSellerCommand(INDEX_SECOND_PERSON, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardSellerCommand.equals(new EditSellerCommand(INDEX_FIRST_PERSON, DESC_BOB)));
    }
}
