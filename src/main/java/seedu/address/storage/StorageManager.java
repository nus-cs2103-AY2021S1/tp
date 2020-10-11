package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyBidBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.bidderaddressbook.ReadOnlyBidderAddressBook;
import seedu.address.model.selleraddressbook.ReadOnlySellerAddressBook;
import seedu.address.storage.bidderstorage.BidderAddressBookStorage;
import seedu.address.storage.sellerstorage.SellerAddressBookStorage;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private BidderAddressBookStorage bidderAddressBookStorage;
    private SellerAddressBookStorage sellerAddressBookStorage;
    private UserPrefsStorage userPrefsStorage;
    private BidBookStorage bidBookStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage},
     * {@code BidderAddressBookStorage}, {@code SellerAddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(AddressBookStorage addressBookStorage,
                          UserPrefsStorage userPrefsStorage,
                          BidBookStorage bidBookStorage,
                          BidderAddressBookStorage bidderAddressBookStorage,
                          SellerAddressBookStorage sellerAddressBookStorage) {
        super();
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.bidBookStorage = bidBookStorage;
        this.bidderAddressBookStorage = bidderAddressBookStorage;
        this.sellerAddressBookStorage = sellerAddressBookStorage;
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


    // ================ BidBook methods ==============================

    @Override
    public Path getBidBookFilePath() {
        return bidBookStorage.getBidBookFilePath();
    }

    @Override
    public Optional<ReadOnlyBidBook> readBidBook() throws DataConversionException, IOException {
        return readBidBook(bidBookStorage.getBidBookFilePath());
    }

    @Override
    public Optional<ReadOnlyBidBook> readBidBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return bidBookStorage.readBidBook(filePath);
    }

    @Override
    public void saveBidBook(ReadOnlyBidBook bidBook) throws IOException {
        saveBidBook(bidBook, bidBookStorage.getBidBookFilePath());
    }

    @Override
    public void saveBidBook(ReadOnlyBidBook bidBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        bidBookStorage.saveBidBook(bidBook, filePath);
    }
    
    // ================ BidderAddressBook methods ==============================

    @Override
    public Path getBidderAddressBookFilePath() {
        return bidderAddressBookStorage.getBidderAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyBidderAddressBook> readBidderAddressBook() throws DataConversionException, IOException {
        return readBidderAddressBook(bidderAddressBookStorage.getBidderAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyBidderAddressBook> readBidderAddressBook(Path filePath) throws
    DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return bidderAddressBookStorage.readBidderAddressBook(filePath);
    }

    @Override
    public void saveBidderAddressBook(ReadOnlyBidderAddressBook bidderAddressBook) throws IOException {
        logger.info("-----------[TRIPLE CHECK][" + bidderAddressBookStorage.getBidderAddressBookFilePath() + "]");
        saveBidderAddressBook(bidderAddressBook, bidderAddressBookStorage.getBidderAddressBookFilePath());
    }

    @Override
    public void saveBidderAddressBook(ReadOnlyBidderAddressBook bidderAddressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        bidderAddressBookStorage.saveBidderAddressBook(bidderAddressBook, filePath);
    }

    // ================ SellerAddressBook methods ==============================

    @Override
    public Path getSellerAddressBookFilePath() {
        return sellerAddressBookStorage.getSellerAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlySellerAddressBook> readSellerAddressBook() throws DataConversionException, IOException {
        return readSellerAddressBook(sellerAddressBookStorage.getSellerAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlySellerAddressBook> readSellerAddressBook(Path filePath) throws
    DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return sellerAddressBookStorage.readSellerAddressBook(filePath);
    }

    @Override
    public void saveSellerAddressBook(ReadOnlySellerAddressBook sellerAddressBook) throws IOException {
        saveSellerAddressBook(sellerAddressBook, sellerAddressBookStorage.getSellerAddressBookFilePath());
    }

    @Override
    public void saveSellerAddressBook(ReadOnlySellerAddressBook sellerAddressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        sellerAddressBookStorage.saveSellerAddressBook(sellerAddressBook, filePath);
    }
}
