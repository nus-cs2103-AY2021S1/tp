package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTaskmaster;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of Taskmaster data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private TaskmasterStorage taskmasterStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code TaskmasterStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(TaskmasterStorage taskmasterStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.taskmasterStorage = taskmasterStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ Taskmaster methods ==============================

    @Override
    public Path getTaskmasterFilePath() {
        return taskmasterStorage.getTaskmasterFilePath();
    }

    @Override
    public Optional<ReadOnlyTaskmaster> readTaskmaster() throws DataConversionException, IOException {
        return readTaskmaster(taskmasterStorage.getTaskmasterFilePath());
    }

    @Override
    public Optional<ReadOnlyTaskmaster> readTaskmaster(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return taskmasterStorage.readTaskmaster(filePath);
    }

    @Override
    public void saveTaskmaster(ReadOnlyTaskmaster taskmaster) throws IOException {
        saveTaskmaster(taskmaster, taskmasterStorage.getTaskmasterFilePath());
    }

    @Override
    public void saveTaskmaster(ReadOnlyTaskmaster taskmaster, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        taskmasterStorage.saveTaskmaster(taskmaster, filePath);
    }

}
