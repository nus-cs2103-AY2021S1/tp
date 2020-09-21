package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyItemList;
import seedu.address.model.ReadOnlyLocationList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private ItemListStorage itemListStorage;
    private UserPrefsStorage userPrefsStorage;
    private LocationListStorage locationListStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(AddressBookStorage addressBookStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    /**
     * Creates a {@code StorageManager} with the given {@code ItemListStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ItemListStorage itemListStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.itemListStorage = itemListStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(LocationListStorage locationListStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.locationListStorage = locationListStorage;
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
    public Path getAddressBookFilePath() {
        return addressBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }

    @Override
    public Path getItemListFilePath() {
        return itemListStorage.getItemListFilePath();
    }

    @Override
    public Optional<ReadOnlyItemList> readItemList() throws DataConversionException, IOException {
        return readItemList(itemListStorage.getItemListFilePath());
    }

    @Override
    public Optional<ReadOnlyItemList> readItemList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return itemListStorage.readItemList(filePath);
    }

    @Override
    public void saveItemList(ReadOnlyItemList itemList) throws IOException {
        saveItemList(itemList, itemListStorage.getItemListFilePath());
    }

    @Override
    public void saveItemList(ReadOnlyItemList itemList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        itemListStorage.saveItemList(itemList, filePath);
    }

    @Override
    public Optional<ReadOnlyLocationList> readLocationList() {
        return Optional.empty();
    }

    @Override
    public void saveLocationList(ReadOnlyLocationList locationList) throws IOException {
        saveLocationList(locationList, locationListStorage.getLocationListFilePath());
    }

    @Override
    public void saveLocationList(ReadOnlyLocationList locationList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        locationListStorage.saveLocationList(locationList, filePath);
    }
}
