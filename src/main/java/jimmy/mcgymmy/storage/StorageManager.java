package jimmy.mcgymmy.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import jimmy.mcgymmy.commons.core.LogsCenter;
import jimmy.mcgymmy.commons.exceptions.DataConversionException;
import jimmy.mcgymmy.model.ReadOnlyMcGymmy;
import jimmy.mcgymmy.model.ReadOnlyUserPrefs;
import jimmy.mcgymmy.model.UserPrefs;

/**
 * Manages storage of McGymmy data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private McGymmyStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code McGymmyStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(McGymmyStorage addressBookStorage, UserPrefsStorage userPrefsStorage) {
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


    // ================ McGymmy methods ==============================

    @Override
    public Path getMcGymmyFilePath() {
        return addressBookStorage.getMcGymmyFilePath();
    }

    @Override
    public Optional<ReadOnlyMcGymmy> readMcGymmy() throws DataConversionException, IOException {
        return readMcGymmy(addressBookStorage.getMcGymmyFilePath());
    }

    @Override
    public Optional<ReadOnlyMcGymmy> readMcGymmy(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readMcGymmy(filePath);
    }

    @Override
    public void saveMcGymmy(ReadOnlyMcGymmy addressBook) throws IOException {
        saveMcGymmy(addressBook, addressBookStorage.getMcGymmyFilePath());
    }

    @Override
    public void saveMcGymmy(ReadOnlyMcGymmy addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveMcGymmy(addressBook, filePath);
    }

}
