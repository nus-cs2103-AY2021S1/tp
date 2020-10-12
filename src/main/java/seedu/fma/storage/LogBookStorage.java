package seedu.fma.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.fma.commons.exceptions.DataConversionException;
import seedu.fma.model.ReadOnlyAddressBook;

/**
 * Represents a storage for {@link seedu.fma.model.AddressBook}.
 */
public interface LogBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getLogBookFilePath();

    /**
     * Returns LogBook data as a {@link ReadOnlyAddressBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyAddressBook> readLogBook() throws DataConversionException, IOException;

    /**
     * @see #getLogBookFilePath()
     */
    Optional<ReadOnlyAddressBook> readLogBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyAddressBook} to the storage.
     * @param logBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveLogBook(ReadOnlyAddressBook logBook) throws IOException;

    /**
     * @see #saveLogBook(ReadOnlyAddressBook)
     */
    void saveLogBook(ReadOnlyAddressBook logBook, Path filePath) throws IOException;

}
