package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyIngredientBook;
import seedu.address.model.ReadOnlySalesBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private SalesBookStorage salesBookStorage;
    private UserPrefsStorage userPrefsStorage;
    private IngredientBookStorage ingredientBookStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(AddressBookStorage addressBookStorage, SalesBookStorage salesBookStorage,
                          UserPrefsStorage userPrefsStorage, IngredientBookStorage ingredientBookStorage) {
        super();
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.salesBookStorage = salesBookStorage;
        this.ingredientBookStorage = ingredientBookStorage;
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
    public Path getAddressBookFilePath() {
        return addressBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException, IOException {
        assert filePath.toString().length() > 0 : "filePath should not be empty.";
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        assert filePath.toString().length() > 0 : "filePath should not be empty.";
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }

    // ================ SalesBook methods ==============================

    @Override
    public Path getSalesBookFilePath() {
        return salesBookStorage.getSalesBookFilePath();
    }

    @Override
    public Optional<ReadOnlySalesBook> readSalesBook() throws DataConversionException, IOException {
        return readSalesBook(salesBookStorage.getSalesBookFilePath());
    }

    @Override
    public Optional<ReadOnlySalesBook> readSalesBook(Path filePath) throws DataConversionException,
            IOException {
        assert filePath.toString().length() > 0 : "filePath should not be empty.";
        logger.fine("Attempting to read data from file: " + filePath);
        return salesBookStorage.readSalesBook(filePath);
    }

    @Override
    public void saveSalesBook(ReadOnlySalesBook salesBook) throws IOException {
        saveSalesBook(salesBook, salesBookStorage.getSalesBookFilePath());
    }

    @Override
    public void saveSalesBook(ReadOnlySalesBook salesBook, Path filePath) throws IOException {
        assert filePath.toString().length() > 0 : "filePath should not be empty.";
        logger.fine("Attempting to write to data file: " + filePath);
        salesBookStorage.saveSalesBook(salesBook, filePath);
    }

    // ================ IngredientBook methods ==============================

    @Override
    public Path getIngredientBookFilePath() {
        return ingredientBookStorage.getIngredientBookFilePath();
    }

    @Override
    public Optional<ReadOnlyIngredientBook> readIngredientBook() throws DataConversionException, IOException {
        return readIngredientBook(ingredientBookStorage.getIngredientBookFilePath());
    }

    @Override
    public Optional<ReadOnlyIngredientBook> readIngredientBook(Path filePath) throws DataConversionException,
            IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return ingredientBookStorage.readIngredientBook(filePath);
    }

    @Override
    public void saveIngredientBook(ReadOnlyIngredientBook ingredientBook) throws IOException {
        saveIngredientBook(ingredientBook, ingredientBookStorage.getIngredientBookFilePath());
    }

    @Override
    public void saveIngredientBook(ReadOnlyIngredientBook ingredientBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        ingredientBookStorage.saveIngredientBook(ingredientBook, filePath);
    }

}
