package seedu.address.testutil;

import seedu.address.model.vendor.Vendor;
import seedu.address.model.vendor.VendorManager;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code VendorManager ab = new VendorManagerBuilder().withVendor("John", "Doe").build();}
 */
public class VendorManagerBuilder {

    private VendorManager vendorManager;

    public VendorManagerBuilder() {
        vendorManager = new VendorManager();
    }

    /**
     * Adds a new {@code Vendor} to the {@code VendorManager} that we are building.
     */
    public VendorManagerBuilder withVendor(Vendor vendor) {
        vendorManager.addVendor(vendor);
        return this;
    }

    public VendorManager build() {
        return vendorManager;
    }
}
