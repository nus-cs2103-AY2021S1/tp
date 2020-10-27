package seedu.address.model.bid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.bids.TypicalBid.BID_A;
import static seedu.address.testutil.bids.TypicalBid.BID_B;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.bid.exceptions.BidNotFoundException;
import seedu.address.model.bid.exceptions.DuplicateBidException;
import seedu.address.testutil.bids.BidBuilder;

public class UniqueBidListTest {

    private final UniqueBidList uniqueBidList = new UniqueBidList();

    @Test
    public void contains_nullBid_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBidList.contains(null));
    }

    @Test
    public void contains_bidNotInList_returnsFalse() {
        assertFalse(uniqueBidList.contains(BID_A));
    }

    @Test
    public void contains_bidInList_returnsTrue() {
        uniqueBidList.add(BID_A);
        assertTrue(uniqueBidList.contains(BID_A));
    }

    @Test
    public void contains_bidWithSameIdentityFieldsInList_returnsTrue() {
        uniqueBidList.add(BID_A);

        Bid editedBid = new BidBuilder(BID_A).build();
        assertTrue(uniqueBidList.contains(editedBid));
    }

    @Test
    public void add_nullBid_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBidList.add(null));
    }

    @Test
    public void add_duplicateBid_throwsDuplicateBidException() {
        uniqueBidList.add(BID_A);
        assertThrows(DuplicateBidException.class, () -> uniqueBidList.add(BID_A));
    }

    @Test
    public void setBid_nullTargetBid_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBidList.setBid(null, BID_A));
    }

    @Test
    public void setBid_nullEditedBid_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBidList.setBid(BID_A, null));
    }

    @Test
    public void setBid_targetBidNotInList_throwsBidNotFoundException() {
        assertThrows(BidNotFoundException.class, () -> uniqueBidList.setBid(BID_A, BID_A));
    }

    @Test
    public void setBid_editedBidIsSameBid_success() {
        uniqueBidList.add(BID_A);
        uniqueBidList.setBid(BID_A, BID_A);
        UniqueBidList expectedUniqueBidList = new UniqueBidList();
        expectedUniqueBidList.add(BID_A);
        assertEquals(expectedUniqueBidList, uniqueBidList);
    }

    @Test
    public void setBid_editedBidHasSameIdentity_success() {
        uniqueBidList.add(BID_A);
        Bid editedBid = new BidBuilder(BID_A).build();
        uniqueBidList.setBid(BID_A, editedBid);
        UniqueBidList expectedUniqueBidList = new UniqueBidList();
        expectedUniqueBidList.add(editedBid);
        assertEquals(expectedUniqueBidList, uniqueBidList);
    }

    @Test
    public void setBid_editedBidHasDifferentIdentity_success() {
        uniqueBidList.add(BID_A);
        uniqueBidList.setBid(BID_A, BID_B);
        UniqueBidList expectedUniqueBidList = new UniqueBidList();
        expectedUniqueBidList.add(BID_B);
        assertEquals(expectedUniqueBidList, uniqueBidList);
    }

    @Test
    public void setBid_editedBidHasNonUniqueIdentity_throwsDuplicateBidException() {
        uniqueBidList.add(BID_A);
        uniqueBidList.add(BID_B);
        assertThrows(DuplicateBidException.class, () -> uniqueBidList.setBid(BID_A, BID_B));
    }

    /*
    @Test
    public void remove_nullBid_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBidList.remove(null));
    }

    @Test
    public void remove_BidDoesNotExist_throwsBidNotFoundException() {
        assertThrows(BidNotFoundException.class, () -> uniqueBidList.remove(BID_A));
    }

    @Test
    public void remove_existingBid_removesBid() {
        uniqueBidList.add(BID_A);
        uniqueBidList.remove(BID_A);
        UniqueBidList expectedUniqueBidList = new UniqueBidList();
        assertEquals(expectedUniqueBidList, uniqueBidList);
    }
*/
    @Test
    public void setBids_nullUniqueBidList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBidList.setBids((UniqueBidList) null));
    }

    @Test
    public void setBids_uniqueBidList_replacesOwnListWithProvidedUniqueBidList() {
        uniqueBidList.add(BID_A);
        UniqueBidList expectedUniqueBidList = new UniqueBidList();
        expectedUniqueBidList.add(BID_B);
        uniqueBidList.setBids(expectedUniqueBidList);
        assertEquals(expectedUniqueBidList, uniqueBidList);
    }

    @Test
    public void setBids_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueBidList.setBids((List<Bid>) null));
    }

    @Test
    public void setBids_list_replacesOwnListWithProvidedList() {
        uniqueBidList.add(BID_A);
        List<Bid> bidList = Collections.singletonList(BID_B);
        uniqueBidList.setBids(bidList);
        UniqueBidList expectedUniqueBidList = new UniqueBidList();
        expectedUniqueBidList.add(BID_B);
        assertEquals(expectedUniqueBidList, uniqueBidList);
    }

    @Test
    public void setBids_listWithDuplicateBids_throwsDuplicateBidException() {
        List<Bid> listWithDuplicateBids = Arrays.asList(BID_A, BID_A);
        assertThrows(DuplicateBidException.class, () -> uniqueBidList.setBids(listWithDuplicateBids));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueBidList.asUnmodifiableObservableBidList().remove(0));
    }

}
