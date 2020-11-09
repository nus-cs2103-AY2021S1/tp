package seedu.fma.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.fma.commons.core.LogsCenter;
import seedu.fma.commons.exceptions.DataConversionException;
import seedu.fma.model.ReadOnlyLogBook;
import seedu.fma.model.ReadOnlyUserPrefs;
import seedu.fma.model.UserPrefs;

/**
 * Manages storage of LogBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private final LogBookStorage logBookStorage;
    private final UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code LogBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(LogBookStorage logBookStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.logBookStorage = logBookStorage;
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


    // ================ LogBook methods ==============================

    @Override
    public Path getLogBookFilePath() {
        return logBookStorage.getLogBookFilePath();
    }

    @Override
    public Optional<ReadOnlyLogBook> readLogBook() throws DataConversionException, IOException {
        return readLogBook(logBookStorage.getLogBookFilePath());
    }

    @Override
    public Optional<ReadOnlyLogBook> readLogBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return logBookStorage.readLogBook(filePath);
    }

    @Override
    public void saveLogBook(ReadOnlyLogBook logBook) throws IOException {
        saveLogBook(logBook, logBookStorage.getLogBookFilePath());
    }

    @Override
    public void saveLogBook(ReadOnlyLogBook logBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        logBookStorage.saveLogBook(logBook, filePath);
    }

}
