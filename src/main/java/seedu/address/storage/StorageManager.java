package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyCommonCents;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private CommonCentsStorage commonCentsStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(CommonCentsStorage commonCentsStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.commonCentsStorage = commonCentsStorage;
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


    // ================ AddressBook methods ==============================

    @Override
    public Path getCommonCentsFilePath() {
        return commonCentsStorage.getCommonCentsFilePath();
    }

    @Override
    public Optional<ReadOnlyCommonCents> readCommonCents() throws DataConversionException, IOException {
        return readCommonCents(commonCentsStorage.getCommonCentsFilePath());
    }

    @Override
    public Optional<ReadOnlyCommonCents> readCommonCents(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return commonCentsStorage.readCommonCents(filePath);
    }

    @Override
    public void saveCommonCents(ReadOnlyCommonCents commonCents) throws IOException {
        saveCommonCents(commonCents, commonCentsStorage.getCommonCentsFilePath());
    }

    @Override
    public void saveCommonCents(ReadOnlyCommonCents addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        commonCentsStorage.saveCommonCents(addressBook, filePath);
    }

}
