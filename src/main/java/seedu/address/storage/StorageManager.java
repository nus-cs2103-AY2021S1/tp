package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyProjact;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of Projact data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ProjactStorage projactStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ProjactStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ProjactStorage projactStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.projactStorage = projactStorage;
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


    // ================ Projact methods ==============================

    @Override
    public Path getProjactFilePath() {
        return projactStorage.getProjactFilePath();
    }

    @Override
    public Optional<ReadOnlyProjact> readProjact() throws DataConversionException, IOException {
        return readProjact(projactStorage.getProjactFilePath());
    }

    @Override
    public Optional<ReadOnlyProjact> readProjact(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return projactStorage.readProjact(filePath);
    }

    @Override
    public void saveProjact(ReadOnlyProjact projact) throws IOException {
        saveProjact(projact, projactStorage.getProjactFilePath());
    }

    @Override
    public void saveProjact(ReadOnlyProjact projact, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        projactStorage.saveProjact(projact, filePath);
    }

}
