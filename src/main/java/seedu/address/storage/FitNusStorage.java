package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyFitNus;

/**
 * Represents a storage for {@link seedu.address.model.FitNus}.
 */
public interface FitNusStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getFitNusFilePath();

    /**
     * Returns FitNus data as a {@link ReadOnlyFitNus}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyFitNus> readFitNus() throws DataConversionException, IOException;

    /**
     * @see #getFitNusFilePath()
     */
    Optional<ReadOnlyFitNus> readFitNus(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyFitNus} to the storage.
     * @param fitNus cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveFitNus(ReadOnlyFitNus fitNus) throws IOException;

    /**
     * @see #saveFitNus(ReadOnlyFitNus)
     */
    void saveFitNus(ReadOnlyFitNus fitNus, Path filePath) throws IOException;

}
