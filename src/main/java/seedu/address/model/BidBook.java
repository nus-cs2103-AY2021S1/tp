package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.bid.Bid;
import seedu.address.model.bid.UniqueBidList;


public class BidBook implements ReadOnlyBidBook {

    private final UniqueBidList listOfBids;

    {
        listOfBids = new UniqueBidList();
    }

    public BidBook() {
    }

    /**
     * Constructor to create a BidBook
     * @param toBeCopied item to be contained in teh BidBook Object
     */
    public BidBook(ReadOnlyBidBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    public void setBids(List<Bid> bids) {
        this.listOfBids.setBids(bids);
    }

    public void addBid(Bid b) {
        listOfBids.add(b);
    }

    /**
     * resets the data inside the bidBook bye setting it as the new bidBook
     * @param newData data to overwrite the old data
     */
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
