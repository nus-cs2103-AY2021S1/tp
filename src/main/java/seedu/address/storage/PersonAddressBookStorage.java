package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyPersonAddressBook;

/**
 * Represents a storage for {@link seedu.address.model.PersonAddressBook}.
 */
public interface PersonAddressBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getPersonAddressBookFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyPersonAddressBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyPersonAddressBook> readPersonAddressBook() throws DataConversionException, IOException;

    /**
     * @see #getPersonAddressBookFilePath()
     */
    Optional<ReadOnlyPersonAddressBook> readPersonAddressBook(Path filePath)
            throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyPersonAddressBook} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void savePersonAddressBook(ReadOnlyPersonAddressBook addressBook) throws IOException;

    /**
     * @see #savePersonAddressBook(ReadOnlyPersonAddressBook)
     */
    void savePersonAddressBook(ReadOnlyPersonAddressBook addressBook, Path filePath) throws IOException;

}
