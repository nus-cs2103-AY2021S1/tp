package seedu.address.logic.commands.property;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PROPERTY_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.property.TypicalProperties.PROPERTY_A;
import static seedu.address.testutil.property.TypicalProperties.PROPERTY_B;
import static seedu.address.testutil.property.TypicalProperties.getTypicalPropertyBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.property.FindPropertyCommand.FindPropertyDescriptor;
import seedu.address.model.MeetingBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.bidbook.BidBook;
import seedu.address.model.bidderaddressbook.BidderAddressBook;
import seedu.address.model.price.PriceFilter;
import seedu.address.model.property.AskingPricePredicate;
import seedu.address.model.property.IsClosedDeal;
import seedu.address.model.property.IsRental;
import seedu.address.model.property.PropertyAddressContainsKeywordsPredicate;
import seedu.address.model.property.PropertyIdContainsKeywordsPredicate;
import seedu.address.model.property.PropertyIsClosedDealPredicate;
import seedu.address.model.property.PropertyIsRentalPredicate;
import seedu.address.model.property.PropertyNameContainsKeywordsPredicate;
import seedu.address.model.property.PropertyTypeContainsKeywordsPredicate;
import seedu.address.model.property.SellerIdContainsKeywordsPredicate;
import seedu.address.model.selleraddressbook.SellerAddressBook;

/**
 * Contains integration tests (interaction with the Model) for {@code FindPropertyCommand}.
 */
public class FindPropertyCommandTest {

    private Model model = new ModelManager(new UserPrefs(), new BidBook(),
            getTypicalPropertyBook(), new BidderAddressBook(), new SellerAddressBook(), new MeetingBook());
    private Model expectedModel = new ModelManager(new UserPrefs(), new BidBook(),
            getTypicalPropertyBook(), new BidderAddressBook(), new SellerAddressBook(), new MeetingBook());

    @Test
    public void equals() {

        PropertyNameContainsKeywordsPredicate firstPredicate =
                new PropertyNameContainsKeywordsPredicate(Collections.singletonList("first"));
        PropertyNameContainsKeywordsPredicate secondPredicate =
                new PropertyNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindPropertyDescriptor firstDescriptor = new FindPropertyDescriptor();
        firstDescriptor.setPropertyNameContainsKeywordsPredicate(firstPredicate);
        FindPropertyDescriptor secondDescriptor = new FindPropertyDescriptor();
        secondDescriptor.setPropertyNameContainsKeywordsPredicate(secondPredicate);

        FindPropertyCommand findFirstCommand = new FindPropertyCommand(firstDescriptor);
        FindPropertyCommand findSecondCommand = new FindPropertyCommand(secondDescriptor);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindPropertyCommand findFirstCommandCopy = new FindPropertyCommand(firstDescriptor);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different command -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPropertyFound() {

        String expectedMessage = String.format(MESSAGE_PROPERTY_LISTED_OVERVIEW, 0);
        FindPropertyDescriptor descriptor = new FindPropertyDescriptor();

        // property name
        PropertyNameContainsKeywordsPredicate propertyNamePredicate = prepareNamePredicate(" ");
        descriptor.setPropertyNameContainsKeywordsPredicate(propertyNamePredicate);
        FindPropertyCommand command = new FindPropertyCommand(descriptor);
        expectedModel.updateFilteredPropertyList(propertyNamePredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPropertyList());

        // property address
        PropertyAddressContainsKeywordsPredicate propertyAddressPredicate = prepareAddressPredicate(" ");
        descriptor = new FindPropertyDescriptor();
        descriptor.setPropertyAddressContainsKeywordsPredicate(propertyAddressPredicate);
        command = new FindPropertyCommand(descriptor);
        expectedModel.updateFilteredPropertyList(propertyAddressPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPropertyList());

        // property type
        PropertyTypeContainsKeywordsPredicate propertyTypePredicate = prepareTypePredicate(" ");
        descriptor = new FindPropertyDescriptor();
        descriptor.setPropertyTypeContainsKeywordsPredicate(propertyTypePredicate);
        command = new FindPropertyCommand(descriptor);
        expectedModel.updateFilteredPropertyList(propertyTypePredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPropertyList());

        // property id
        PropertyIdContainsKeywordsPredicate propertyIdPredicate = preparePropertyIdPredicate(" ");
        descriptor = new FindPropertyDescriptor();
        descriptor.setPropertyIdPredicate(propertyIdPredicate);
        command = new FindPropertyCommand(descriptor);
        expectedModel.updateFilteredPropertyList(propertyIdPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPropertyList());

        // seller id
        SellerIdContainsKeywordsPredicate sellerIdPredicate = prepareSellerIdPredicate(" ");
        descriptor = new FindPropertyDescriptor();
        descriptor.setSellerIdContainsKeywordsPredicate(sellerIdPredicate);
        command = new FindPropertyCommand(descriptor);
        expectedModel.updateFilteredPropertyList(sellerIdPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPropertyList());

        // asking price, isRental, isClosedDeal - ParseException caught in Parser
    }

    @Test
    public void execute_multipleKeywords_multiplePropertiesFound() {

        String expectedMessage = String.format(MESSAGE_PROPERTY_LISTED_OVERVIEW, 2);
        FindPropertyDescriptor descriptor = new FindPropertyDescriptor();

        // property name
        PropertyNameContainsKeywordsPredicate namePredicate =
                prepareNamePredicate("Building Condominium");
        descriptor.setPropertyNameContainsKeywordsPredicate(namePredicate);
        FindPropertyCommand command = new FindPropertyCommand(descriptor);
        expectedModel.updateFilteredPropertyList(namePredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PROPERTY_A, PROPERTY_B), model.getFilteredPropertyList());

        // property address
        PropertyAddressContainsKeywordsPredicate propertyAddressPredicate =
                prepareAddressPredicate("Aljunied Bayfront");
        descriptor = new FindPropertyDescriptor();
        descriptor.setPropertyAddressContainsKeywordsPredicate(propertyAddressPredicate);
        command = new FindPropertyCommand(descriptor);
        expectedModel.updateFilteredPropertyList(propertyAddressPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PROPERTY_A, PROPERTY_B), model.getFilteredPropertyList());

