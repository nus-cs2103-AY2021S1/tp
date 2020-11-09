package seedu.address.testutil.bidder;

import seedu.address.model.bidderaddressbook.BidderAddressBook;
import seedu.address.model.person.bidder.Bidder;

/**
 * A utility class to help with building BidderAddressbook objects.
 * Example usage: <br>
 *     {@code BidderAddressBook sb = new BidderAddressBookBuilder().withBidder("John", "Doe").build();}
 */
public class BidderAddressBookBuilder {

    private BidderAddressBook bidderAddressBook;

    public BidderAddressBookBuilder() {
        bidderAddressBook = new BidderAddressBook();
    }

    public BidderAddressBookBuilder(BidderAddressBook bidderAddressBook) {
        this.bidderAddressBook = bidderAddressBook;
    }

    /**
     * Adds a new {@code Bidder} to the {@code BidderAddressBook} that we are building.
     */
    public BidderAddressBookBuilder withBidder(Bidder bidder) {
        bidderAddressBook.addBidder(bidder);
        return this;
    }

    public BidderAddressBook build() {
        return bidderAddressBook;
    }
}
