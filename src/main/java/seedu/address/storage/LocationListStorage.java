package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyLocationList;

/**
 * Represents a storage for {@link seedu.address.model.LocationList}.
 */
public interface LocationListStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getLocationListFilePath();

    /**
     * Returns LocationList data as a {@link ReadOnlyLocationList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyLocationList> readLocationList() throws DataConversionException, IOException;

    /**
     * @see #getLocationListFilePath()
     */
    Optional<ReadOnlyLocationList> readLocationList(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyLocationList} to the storage.
     * @param locationList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveLocationList(ReadOnlyLocationList locationList) throws IOException;

    /**
     * @see #saveLocationList(ReadOnlyLocationList)
     */
    void saveLocationList(ReadOnlyLocationList locationList, Path filePath) throws IOException;

}
