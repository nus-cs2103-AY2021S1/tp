package seedu.address.model.bidderaddressbook;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.id.BidderId;
import seedu.address.model.person.bidder.Bidder;
import seedu.address.model.person.bidder.UniqueBidderList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class BidderAddressBook implements ReadOnlyBidderAddressBook {

    private final UniqueBidderList bidders;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        bidders = new UniqueBidderList();
    }

    public BidderAddressBook() {}

    /**
     * Creates an BidderAddressBook using the Bidders in the {@code toBeCopied}
     */
    public BidderAddressBook(ReadOnlyBidderAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations
    /**
     * Replaces the contents of the bidders list with {@code bidders}.
     * {@code bidders} must not contain duplicate persons.
     */
    public void setBidders(List<Bidder> bidders) {
        this.bidders.setBidders(bidders);
    }

    /**
     * Resets the existing data of this {@code BidderAddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyBidderAddressBook newData) {
        requireNonNull(newData);
        setBidders(newData.getBidderList());
    }

    //// person-level operations

    /**
     * Returns true if a Bidder with the same identity as {@code Bidder} exists in the address book.
     */
    public boolean hasBidder(Bidder bidder) {
        requireNonNull(bidder);
        return bidders.contains(bidder);
    }

    /**
     * Adds a bidder to the address book.
     * The person must not already exist in the address book.
     */
    public void addBidder(Bidder p) {
        bidders.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setBidder(Bidder target, Bidder editedBidder) {
        requireNonNull(editedBidder);
        bidders.setBidder(target, editedBidder);
    }

    /**
     * Checks if this {@code BidderAddressBook} contains a bidder with the given {@code id}.
     *
     * @param bidderId The given id.
     * @return True if a bidder with the given id exists in the list.
     */
    public boolean containsBidderId(BidderId bidderId) {
        return bidders.containsBidderId(bidderId);
    }

    /**
     * Removes {@code key} from this {@code BidderAddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeBidder(Bidder key) {
        bidders.remove(key);
    }

    public boolean hasBidderExceptBidderId(Bidder editedBidder, BidderId bidderId) {
        requireAllNonNull(editedBidder, bidderId);
        return bidders.containsExceptBidderId(editedBidder, bidderId);
    }

    //// util methods

    @Override
    public String toString() {
        return bidders.asUnmodifiableObservableList().size() + " bidders";
    }

    @Override
    public ObservableList<Bidder> getBidderList() {
        return bidders.asUnmodifiableObservableList();
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BidderAddressBook // instanceof handles nulls
                && bidders.equals(((BidderAddressBook) other).bidders));
    }

    @Override
    public int hashCode() {
        return bidders.hashCode();
    }
}
