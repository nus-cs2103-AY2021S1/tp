package seedu.address.logic.commands.bids;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_BIDS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.bidder.TypicalBidder.getTypicalBidderAddressBook;
import static seedu.address.testutil.bids.TypicalBid.BID_A;
import static seedu.address.testutil.bids.TypicalBid.BID_B;
import static seedu.address.testutil.bids.TypicalBid.getTypicalBidBook;
import static seedu.address.testutil.seller.TypicalSeller.getTypicalSellerAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.bidcommands.FindBidCommand;
import seedu.address.model.MeetingBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.bid.BidContainsKeywordsPredicate;
import seedu.address.model.bidderaddressbook.BidderAddressBook;
import seedu.address.model.propertybook.PropertyBook;
import seedu.address.model.selleraddressbook.SellerAddressBook;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindBidCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalBidBook(),
            new PropertyBook(), new BidderAddressBook(), new SellerAddressBook(), new MeetingBook());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalBidBook(),
            new PropertyBook(), getTypicalBidderAddressBook(), getTypicalSellerAddressBook(), new MeetingBook());

    @Test
    public void equals() {
        BidContainsKeywordsPredicate firstPredicate =
                new BidContainsKeywordsPredicate(Collections.singletonList("P1"));
        BidContainsKeywordsPredicate secondPredicate =
                new BidContainsKeywordsPredicate(Collections.singletonList("P2"));

        FindBidCommand findFirstCommand = new FindBidCommand(firstPredicate);
        FindBidCommand findSecondCommand = new FindBidCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindBidCommand findFirstCommandCopy = new FindBidCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different bid -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noBidFound() {
        String expectedMessage = String.format(MESSAGE_BIDS_LISTED_OVERVIEW, 0);
        BidContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindBidCommand command = new FindBidCommand(predicate);
        expectedModel.updateFilteredBidList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredBidList());
    }

    @Test
    public void execute_multipleKeywords_multipleBidsFound() {
        String expectedMessage = String.format(MESSAGE_BIDS_LISTED_OVERVIEW, 2);
        BidContainsKeywordsPredicate predicate = preparePredicate("P1 B2");
        FindBidCommand command = new FindBidCommand(predicate);
        expectedModel.updateFilteredBidList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BID_A, BID_B), model.getFilteredBidList());
    }

    /**
     * Parses {@code userInput} into a {@code BidContainsKeywordsPredicate}.
     */
    private BidContainsKeywordsPredicate preparePredicate(String userInput) {
        return new BidContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}

