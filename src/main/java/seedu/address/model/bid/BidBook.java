package seedu.address.model.bid;


import javafx.collections.ObservableList;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class BidBook implements ReadOnlyBidBook {

    private final UniqueBidList listOfBids;

    {
       listOfBids = new UniqueBidList();
    }

    public BidBook() {
    }

    public BidBook(ReadOnlyBidBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    public void setBids(List<Bid> bids) {
        this.listOfBids.setBids(bids);
    }

    public void addBids(Bid b) {
        listOfBids.add(b);
    }

    public void resetData(ReadOnlyBidBook newData) {
        requireNonNull(newData);

        setBids(newData.getBidList());
    }

    @Override
    public String toString() {
        return listOfBids.asUnmodifiableObservableBidList().size() + " bids";
        // TODO: refine later
    }

    @Override
    public ObservableList<Bid> getBidList() {
        return listOfBids.asUnmodifiableObservableBidList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BidBook // instanceof handles nulls
                && listOfBids.equals(((BidBook) other).listOfBids));
    }

    @Override
    public int hashCode() {
        return listOfBids.hashCode();
    }

}
