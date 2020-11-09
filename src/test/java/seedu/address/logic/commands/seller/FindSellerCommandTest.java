package seedu.address.logic.commands.seller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_SELLERS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.bidder.TypicalBidder.getTypicalBidderAddressBook;
import static seedu.address.testutil.seller.TypicalSeller.CARL;
import static seedu.address.testutil.seller.TypicalSeller.ELLE;
import static seedu.address.testutil.seller.TypicalSeller.FIONA;
import static seedu.address.testutil.seller.TypicalSeller.getTypicalSellerAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.sellercommands.FindSellerCommand;
import seedu.address.model.MeetingBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.bidbook.BidBook;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.propertybook.PropertyBook;

/**
 * Contains integration tests (interaction with the Model) for {@code FindSellerCommand}.
 */
public class FindSellerCommandTest {

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

        FindSellerCommand findFirstCommand = new FindSellerCommand(firstPredicate);
        FindSellerCommand findSecondCommand = new FindSellerCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindSellerCommand findFirstCommandCopy = new FindSellerCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noFound() {
        String expectedMessage = String.format(MESSAGE_SELLERS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindSellerCommand command = new FindSellerCommand(predicate);
        expectedModel.updateFilteredSellerList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredSellerList());
    }

    @Test
    public void execute_multipleKeywords_multipleSellersFound() {
        String expectedMessage = String.format(MESSAGE_SELLERS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindSellerCommand command = new FindSellerCommand(predicate);
        expectedModel.updateFilteredSellerList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredSellerList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
