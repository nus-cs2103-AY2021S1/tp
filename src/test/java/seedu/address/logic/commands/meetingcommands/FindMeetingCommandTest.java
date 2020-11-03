package seedu.address.logic.commands.meetingcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_MEETINGS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalMeeting.ADMINMEETING01;
import static seedu.address.testutil.TypicalMeeting.PAPERWORKMEETING05;
import static seedu.address.testutil.TypicalMeeting.VIEWINGMEETING03;
import static seedu.address.testutil.TypicalMeeting.getTypicalMeetingAddressBook;
import static seedu.address.testutil.bidder.TypicalBidder.getTypicalBidderAddressBook;
import static seedu.address.testutil.seller.TypicalSeller.getTypicalSellerAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.meetingcommands.FindMeetingCommand.FindMeetingDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.bidbook.BidBook;
import seedu.address.model.meeting.BidderIdContainsKeywordsPredicate;
import seedu.address.model.meeting.PropertyIdContainsKeywordsPredicate;
import seedu.address.model.meeting.VenueContainsKeywordsPredicate;
import seedu.address.model.propertybook.PropertyBook;

class FindMeetingCommandTest {
    private Model model = new ModelManager(new UserPrefs(), new BidBook(),
            new PropertyBook(), getTypicalBidderAddressBook(), getTypicalSellerAddressBook(),
            getTypicalMeetingAddressBook());
    private Model expectedModel = new ModelManager(new UserPrefs(), new BidBook(),
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
        String expectedMessage = String.format(MESSAGE_MEETINGS_LISTED_OVERVIEW, 0);
        FindMeetingDescriptor descriptor = new FindMeetingDescriptor();

        // BidderId
        BidderIdContainsKeywordsPredicate bidderIdPredicate = prepareBidderIdPredicate(" ");
        descriptor.setBidderIdContainsKeywordsPredicate(bidderIdPredicate);
        FindMeetingCommand command = new FindMeetingCommand(descriptor);
        expectedModel.updateFilteredMeetingList(bidderIdPredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredMeetingList());
    }

    @Test
    public void execute_multipleKeywords_multipleMeetingFound() {
        String expectedMessage = String.format(MESSAGE_MEETINGS_LISTED_OVERVIEW, 3);
        FindMeetingDescriptor descriptor = new FindMeetingDescriptor();

        // property name
        BidderIdContainsKeywordsPredicate namePredicate =
                prepareBidderIdPredicate("B1");
        descriptor.setBidderIdContainsKeywordsPredicate(namePredicate);
        FindMeetingCommand command = new FindMeetingCommand(descriptor);
        expectedModel.updateFilteredMeetingList(namePredicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ADMINMEETING01, VIEWINGMEETING03, PAPERWORKMEETING05),
         model.getFilteredMeetingList());
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

