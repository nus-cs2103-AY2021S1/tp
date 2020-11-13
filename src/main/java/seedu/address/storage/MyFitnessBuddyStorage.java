package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.MyFitnessBuddy;
import seedu.address.model.ReadOnlyMyFitnessBuddy;

/**
 * Represents a storage for {@link MyFitnessBuddy}.
 */
public interface MyFitnessBuddyStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getMyFitnessBuddyFilePath();

    /**
     * Returns MyFitnessBuddy data as a {@link ReadOnlyMyFitnessBuddy}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyMyFitnessBuddy> readFitnessBuddy() throws DataConversionException, IOException;

    /**
     * @see #getMyFitnessBuddyFilePath()
     */
    Optional<ReadOnlyMyFitnessBuddy> readFitnessBuddy(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyMyFitnessBuddy} to the storage.
     * @param myFitnessBuddy cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveFitnessBuddy(ReadOnlyMyFitnessBuddy myFitnessBuddy) throws IOException;

    /**
     * @see #saveFitnessBuddy(ReadOnlyMyFitnessBuddy)
     */
    void saveFitnessBuddy(ReadOnlyMyFitnessBuddy myFitnessBuddy, Path filePath) throws IOException;

}
