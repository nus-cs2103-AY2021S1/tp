package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyReeve;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.ReadOnlyEvent;
import seedu.address.storage.schedule.ScheduleStorage;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ReeveStorage reeveStorage;
    private UserPrefsStorage userPrefsStorage;
    private ScheduleStorage scheduleStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ReeveStorage reeveStorage,
                          UserPrefsStorage userPrefsStorage, ScheduleStorage scheduleStorage) {
        super();
        this.reeveStorage = reeveStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.scheduleStorage = scheduleStorage;
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
    public Path getAddressBookFilePath() {
        return reeveStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyReeve> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(reeveStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyReeve> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return reeveStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyReeve addressBook) throws IOException {
        saveAddressBook(addressBook, reeveStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyReeve addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        reeveStorage.saveAddressBook(addressBook, filePath);
    }

    // ================ Schedule methods ==============================

    @Override
    public Path getScheduleFilePath() {
        return scheduleStorage.getScheduleFilePath();
    }

    @Override
    public Optional<ReadOnlyEvent> readSchedule() throws DataConversionException, IOException {
        return readSchedule(scheduleStorage.getScheduleFilePath());
    }

    @Override
    public Optional<ReadOnlyEvent> readSchedule(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return scheduleStorage.readSchedule(filePath);
    }

    @Override
    public void saveSchedule(ReadOnlyEvent schedule) throws IOException {
        saveSchedule(schedule, scheduleStorage.getScheduleFilePath());
    }

    @Override
    public void saveSchedule(ReadOnlyEvent schedule, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        scheduleStorage.saveSchedule(schedule, filePath);
    }
}
