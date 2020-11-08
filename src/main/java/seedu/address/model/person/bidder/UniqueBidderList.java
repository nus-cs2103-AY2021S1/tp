package seedu.address.model.person.bidder;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.id.BidderId.DEFAULT_BIDDER_ID;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.id.BidderId;
import seedu.address.model.id.Id;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Person#isSamePerson(Person)}. As such, adding and updating of
 * persons uses Person#isSamePerson(Person) for equality so as to ensure that the person being added or updated is
 * unique in terms of identity in the UniquePersonList. However, the removal of a person uses Person#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Bidder#isSameBidder(Bidder)
 */
public class UniqueBidderList implements Iterable<Bidder> {

    protected final ObservableList<Bidder> internalList = FXCollections.observableArrayList();
    protected final ObservableList<Bidder> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Bidder toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameBidder);
    }

    /**
     * Adds a person to the list.
     * The person must not already exist in the list.
     */
    public void add(Bidder toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        if (toAdd.getId().equals(DEFAULT_BIDDER_ID)) {
            toAdd.setId(getLatestId().increment());
        }
        internalList.add(toAdd);
    }

    public BidderId getLatestId() {
        if (internalList.size() == 0) {
            return new BidderId(0);
        }
        return (BidderId) this.internalList.get(internalList.size() - 1).getId();
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedBidder}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the list.
     */
    public void setBidder(Bidder target, Bidder editedBidder) {
        requireAllNonNull(target, editedBidder);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSameBidder(editedBidder) && contains(editedBidder)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedBidder);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(Bidder toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    public void setBidders(UniqueBidderList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code bidders}.
     * {@code bidders} must not contain duplicate bidders.
     */
    public void setBidders(List<Bidder> bidders) {
        requireAllNonNull(bidders);
        if (!biddersAreUnique(bidders)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(bidders);
    }

    /**
     * Checks if the bidder list contains a bidder with the given id.
     *
     * @param id The specified id.
     * @return True if the bidder list contains the bidder with the given id.
     */
    public boolean containsBidderId(Id id) {
        requireNonNull(id);
        return internalList.filtered(bidder -> bidder.getId().equals(id))
                .size() > 0;
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Bidder> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Bidder> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueBidderList // instanceof handles nulls
                && internalList.equals(((UniqueBidderList) other).internalList));
    }

    /**
     * Returns true if {@code bidders} contains only unique persons.
     */
    private boolean biddersAreUnique(List<Bidder> bidders) {
        for (int i = 0; i < bidders.size() - 1; i++) {
            for (int j = i + 1; j < bidders.size(); j++) {
                if (bidders.get(i).isSameBidder(bidders.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns true if the list contains a bidder that is equivalent to the bidder.
     */
    public boolean containsExceptBidderId(Bidder inCheck, BidderId excludedId) {
        requireAllNonNull(inCheck, excludedId);
        return internalList.stream().filter(bidder -> !(bidder.getId().equals(excludedId)))
                .anyMatch(inCheck::isSameBidder);
    }
}
