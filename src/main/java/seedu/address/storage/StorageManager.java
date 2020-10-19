package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyModuleList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ModuleStorage moduleStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ModuleStorage moduleStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.moduleStorage = moduleStorage;
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
    public Path getModuleFilePath() {
        return moduleStorage.getModuleFilePath();
    }

    @Override
    public Optional<ReadOnlyModuleList> readModuleList() throws DataConversionException, IOException {
        return readModuleList(moduleStorage.getModuleFilePath());
    }

    @Override
    public Optional<ReadOnlyModuleList> readModuleList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return moduleStorage.readModuleList(filePath);
    }

    @Override
    public void saveModuleList(ReadOnlyModuleList moduleList) throws IOException {
        saveModuleList(moduleList, moduleStorage.getModuleFilePath());
    }

    @Override
    public void saveModuleList(ReadOnlyModuleList moduleList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        moduleStorage.saveModuleList(moduleList, filePath);
    }

}
