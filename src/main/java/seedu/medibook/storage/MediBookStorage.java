package seedu.medibook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.medibook.commons.exceptions.DataConversionException;
import seedu.medibook.model.MediBook;
import seedu.medibook.model.ReadOnlyMediBook;

/**
 * Represents a storage for {@link MediBook}.
 */
public interface MediBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getMediBookFilePath();

    /**
     * Returns MediBook data as a {@link ReadOnlyMediBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyMediBook> readMediBook() throws DataConversionException, IOException;

    /**
     * @see #getMediBookFilePath()
     */
    Optional<ReadOnlyMediBook> readMediBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyMediBook} to the storage.
     * @param mediBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveMediBook(ReadOnlyMediBook mediBook) throws IOException;

    /**
     * @see #saveMediBook(ReadOnlyMediBook)
     */
    void saveMediBook(ReadOnlyMediBook mediBook, Path filePath) throws IOException;

}
