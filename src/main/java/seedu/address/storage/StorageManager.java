package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyGradeBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.GoalTarget;

/**
 * Manages storage of GradeBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private GradeBookStorage gradeBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code GradeBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(GradeBookStorage gradeBookStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.gradeBookStorage = gradeBookStorage;
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


    // ================ GradeBook methods ==============================

    @Override
    public Path getGradeBookFilePath() {
        return gradeBookStorage.getGradeBookFilePath();
    }

    @Override
    public GoalTarget getGoalTarget() throws DataConversionException {
        return gradeBookStorage.getGoalTarget();
    }

    @Override
    public Optional<ReadOnlyGradeBook> readGradeBook() throws DataConversionException, IOException {
        return readGradeBook(gradeBookStorage.getGradeBookFilePath());
    }

    @Override
    public Optional<ReadOnlyGradeBook> readGradeBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return gradeBookStorage.readGradeBook(filePath);
    }

    @Override
    public void saveGradeBook(ReadOnlyGradeBook gradeBook, GoalTarget goalTarget) throws IOException {
        saveGradeBook(gradeBook, gradeBookStorage.getGradeBookFilePath(), goalTarget);
    }

    @Override
    public void saveGradeBook(ReadOnlyGradeBook gradeBook, Path filePath, GoalTarget goalTarget)
            throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        gradeBookStorage.saveGradeBook(gradeBook, filePath, goalTarget);
    }

}
