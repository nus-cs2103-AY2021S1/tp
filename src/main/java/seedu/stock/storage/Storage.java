package seedu.stock.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.stock.commons.exceptions.DataConversionException;
import seedu.stock.model.ReadOnlySerialNumberSetsBook;
import seedu.stock.model.ReadOnlyStockBook;
import seedu.stock.model.ReadOnlyUserPrefs;
import seedu.stock.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends StockBookStorage, UserPrefsStorage, SerialNumberSetsBookStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getStockBookFilePath();

    @Override
    Optional<ReadOnlyStockBook> readStockBook() throws DataConversionException, IOException;

    @Override
    void saveStockBook(ReadOnlyStockBook stockBook) throws IOException;

    @Override
    Optional<ReadOnlySerialNumberSetsBook> readSerialNumberSetsBook() throws DataConversionException, IOException;
}
