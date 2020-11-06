package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyProductiveNus;

/**
 * Represents a storage for {@link seedu.address.model.ProductiveNus}.
 */
public interface ProductiveNusStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getProductiveNusFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyProductiveNus}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyProductiveNus> readProductiveNus() throws DataConversionException, IOException;

    /**
     * @see #getProductiveNusFilePath()
     */
    Optional<ReadOnlyProductiveNus> readProductiveNus(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyProductiveNus} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveProductiveNus(ReadOnlyProductiveNus addressBook) throws IOException;

    /**
     * @see #saveProductiveNus(ReadOnlyProductiveNus)
     */
    void saveProductiveNus(ReadOnlyProductiveNus addressBook, Path filePath) throws IOException;

}
