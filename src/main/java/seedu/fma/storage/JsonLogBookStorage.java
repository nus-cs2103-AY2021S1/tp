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
import seedu.fma.model.ReadOnlyAddressBook;

/**
 * A class to access AddressBook data stored as a json file on the hard disk.
 */
public class JsonLogBookStorage implements LogBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonLogBookStorage.class);

    private Path filePath;

    public JsonLogBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getLogBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyAddressBook> readLogBook() throws DataConversionException {
        return readLogBook(filePath);
    }

    /**
     * Similar to {@link #readLogBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyAddressBook> readLogBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableLogBook> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableLogBook.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveLogBook(ReadOnlyAddressBook logBook) throws IOException {
        saveLogBook(logBook, filePath);
    }

    /**
     * Similar to {@link #saveLogBook(ReadOnlyAddressBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveLogBook(ReadOnlyAddressBook logBook, Path filePath) throws IOException {
        requireNonNull(logBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableLogBook(logBook), filePath);
    }

}
