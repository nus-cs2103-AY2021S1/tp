package seedu.resireg.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.resireg.commons.core.LogsCenter;
import seedu.resireg.commons.exceptions.DataConversionException;
import seedu.resireg.model.ReadOnlyResiReg;
import seedu.resireg.model.ReadOnlyUserPrefs;
import seedu.resireg.model.UserPrefs;

/**
 * Manages storage of ResiReg data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ResiRegStorage resiRegStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ResiRegStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ResiRegStorage resiRegStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.resiRegStorage = resiRegStorage;
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


    // ================ ResiReg methods ==============================

    @Override
    public Path getResiRegFilePath() {
        return resiRegStorage.getResiRegFilePath();
    }

    @Override
    public Optional<ReadOnlyResiReg> readResiReg() throws DataConversionException, IOException {
        return readResiReg(resiRegStorage.getResiRegFilePath());
    }

    @Override
    public Optional<ReadOnlyResiReg> readResiReg(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return resiRegStorage.readResiReg(filePath);
    }

    @Override
    public void saveResiReg(ReadOnlyResiReg resiReg) throws IOException {
        saveResiReg(resiReg, resiRegStorage.getResiRegFilePath());
    }

    @Override
    public void saveResiReg(ReadOnlyResiReg resiReg, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        resiRegStorage.saveResiReg(resiReg, filePath);
    }

}
