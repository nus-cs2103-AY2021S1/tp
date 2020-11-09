package seedu.taskmaster.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.taskmaster.commons.exceptions.DataConversionException;
import seedu.taskmaster.model.ReadOnlyTaskmaster;
import seedu.taskmaster.model.Taskmaster;
import seedu.taskmaster.model.session.SessionList;

/**
 * Represents a storage for {@link Taskmaster}.
 */
public interface TaskmasterStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getTaskmasterFilePath();

    /**
     * Returns the file path of the SessionList data file.
     */
    Path getSessionListFilePath();

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
     * @throws IOException if there is a problem writing to the file.
     */
    void saveTaskmaster(ReadOnlyTaskmaster taskmaster) throws IOException;

    /**
     * @see #saveTaskmaster(ReadOnlyTaskmaster)
     */
    void saveTaskmaster(ReadOnlyTaskmaster taskmaster, Path filePath) throws IOException;

    /**
     * Saves the SessionList of the Taskmaster to the storage.
     * @param taskmaster cannot be null.
     * @throws IOException if there is a problem writing to the file.
     */
    void saveSessionList(ReadOnlyTaskmaster taskmaster) throws IOException;

    /**
     * @see #saveTaskmaster(ReadOnlyTaskmaster)
     */
    void saveSessionList(ReadOnlyTaskmaster taskmaster, Path filePath) throws IOException;

    /**
     * Returns SessionList data as a {@link ReadOnlyTaskmaster}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<SessionList> readSessionList() throws DataConversionException, IOException;

    /**
     * @see #readSessionList()
     */
    Optional<SessionList> readSessionList(Path filePath) throws DataConversionException, IOException;
}
