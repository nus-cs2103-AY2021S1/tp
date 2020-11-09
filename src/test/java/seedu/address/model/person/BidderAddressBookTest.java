package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.bidder.TypicalBidder.BIDDER_ALICE;
import static seedu.address.testutil.bidder.TypicalBidder.getTypicalBidderAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.bidderaddressbook.BidderAddressBook;
import seedu.address.model.bidderaddressbook.ReadOnlyBidderAddressBook;
import seedu.address.model.person.bidder.Bidder;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.bidder.BidderBuilder;

public class BidderAddressBookTest {

    private final BidderAddressBook bidderAddressBook = new BidderAddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), bidderAddressBook.getBidderList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> bidderAddressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        BidderAddressBook newData = getTypicalBidderAddressBook();
        bidderAddressBook.resetData(newData);
        assertEquals(newData, bidderAddressBook);
    }

    @Test
    public void resetData_withDuplicateBidders_throwsDuplicatePersonException() {
        // Two bidders with the same identity fields
        Bidder editedAlice = new BidderBuilder(BIDDER_ALICE).build();
        List<Bidder> newBidders = Arrays.asList(BIDDER_ALICE, editedAlice);
        BidderAddressBookStub newData = new BidderAddressBookStub(newBidders);

        assertThrows(DuplicatePersonException.class, () -> bidderAddressBook.resetData(newData));
    }

    @Test
    public void hasBidder_nullBidder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> bidderAddressBook.hasBidder(null));
    }

    @Test
    public void hasBidder_bidderNotInAddressBook_returnsFalse() {
        assertFalse(bidderAddressBook.hasBidder(BIDDER_ALICE));
    }

    @Test
    public void hasBidder_bidderInAddressBook_returnsTrue() {
        bidderAddressBook.addBidder(BIDDER_ALICE);
        assertTrue(bidderAddressBook.hasBidder(BIDDER_ALICE));
    }

    @Test
    public void hasBidder_bidderWithSameIdentityFieldsInAddressBook_returnsTrue() {
        bidderAddressBook.addBidder(BIDDER_ALICE);
        Bidder editedAlice = new BidderBuilder(BIDDER_ALICE).build();
        assertTrue(bidderAddressBook.hasBidder(editedAlice));
    }

    @Test
    public void getBidderList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> bidderAddressBook.getBidderList().remove(0));
    }

    /**
     * A stub ReadOnlyBidderAddressBook whose bidders list can violate interface constraints.
     */
    private static class BidderAddressBookStub implements ReadOnlyBidderAddressBook {
        private final ObservableList<Bidder> bidders = FXCollections.observableArrayList();

        BidderAddressBookStub(Collection<Bidder> bidders) {
            this.bidders.setAll(bidders);
        }

        @Override
        public ObservableList<Bidder> getBidderList() {
            return bidders;
        }
    }

}
