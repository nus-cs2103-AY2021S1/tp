package seedu.address.testutil.bids;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.bid.Bid;
import seedu.address.model.bidbook.BidBook;

public class TypicalBid {

    public static final Bid BID_A = new BidBuilder()
            .withPropertyId("P1")
            .withBidderId("B1")
            .withBidAmount(10000.20).build();

    public static final Bid BID_B = new BidBuilder()
            .withPropertyId("P2")
            .withBidderId("B2")
            .withBidAmount(123450.20).build();

    public static final Bid BID_C = new BidBuilder()
            .withPropertyId("P3")
            .withBidderId("B3")
            .withBidAmount(10.99).build();

    private TypicalBid() {

    }

    public static BidBook getTypicalBidBook() {
        BidBook bb = new BidBook();
        for (Bid bid : getTypicalBids()) {
            bb.addBid(bid);
        }
        return bb;
    }

    public static List<Bid> getTypicalBids() {
        return new ArrayList<>(Arrays.asList(BID_A, BID_B));
    }
}
