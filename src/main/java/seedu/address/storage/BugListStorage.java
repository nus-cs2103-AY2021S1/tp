package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.BugList;
import seedu.address.model.ReadOnlyBugList;

/**
 * Represents a storage for {@link BugList}.
 */
public interface BugListStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAddressBookFilePath();

    /**
     * Returns BugList data as a {@link ReadOnlyBugList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyBugList> readAddressBook() throws DataConversionException, IOException;

    /**
     * @see #getAddressBookFilePath()
     */
    Optional<ReadOnlyBugList> readAddressBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyBugList} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyBugList addressBook) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlyBugList)
     */
    void saveAddressBook(ReadOnlyBugList addressBook, Path filePath) throws IOException;

}
