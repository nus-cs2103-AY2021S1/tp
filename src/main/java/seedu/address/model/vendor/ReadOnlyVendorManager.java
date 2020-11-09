package seedu.address.model.vendor;

import javafx.collections.ObservableList;

/**
 * Unmodifiable view of a vendor manager
 */
public interface ReadOnlyVendorManager {

    /**
     * Returns an unmodifiable view of the vendors list.
     * This list will not contain any duplicate vendors.
     */
    ObservableList<Vendor> getVendorList();

    /**
     * Returns an unmodifiable view of the selected vendor index.
     */
    int getVendorIndex();

}
