package seedu.address.logic.commands.bidder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_BIDDERS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.bidder.TypicalBidder.CARL;
import static seedu.address.testutil.bidder.TypicalBidder.ELLE;
import static seedu.address.testutil.bidder.TypicalBidder.FIONA;
import static seedu.address.testutil.bidder.TypicalBidder.getTypicalBidderAddressBook;
import static seedu.address.testutil.seller.TypicalSeller.getTypicalSellerAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.biddercommands.FindBidderCommand;
import seedu.address.model.MeetingBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.bidbook.BidBook;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.propertybook.PropertyBook;

/**
 * Contains integration tests (interaction with the Model) for {@code FindBidderCommand}.
 */
public class FindBidderCommandTest {

    private Model model = new ModelManager(new UserPrefs(), new BidBook(),
            new PropertyBook(), getTypicalBidderAddressBook(), getTypicalSellerAddressBook(), new MeetingBook());
    private Model expectedModel = new ModelManager(new UserPrefs(), new BidBook(),
            new PropertyBook(), getTypicalBidderAddressBook(), getTypicalSellerAddressBook(), new MeetingBook());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindBidderCommand findFirstCommand = new FindBidderCommand(firstPredicate);
        FindBidderCommand findSecondCommand = new FindBidderCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindBidderCommand findFirstCommandCopy = new FindBidderCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noBidderFound() {
        String expectedMessage = String.format(MESSAGE_BIDDERS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindBidderCommand command = new FindBidderCommand(predicate);
        expectedModel.updateFilteredBidderList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredBidderList());
    }

    @Test
    public void execute_multipleKeywords_multipleBiddersFound() {
        String expectedMessage = String.format(MESSAGE_BIDDERS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindBidderCommand command = new FindBidderCommand(predicate);
        expectedModel.updateFilteredBidderList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredBidderList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
