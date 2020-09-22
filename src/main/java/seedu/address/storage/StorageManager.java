package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyStockBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private StockBookStorage stockBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(StockBookStorage stockBookStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.stockBookStorage = stockBookStorage;
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
    public Path getStockBookFilePath() {
        return stockBookStorage.getStockBookFilePath();
    }

    @Override
    public Optional<ReadOnlyStockBook> readStockBook() throws DataConversionException, IOException {
        return readStockBook(stockBookStorage.getStockBookFilePath());
    }

    @Override
    public Optional<ReadOnlyStockBook> readStockBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return stockBookStorage.readStockBook(filePath);
    }

    @Override
    public void saveStockBook(ReadOnlyStockBook stockBook) throws IOException {
        saveStockBook(stockBook, stockBookStorage.getStockBookFilePath());
    }

    @Override
    public void saveStockBook(ReadOnlyStockBook StockBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        stockBookStorage.saveStockBook(StockBook, filePath);
    }

}
