package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.bidderaddressbook.ReadOnlyBidderAddressBook;
import seedu.address.model.selleraddressbook.ReadOnlySellerAddressBook;
import seedu.address.storage.bidderstorage.BidderAddressBookStorage;
import seedu.address.storage.sellerstorage.SellerAddressBookStorage;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, UserPrefsStorage,
        SellerAddressBookStorage, BidderAddressBookStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;

    // BIDDER METHODS
    @Override
    Path getBidderAddressBookFilePath();

    @Override
    Optional<ReadOnlyBidderAddressBook> readBidderAddressBook() throws DataConversionException, IOException;

    @Override
    void saveBidderAddressBook(ReadOnlyBidderAddressBook bidderAddressBook) throws IOException;

    // SELLER METHODS
    @Override
    Path getSellerAddressBookFilePath();

    @Override
    Optional<ReadOnlySellerAddressBook> readSellerAddressBook() throws DataConversionException, IOException;

    @Override
    void saveSellerAddressBook(ReadOnlySellerAddressBook sellerAddressBook) throws IOException;

}
