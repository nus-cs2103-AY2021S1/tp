package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyFitNus;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of fitNUS data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private FitNusStorage fitNusStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code FitNusStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(FitNusStorage fitNusStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.fitNusStorage = fitNusStorage;
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


    // ================ fitNUS methods ==============================

    @Override
    public Path getFitNusFilePath() {
        return fitNusStorage.getFitNusFilePath();
    }

    @Override
    public Optional<ReadOnlyFitNus> readFitNus() throws DataConversionException, IOException {
        return readFitNus(fitNusStorage.getFitNusFilePath());
    }

    @Override
    public Optional<ReadOnlyFitNus> readFitNus(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return fitNusStorage.readFitNus(filePath);
    }

    @Override
    public void saveFitNus(ReadOnlyFitNus fitNus) throws IOException {
        saveFitNus(fitNus, fitNusStorage.getFitNusFilePath());
    }

    @Override
    public void saveFitNus(ReadOnlyFitNus fitNus, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        fitNusStorage.saveFitNus(fitNus, filePath);
    }
}
