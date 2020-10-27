package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyHospifyBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of HospifyBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private HospifyStorage hospifyStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(HospifyStorage hospifyStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.hospifyStorage = hospifyStorage;
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


    // ================ HospifyBook methods ==============================

    @Override
    public Path getHospifyFilePath() {
        return hospifyStorage.getHospifyFilePath();
    }

    @Override
    public Optional<ReadOnlyHospifyBook> readHospifyBook() throws DataConversionException, IOException {
        return readHospifyBook(hospifyStorage.getHospifyFilePath());
    }

    @Override
    public Optional<ReadOnlyHospifyBook> readHospifyBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return hospifyStorage.readHospifyBook(filePath);
    }

    @Override
    public void saveHospifyBook(ReadOnlyHospifyBook hospifyBook) throws IOException {
        saveHospifyBook(hospifyBook, hospifyStorage.getHospifyFilePath());
    }

    @Override
    public void saveHospifyBook(ReadOnlyHospifyBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        hospifyStorage.saveHospifyBook(addressBook, filePath);
    }

}
