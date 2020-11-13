package seedu.bagel.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.bagel.commons.exceptions.DataConversionException;
import seedu.bagel.model.ReadOnlyBagel;
/**
 * Represents a storage for {@link seedu.bagel.model.Bagel}.
 */
public interface BagelStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getBagelFilePath();

    /**
     * Returns Bagel data as a {@link ReadOnlyBagel}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyBagel> readBagel() throws DataConversionException, IOException;

    /**
     * @see #getBagelFilePath()
     */
    Optional<ReadOnlyBagel> readBagel(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyBagel} to the storage.
     * @param bagel cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveBagel(ReadOnlyBagel bagel) throws IOException;

    /**
     * @see #saveBagel(ReadOnlyBagel)
     */
    void saveBagel(ReadOnlyBagel bagel, Path filePath) throws IOException;

}
