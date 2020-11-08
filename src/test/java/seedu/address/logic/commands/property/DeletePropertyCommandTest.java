package seedu.address.logic.commands.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.showPropertyAtIndex;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROPERTY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PROPERTY;
import static seedu.address.testutil.bidder.TypicalBidder.getTypicalBidderAddressBook;
import static seedu.address.testutil.meeting.TypicalMeeting.getTypicalMeetingAddressBook;
import static seedu.address.testutil.property.TypicalProperties.getTypicalPropertyBook;
import static seedu.address.testutil.seller.TypicalSeller.getTypicalSellerAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.bidbook.BidBook;
import seedu.address.model.id.PropertyId;
import seedu.address.model.property.Property;
import seedu.address.model.property.exceptions.PropertyNotFoundException;

/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code DeletePropertyCommand}.
 */
public class DeletePropertyCommandTest {

    private Model model = new ModelManager(
            new UserPrefs(),
            new BidBook(),
            getTypicalPropertyBook(),
            getTypicalBidderAddressBook(),
            getTypicalSellerAddressBook(),
            getTypicalMeetingAddressBook());

    @Test
    public void executeByIndex_validIndexUnfilteredList_success() {
        Property propertyToDelete = model.getFilteredPropertyList().get(INDEX_FIRST_PROPERTY.getZeroBased());
        DeletePropertyCommand deletePropertyCommand = new DeletePropertyCommand(INDEX_FIRST_PROPERTY, null);

        String expectedMessage = String.format(DeletePropertyCommand.MESSAGE_DELETE_PROPERTY_SUCCESS, propertyToDelete);

        ModelManager expectedModel = new ModelManager(new UserPrefs(), model.getBidBook(),
                model.getPropertyBook(), model.getBidderAddressBook(),
                model.getSellerAddressBook(), model.getMeetingBook());
        expectedModel.deleteProperty(propertyToDelete);
        assertCommandSuccess(deletePropertyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void executeByIndex_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPropertyList().size() + 1);
        DeletePropertyCommand deletePropertyCommand = new DeletePropertyCommand(outOfBoundIndex, null);
        assertCommandFailure(deletePropertyCommand, model, Messages.MESSAGE_INVALID_PROPERTY_DISPLAYED_INDEX);
    }

    @Test
    public void executeByIndex_validIndexFilteredList_success() {
        showPropertyAtIndex(model, INDEX_FIRST_PROPERTY);

        Property propertyToDelete = model.getFilteredPropertyList().get(INDEX_FIRST_PROPERTY.getZeroBased());
        DeletePropertyCommand deletePropertyCommand = new DeletePropertyCommand(INDEX_FIRST_PROPERTY, null);

        String expectedMessage = String.format(DeletePropertyCommand.MESSAGE_DELETE_PROPERTY_SUCCESS, propertyToDelete);
        ModelManager expectedModel = new ModelManager(new UserPrefs(), model.getBidBook(),
                model.getPropertyBook(), model.getBidderAddressBook(),
                model.getSellerAddressBook(), model.getMeetingBook());
        expectedModel.deleteProperty(propertyToDelete);
        showNoProperty(expectedModel);

        assertCommandSuccess(deletePropertyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void executeByIndex_invalidIndexFilteredList_throwsCommandException() {
        showPropertyAtIndex(model, INDEX_FIRST_PROPERTY);

        Index outOfBoundIndex = INDEX_SECOND_PROPERTY;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPropertyBook().getPropertyList().size());

        DeletePropertyCommand deletePropertyCommand = new DeletePropertyCommand(outOfBoundIndex, null);

        assertCommandFailure(deletePropertyCommand, model, Messages.MESSAGE_INVALID_PROPERTY_DISPLAYED_INDEX);
    }

    @Test
    public void executeById_propertyExists_success() {
        PropertyId targetId = new PropertyId("P1");
        DeletePropertyCommand command = new DeletePropertyCommand(null, targetId);
        ModelManager expectedModel = new ModelManager(new UserPrefs(), model.getBidBook(),
                model.getPropertyBook(), model.getBidderAddressBook(),
                model.getSellerAddressBook(), model.getMeetingBook());
        expectedModel.deletePropertyByPropertyId(targetId);
        Property propertyToDelete = model.getPropertyById(targetId);
        String expectedMessage = String.format(DeletePropertyCommand.MESSAGE_DELETE_PROPERTY_SUCCESS, propertyToDelete);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void executeById_propertyDoesNotExist_throwsCommandException() {
        PropertyId targetId = new PropertyId(model.getPropertyBook().getPropertyList().size() + 1);
        DeletePropertyCommand command = new DeletePropertyCommand(null, targetId);
        assertThrows(PropertyNotFoundException.class, () -> command.execute(model));
    }

    @Test
    public void equals() {
        DeletePropertyCommand deleteFirstPropertyCommand = new DeletePropertyCommand(INDEX_FIRST_PROPERTY, null);
        DeletePropertyCommand deleteSecondPropertyCommand = new DeletePropertyCommand(INDEX_SECOND_PROPERTY, null);
        DeletePropertyCommand deleteId1 = new DeletePropertyCommand(null, new PropertyId(1));

        // same object -> returns true
        assertTrue(deleteFirstPropertyCommand.equals(deleteFirstPropertyCommand));

        // same values -> returns true
        DeletePropertyCommand deleteFirstPropertyCommandCopy = new DeletePropertyCommand(INDEX_FIRST_PROPERTY, null);
        assertTrue(deleteFirstPropertyCommand.equals(deleteFirstPropertyCommandCopy));
        DeletePropertyCommand deleteId1Copy = new DeletePropertyCommand(null, new PropertyId(1));
        assertTrue(deleteId1.equals(deleteId1Copy));

        // different types -> returns false
        assertFalse(deleteFirstPropertyCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstPropertyCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstPropertyCommand.equals(deleteSecondPropertyCommand));
        assertFalse(deleteFirstPropertyCommand.equals(deleteId1));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoProperty(Model model) {
        model.updateFilteredPropertyList(p -> false);

        assertTrue(model.getFilteredPropertyList().isEmpty());
    }

}
