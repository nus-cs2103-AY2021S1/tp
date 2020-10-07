package seedu.stock.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.stock.commons.exceptions.DataConversionException;
import seedu.stock.model.ReadOnlySerialNumberSetsBook;
import seedu.stock.model.SerialNumberSetsBook;

/**
 * Represents a storage for {@link SerialNumberSetsBook}.
 */
public interface SerialNumberSetsBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getSerialNumberSetsBookFilePath();

    /**
     * Returns SerialNumberSetsBook data as a {@link ReadOnlySerialNumberSetsBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlySerialNumberSetsBook> readSerialNumberSetsBook()
            throws DataConversionException, IOException;

    /**
     * @see #getSerialNumberSetsBookFilePath()
     */
    Optional<ReadOnlySerialNumberSetsBook> readSerialNumberSetsBook(Path filePath)
            throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlySerialNumberSetsBook} to the storage.
     * @param serialNumberSetsBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveSerialNumberSetsBook(ReadOnlySerialNumberSetsBook serialNumberSetsBook) throws IOException;

    /**
     * @see #saveSerialNumberSetsBook(ReadOnlySerialNumberSetsBook)
     */
    void saveSerialNumberSetsBook(ReadOnlySerialNumberSetsBook serialNumberSetsBook, Path filePath)
            throws IOException;

}
