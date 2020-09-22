package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyInventoryBook;

/**
 * Represents a storage for {@link seedu.address.model.InventoryBook}.
 */
public interface InventoryBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getInventoryBookFilePath();

    /**
     * Returns InventoryBook data as a {@link ReadOnlyInventoryBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyInventoryBook> readInventoryBook() throws DataConversionException, IOException;

    /**
     * @see #getInventoryBookFilePath()
     */
    Optional<ReadOnlyInventoryBook> readInventoryBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyInventoryBook} to the storage.
     * @param inventoryBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveInventoryBook(ReadOnlyInventoryBook inventoryBook) throws IOException;

    /**
     * @see #saveInventoryBook(ReadOnlyInventoryBook)
     */
    void saveInventoryBook(ReadOnlyInventoryBook inventoryBook, Path filePath) throws IOException;

}
