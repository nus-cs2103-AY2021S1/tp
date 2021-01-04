package seedu.homerce.storage.service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.homerce.commons.exceptions.DataConversionException;
import seedu.homerce.model.manager.ReadOnlyServiceManager;
import seedu.homerce.model.manager.ServiceManager;

/**
 * Represents a storage for {@link ServiceManager}.
 */
public interface ServiceStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getServiceManagerStorageFilePath();

    /**
     * Returns ServiceManager data as a {@link ReadOnlyServiceManager}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyServiceManager> readServiceManager() throws DataConversionException, IOException;

    /**
     * @see #readServiceManager()
     */
    Optional<ReadOnlyServiceManager> readServiceManager(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyServiceManager} to the storage.
     *
     * @param serviceManager cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveServiceManager(ReadOnlyServiceManager serviceManager) throws IOException;

    /**
     * @see #saveServiceManager(ReadOnlyServiceManager)
     */
    void saveServiceManager(ReadOnlyServiceManager serviceManager, Path filePath) throws IOException;
}
