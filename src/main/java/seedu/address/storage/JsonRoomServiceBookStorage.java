package seedu.address.storage;

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
import seedu.address.model.ReadOnlyRoomServiceBook;

public class JsonRoomServiceBookStorage implements RoomServiceBookStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonPersonBookStorage.class);

    private Path filePath;

    public JsonRoomServiceBookStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getRoomServiceBookFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyRoomServiceBook> readRoomServiceBook() throws DataConversionException, IOException {
        return readRoomServiceBook(filePath);
    }

    /**
     * Similar to {@link #readRoomServiceBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    @Override
    public Optional<ReadOnlyRoomServiceBook> readRoomServiceBook(Path filePath)
            throws DataConversionException, IOException {
        requireNonNull(filePath);

        Optional<JsonSerializableRoomServiceBook> jsonRoomServiceBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableRoomServiceBook.class);
        if (!jsonRoomServiceBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonRoomServiceBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveRoomServiceBook(ReadOnlyRoomServiceBook roomServiceBook) throws IOException {
        saveRoomServiceBook(roomServiceBook, filePath);
    }

    @Override
    public void saveRoomServiceBook(ReadOnlyRoomServiceBook roomServiceBook, Path filePath) throws IOException {
        requireNonNull(roomServiceBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableRoomServiceBook(roomServiceBook), filePath);
    }
}
