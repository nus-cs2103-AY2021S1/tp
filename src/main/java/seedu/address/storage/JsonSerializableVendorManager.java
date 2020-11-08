package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.vendor.ReadOnlyVendorManager;
import seedu.address.model.vendor.Vendor;
import seedu.address.model.vendor.VendorManager;


/**
 * An Immutable VendorManager that is serializable to JSON format.
 */
@JsonRootName(value = "vendorManager")
class JsonSerializableVendorManager {

    public static final String MESSAGE_DUPLICATE_VENDOR = "Vendors list contains duplicate vendor(s).";

    private final List<JsonAdaptedVendor> vendors = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableVendorManager} with the given vendors.
     */
    @JsonCreator
    public JsonSerializableVendorManager(@JsonProperty("vendors") List<JsonAdaptedVendor> vendors) {
        this.vendors.addAll(vendors);
    }

    /**
     * Converts a given {@code ReadOnlyVendorManager} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableVendorManager}.
     */
    public JsonSerializableVendorManager(ReadOnlyVendorManager source) {
        vendors.addAll(source.getVendorList().stream().map(JsonAdaptedVendor::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code VendorManager} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public VendorManager toModelType() throws IllegalValueException {
        VendorManager vendorManager = new VendorManager();
        for (JsonAdaptedVendor jsonAdaptedVendor : vendors) {
            Vendor vendor = jsonAdaptedVendor.toModelType();
            if (vendorManager.hasVendor(vendor)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_VENDOR);
            }
            vendorManager.addVendor(vendor);
        }
        return vendorManager;
    }

}
