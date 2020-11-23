package seedu.tr4cker.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.tr4cker.commons.exceptions.DataConversionException;
import seedu.tr4cker.model.ReadOnlyTr4cker;

/**
 * Represents a storage for {@link seedu.tr4cker.model.Tr4cker}.
 */
public interface Tr4cker {

    /**
     * Returns the file path of the data file.
     */
    Path getTr4ckerFilePath();

    /**
     * Returns Tr4cker data as a {@link ReadOnlyTr4cker}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTr4cker> readTr4cker() throws DataConversionException, IOException;

    /**
     * @see #getTr4ckerFilePath()
     */
    Optional<ReadOnlyTr4cker> readTr4cker(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyTr4cker} to the storage.
     * @param tr4cker cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTr4cker(ReadOnlyTr4cker tr4cker) throws IOException;

    /**
     * @see #saveTr4cker(ReadOnlyTr4cker)
     */
    void saveTr4cker(ReadOnlyTr4cker tr4cker, Path filePath) throws IOException;

}
