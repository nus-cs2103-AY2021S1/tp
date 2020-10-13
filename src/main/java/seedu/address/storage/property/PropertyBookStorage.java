package seedu.address.storage.property;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.propertybook.PropertyBook;
import seedu.address.model.propertybook.ReadOnlyPropertyBook;

/**
 * Represents a storage for {@link PropertyBook}.
 */
public interface PropertyBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getPropertyBookFilePath();

    /**
     * Returns PropertyBook data as a {@link ReadOnlyPropertyBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyPropertyBook> readPropertyBook() throws DataConversionException, IOException;

    /**
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     * @see #getPropertyBookFilePath()
     */
    Optional<ReadOnlyPropertyBook> readPropertyBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyPropertyBook} to the storage.
     * @param propertyBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void savePropertyBook(ReadOnlyPropertyBook propertyBook) throws IOException;

    /**
     * @throws IOException if there was any problem writing to the file.
     * @see #savePropertyBook(ReadOnlyPropertyBook)
     */
    void savePropertyBook(ReadOnlyPropertyBook propertyBook, Path filePath) throws IOException;

}
