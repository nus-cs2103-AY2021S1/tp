package seedu.address.logic.commands.property;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.EDIT_DESC_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.EDIT_DESC_BEDOK;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_ASKING_PRICE_BEDOK;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_IS_RENTAL_BEDOK;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_NAME_BEDOK;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.showPropertyAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROPERTY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PROPERTY;
import static seedu.address.testutil.property.TypicalProperties.getTypicalPropertyBook;
import static seedu.address.testutil.seller.TypicalSeller.getTypicalSellerAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.MeetingBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.bidbook.BidBook;
import seedu.address.model.bidderaddressbook.BidderAddressBook;
import seedu.address.model.id.PropertyId;
import seedu.address.model.id.SellerId;
import seedu.address.model.property.Property;
import seedu.address.model.property.exceptions.InvalidSellerIdException;
import seedu.address.model.propertybook.PropertyBook;
import seedu.address.testutil.property.EditPropertyDescriptorBuilder;
import seedu.address.testutil.property.PropertyBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditPropertyCommand.
 */
public class EditPropertyCommandTest {

    private Model model = new ModelManager(new UserPrefs(),
            new BidBook(), getTypicalPropertyBook(),
            new BidderAddressBook(), getTypicalSellerAddressBook(), new MeetingBook());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() throws CommandException {
        Property editedProperty = new PropertyBuilder().build();
        EditPropertyCommand.EditPropertyDescriptor descriptor =
                new EditPropertyDescriptorBuilder(editedProperty).build();
        EditPropertyCommand editCommand = new EditPropertyCommand(INDEX_FIRST_PROPERTY, descriptor);

        String expectedMessage = String.format(EditPropertyCommand.MESSAGE_EDIT_PROPERTY_SUCCESS, editedProperty);

        Model expectedModel = new ModelManager(new UserPrefs(),
                new BidBook(), new PropertyBook(model.getPropertyBook()),
                new BidderAddressBook(), getTypicalSellerAddressBook(), new MeetingBook());
        expectedModel.setProperty(model.getFilteredPropertyList().get(0), editedProperty);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() throws CommandException {
        Index indexLastProperty = Index.fromOneBased(model.getFilteredPropertyList().size());
        Property lastProperty = model.getFilteredPropertyList().get(indexLastProperty.getZeroBased());

        PropertyBuilder propertyInList = new PropertyBuilder(lastProperty);
        Property editedProperty = propertyInList
                .withPropertyName(VALID_PROPERTY_NAME_BEDOK)
                .withAskingPrice(VALID_PROPERTY_ASKING_PRICE_BEDOK)
                .withIsRental(VALID_PROPERTY_IS_RENTAL_BEDOK)
                .build();

        EditPropertyCommand.EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder()
                .withPropertyName(VALID_PROPERTY_NAME_BEDOK)
                .withAskingPrice(VALID_PROPERTY_ASKING_PRICE_BEDOK)
                .withIsRental(VALID_PROPERTY_IS_RENTAL_BEDOK).build();
        EditPropertyCommand editCommand = new EditPropertyCommand(indexLastProperty, descriptor);

        String expectedMessage = String.format(EditPropertyCommand.MESSAGE_EDIT_PROPERTY_SUCCESS, editedProperty);

        Model expectedModel = new ModelManager(new UserPrefs(),
                new BidBook(), new PropertyBook(model.getPropertyBook()),
                new BidderAddressBook(), getTypicalSellerAddressBook(), new MeetingBook());
        expectedModel.setProperty(lastProperty, editedProperty);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditPropertyCommand editCommand = new EditPropertyCommand(INDEX_FIRST_PROPERTY,
                new EditPropertyCommand.EditPropertyDescriptor());
        Property editedProperty = model.getFilteredPropertyList().get(INDEX_FIRST_PROPERTY.getZeroBased());

        String expectedMessage = String.format(EditPropertyCommand.MESSAGE_EDIT_PROPERTY_SUCCESS, editedProperty);

        Model expectedModel = new ModelManager(new UserPrefs(),
                new BidBook(), new PropertyBook(model.getPropertyBook()),
                new BidderAddressBook(), getTypicalSellerAddressBook(), new MeetingBook());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() throws CommandException {
        showPropertyAtIndex(model, INDEX_FIRST_PROPERTY);

        Property propertyInFilteredList = model.getFilteredPropertyList().get(INDEX_FIRST_PROPERTY.getZeroBased());
        Property editedProperty = new PropertyBuilder(propertyInFilteredList)
                .withPropertyName(VALID_PROPERTY_NAME_BEDOK)
                .build();
        EditPropertyCommand editCommand = new EditPropertyCommand(INDEX_FIRST_PROPERTY,
                new EditPropertyDescriptorBuilder().withPropertyName(VALID_PROPERTY_NAME_BEDOK).build());

        String expectedMessage = String.format(EditPropertyCommand.MESSAGE_EDIT_PROPERTY_SUCCESS, editedProperty);

        Model expectedModel = new ModelManager(new UserPrefs(),
                new BidBook(), new PropertyBook(model.getPropertyBook()),
                new BidderAddressBook(), getTypicalSellerAddressBook(), new MeetingBook());
        expectedModel.setProperty(model.getFilteredPropertyList().get(0), editedProperty);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePropertyUnfilteredList_failure() {
        Property firstProperty = model.getFilteredPropertyList().get(INDEX_FIRST_PROPERTY.getZeroBased());
        EditPropertyCommand.EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder(firstProperty)
                .build();
        EditPropertyCommand editCommand = new EditPropertyCommand(INDEX_SECOND_PROPERTY, descriptor);

        assertCommandFailure(editCommand, model, EditPropertyCommand.MESSAGE_DUPLICATE_PROPERTY);
    }

    @Test
    public void execute_duplicatePropertyFilteredList_failure() {
        showPropertyAtIndex(model, INDEX_FIRST_PROPERTY);

        // edit property in filtered list into a duplicate in address book
        Property propertyInList = model.getPropertyBook().getPropertyList().get(INDEX_SECOND_PROPERTY.getZeroBased());
        EditPropertyCommand editCommand = new EditPropertyCommand(INDEX_FIRST_PROPERTY,
                new EditPropertyDescriptorBuilder(propertyInList.setId(new PropertyId("P2"))).build());
        assertCommandFailure(editCommand, model, EditPropertyCommand.MESSAGE_DUPLICATE_PROPERTY);
    }

    @Test
    public void execute_invalidPropertyIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPropertyList().size() + 1);
        EditPropertyCommand.EditPropertyDescriptor descriptor = new EditPropertyDescriptorBuilder()
                .withPropertyName(VALID_PROPERTY_NAME_BEDOK).build();
        EditPropertyCommand editCommand = new EditPropertyCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PROPERTY_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPropertyIndexFilteredList_failure() {
        showPropertyAtIndex(model, INDEX_FIRST_PROPERTY);
        Index outOfBoundIndex = INDEX_SECOND_PROPERTY;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPropertyBook().getPropertyList().size());

        EditPropertyCommand editCommand = new EditPropertyCommand(outOfBoundIndex,
                new EditPropertyDescriptorBuilder().withPropertyName(VALID_PROPERTY_NAME_BEDOK).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PROPERTY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidSellerId_failure() {
        SellerId sellerId = new SellerId(model.getSellerAddressBook().getSellerList().size() + 1);
        EditPropertyCommand editCommand = new EditPropertyCommand(INDEX_FIRST_PROPERTY,
                new EditPropertyDescriptorBuilder().withSellerId(sellerId.toString()).build());
        assertCommandFailure(editCommand, model, new InvalidSellerIdException().getMessage());
    }

    @Test
    public void equals() {
        final EditPropertyCommand standardCommand = new EditPropertyCommand(INDEX_FIRST_PROPERTY, EDIT_DESC_ANCHORVALE);

        // same values -> returns true
        EditPropertyCommand.EditPropertyDescriptor copyDescriptor =
                new EditPropertyCommand.EditPropertyDescriptor(EDIT_DESC_ANCHORVALE);
        EditPropertyCommand commandWithSameValues = new EditPropertyCommand(INDEX_FIRST_PROPERTY, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ListPropertyCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditPropertyCommand(INDEX_SECOND_PROPERTY, EDIT_DESC_ANCHORVALE)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditPropertyCommand(INDEX_FIRST_PROPERTY, EDIT_DESC_BEDOK)));
    }

}
