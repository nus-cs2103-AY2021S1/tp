package seedu.fma.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.fma.commons.core.LogsCenter;
import seedu.fma.commons.exceptions.DataConversionException;
import seedu.fma.commons.exceptions.IllegalValueException;
import seedu.fma.commons.util.FileUtil;
import seedu.fma.commons.util.JsonUtil;
import seedu.fma.model.ReadOnlyLogBook;

/**
 * A class to access LogBook data stored as a json file on the hard disk.
 */
public class JsonLogBookStorage implements LogBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonLogBookStorage.class);

    private final Path filePath;

    public JsonLogBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getLogBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyLogBook> readLogBook() throws DataConversionException {
        return readLogBook(filePath);
    }

    /**
     * Similar to {@link #readLogBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyLogBook> readLogBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);
        assert filePath != null;

        Optional<JsonSerializableLogBook> jsonLogBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableLogBook.class);
        if (jsonLogBook.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonLogBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveLogBook(ReadOnlyLogBook logBook) throws IOException {
        saveLogBook(logBook, filePath);
    }

    /**
     * Similar to {@link #saveLogBook(ReadOnlyLogBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveLogBook(ReadOnlyLogBook logBook, Path filePath) throws IOException {
        requireNonNull(logBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableLogBook(logBook), filePath);
    }

}
