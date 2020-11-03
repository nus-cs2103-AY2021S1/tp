package seedu.stock.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.stock.commons.core.LogsCenter;
import seedu.stock.commons.exceptions.DataConversionException;
import seedu.stock.commons.exceptions.IllegalValueException;
import seedu.stock.commons.util.FileUtil;
import seedu.stock.commons.util.JsonUtil;
import seedu.stock.model.ReadOnlyStockBook;

/**
 * A class to access StockBook data stored as a json file on the hard disk.
 */
public class JsonStockBookStorage implements StockBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonStockBookStorage.class);

    private Path filePath;

    public JsonStockBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getStockBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyStockBook> readStockBook() throws DataConversionException {
        return readStockBook(filePath);
    }

    /**
     * Similar to {@link #readStockBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyStockBook> readStockBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableStockBook> jsonStockBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableStockBook.class);
        if (!jsonStockBook.isPresent()) {
            return Optional.empty();
        }
        try {
            return Optional.of(jsonStockBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveStockBook(ReadOnlyStockBook stockBook) throws IOException {
        saveStockBook(stockBook, filePath);
    }

    /**
     * Similar to {@link #saveStockBook(ReadOnlyStockBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveStockBook(ReadOnlyStockBook stockBook, Path filePath) throws IOException {
        requireNonNull(stockBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableStockBook(stockBook), filePath);
    }

}
