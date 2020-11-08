package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyProductiveNus;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of ProductiveNus data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ProductiveNusStorage productiveNusStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ProductiveNusStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ProductiveNusStorage productiveNusStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.productiveNusStorage = productiveNusStorage;
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


    // ================ ProductiveNus methods ==============================

    @Override
    public Path getProductiveNusFilePath() {
        return productiveNusStorage.getProductiveNusFilePath();
    }

    @Override
    public Optional<ReadOnlyProductiveNus> readProductiveNus() throws DataConversionException, IOException {
        return readProductiveNus(productiveNusStorage.getProductiveNusFilePath());
    }

    @Override
    public Optional<ReadOnlyProductiveNus> readProductiveNus(Path filePath) throws DataConversionException,
            IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return productiveNusStorage.readProductiveNus(filePath);
    }

    @Override
    public void saveProductiveNus(ReadOnlyProductiveNus productiveNus) throws IOException {
        saveProductiveNus(productiveNus, productiveNusStorage.getProductiveNusFilePath());
    }

    @Override
    public void saveProductiveNus(ReadOnlyProductiveNus productiveNus, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        productiveNusStorage.saveProductiveNus(productiveNus, filePath);
    }

}
