package seedu.tr4cker.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.tr4cker.commons.core.LogsCenter;
import seedu.tr4cker.commons.exceptions.DataConversionException;
import seedu.tr4cker.model.ReadOnlyTr4cker;
import seedu.tr4cker.model.ReadOnlyUserPrefs;
import seedu.tr4cker.model.UserPrefs;

/**
 * Manages storage of Tr4cker data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private Tr4cker tr4cker;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code Tr4cker} and {@code UserPrefStorage}.
     */
    public StorageManager(Tr4cker tr4cker, UserPrefsStorage userPrefsStorage) {
        super();
        this.tr4cker = tr4cker;
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


    // ================ Tr4cker methods ==============================

    @Override
    public Path getTr4ckerFilePath() {
        return tr4cker.getTr4ckerFilePath();
    }

    @Override
    public Optional<ReadOnlyTr4cker> readTr4cker() throws DataConversionException, IOException {
        return readTr4cker(tr4cker.getTr4ckerFilePath());
    }

    @Override
    public Optional<ReadOnlyTr4cker> readTr4cker(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return tr4cker.readTr4cker(filePath);
    }

    @Override
    public void saveTr4cker(ReadOnlyTr4cker tr4cker) throws IOException {
        saveTr4cker(tr4cker, this.tr4cker.getTr4ckerFilePath());
    }

    @Override
    public void saveTr4cker(ReadOnlyTr4cker tr4cker, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        this.tr4cker.saveTr4cker(tr4cker, filePath); // JsonTr4cker saves model.Tr4cker.
        // todo save module list
    }

}
