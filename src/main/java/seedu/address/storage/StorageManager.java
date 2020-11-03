package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTrackr;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;

/**
 * Manages storage of Trackr data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ModuleListStorage moduleListStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given storages.
     */
    public StorageManager(ModuleListStorage moduleListStorage,
                          UserPrefsStorage userPrefsStorage) {
        super();
        this.moduleListStorage = moduleListStorage;
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

    // ================ Module methods ==============================

    @Override
    public Path getModuleFilePath() {
        return moduleListStorage.getModuleFilePath();
    }

    @Override
    public Optional<ReadOnlyTrackr<Module>> readModuleList() throws DataConversionException, IOException {
        return readModuleList(moduleListStorage.getModuleFilePath());
    }

    @Override
    public Optional<ReadOnlyTrackr<Module>> readModuleList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read module data from file: " + filePath);
        return moduleListStorage.readModuleList(filePath);
    }

    @Override
    public void saveModuleList(ReadOnlyTrackr<Module> moduleList) throws IOException {
        saveModuleList(moduleList, moduleListStorage.getModuleFilePath());
    }

    @Override
    public void saveModuleList(ReadOnlyTrackr<Module> moduleList, Path filePath) throws IOException {
        logger.fine("Attempting to write to module data file: " + filePath);
        moduleListStorage.saveModuleList(moduleList, filePath);
    }

}
