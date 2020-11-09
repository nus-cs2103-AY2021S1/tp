package seedu.address.model.bid;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.bids.BidCommandTestUtil.VALID_BIDDER_ID_BID_B;
import static seedu.address.logic.commands.bids.BidCommandTestUtil.VALID_BID_AMOUNT_BID_B;
import static seedu.address.logic.commands.bids.BidCommandTestUtil.VALID_PROPERTY_ID_BID_B;
import static seedu.address.testutil.bids.TypicalBid.BID_A;
import static seedu.address.testutil.bids.TypicalBid.BID_B;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.bids.BidBuilder;

public class BidTest {

    @Test
    public void isSameBid() {
        // same object -> returns true
        assertTrue(BID_A.isSameBid(BID_A));

        // null -> returns false
        assertFalse(BID_A.isSameBid(null));

        // different propertyId -> returns false
        Bid editedBid = new BidBuilder(BID_A).withPropertyId(VALID_PROPERTY_ID_BID_B).build();
        assertFalse(BID_A.isSameBid(editedBid));

        // different bidderId -> returns false
        editedBid = new BidBuilder(BID_A).withBidderId(VALID_BIDDER_ID_BID_B).build();
        assertFalse(BID_A.isSameBid(editedBid));

        // different bidAmount -> returns true
        editedBid = new BidBuilder(BID_A).withBidAmount(VALID_BID_AMOUNT_BID_B).build();
        assertTrue(BID_A.isSameBid(editedBid));

    }

    @Test
    public void equals() {
        // same values -> returns true
        Bid bidCopy = new BidBuilder(BID_A).build();

        assertTrue(BID_A.equals(bidCopy));

        // same object -> returns true
        assertTrue(BID_A.equals(BID_A));

        // null -> returns false
        assertFalse(BID_A.equals(null));

        // different type -> returns false
        assertFalse(BID_A.equals(5));

        // different bid -> returns false
        assertFalse(BID_A.equals(BID_B));

        // different propertyIds -> returns false
        Bid editedBid = new BidBuilder(BID_A).withPropertyId(VALID_PROPERTY_ID_BID_B).build();
        assertFalse(BID_A.equals(editedBid));

        // different bidderIds -> returns false
        editedBid = new BidBuilder(BID_A).withBidderId(VALID_BIDDER_ID_BID_B).build();
        assertFalse(BID_A.equals(editedBid));

        // different bidAmounts -> returns false
        editedBid = new BidBuilder(BID_A).withBidAmount(VALID_BID_AMOUNT_BID_B).build();
        assertFalse(BID_A.equals(editedBid));


    }
}
