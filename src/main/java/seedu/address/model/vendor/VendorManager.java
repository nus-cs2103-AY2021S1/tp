package seedu.address.model.vendor;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;

/**
 * Wraps all data at the vendor manager level
 * Duplicates are not allowed (by .isSameVendor comparison)
 */
public class VendorManager implements ReadOnlyVendorManager {

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
     * Default vendor index is -1. Assumes that there is no vendor chosen yet.
     */
    public VendorManager() {
        this.vendorIndex = -1;
    }

    /**
     * Creates an VendorManager using the Vendors in the {@code toBeCopied}
     */
    public VendorManager(ReadOnlyVendorManager toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Sets the vendorIndex of the VendorManager to {@code vendorIndex}. This method is only called when
     * the selectVendor method is called.
     */
    private VendorManager(List<Vendor> vendors, int vendorIndex) {
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
     * Resets the existing data of this {@code VendorManager} with {@code newData}.
     */
    public void resetData(ReadOnlyVendorManager newData) {
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
     * Adds a vendor to the vendor manager.
     * The vendor must not already exist in the vendor manager.
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
     * Removes {@code key} from this {@code VendorManager}.
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
                || (other instanceof VendorManager // instanceof handles nulls
                && vendors.equals(((VendorManager) other).vendors))
                && this.getVendorIndex() == ((VendorManager) other).getVendorIndex();
    }

    @Override
    public int hashCode() {
        return vendors.hashCode();
    }


}
