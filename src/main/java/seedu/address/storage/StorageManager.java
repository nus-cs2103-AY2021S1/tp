package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyMyFitnessBuddy;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private MyFitnessBuddyStorage myFitnessBuddyStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code MyFitnessBuddyStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(MyFitnessBuddyStorage myFitnessBuddyStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.myFitnessBuddyStorage = myFitnessBuddyStorage;
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


    // ================ My Fitness Buddy methods ==============================

    @Override
    public Path getMyFitnessBuddyFilePath() {
        return myFitnessBuddyStorage.getMyFitnessBuddyFilePath();
    }

    @Override
    public Optional<ReadOnlyMyFitnessBuddy> readFitnessBuddy() throws DataConversionException, IOException {
        return readFitnessBuddy(myFitnessBuddyStorage.getMyFitnessBuddyFilePath());
    }

    @Override
    public Optional<ReadOnlyMyFitnessBuddy> readFitnessBuddy(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return myFitnessBuddyStorage.readFitnessBuddy(filePath);
    }

    @Override
    public void saveFitnessBuddy(ReadOnlyMyFitnessBuddy myFitnessBuddy) throws IOException {
        saveFitnessBuddy(myFitnessBuddy, myFitnessBuddyStorage.getMyFitnessBuddyFilePath());
    }

    @Override
    public void saveFitnessBuddy(ReadOnlyMyFitnessBuddy myFitnessBuddy, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        myFitnessBuddyStorage.saveFitnessBuddy(myFitnessBuddy, filePath);
    }

}
