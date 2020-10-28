package seedu.pivot.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.pivot.commons.core.LogsCenter;
import seedu.pivot.commons.exceptions.DataConversionException;
import seedu.pivot.model.ReadOnlyPivot;
import seedu.pivot.model.ReadOnlyUserPrefs;
import seedu.pivot.model.UserPrefs;

/**
 * Manages storage of PIVOT data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private PivotStorage pivotStorage;
    private UserPrefsStorage userPrefsStorage;
    private ReferenceStorage referenceStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code PivotStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(PivotStorage pivotStorage, UserPrefsStorage userPrefsStorage,
                          ReferenceStorage referenceStorage) throws IOException {
        super();
        this.pivotStorage = pivotStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.referenceStorage = referenceStorage;
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


    // ================ PIVOT methods ==============================

    @Override
    public Path getPivotFilePath() {
        return pivotStorage.getPivotFilePath();
    }

    @Override
    public Optional<ReadOnlyPivot> readPivot() throws DataConversionException, IOException {
        return readPivot(pivotStorage.getPivotFilePath());
    }

    @Override
    public Optional<ReadOnlyPivot> readPivot(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return pivotStorage.readPivot(filePath);
    }

    @Override
    public void savePivot(ReadOnlyPivot pivot) throws IOException {
        savePivot(pivot, pivotStorage.getPivotFilePath());
    }

    @Override
    public void savePivot(ReadOnlyPivot pivot, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        pivotStorage.savePivot(pivot, filePath);
    }

    // ================ ReferenceStorage methods ==============================
    @Override
    public void addReferenceTestFile() throws IOException {
        referenceStorage.addTestFile();
    }

}
