package seedu.homerce.storage.client;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.homerce.commons.exceptions.DataConversionException;
import seedu.homerce.model.manager.ClientManager;
import seedu.homerce.model.manager.ReadOnlyClientManager;

/**
 * Represents a storage for {@link ClientManager}.
 */
public interface ClientStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getClientManagerFilePath();

    /**
     * Returns ClientManager data as a {@link ReadOnlyClientManager}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyClientManager> readClientManager() throws DataConversionException, IOException;

    /**
     * @see #getClientManagerFilePath()
     */
    Optional<ReadOnlyClientManager> readClientManager(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyClientManager} to the storage.
     * @param clientManager cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveClientManager(ReadOnlyClientManager clientManager) throws IOException;

    /**
     * @see #saveClientManager(ReadOnlyClientManager)
     */
    void saveClientManager(ReadOnlyClientManager clientManager, Path filePath) throws IOException;

}
