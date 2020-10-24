package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyMeetingBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.bidbook.ReadOnlyBidBook;
import seedu.address.model.bidderaddressbook.ReadOnlyBidderAddressBook;
import seedu.address.model.propertybook.ReadOnlyPropertyBook;
import seedu.address.model.selleraddressbook.ReadOnlySellerAddressBook;
import seedu.address.storage.bidderstorage.BidderAddressBookStorage;
import seedu.address.storage.bidstorage.BidBookStorage;
import seedu.address.storage.meeting.MeetingBookStorage;
import seedu.address.storage.property.PropertyBookStorage;
import seedu.address.storage.sellerstorage.SellerAddressBookStorage;

/**
 * API of the Storage component
 */

public interface Storage extends SellerAddressBookStorage, BidderAddressBookStorage,
        AddressBookStorage, UserPrefsStorage, BidBookStorage, MeetingBookStorage,
        PropertyBookStorage {

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

    // ===================== BID =======================
    @Override
    Path getBidBookFilePath();

    @Override
    Optional<ReadOnlyBidBook> readBidBook() throws DataConversionException, IOException;

    @Override
    void saveBidBook(ReadOnlyBidBook bidBook) throws IOException;

    // ===================== BIDDER =======================
    @Override
    Path getBidderAddressBookFilePath();

    @Override
    Optional<ReadOnlyBidderAddressBook> readBidderAddressBook() throws DataConversionException, IOException;

    @Override
    void saveBidderAddressBook(ReadOnlyBidderAddressBook bidderAddressBook) throws IOException;

    // ===================== SELLER =======================
    @Override
    Path getSellerAddressBookFilePath();

    @Override
    Optional<ReadOnlySellerAddressBook> readSellerAddressBook() throws DataConversionException, IOException;

    @Override
    void saveSellerAddressBook(ReadOnlySellerAddressBook sellerAddressBook) throws IOException;

    // ===================== PROPERTY =======================

    @Override
    Path getPropertyBookFilePath();

    @Override
    Optional<ReadOnlyPropertyBook> readPropertyBook() throws DataConversionException, IOException;

    @Override
    void savePropertyBook(ReadOnlyPropertyBook propertyBook) throws IOException;

    // ===================== MEETING =======================
    @Override
    Path getMeetingBookFilePath();

    @Override
    Optional<ReadOnlyMeetingBook> readMeetingBook() throws DataConversionException, IOException;

    @Override
    void saveMeetingBook(ReadOnlyMeetingBook meetingBook) throws IOException;
}
