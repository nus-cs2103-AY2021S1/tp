package seedu.address.storage.bidderstorage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.bidderaddressbook.ReadOnlyBidderAddressBook;

/**
 * Represents a storage for {@link seedu.address.model.bidderaddressbook.BidderAddressBook}.
 */
public interface BidderAddressBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getBidderAddressBookFilePath();

    /**
     * Returns BidderAddressBook data as a {@link ReadOnlyBidderAddressBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyBidderAddressBook> readBidderAddressBook() throws DataConversionException, IOException;

    /**
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     * @see #getBidderAddressBookFilePath()
     */
    Optional<ReadOnlyBidderAddressBook> readBidderAddressBook(Path filePath)
            throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyBidderAddressBook} to the storage.
     * @param bidderAddressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveBidderAddressBook(ReadOnlyBidderAddressBook bidderAddressBook) throws IOException;

    /**
     * @throws IOException if there was any problem writing to the file.
     * @see #saveBidderAddressBook(ReadOnlyBidderAddressBook)
     */
    void saveBidderAddressBook(ReadOnlyBidderAddressBook bidderAddressBook, Path filePath) throws IOException;

}
