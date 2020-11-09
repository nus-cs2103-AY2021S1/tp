package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.Projact;
import seedu.address.model.ReadOnlyProjact;

/**
 * Represents a storage for {@link Projact}.
 */
public interface ProjactStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getProjactFilePath();

    /**
     * Returns Projact data as a {@link ReadOnlyProjact}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyProjact> readProjact() throws DataConversionException, IOException;

    /**
     * @see #getProjactFilePath()
     */
    Optional<ReadOnlyProjact> readProjact(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyProjact} to the storage.
     * @param projact cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveProjact(ReadOnlyProjact projact) throws IOException;

    /**
     * @see #saveProjact(ReadOnlyProjact)
     */
    void saveProjact(ReadOnlyProjact projact, Path filePath) throws IOException;

}
