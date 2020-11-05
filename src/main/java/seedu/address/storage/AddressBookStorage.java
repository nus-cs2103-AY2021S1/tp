package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.FitNus;
import seedu.address.model.ReadOnlyFitNus;

/**
 * Represents a storage for {@link FitNus}.
 */
public interface AddressBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAddressBookFilePath();

    /**
     * Returns FitNus data as a {@link ReadOnlyFitNus}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyFitNus> readFitNus() throws DataConversionException, IOException;

    /**
     * @see #getAddressBookFilePath()
     */
    Optional<ReadOnlyFitNus> readFitNus(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyFitNus} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveFitNus(ReadOnlyFitNus addressBook) throws IOException;

    /**
     * @see #saveFitNus(ReadOnlyFitNus)
     */
    void saveFitNus(ReadOnlyFitNus addressBook, Path filePath) throws IOException;

}
