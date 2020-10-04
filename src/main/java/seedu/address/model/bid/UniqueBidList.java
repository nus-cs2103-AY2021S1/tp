package seedu.address.model.bid;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.exceptions.DuplicatePersonException;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;


public class UniqueBidList implements Iterable<Bid> {

    private final ObservableList<Bid> internalBidList = FXCollections.observableArrayList();
    private final ObservableList<Bid> internalUnmodifiableBidList = FXCollections.unmodifiableObservableList(internalBidList);


    public void add(Bid toAdd) {
        requireNonNull(toAdd);

        internalBidList.add(toAdd);
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
