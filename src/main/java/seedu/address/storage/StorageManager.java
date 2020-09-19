package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyMainCatalogue;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of MainCatalogue data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private MainCatalogueStorage mainCatalogueStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code MainCatalogueStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(MainCatalogueStorage mainCatalogueStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.mainCatalogueStorage = mainCatalogueStorage;
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


    // ================ MainCatalogue methods ==============================

    @Override
    public Path getMainCatalogueFilePath() {
        return mainCatalogueStorage.getMainCatalogueFilePath();
    }

    @Override
    public Optional<ReadOnlyMainCatalogue> readMainCatalogue() throws DataConversionException, IOException {
        return readMainCatalogue(mainCatalogueStorage.getMainCatalogueFilePath());
    }

    @Override
    public Optional<ReadOnlyMainCatalogue> readMainCatalogue(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return mainCatalogueStorage.readMainCatalogue(filePath);
    }

    @Override
    public void saveMainCatalogue(ReadOnlyMainCatalogue mainCatalogue) throws IOException {
        saveMainCatalogue(mainCatalogue, mainCatalogueStorage.getMainCatalogueFilePath());
    }

    @Override
    public void saveMainCatalogue(ReadOnlyMainCatalogue mainCatalogue, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        mainCatalogueStorage.saveMainCatalogue(mainCatalogue, filePath);
    }

}
