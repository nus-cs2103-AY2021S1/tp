package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyClientList;

/**
 * Represents a storage for {@link seedu.address.model.ClientList}.
 */
public interface ClientListStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getClientListFilePath();

    /**
     * Returns ClientList data as a {@link ReadOnlyClientList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyClientList> readClientList() throws DataConversionException, IOException;

    /**
     * @see #getClientListFilePath()
     */
    Optional<ReadOnlyClientList> readClientList(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyClientList} to the storage.
     * @param clientList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveClientList(ReadOnlyClientList clientList) throws IOException;

    /**
     * @see #saveClientList(ReadOnlyClientList)
     */
    void saveClientList(ReadOnlyClientList clientList, Path filePath) throws IOException;

}
