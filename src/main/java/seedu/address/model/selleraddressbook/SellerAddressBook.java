package seedu.address.model.selleraddressbook;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.id.SellerId;
import seedu.address.model.person.seller.Seller;
import seedu.address.model.person.seller.UniqueSellerList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class SellerAddressBook implements ReadOnlySellerAddressBook {

    private final UniqueSellerList sellers;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        sellers = new UniqueSellerList();
    }

    public SellerAddressBook() {}

    /**
     * Creates an SellerAddressBook using the Sellers in the {@code toBeCopied}
     */
    public SellerAddressBook(ReadOnlySellerAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations
    /**
     * Replaces the contents of the bidders list with {@code bidders}.
     * {@code bidders} must not contain duplicate persons.
     */
    public void setSellers(List<Seller> bidders) {
        this.sellers.setSellers(bidders);
    }

    /**
     * Resets the existing data of this {@code SellerAddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlySellerAddressBook newData) {
        requireNonNull(newData);
        setSellers(newData.getSellerList());
    }

    //// person-level operations

    /**
     * Returns true if a Seller with the same identity as {@code Seller} exists in the address book.
     */
    public boolean hasSeller(Seller seller) {
        requireNonNull(seller);
        return sellers.contains(seller);
    }

    /**
     * Adds a bidder to the address book.
     * The person must not already exist in the address book.
     */
    public void addSeller(Seller p) {
        sellers.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setSeller(Seller target, Seller editedSeller) {
        requireNonNull(editedSeller);
        sellers.setSeller(target, editedSeller);
    }

    /**
     * Checks if this {@code SellerAddressBook} contains a seller with the given {@code id}.
     *
     * @param sellerId The given id.
     * @return True if a bidder with the given id exists in the list.
     */
    public boolean containsSellerId(SellerId sellerId) {
        return sellers.containsSellerId(sellerId);
    }


    /**
     * Removes {@code key} from this {@code SellerAddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeSeller(Seller key) {
        sellers.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return sellers.asUnmodifiableObservableList().size() + " sellers";
    }

    @Override
    public ObservableList<Seller> getSellerList() {
        return sellers.asUnmodifiableObservableList();
    }

    public boolean hasSellerExceptSellerId(Seller editedSeller, SellerId sellerId) {
        requireAllNonNull(editedSeller, sellerId);
        return sellers.containsExceptSellerId(editedSeller, sellerId);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SellerAddressBook // instanceof handles nulls
                && sellers.equals(((SellerAddressBook) other).sellers));
    }

}
