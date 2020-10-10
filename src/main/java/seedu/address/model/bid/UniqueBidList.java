package seedu.address.model.bid;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UniqueBidList implements Iterable<Bid> {

    private final ObservableList<Bid> internalBidList = FXCollections.observableArrayList();
    private final ObservableList<Bid> internalUnmodifiableBidList =
            FXCollections.unmodifiableObservableList(internalBidList);

    /**
     * adds a bid to the internal list containing all the bids
     * @param toAdd the bid to add to the list
     */
    public void add(Bid toAdd) {
        requireNonNull(toAdd);

        internalBidList.add(toAdd);
    }

    /**
     * checks if their is any identical bids
     * @param toCheck bid object to compare
     * @return boolean value if the bid is contained in the list
     */
    public boolean contains(Bid toCheck) {
        requireNonNull(toCheck);
        return internalBidList.stream().anyMatch(toCheck::isSameBid);
    }

    public void setBids(List<Bid> bids) {
        requireAllNonNull(bids);


        internalBidList.setAll(bids);
    }

    public ObservableList<Bid> asUnmodifiableObservableBidList() {
        return internalUnmodifiableBidList;
    }

    @Override
    public Iterator<Bid> iterator() {
        return internalBidList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueBidList // instanceof handles nulls
                && internalBidList.equals(((UniqueBidList) other).internalBidList));
    }

    @Override
    public int hashCode() {
        return internalBidList.hashCode();
    }
}
