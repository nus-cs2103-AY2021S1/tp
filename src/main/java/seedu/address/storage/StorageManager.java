package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyJobAddressBook;
import seedu.address.model.ReadOnlyPersonAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of PersonAddressBook and JobAddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private PersonAddressBookStorage personAddressBookStorage;
    private JobAddressBookStorage jobAddressBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code PersonAddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(PersonAddressBookStorage personAddressBookStorage,
                          JobAddressBookStorage jobAddressBookStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.personAddressBookStorage = personAddressBookStorage;
        this.jobAddressBookStorage = jobAddressBookStorage;
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


    // ================ PersonAddressBook methods ==============================

    @Override
    public Path getPersonAddressBookFilePath() {
        return personAddressBookStorage.getPersonAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyPersonAddressBook> readPersonAddressBook() throws DataConversionException, IOException {
        return readPersonAddressBook(personAddressBookStorage.getPersonAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyPersonAddressBook> readPersonAddressBook(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read person data from file: " + filePath);
        return personAddressBookStorage.readPersonAddressBook(filePath);
    }

    @Override
    public void savePersonAddressBook(ReadOnlyPersonAddressBook personAddressBook) throws IOException {
        savePersonAddressBook(personAddressBook, personAddressBookStorage.getPersonAddressBookFilePath());
    }

    @Override
    public void savePersonAddressBook(ReadOnlyPersonAddressBook personAddressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to person data file: " + filePath);
        personAddressBookStorage.savePersonAddressBook(personAddressBook, filePath);
    }

    // ================ JobAddressBook methods ==============================

    @Override
    public Path getJobAddressBookFilePath() {
        return jobAddressBookStorage.getJobAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyJobAddressBook> readJobAddressBook() throws DataConversionException, IOException {
        return readJobAddressBook(jobAddressBookStorage.getJobAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyJobAddressBook> readJobAddressBook(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read job data from file: " + filePath);
        return jobAddressBookStorage.readJobAddressBook(filePath);
    }

    @Override
    public void saveJobAddressBook(ReadOnlyJobAddressBook jobAddressBook) throws IOException {
        saveJobAddressBook(jobAddressBook, jobAddressBookStorage.getJobAddressBookFilePath());
    }

    @Override
    public void saveJobAddressBook(ReadOnlyJobAddressBook jobAddressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to job data file: " + filePath);
        jobAddressBookStorage.saveJobAddressBook(jobAddressBook, filePath);
    }

}
