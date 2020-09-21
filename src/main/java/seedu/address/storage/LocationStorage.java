package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyLocationList;

/**
 * Represents a storage for {@link seedu.address.model.LocationList}.
 */
public interface LocationStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getLocationFilePath();

    /**
     * Returns Location data as a {@link ReadOnlyLocationList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyLocationList> readLocation() throws DataConversionException, IOException;

    /**
     * @see #getLocationFilePath()
     */
    Optional<ReadOnlyLocationList> readLocation(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyLocationList} to the storage.
     * @param locationList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveLocation(ReadOnlyLocationList locationList) throws IOException;

    /**
     * @see #saveLocation(ReadOnlyLocationList)
     */
    void saveLocation(ReadOnlyLocationList locationList, Path filePath) throws IOException;

}
