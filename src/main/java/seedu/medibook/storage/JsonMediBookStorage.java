package seedu.medibook.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.medibook.commons.core.LogsCenter;
import seedu.medibook.commons.exceptions.DataConversionException;
import seedu.medibook.commons.exceptions.IllegalValueException;
import seedu.medibook.commons.util.FileUtil;
import seedu.medibook.commons.util.JsonUtil;
import seedu.medibook.model.ReadOnlyMediBook;

/**
 * A class to access MediBook data stored as a json file on the hard disk.
 */
public class JsonMediBookStorage implements MediBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonMediBookStorage.class);

    private final Path filePath;

    public JsonMediBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getMediBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyMediBook> readMediBook() throws DataConversionException {
        return readMediBook(filePath);
    }

    /**
     * Similar to {@link #readMediBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyMediBook> readMediBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableMediBook> jsonMediBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableMediBook.class);
        if (!jsonMediBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonMediBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveMediBook(ReadOnlyMediBook mediBook) throws IOException {
        saveMediBook(mediBook, filePath);
    }

    /**
     * Similar to {@link #saveMediBook(ReadOnlyMediBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveMediBook(ReadOnlyMediBook mediBook, Path filePath) throws IOException {
        requireNonNull(mediBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableMediBook(mediBook), filePath);
    }

}
