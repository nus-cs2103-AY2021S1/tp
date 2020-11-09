package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyExerciseBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ExerciseBookStorage exerciseBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ExerciseBookStorage exerciseBookStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.exerciseBookStorage = exerciseBookStorage;
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


    // ================ ExerciseBook methods ==============================

    @Override
    public Path getExerciseBookFilePath() {
        return exerciseBookStorage.getExerciseBookFilePath();
    }

    @Override
    public Optional<ReadOnlyExerciseBook> readExerciseBook() throws DataConversionException, IOException {
        return readExerciseBook(exerciseBookStorage.getExerciseBookFilePath());
    }

    @Override
    public Optional<ReadOnlyExerciseBook> readExerciseBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return exerciseBookStorage.readExerciseBook(filePath);
    }

    @Override
    public void saveExerciseBook(ReadOnlyExerciseBook exerciseBook) throws IOException {
        saveExerciseBook(exerciseBook, exerciseBookStorage.getExerciseBookFilePath());
    }

    @Override
    public void saveExerciseBook(ReadOnlyExerciseBook exerciseBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        exerciseBookStorage.saveExerciseBook(exerciseBook, filePath);
    }

}