        // property type
        PropertyTypeContainsKeywordsPredicate propertyTypePredicate =
                prepareTypePredicate("HDB Condo");
        descriptor = new FindPropertyDescriptor();
        descriptor.setPropertyTypeContainsKeywordsPredicate(propertyTypePredicate);
        command = new FindPropertyCommand(descriptor);
        expectedModel.updateFilteredPropertyList(propertyTypePredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PROPERTY_A, PROPERTY_B), model.getFilteredPropertyList());

        // property id
        PropertyIdContainsKeywordsPredicate propertyIdPredicate =
                preparePropertyIdPredicate("P1 P2");
        descriptor = new FindPropertyDescriptor();
        descriptor.setPropertyIdPredicate(propertyIdPredicate);
        command = new FindPropertyCommand(descriptor);
        expectedModel.updateFilteredPropertyList(propertyIdPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PROPERTY_A, PROPERTY_B), model.getFilteredPropertyList());

        // seller id
        SellerIdContainsKeywordsPredicate sellerIdPredicate =
                prepareSellerIdPredicate("S1 S2");
        descriptor = new FindPropertyDescriptor();
        descriptor.setSellerIdContainsKeywordsPredicate(sellerIdPredicate);
        command = new FindPropertyCommand(descriptor);
        expectedModel.updateFilteredPropertyList(sellerIdPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PROPERTY_A, PROPERTY_B), model.getFilteredPropertyList());

        // is rental - only one keyword
        PropertyIsRentalPredicate isRentalPredicate =
                prepareIsRentalPredicate("no");
        descriptor = new FindPropertyDescriptor();
        descriptor.setIsRentalPredicate(isRentalPredicate);
        command = new FindPropertyCommand(descriptor);
        expectedModel.updateFilteredPropertyList(isRentalPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PROPERTY_A, PROPERTY_B), model.getFilteredPropertyList());

        // is closed deal - only one keyword
        PropertyIsClosedDealPredicate isClosedDealPredicate =
                prepareIsClosedDealPredicate("active");
        descriptor = new FindPropertyDescriptor();
        descriptor.setIsClosedDealPredicate(isClosedDealPredicate);
        command = new FindPropertyCommand(descriptor);
        expectedModel.updateFilteredPropertyList(isClosedDealPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PROPERTY_A, PROPERTY_B), model.getFilteredPropertyList());

        // asking price - only one keyword
        expectedMessage = String.format(MESSAGE_PROPERTY_LISTED_OVERVIEW, 1);
        AskingPricePredicate askingPricePredicate =
                prepareAskingPricePredicate("< 1000");
        descriptor = new FindPropertyDescriptor();
        descriptor.setAskingPricePredicate(askingPricePredicate);
        command = new FindPropertyCommand(descriptor);
        expectedModel.updateFilteredPropertyList(askingPricePredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PROPERTY_A), model.getFilteredPropertyList());

    }

    /**
     * Parses {@code userInput} into a {@code PropertyNameContainsKeywordsPredicate}.
     */
    private PropertyNameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new PropertyNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code PropertyAddressContainsKeywordsPredicate}.
     */
    private PropertyAddressContainsKeywordsPredicate prepareAddressPredicate(String userInput) {
        return new PropertyAddressContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code SellerIdContainsKeywordsPredicate}.
     */
    private SellerIdContainsKeywordsPredicate prepareSellerIdPredicate(String userInput) {
        return new SellerIdContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code PropertyIdContainsKeywordsPredicate}.
     */
    private PropertyIdContainsKeywordsPredicate preparePropertyIdPredicate(String userInput) {
        return new PropertyIdContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code AskingPricePredicate}.
     */
    private AskingPricePredicate prepareAskingPricePredicate(String userInput) {
        return new AskingPricePredicate(new PriceFilter(userInput.trim()));
    }

    /**
     * Parses {@code userInput} into a {@code PropertyTypeContainsKeywordsPredicate}.
     */
    private PropertyTypeContainsKeywordsPredicate prepareTypePredicate(String userInput) {
        return new PropertyTypeContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code PropertyIsRentalPredicate}.
     */
    private PropertyIsRentalPredicate prepareIsRentalPredicate(String userInput) {
        return new PropertyIsRentalPredicate(new IsRental(userInput.trim()));
    }

    /**
     * Parses {@code userInput} into a {@code PropertyIsClosedDealPredicate}.
     */
    private PropertyIsClosedDealPredicate prepareIsClosedDealPredicate(String userInput) {
        return new PropertyIsClosedDealPredicate(new IsClosedDeal(userInput.trim()));
    }
}
