package seedu.pivot.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.pivot.commons.exceptions.DataConversionException;
import seedu.pivot.model.Pivot;
import seedu.pivot.model.ReadOnlyPivot;

/**
 * Represents a storage for {@link Pivot}.
 */
public interface PivotStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getPivotFilePath();

    /**
     * Returns PIVOT data as a {@link ReadOnlyPivot}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyPivot> readPivot() throws DataConversionException, IOException;

    /**
     * @see #getPivotFilePath()
     */
    Optional<ReadOnlyPivot> readPivot(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyPivot} to the storage.
     * @param pivot cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void savePivot(ReadOnlyPivot pivot) throws IOException;

    /**
     * @see #savePivot(ReadOnlyPivot)
     */
    void savePivot(ReadOnlyPivot pivot, Path filePath) throws IOException;

}
