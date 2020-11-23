package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.HairStyleX;
import seedu.address.model.ReadOnlyHairStyleX;

/**
 * Represents a storage for {@link HairStyleX}.
 */
public interface HairStyleXStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getHairStyleXFilePath();

    /**
     * Returns HairStyleX data as a {@link ReadOnlyHairStyleX}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyHairStyleX> readHairStyleX() throws DataConversionException, IOException;

    /**
     * @see #getHairStyleXFilePath()
     */
    Optional<ReadOnlyHairStyleX> readHairStyleX(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyHairStyleX} to the storage.
     * @param hairStyleX cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveHairStyleX(ReadOnlyHairStyleX hairStyleX) throws IOException;

    /**
     * @see #saveHairStyleX(ReadOnlyHairStyleX)
     */
    void saveHairStyleX(ReadOnlyHairStyleX hairStyleX, Path filePath) throws IOException;

}
