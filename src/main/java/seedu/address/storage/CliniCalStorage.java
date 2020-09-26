package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.CliniCal;
import seedu.address.model.ReadOnlyCliniCal;

/**
 * Represents a storage for {@link CliniCal}.
 */
public interface CliniCalStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getCliniCalFilePath();

    /**
     * Returns CliniCal data as a {@link ReadOnlyCliniCal}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyCliniCal> readCliniCal() throws DataConversionException, IOException;

    /**
     * @see #getCliniCalFilePath()
     */
    Optional<ReadOnlyCliniCal> readCliniCal(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyCliniCal} to the storage.
     * @param cliniCal cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveCliniCal(ReadOnlyCliniCal cliniCal) throws IOException;

    /**
     * @see #saveCliniCal(ReadOnlyCliniCal)
     */
    void saveCliniCal(ReadOnlyCliniCal cliniCal, Path filePath) throws IOException;

}
