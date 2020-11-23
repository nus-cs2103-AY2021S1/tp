package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyHairStyleX;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of HairStyleX data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private HairStyleXStorage hairStyleXStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code HairStyleXStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(HairStyleXStorage hairStyleXStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.hairStyleXStorage = hairStyleXStorage;
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


    // ================ HairStyleX methods ==============================

    @Override
    public Path getHairStyleXFilePath() {
        return hairStyleXStorage.getHairStyleXFilePath();
    }

    @Override
    public Optional<ReadOnlyHairStyleX> readHairStyleX() throws DataConversionException, IOException {
        return readHairStyleX(hairStyleXStorage.getHairStyleXFilePath());
    }

    @Override
    public Optional<ReadOnlyHairStyleX> readHairStyleX(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return hairStyleXStorage.readHairStyleX(filePath);
    }

    @Override
    public void saveHairStyleX(ReadOnlyHairStyleX hairStyleX) throws IOException {
        saveHairStyleX(hairStyleX, hairStyleXStorage.getHairStyleXFilePath());
    }

    @Override
    public void saveHairStyleX(ReadOnlyHairStyleX hairStyleX, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        hairStyleXStorage.saveHairStyleX(hairStyleX, filePath);
    }

}
