package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.Planus;
import seedu.address.model.ReadOnlyPlanus;

/**
 * Represents a storage for {@link Planus}.
 */
public interface PlanusStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getPlanusFilePath();

    /**
     * Returns Planus data as a {@link ReadOnlyPlanus}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyPlanus> readPlanus() throws DataConversionException, IOException;

    /**
     * @see #getPlanusFilePath()
     */
    Optional<ReadOnlyPlanus> readPlanus(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyPlanus} to the storage.
     * @param planus cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void savePlanus(ReadOnlyPlanus planus) throws IOException;

    /**
     * @see #savePlanus(ReadOnlyPlanus)
     */
    void savePlanus(ReadOnlyPlanus planus, Path filePath) throws IOException;

}
