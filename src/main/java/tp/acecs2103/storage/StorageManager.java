package tp.acecs2103.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import tp.acecs2103.commons.core.LogsCenter;
import tp.acecs2103.commons.exceptions.DataConversionException;
import tp.acecs2103.model.ReadOnlyUserPrefs;
import tp.acecs2103.model.TaskList;
import tp.acecs2103.model.UserPrefs;

/**
 * Manages storage of TaskList data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private TaskListStorage taskListStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code TaskListStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(TaskListStorage taskListStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.taskListStorage = taskListStorage;
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


    // ================ TaskList methods ==============================

    @Override
    public Path getTaskListFilePath() {
        return taskListStorage.getTaskListFilePath();
    }

    @Override
    public Optional<TaskList> readTaskList() throws DataConversionException, IOException {
        return readTaskList(taskListStorage.getTaskListFilePath());
    }

    @Override
    public Optional<TaskList> readTaskList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return taskListStorage.readTaskList(filePath);
    }

    @Override
    public void saveTaskList(TaskList taskList) throws IOException {
        saveTaskList(taskList, taskListStorage.getTaskListFilePath());
    }

    @Override
    public void saveTaskList(TaskList taskList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        taskListStorage.saveTaskList(taskList, filePath);
    }

}
