package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.vendor.ReadOnlyVendorManager;
import seedu.address.model.vendor.VendorManager;

/**
 * Represents a storage for {@link VendorManager}.
 */
public interface VendorManagerStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getVendorManagerFilePath();

    /**
     * Returns VendorManager data as a {@link ReadOnlyVendorManager}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyVendorManager> readVendorManager() throws DataConversionException, IOException;

    /**
     * @see #getVendorManagerFilePath()
     */
    Optional<ReadOnlyVendorManager> readVendorManager(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyVendorManager} to the storage.
     * @param vendorManager cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveVendorManager(ReadOnlyVendorManager vendorManager) throws IOException;

    /**
     * @see #saveVendorManager(ReadOnlyVendorManager)
     */
    void saveVendorManager(ReadOnlyVendorManager vendorManager, Path filePath) throws IOException;

}
