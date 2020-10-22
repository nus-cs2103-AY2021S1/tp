package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.vendor.UniqueVendorList;
import seedu.address.model.vendor.Vendor;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueVendorList vendors;
    private int vendorIndex;

    /*
    * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
    * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
    *
    * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
    *   among constructors.
    */ {
        vendors = new UniqueVendorList();

    }

    /**
     * Default vendor index is 0. Assumes that there is at least 1 vendor in the AddressBook.
     */
    public AddressBook() {
        this.vendorIndex = 0;
    }

    /**
     * Creates an AddressBook using the Vendors in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Sets the vendorIndex of the AddressBook to {@code vendorIndex}. This method is only called when
     * the selectVendor method is called.
     */
    private AddressBook(List<Vendor> vendors, int vendorIndex) {
        setVendors(vendors);
        this.vendorIndex = vendorIndex;
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the vendor list with {@code vendors}.
     * {@code vendors} must not contain duplicate vendors.
     */

    public void setVendors(List<Vendor> vendors) {
        this.vendors.setVendors(vendors);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);
        setVendors(newData.getVendorList());
        selectVendor(newData.getVendorIndex());
    }

    //// vendor-level operations

    /**
     * Returns true if a vendor with the same identity as {@code vendor} exists in the address book.
     */
    public boolean hasVendor(Vendor vendor) {
        requireNonNull(vendor);
        return vendors.contains(vendor);
    }

    /**
     * Adds a vendor to the address book.
     * The vendor must not already exist in the address book.
     */
    public void addVendor(Vendor p) {
        vendors.add(p);
    }

    /**
     * Replaces the given vendor {@code target} in the list with {@code editedVendor}.
     * {@code target} must exist in the address book.
     * The vendor identity of {@code editedVendor} must not be the same as another existing vendor in the address book.
     */
    public void setVendor(Vendor target, Vendor editedVendor) {
        requireNonNull(editedVendor);

        vendors.setVendor(target, editedVendor);
    }

    public void selectVendor(int vendorIndex) {
        this.vendorIndex = vendorIndex;
    }

    @Override
    public int getVendorIndex() {
        return this.vendorIndex;
    }

    public Vendor getSelectedVendor() {
        return this.vendors.asUnmodifiableObservableList().get(vendorIndex);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeVendor(Vendor key) {
        vendors.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return vendors.asUnmodifiableObservableList().size() + " vendors";
        // TODO: refine later
    }

    @Override
    public ObservableList<Vendor> getVendorList() {
        return vendors.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && vendors.equals(((AddressBook) other).vendors))
                && this.getVendorIndex() == ((AddressBook) other).getVendorIndex();
    }

    @Override
    public int hashCode() {
        return vendors.hashCode();
    }


}
