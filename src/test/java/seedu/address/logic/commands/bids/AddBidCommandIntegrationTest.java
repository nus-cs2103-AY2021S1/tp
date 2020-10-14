package seedu.address.logic.commands.bids;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.bid.Bid.DEFAULT_PROPERTY_ID;
import static seedu.address.testutil.TypicalBidder.getTypicalBidderAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalSeller.getTypicalSellerAddressBook;
import static seedu.address.testutil.bids.TypicalBid.getTypicalBidBook;
import static seedu.address.testutil.property.TypicalProperties.getTypicalPropertyBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddBidCommand;
import seedu.address.model.MeetingBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.bid.Bid;
import seedu.address.testutil.bids.BidBuilder;

public class AddBidCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
                getTypicalBidBook(), getTypicalPropertyBook(),
                getTypicalBidderAddressBook(), getTypicalSellerAddressBook(), new MeetingBook());
    }

    @Test
    public void execute_newBid_success() {
        Bid validBid = new BidBuilder()
                .withPropertyId(DEFAULT_PROPERTY_ID)
                .build();

        Model expectedModel = new ModelManager(
                model.getAddressBook(),
                new UserPrefs(),
                model.getBidBook(),
                model.getPropertyBook(),
                model.getBidderAddressBook(),
                model.getSellerAddressBook(),
                model.getMeetingManager()
        );
        expectedModel.addBid(validBid);

        assertCommandSuccess(new AddBidCommand(validBid), model,
                String.format(AddBidCommand.MESSAGE_SUCCESS, validBid), expectedModel);
    }

    @Test
    public void execute_duplicateBid_throwsCommandException() {
        Bid bidInList = model.getBidBook().getBidList().get(0);
        assertCommandFailure(new AddBidCommand(bidInList), model,
                AddBidCommand.MESSAGE_DUPLICATE_BID);
    }
}
