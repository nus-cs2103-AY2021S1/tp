package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTbmManager;
import seedu.address.model.TbmManager;

/**
 * Represents a storage for {@link TbmManager}.
 */
public interface TbmManagerStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getTbmManagerFilePath();

    /**
     * Returns TbmManager data as a {@link ReadOnlyTbmManager}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTbmManager> readTbmManager() throws DataConversionException, IOException;

    /**
     * @see #getTbmManagerFilePath()
     */
    Optional<ReadOnlyTbmManager> readTbmManager(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyTbmManager} to the storage.
     * @param tbmManager cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTbmManager(ReadOnlyTbmManager tbmManager) throws IOException;

    /**
     * @see #saveTbmManager(ReadOnlyTbmManager)
     */
    void saveTbmManager(ReadOnlyTbmManager tbmManager, Path filePath) throws IOException;

}
