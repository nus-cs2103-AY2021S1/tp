package seedu.cc.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.cc.commons.exceptions.DataConversionException;
import seedu.cc.model.ReadOnlyCommonCents;

/**
 * Represents a storage for {@link seedu.cc.model.CommonCents}.
 */
public interface CommonCentsStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getCommonCentsFilePath();

    /**
     * Returns CommonCents data as a {@link ReadOnlyCommonCents}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyCommonCents> readCommonCents() throws DataConversionException, IOException;

    /**
     * @see #getCommonCentsFilePath()
     */
    Optional<ReadOnlyCommonCents> readCommonCents(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyCommonCents} to the storage.
     * @param commonCents cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveCommonCents(ReadOnlyCommonCents commonCents) throws IOException;

    /**
     * @see #saveCommonCents(ReadOnlyCommonCents)
     */
    void saveCommonCents(ReadOnlyCommonCents commonCents, Path filePath) throws IOException;

}
