package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.MainCatalogue;
import seedu.address.model.ReadOnlyMainCatalogue;

/**
 * Represents a storage for {@link MainCatalogue}.
 */
public interface MainCatalogueStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAddressBookFilePath();

    /**
     * Returns MainCatalogue data as a {@link ReadOnlyMainCatalogue}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyMainCatalogue> readAddressBook() throws DataConversionException, IOException;

    /**
     * @see #getAddressBookFilePath()
     */
    Optional<ReadOnlyMainCatalogue> readAddressBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyMainCatalogue} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyMainCatalogue addressBook) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlyMainCatalogue)
     */
    void saveAddressBook(ReadOnlyMainCatalogue addressBook, Path filePath) throws IOException;

}
