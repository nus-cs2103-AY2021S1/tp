package seedu.taskmaster.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.taskmaster.commons.core.LogsCenter;
import seedu.taskmaster.commons.exceptions.DataConversionException;
import seedu.taskmaster.model.ReadOnlyTaskmaster;
import seedu.taskmaster.model.ReadOnlyUserPrefs;
import seedu.taskmaster.model.UserPrefs;
import seedu.taskmaster.model.record.StudentRecord;

/**
 * Manages storage of Taskmaster data in local storage.
 */
public class StorageManager implements Storage {

    public static final String FILENAME_CONSTRAINTS =
            "Filenames should only be a string of alphanumeric characters with no spaces, and cannot be blank.";

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);

    private static final String VALIDATION_REGEX = "\\w*";

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

    @Override
    public void saveAttendance(ReadOnlyTaskmaster taskmaster, Path filePath) throws IOException {
        logger.fine("Attempting to save attendance to file: " + filePath);
        taskmasterStorage.saveAttendance(taskmaster, filePath);
    }

    @Override
    public Optional<List<StudentRecord>> readAttendance(Path filepath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to load attendance from file: " + filepath);
        return taskmasterStorage.readAttendance(filepath);
    }

    // ================ Util methods ==============================

    public boolean fileExists(Path filePath) {
        return Files.exists(filePath);
    }

    public static boolean isValidFilename(String filename) {
        return filename.matches(VALIDATION_REGEX);
    }
}
