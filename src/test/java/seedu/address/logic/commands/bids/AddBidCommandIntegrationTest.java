package seedu.address.logic.commands.bids;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.bids.BidCommandTestUtil.VALID_BIDDER_ID_BID_B;
import static seedu.address.logic.commands.bids.BidCommandTestUtil.VALID_PROPERTY_ID_BID_A;
import static seedu.address.testutil.bidder.TypicalBidder.getTypicalBidderAddressBook;
import static seedu.address.testutil.bids.TypicalBid.getTypicalBidBook;
import static seedu.address.testutil.property.TypicalProperties.getTypicalPropertyBook;
import static seedu.address.testutil.seller.TypicalSeller.getTypicalSellerAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.bidcommands.AddBidCommand;
import seedu.address.logic.commands.exceptions.CommandException;
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
        model = new ModelManager(new UserPrefs(),
                getTypicalBidBook(), getTypicalPropertyBook(),
                getTypicalBidderAddressBook(), getTypicalSellerAddressBook(), new MeetingBook());
    }

    @Test
    public void execute_newBid_success() throws CommandException {
        Bid validBid = new BidBuilder()
                .withPropertyId(VALID_PROPERTY_ID_BID_A).withBidderId(VALID_BIDDER_ID_BID_B)
                .build();
        Model expectedModel = new ModelManager(
                new UserPrefs(),
                model.getBidBook(),
                model.getPropertyBook(),
                model.getBidderAddressBook(),
                model.getSellerAddressBook(),
                model.getMeetingBook()
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
