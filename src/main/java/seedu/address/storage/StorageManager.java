package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.ReadOnlyWishfulShrinking;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of WishfulShrinking data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private WishfulShrinkingStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code WishfulShrinkingStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(WishfulShrinkingStorage addressBookStorage, UserPrefsStorage userPrefsStorage) {
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


    // ================ WishfulShrinking methods ==============================

    @Override
    public Path getWishfulShrinkingFilePath() {
        return addressBookStorage.getWishfulShrinkingFilePath();
    }

    @Override
    public Optional<ReadOnlyWishfulShrinking> readWishfulShrinking() throws DataConversionException, IOException {
        return readWishfulShrinking(addressBookStorage.getWishfulShrinkingFilePath());
    }

    @Override
    public Optional<ReadOnlyWishfulShrinking> readWishfulShrinking(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readWishfulShrinking(filePath);
    }

    @Override
    public void saveWishfulShrinking(ReadOnlyWishfulShrinking addressBook) throws IOException {
        saveWishfulShrinking(addressBook, addressBookStorage.getWishfulShrinkingFilePath());
    }

    @Override
    public void saveWishfulShrinking(ReadOnlyWishfulShrinking addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveWishfulShrinking(addressBook, filePath);
    }

}
