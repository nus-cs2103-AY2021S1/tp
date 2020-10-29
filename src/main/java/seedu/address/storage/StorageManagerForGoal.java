package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyGoalBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

public class StorageManagerForGoal implements StorageForGoal{
    
    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private GoalBookStorage goalBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code GoalManager} with the given {@code GoalBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManagerForGoal(GoalBookStorage goalBookStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.goalBookStorage = goalBookStorage;
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

    // ================ GoalBook methods ==============================
    
    @Override
    public Path getGoalBookFilePath() {
        return goalBookStorage.getGoalBookFilePath();
    }

    @Override
    public Optional<ReadOnlyGoalBook> readGoalBook() throws DataConversionException, IOException {
        return readGoalBook(goalBookStorage.getGoalBookFilePath());
    }
    
    @Override
    public Optional<ReadOnlyGoalBook> readGoalBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return goalBookStorage.readGoalBook(filePath);
    }

    @Override
    public void saveGoalBook(ReadOnlyGoalBook goalBook) throws IOException {
        saveGoalBook(goalBook, goalBookStorage.getGoalBookFilePath());
    }

    @Override
    public void saveGoalBook(ReadOnlyGoalBook goalBook, Path filePath) throws IOException {
        logger.fine("Attempting to write goal to data file: " + filePath);
        goalBookStorage.saveGoalBook(goalBook, filePath);
    }
}
