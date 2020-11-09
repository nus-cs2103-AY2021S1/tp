package ay2021s1_cs2103_w16_3.finesse.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import ay2021s1_cs2103_w16_3.finesse.commons.exceptions.DataConversionException;
import ay2021s1_cs2103_w16_3.finesse.model.FinanceTracker;
import ay2021s1_cs2103_w16_3.finesse.model.ReadOnlyFinanceTracker;

/**
 * Represents a storage for {@link FinanceTracker}.
 */
public interface FinanceTrackerStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getFinanceTrackerFilePath();

    /**
     * Returns FinanceTracker data as a {@link ReadOnlyFinanceTracker}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyFinanceTracker> readFinanceTracker() throws DataConversionException, IOException;

    /**
     * @see #getFinanceTrackerFilePath()
     */
    Optional<ReadOnlyFinanceTracker> readFinanceTracker(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyFinanceTracker} to the storage.
     * @param financeTracker cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveFinanceTracker(ReadOnlyFinanceTracker financeTracker) throws IOException;

    /**
     * @see #saveFinanceTracker(ReadOnlyFinanceTracker)
     */
    void saveFinanceTracker(ReadOnlyFinanceTracker financeTracker, Path filePath) throws IOException;

}
