package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyJobAddressBook;

/**
 * Represents a storage for {@link seedu.address.model.JobAddressBook}.
 */
public interface JobAddressBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getJobAddressBookFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyJobAddressBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyJobAddressBook> readJobAddressBook() throws DataConversionException, IOException;

    /**
     * @see #getJobAddressBookFilePath()
     */
    Optional<ReadOnlyJobAddressBook> readJobAddressBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyJobAddressBook} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveJobAddressBook(ReadOnlyJobAddressBook addressBook) throws IOException;

    /**
     * @see #saveJobAddressBook(ReadOnlyJobAddressBook)
     */
    void saveJobAddressBook(ReadOnlyJobAddressBook addressBook, Path filePath) throws IOException;

}
