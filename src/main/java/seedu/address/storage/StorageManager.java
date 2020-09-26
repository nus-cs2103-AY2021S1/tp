package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyBugList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of BugList data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private BugListStorage bugListStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code BugListStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(BugListStorage bugListStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.bugListStorage = bugListStorage;
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


    // ================ BugList methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return bugListStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyBugList> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(bugListStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyBugList> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return bugListStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyBugList addressBook) throws IOException {
        saveAddressBook(addressBook, bugListStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyBugList addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        bugListStorage.saveAddressBook(addressBook, filePath);
    }

}
