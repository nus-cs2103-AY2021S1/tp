package seedu.address.logic.commands.seller;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.seller.SellerCommandTestUtil.showSellerAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.bidder.TypicalBidder.getTypicalBidderAddressBook;
import static seedu.address.testutil.property.TypicalProperties.getTypicalPropertyBook;
import static seedu.address.testutil.seller.TypicalSeller.getTypicalSellerAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.sellercommands.ListSellerCommand;
import seedu.address.model.MeetingBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.bidbook.BidBook;
/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListSellerCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new BidBook(),
                getTypicalPropertyBook(), getTypicalBidderAddressBook(), getTypicalSellerAddressBook(),
                new MeetingBook());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getBidBook(),
                model.getPropertyBook(), model.getBidderAddressBook(),
                model.getSellerAddressBook(), model.getMeetingBook());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListSellerCommand(), model, ListSellerCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showSellerAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListSellerCommand(), model, ListSellerCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
