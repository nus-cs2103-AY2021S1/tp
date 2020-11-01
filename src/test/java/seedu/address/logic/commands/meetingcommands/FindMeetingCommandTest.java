package seedu.address.logic.commands.meetingcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalMeeting.getTypicalMeetingAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.bidder.TypicalBidder.getTypicalBidderAddressBook;
import static seedu.address.testutil.seller.TypicalSeller.getTypicalSellerAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.bidbook.BidBook;
import seedu.address.model.meeting.BidderIdContainsKeywordsPredicate;
import seedu.address.model.meeting.PropertyIdContainsKeywordsPredicate;
import seedu.address.model.meeting.VenueContainsKeywordsPredicate;
import seedu.address.model.propertybook.PropertyBook;



class FindMeetingCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new BidBook(),
            new PropertyBook(), getTypicalBidderAddressBook(), getTypicalSellerAddressBook(),
            getTypicalMeetingAddressBook());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new BidBook(),
            new PropertyBook(), getTypicalBidderAddressBook(), getTypicalSellerAddressBook(),
            getTypicalMeetingAddressBook());

    @Test
    public void equals() {
        VenueContainsKeywordsPredicate firstPredicate =
                new VenueContainsKeywordsPredicate(Collections.singletonList("first"));
        VenueContainsKeywordsPredicate secondPredicate =
                new VenueContainsKeywordsPredicate(Collections.singletonList("second"));

        FindMeetingCommand.FindMeetingDescriptor firstDescriptor = new FindMeetingCommand.FindMeetingDescriptor();
        firstDescriptor.setVenueContainsKeywordsPredicate(firstPredicate);
        FindMeetingCommand.FindMeetingDescriptor secondDescriptor = new FindMeetingCommand.FindMeetingDescriptor();
        secondDescriptor.setVenueContainsKeywordsPredicate(secondPredicate);

        FindMeetingCommand findFirstCommand = new FindMeetingCommand(firstDescriptor);
        FindMeetingCommand findSecondCommand = new FindMeetingCommand(secondDescriptor);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindMeetingCommand findFirstCommandCopy = new FindMeetingCommand(firstDescriptor);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different command -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noMeetingFound() {
        /*

            String expectedMessage = String.format(MESSAGE_MEETINGS_LISTED_OVERVIEW, 0);
            FindMeetingCommand.FindMeetingDescriptor descriptor = new FindMeetingCommand.FindMeetingDescriptor();

            // BidderId
            BidderIdContainsKeywordsPredicate meetingBidderIdPredicate = prepareBidderIdPredicate(" ");
            descriptor.setBidderIdContainsKeywordsPredicate(meetingBidderIdPredicate);
            FindMeetingCommand command = new FindMeetingCommand(descriptor);
            expectedModel.updateFilteredMeetingList(meetingBidderIdPredicate);
            assertCommandSuccess(command, model, expectedMessage, expectedModel);
            assertEquals(Collections.emptyList(), model.getFilteredMeetingList());
         */
    }

    @Test
    public void execute_multipleKeywords_multiplePropertiesFound() {
        /*

        String expectedMessage = String.format(MESSAGE_MEETINGS_LISTED_OVERVIEW, 2);
        FindMeetingDescriptor descriptor = new FindMeetingDescriptor();

        // property name
        PropertyIdContainsKeywordsPredicate namePredicate =
                preparePropertyIdPredicate("P1");
        descriptor.setPropertyIdContainsKeywordsPredicate(namePredicate);
        FindMeetingCommand command = new FindMeetingCommand(descriptor);
        expectedModel.updateFilteredMeetingList(namePredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MEETING01, MEETING02), model.getFilteredMeetingList());

        // property address
        PropertyAddressContainsKeywordsPredicate propertyAddressPredicate =
                prepareAddressPredicate("Aljunied Bayfront");
        descriptor = new FindPropertyCommand.FindPropertyDescriptor();
        descriptor.setPropertyAddressContainsKeywordsPredicate(propertyAddressPredicate);
        command = new FindPropertyCommand(descriptor);
        expectedModel.updateFilteredPropertyList(propertyAddressPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PROPERTY_A, PROPERTY_B), model.getFilteredPropertyList());

        // property type
        PropertyTypeContainsKeywordsPredicate propertyTypePredicate =
                prepareTypePredicate("HDB Condo");
        descriptor = new FindPropertyCommand.FindPropertyDescriptor();
        descriptor.setPropertyTypeContainsKeywordsPredicate(propertyTypePredicate);
        command = new FindPropertyCommand(descriptor);
        expectedModel.updateFilteredPropertyList(propertyTypePredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PROPERTY_A, PROPERTY_B), model.getFilteredPropertyList());

        // property id
        PropertyIdContainsKeywordsPredicate propertyIdPredicate =
                preparePropertyIdPredicate("P1 P2");
        descriptor = new FindPropertyCommand.FindPropertyDescriptor();
        descriptor.setPropertyIdPredicate(propertyIdPredicate);
        command = new FindPropertyCommand(descriptor);
        expectedModel.updateFilteredPropertyList(propertyIdPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PROPERTY_A, PROPERTY_B), model.getFilteredPropertyList());

        // seller id
        SellerIdContainsKeywordsPredicate sellerIdPredicate =
                prepareSellerIdPredicate("S1 S2");
        descriptor = new FindPropertyCommand.FindPropertyDescriptor();
        descriptor.setSellerIdContainsKeywordsPredicate(sellerIdPredicate);
        command = new FindPropertyCommand(descriptor);
        expectedModel.updateFilteredPropertyList(sellerIdPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PROPERTY_A, PROPERTY_B), model.getFilteredPropertyList());

        // is rental - only one keyword
        PropertyIsRentalPredicate isRentalPredicate =
                prepareIsRentalPredicate("no");
        descriptor = new FindPropertyCommand.FindPropertyDescriptor();
        descriptor.setIsRentalPredicate(isRentalPredicate);
        command = new FindPropertyCommand(descriptor);
        expectedModel.updateFilteredPropertyList(isRentalPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PROPERTY_A, PROPERTY_B), model.getFilteredPropertyList());

        // is closed deal - only one keyword
        PropertyIsClosedDealPredicate isClosedDealPredicate =
                prepareIsClosedDealPredicate("active");
        descriptor = new FindPropertyCommand.FindPropertyDescriptor();
        descriptor.setIsClosedDealPredicate(isClosedDealPredicate);
        command = new FindPropertyCommand(descriptor);
        expectedModel.updateFilteredPropertyList(isClosedDealPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PROPERTY_A, PROPERTY_B), model.getFilteredPropertyList());

        // asking price - only one keyword
        expectedMessage = String.format(MESSAGE_PROPERTY_LISTED_OVERVIEW, 1);
        AskingPricePredicate askingPricePredicate =
                prepareAskingPricePredicate("< 1000");
        descriptor = new FindPropertyCommand.FindPropertyDescriptor();
        descriptor.setAskingPricePredicate(askingPricePredicate);
        command = new FindPropertyCommand(descriptor);
        expectedModel.updateFilteredPropertyList(askingPricePredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(PROPERTY_A), model.getFilteredPropertyList());
         */
    }

    /**
     * Parses {@code userInput} into a {@code BidderIdContainsKeywordsPredicate}.
     */
    private BidderIdContainsKeywordsPredicate prepareBidderIdPredicate(String userInput) {
        return new BidderIdContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code PropertyIdContainsKeywordsPredicate}.
     */
    private PropertyIdContainsKeywordsPredicate preparePropertyIdPredicate(String userInput) {
        return new PropertyIdContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}

