package seedu.address.model.bid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.bids.TypicalBid.BID_A;
import static seedu.address.testutil.bids.TypicalBid.getTypicalBidBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.bid.exceptions.DuplicateBidException;
import seedu.address.model.bidbook.BidBook;
import seedu.address.model.bidbook.ReadOnlyBidBook;
import seedu.address.testutil.bids.BidBuilder;

public class BidBookTest {

    private final BidBook bidBook = new BidBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), bidBook.getBidList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> bidBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyBidBook_replacesData() {
        BidBook newData = getTypicalBidBook();
        bidBook.resetData(newData);
        assertEquals(newData, bidBook);
    }

    @Test//
    public void resetData_withDuplicateBids_throwsDuplicateBidException() {
        // Two bids with the same identity fields
        Bid editedBid = new BidBuilder(BID_A).build();
        List<Bid> newBids = Arrays.asList(BID_A, editedBid);
        BidBookStub newData = new BidBookStub(newBids);

        assertThrows(DuplicateBidException.class, () -> bidBook.resetData(newData));
    }

    @Test
    public void hasBid_nullBid_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> bidBook.hasBid(null));
    }

    @Test
    public void hasBid_bidNotInBidBook_returnsFalse() {
        assertFalse(bidBook.hasBid(BID_A));
    }

    @Test
    public void hasBid_bidInBidBook_returnsTrue() {
        bidBook.addBid(BID_A);
        assertTrue(bidBook.hasBid(BID_A));
    }

    @Test
    public void hasBid_bidWithSameIdentityFieldsInBidBook_returnsTrue() {
        bidBook.addBid(BID_A);
        Bid editedBid = new BidBuilder(BID_A).build();
        assertTrue(bidBook.hasBid(editedBid));
    }

    @Test
    public void getBidList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> bidBook.getBidList().remove(0));
    }

    /**
     * A stub ReadOnlyBidBook whose bid list can violate interface constraints.
     */
    private static class BidBookStub implements ReadOnlyBidBook {
        private final ObservableList<Bid> bids = FXCollections.observableArrayList();

        BidBookStub(Collection<Bid> bids) {
            this.bids.setAll(bids);
        }

        @Override
        public ObservableList<Bid> getBidList() {
            return bids;
        }
    }


}
