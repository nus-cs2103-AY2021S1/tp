package seedu.address.model.person.seller;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.id.SellerId.DEFAULT_SELLER_ID;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.id.Id;
import seedu.address.model.id.SellerId;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A person is considered unique by comparing using {@code Seller#isSamePerson(Person)}. As such, adding and updating of
 * persons uses Seller#isSamePerson(Person) for equality so as to ensure that the person being added or updated is
 * unique in terms of identity in the UniqueSellerList. However, the removal of a person uses Seller#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Seller#isSameSeller(Seller)
 */
public class UniqueSellerList implements Iterable<Seller> {

    protected final ObservableList<Seller> internalList = FXCollections.observableArrayList();
    protected final ObservableList<Seller> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Seller toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameSeller);
    }

    /**
     * Adds a seller to the list.
     * The person must not already exist in the list.
     */
    public void add(Seller toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        if (toAdd.getId().equals(DEFAULT_SELLER_ID)) {
            toAdd.setId(getLatestId().increment());
        }
        internalList.add(toAdd);
    }

    public SellerId getLatestId() {
        if (internalList.size() == 0) {
            return new SellerId(0);
        }
        return (SellerId) this.internalList.get(internalList.size() - 1).getId();
    }

    /**
     * Replaces the contents of this list with {@code sellers}.
     * {@code sellers} must not contain duplicate bidders.
     */
    public void setSellers(List<Seller> sellers) {
        requireAllNonNull(sellers);
        if (!sellersAreUnique(sellers)) {
            throw new DuplicatePersonException();
        }

        internalList.setAll(sellers);
    }

    public void setSellers(UniqueSellerList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedSeller}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedSeller} must not be the same as another existing person in the list.
     */
    public void setSeller(Seller target, Seller editedSeller) {
        requireAllNonNull(target, editedSeller);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        if (!target.isSameSeller(editedSeller) && contains(editedSeller)) {
            throw new DuplicatePersonException();
        }

        internalList.set(index, editedSeller);
    }

    /**
     * Removes the equivalent person from the list.
     * The person must exist in the list.
     */
    public void remove(Seller toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new PersonNotFoundException();
        }
    }

    /**
     * Checks if the seller list contains a seller with the given id.
     *
     * @param id The specified id.
     * @return True if the seller list contains the bidder with the given id.
     */
    public boolean containsSellerId(Id id) {
        requireNonNull(id);
        return internalList.filtered(seller -> seller.getId().equals(id))
                .size() > 0;
    }


    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Seller> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Seller> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueSellerList // instanceof handles nulls
                && internalList.equals(((UniqueSellerList) other).internalList));
    }

    /**
     * Returns true if {@code persons} contains only unique persons.
     */
    private boolean sellersAreUnique(List<Seller> sellers) {
        for (int i = 0; i < sellers.size() - 1; i++) {
            for (int j = i + 1; j < sellers.size(); j++) {
                if (sellers.get(i).isSameSeller(sellers.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns true if the list contains a seller that is equivalent to the seller.
     */
    public boolean containsExceptSellerId(Seller inCheck, SellerId excludedId) {
        requireAllNonNull(inCheck, excludedId);
        return internalList.stream().filter(seller -> !(seller.getId().equals(excludedId)))
                .anyMatch(inCheck::isSameSeller);
    }
}
