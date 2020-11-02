package seedu.address.storage.sellerstorage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.selleraddressbook.ReadOnlySellerAddressBook;

/**
 * Represents a storage for {@link seedu.address.model.selleraddressbook.SellerAddressBook}.
 */
public interface SellerAddressBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getSellerAddressBookFilePath();

    /**
     * Returns SellerAddressBook data as a {@link ReadOnlySellerAddressBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlySellerAddressBook> readSellerAddressBook() throws DataConversionException, IOException;

    /**
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     * @see #getSellerAddressBookFilePath()
     */
    Optional<ReadOnlySellerAddressBook> readSellerAddressBook(Path filePath)
            throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlySellerAddressBook} to the storage.
     * @param sellerAddressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveSellerAddressBook(ReadOnlySellerAddressBook sellerAddressBook) throws IOException;

    /**
     * @throws IOException if there was any problem writing to the file.
     * @see #saveSellerAddressBook(ReadOnlySellerAddressBook)
     */
    void saveSellerAddressBook(ReadOnlySellerAddressBook sellerAddressBook, Path filePath) throws IOException;

}
