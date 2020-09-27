package seedu.stock.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.stock.commons.core.LogsCenter;
import seedu.stock.commons.exceptions.DataConversionException;
import seedu.stock.model.*;

/**
 * Manages storage of StockBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private StockBookStorage stockBookStorage;
    private UserPrefsStorage userPrefsStorage;
    private SerialNumberSetsBookStorage serialNumberSetsBookStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code StockBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(StockBookStorage stockBookStorage, UserPrefsStorage userPrefsStorage,
                          SerialNumberSetsBookStorage serialNumberSetsBook) {
        super();
        this.stockBookStorage = stockBookStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.serialNumberSetsBookStorage = serialNumberSetsBook;
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


    // ================ StockBook methods ==============================

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
    public void saveStockBook(ReadOnlyStockBook stockBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        stockBookStorage.saveStockBook(stockBook, filePath);
    }

    // ================ SerialNumberSetsBook methods ==============================
    public Path getSerialNumberSetsBookFilePath() {
        return serialNumberSetsBookStorage.getSerialNumberSetsBookFilePath();
    }

    public Optional<ReadOnlySerialNumberSetsBook> readSerialNumberSetsBook() throws DataConversionException, IOException {
        return readSerialNumberSetsBook(serialNumberSetsBookStorage.getSerialNumberSetsBookFilePath());
    }

    public Optional<ReadOnlySerialNumberSetsBook> readSerialNumberSetsBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return serialNumberSetsBookStorage.readSerialNumberSetsBook(filePath);
    }

    public void saveSerialNumberSetsBook(ReadOnlySerialNumberSetsBook serialNumberSetsBook) throws IOException {
        saveSerialNumberSetsBook(serialNumberSetsBook, serialNumberSetsBookStorage.getSerialNumberSetsBookFilePath());
    }

    public void saveSerialNumberSetsBook(ReadOnlySerialNumberSetsBook serialNumberSetsBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        serialNumberSetsBookStorage.saveSerialNumberSetsBook(serialNumberSetsBook, filePath);
    }
}
