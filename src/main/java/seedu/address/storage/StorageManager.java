package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyExpenseBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of ExpenseBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ExpenseBookStorage expenseBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ExpenseBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ExpenseBookStorage expenseBookStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.expenseBookStorage = expenseBookStorage;
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


    // ================ ExpenseBook methods ==============================

    @Override
    public Path getExpenseBookFilePath() {
        return expenseBookStorage.getExpenseBookFilePath();
    }

    @Override
    public Optional<ReadOnlyExpenseBook> readExpenseBook() throws DataConversionException, IOException {
        return readExpenseBook(expenseBookStorage.getExpenseBookFilePath());
    }

    @Override
    public Optional<ReadOnlyExpenseBook> readExpenseBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return expenseBookStorage.readExpenseBook(filePath);
    }

    @Override
    public void saveExpenseBook(ReadOnlyExpenseBook expenseBook) throws IOException {
        saveExpenseBook(expenseBook, expenseBookStorage.getExpenseBookFilePath());
    }

    @Override
    public void saveExpenseBook(ReadOnlyExpenseBook expenseBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        expenseBookStorage.saveExpenseBook(expenseBook, filePath);
    }

}
