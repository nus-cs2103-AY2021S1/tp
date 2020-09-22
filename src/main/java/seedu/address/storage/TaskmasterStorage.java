package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTaskmaster;
import seedu.address.model.Taskmaster;

/**
 * Represents a storage for {@link Taskmaster}.
 */
public interface TaskmasterStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getTaskmasterFilePath();

    /**
     * Returns Taskmaster data as a {@link ReadOnlyTaskmaster}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTaskmaster> readTaskmaster() throws DataConversionException, IOException;

    /**
     * @see #getTaskmasterFilePath()
     */
    Optional<ReadOnlyTaskmaster> readTaskmaster(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyTaskmaster} to the storage.
     * @param taskmaster cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTaskmaster(ReadOnlyTaskmaster taskmaster) throws IOException;

    /**
     * @see #saveTaskmaster(ReadOnlyTaskmaster)
     */
    void saveTaskmaster(ReadOnlyTaskmaster taskmaster, Path filePath) throws IOException;

}
