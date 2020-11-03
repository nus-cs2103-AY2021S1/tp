package seedu.address.logic.commands.property;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.showPropertyAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROPERTY;
import static seedu.address.testutil.bidder.TypicalBidder.getTypicalBidderAddressBook;
import static seedu.address.testutil.property.TypicalProperties.getTypicalPropertyBook;
import static seedu.address.testutil.seller.TypicalSeller.getTypicalSellerAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.MeetingBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.bidbook.BidBook;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListPropertyCommand.
 */
public class ListPropertyCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new UserPrefs(), new BidBook(),
                getTypicalPropertyBook(), getTypicalBidderAddressBook(), getTypicalSellerAddressBook(),
                new MeetingBook());
        expectedModel = new ModelManager(new UserPrefs(), model.getBidBook(),
                model.getPropertyBook(), model.getBidderAddressBook(),
                model.getSellerAddressBook(), model.getMeetingBook());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListPropertyCommand(), model, ListPropertyCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPropertyAtIndex(model, INDEX_FIRST_PROPERTY);
        assertCommandSuccess(new ListPropertyCommand(), model, ListPropertyCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
