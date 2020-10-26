package seedu.zookeep.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.zookeep.commons.core.LogsCenter;
import seedu.zookeep.commons.exceptions.DataConversionException;
import seedu.zookeep.commons.exceptions.IllegalValueException;
import seedu.zookeep.commons.util.FileUtil;
import seedu.zookeep.commons.util.JsonUtil;
import seedu.zookeep.model.ReadOnlyZooKeepBook;

/**
 * A class to access ZooKeepBook data stored as a json file on the hard disk.
 */
public class JsonZooKeepBookStorage implements ZooKeepBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonZooKeepBookStorage.class);

    private Path filePath;

    public JsonZooKeepBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getZooKeepBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyZooKeepBook> readZooKeepBook() throws DataConversionException {
        return readZooKeepBook(filePath);
    }

    /**
     * Similar to {@link #readZooKeepBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyZooKeepBook> readZooKeepBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableZooKeepBook> jsonZooKeepBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableZooKeepBook.class);
        if (!jsonZooKeepBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonZooKeepBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveZooKeepBook(ReadOnlyZooKeepBook zooKeepBook) throws IOException {
        saveZooKeepBook(zooKeepBook, filePath);
    }

    /**
     * Similar to {@link #saveZooKeepBook(ReadOnlyZooKeepBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveZooKeepBook(ReadOnlyZooKeepBook zooKeepBook, Path filePath) throws IOException {
        requireNonNull(zooKeepBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableZooKeepBook(zooKeepBook), filePath);
    }

}
