package seedu.homerce.storage.revenue;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.homerce.commons.exceptions.DataConversionException;
import seedu.homerce.model.manager.ReadOnlyRevenueTracker;

/**
 * Represents a storage for {@link seedu.homerce.model.manager.RevenueTracker}.
 */
public interface RevenueStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getRevenueTrackerStorageFilePath();

    /**
     * Returns RevenueTracker data as a {@link seedu.homerce.model.manager.ReadOnlyRevenueTracker}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyRevenueTracker> readRevenueTracker() throws DataConversionException, IOException;

    /**
     * @see #readRevenueTracker()
     */
    Optional<ReadOnlyRevenueTracker> readRevenueTracker(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyRevenueTracker} to the storage.
     *
     * @param revenueTracker cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveRevenueTracker(ReadOnlyRevenueTracker revenueTracker) throws IOException;

    /**
     * @see #saveRevenueTracker(ReadOnlyRevenueTracker)
     */
    void saveRevenueTracker(ReadOnlyRevenueTracker revenueTracker, Path filePath) throws IOException;
}
