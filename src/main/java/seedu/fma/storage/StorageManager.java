package seedu.fma.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.fma.commons.core.LogsCenter;
import seedu.fma.commons.exceptions.DataConversionException;
import seedu.fma.model.ReadOnlyAddressBook;
import seedu.fma.model.ReadOnlyUserPrefs;
import seedu.fma.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private LogBookStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code LogBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(LogBookStorage addressBookStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.addressBookStorage = addressBookStorage;
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
    public Path getLogBookFilePath() {
        return addressBookStorage.getLogBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readLogBook() throws DataConversionException, IOException {
        return readLogBook(addressBookStorage.getLogBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readLogBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readLogBook(filePath);
    }

    @Override
    public void saveLogBook(ReadOnlyAddressBook logBook) throws IOException {
        saveLogBook(logBook, addressBookStorage.getLogBookFilePath());
    }

    @Override
    public void saveLogBook(ReadOnlyAddressBook logBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveLogBook(logBook, filePath);
    }

}
