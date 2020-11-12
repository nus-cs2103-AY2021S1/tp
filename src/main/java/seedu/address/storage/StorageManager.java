package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyPlanus;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of Planus data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private PlanusStorage planusStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code PlanusStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(PlanusStorage planusStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.planusStorage = planusStorage;
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


    // ================ Planus methods ==============================

    @Override
    public Path getPlanusFilePath() {
        return planusStorage.getPlanusFilePath();
    }

    @Override
    public Optional<ReadOnlyPlanus> readPlanus() throws DataConversionException, IOException {
        return readPlanus(planusStorage.getPlanusFilePath());
    }

    @Override
    public Optional<ReadOnlyPlanus> readPlanus(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return planusStorage.readPlanus(filePath);
    }

    @Override
    public void savePlanus(ReadOnlyPlanus planus) throws IOException {
        savePlanus(planus, planusStorage.getPlanusFilePath());
    }

    @Override
    public void savePlanus(ReadOnlyPlanus planus, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        planusStorage.savePlanus(planus, filePath);
    }

}
