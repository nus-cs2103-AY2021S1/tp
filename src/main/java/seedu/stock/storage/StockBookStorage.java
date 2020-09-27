package seedu.stock.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.stock.commons.exceptions.DataConversionException;
import seedu.stock.model.ReadOnlyStockBook;
import seedu.stock.model.StockBook;

/**
 * Represents a storage for {@link StockBook}.
 */
public interface StockBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getStockBookFilePath();

    /**
     * Returns StockBook data as a {@link ReadOnlyStockBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyStockBook> readStockBook() throws DataConversionException, IOException;

    /**
     * @see #getStockBookFilePath()
     */
    Optional<ReadOnlyStockBook> readStockBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyStockBook} to the storage.
     * @param stockBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveStockBook(ReadOnlyStockBook stockBook) throws IOException;

    /**
     * @see #saveStockBook(ReadOnlyStockBook)
     */
    void saveStockBook(ReadOnlyStockBook stockBook, Path filePath) throws IOException;

}
