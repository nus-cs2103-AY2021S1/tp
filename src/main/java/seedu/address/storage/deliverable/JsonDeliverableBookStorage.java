package seedu.address.storage.deliverable;


import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.deliverable.ReadOnlyDeliverableBook;

/**
 * A class to access DeliverableBook data stored as a json file on the hard disk.
 */
public class JsonDeliverableBookStorage implements DeliverableBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonDeliverableBookStorage.class);

    private Path filePath;

    public JsonDeliverableBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getDeliverableBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyDeliverableBook> readDeliverableBook() throws DataConversionException, IOException {
        return readDeliverableBook(filePath);
    }

    /**
     * Similar to {@link #readDeliverableBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    @Override
    public Optional<ReadOnlyDeliverableBook> readDeliverableBook(Path filePath)
            throws DataConversionException, IOException {
        requireNonNull(filePath);

        Optional<JsonSerializableDeliverableBook> jsonDeliverableBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableDeliverableBook.class);
        if (!jsonDeliverableBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonDeliverableBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveDeliverableBook(ReadOnlyDeliverableBook deliverableBook) throws IOException {
        saveDeliverableBook(deliverableBook, filePath);
    }

    @Override
    public void saveDeliverableBook(ReadOnlyDeliverableBook deliverableBook, Path filePath) throws IOException {
        requireNonNull(deliverableBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableDeliverableBook(deliverableBook), filePath);
    }
}
