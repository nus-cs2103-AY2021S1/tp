package seedu.address.model.bid;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.bid.exceptions.BidNotFoundException;
import seedu.address.model.bid.exceptions.DuplicateBidException;
import seedu.address.model.id.BidderId;

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
        if (contains(toAdd)) {
            throw new DuplicateBidException();
        }
        internalBidList.add(toAdd);
    }

    /**
     * Removes the bid with the corresponding bidder id.
     *
     * @param bidderId The bidder id specified.
     */
    public void removeByBidderId(BidderId bidderId) {
        internalBidList.removeIf(bid -> bid.getBidderId().equals(bidderId));
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

    /**
     * Removes the equivalent bid from the list.
     * The bid must exist in the list.
     */
    public void remove(Bid toRemove) {
        requireNonNull(toRemove);
        if (!internalBidList.remove(toRemove)) {
            throw new BidNotFoundException();
        }
    }


    /**
     * replaces a bid at the specific index in the list
     *
     * @param target bid to be replaced
     * @param editedBid bid to replace target
     * @throws DuplicateBidException if editedBid already exists in the bid list.
     * @throws BidNotFoundException if the target bid does not exist in the bid list.
     */
    public void setBid(Bid target, Bid editedBid) throws DuplicateBidException, BidNotFoundException {
        requireAllNonNull(target, editedBid);

        int index = internalBidList.indexOf(target);
        if (index == -1) {
            throw new BidNotFoundException();
        }

        if (!target.isSameBid(editedBid) && contains(editedBid)) {
            throw new DuplicateBidException();
        }

        internalBidList.set(index, editedBid);
    }

    /**
     * replaces the entire list with a new list
     * @param replacement bid list to replace the current list
     */
    public void setBids(UniqueBidList replacement) {
        requireNonNull(replacement);
        internalBidList.setAll(replacement.internalBidList);
    }

    /**
     * replaces the entire list with a new list
     * @param bids bid list to replace the current list
     */
    public void setBids(List<Bid> bids) {
        requireAllNonNull(bids);
        if (!bidsAreUnique(bids)) {
            throw new DuplicateBidException();
        }

        internalBidList.setAll(bids);
    }

    /**
     * provides an unmodifiable list that can only be observed
     * @return an observable list of type Bid
     */
    public ObservableList<Bid> asUnmodifiableObservableBidList() {
        return internalUnmodifiableBidList;
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean bidsAreUnique(List<Bid> bids) {
        for (int i = 0; i < bids.size() - 1; i++) {
            for (int j = i + 1; j < bids.size(); j++) {
                if (bids.get(i).isSameBid(bids.get(j))) {
                    return false;
                }
            }
        }
        return true;
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
