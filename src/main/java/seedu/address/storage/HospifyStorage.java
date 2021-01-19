package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.HospifyBook;
import seedu.address.model.ReadOnlyHospifyBook;

/**
 * Represents a storage for {@link HospifyBook}.
 */
public interface HospifyStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getHospifyFilePath();

    /**
     * Returns HospifyBook data as a {@link ReadOnlyHospifyBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyHospifyBook> readHospifyBook() throws DataConversionException, IOException;

    /**
     * @see #getHospifyFilePath()
     */
    Optional<ReadOnlyHospifyBook> readHospifyBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyHospifyBook} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveHospifyBook(ReadOnlyHospifyBook addressBook) throws IOException;

    /**
     * @see #saveHospifyBook(ReadOnlyHospifyBook)
     */
    void saveHospifyBook(ReadOnlyHospifyBook addressBook, Path filePath) throws IOException;

}
