package seedu.address.logic.commands.bids;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.bidder.TypicalBidder.getTypicalBidderAddressBook;
import static seedu.address.testutil.bids.TypicalBid.getTypicalBidBook;
import static seedu.address.testutil.property.TypicalProperties.getTypicalPropertyBook;
import static seedu.address.testutil.seller.TypicalSeller.getTypicalSellerAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.bidcommands.ListBidCommand;
import seedu.address.model.MeetingBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;


public class ListBidCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalBidBook(),
                getTypicalPropertyBook(), getTypicalBidderAddressBook(), getTypicalSellerAddressBook(),
                new MeetingBook());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getBidBook(),
                model.getPropertyBook(), model.getBidderAddressBook(),
                model.getSellerAddressBook(), model.getMeetingBook());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListBidCommand(), model, ListBidCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
