package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.QuickCache;
import seedu.address.model.ReadOnlyQuickCache;

/**
 * Represents a storage for {@link QuickCache}.
 */
public interface AddressBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAddressBookFilePath();

    /**
     * Returns QuickCache data as a {@link ReadOnlyQuickCache}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyQuickCache> readAddressBook() throws DataConversionException, IOException;

    /**
     * @see #getAddressBookFilePath()
     */
    Optional<ReadOnlyQuickCache> readAddressBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyQuickCache} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyQuickCache addressBook) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlyQuickCache)
     */
    void saveAddressBook(ReadOnlyQuickCache addressBook, Path filePath) throws IOException;

}
