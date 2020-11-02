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
import seedu.stock.model.ReadOnlySerialNumberSetsBook;

/**
 * A class to access SerialNumberSetsBook data stored as a json file on the hard disk.
 */
public class JsonSerialNumberSetsBookStorage implements SerialNumberSetsBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonSerialNumberSetsBookStorage.class);

    private Path filePath;

    public JsonSerialNumberSetsBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getSerialNumberSetsBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlySerialNumberSetsBook> readSerialNumberSetsBook()
            throws DataConversionException, IOException {
        return readSerialNumberSetsBook(filePath);
    }

    /**
     * Similar to {@link #readSerialNumberSetsBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlySerialNumberSetsBook> readSerialNumberSetsBook(Path filePath)
            throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableSerialNumberSetsBook> jsonSerialNumberSetsBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableSerialNumberSetsBook.class);
        if (!jsonSerialNumberSetsBook.isPresent()) {
            return Optional.empty();
        }
        try {
            return Optional.of(jsonSerialNumberSetsBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveSerialNumberSetsBook(ReadOnlySerialNumberSetsBook serialNumberSetsBook)
            throws IOException {
        saveSerialNumberSetsBook(serialNumberSetsBook, filePath);
    }

    /**
     * Similar to {@link #saveSerialNumberSetsBook(ReadOnlySerialNumberSetsBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveSerialNumberSetsBook(ReadOnlySerialNumberSetsBook serialNumberSetsBook, Path filePath)
            throws IOException {
        requireNonNull(serialNumberSetsBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableSerialNumberSetsBook(serialNumberSetsBook), filePath);
    }

}
