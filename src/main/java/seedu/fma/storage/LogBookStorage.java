package seedu.fma.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.fma.commons.exceptions.DataConversionException;
import seedu.fma.model.LogBook;
import seedu.fma.model.ReadOnlyLogBook;

/**
 * Represents a storage for {@link LogBook}.
 */
public interface LogBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getLogBookFilePath();

    /**
     * Returns LogBook data as a {@link ReadOnlyLogBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyLogBook> readLogBook() throws DataConversionException, IOException;

    /**
     * @see #getLogBookFilePath()
     */
    Optional<ReadOnlyLogBook> readLogBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyLogBook} to the storage.
     * @param logBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveLogBook(ReadOnlyLogBook logBook) throws IOException;

    /**
     * @see #saveLogBook(ReadOnlyLogBook)
     */
    void saveLogBook(ReadOnlyLogBook logBook, Path filePath) throws IOException;

}
