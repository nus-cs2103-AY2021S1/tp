package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyNusave;

/**
 * Represents a storage for {@link seedu.address.model.Nusave}.
 */
public interface NusaveStorage {

    /**
     * Returns the file path of the {@link seedu.address.model.Nusave} data file.
     * @return the {@code Path} containing the Nusave data file.
     */
    Path getNusaveFilePath();

    /**
     * Returns the Nusave data from storage.
     * @return {@code Optional.empty()} if the storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyNusave> readNusave() throws DataConversionException, IOException;

    /**
     * Returns the Nusave data from storage at the specified file path.
     * @param filePath the {@code Path} containing the Nusave data file.
     * @return {@code Optional.empty()} if the storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyNusave> readNusave(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyNusave} to the storage.
     * @param nusave cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveNusave(ReadOnlyNusave nusave) throws IOException;

    /**
     * Saves the given {@link ReadOnlyNusave} to the storage specified by the filePath.
     * @param nusave cannot be null.
     * @param filePath the {@code Path} containing the Nusave data file.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveNusave(ReadOnlyNusave nusave, Path filePath) throws IOException;
}
