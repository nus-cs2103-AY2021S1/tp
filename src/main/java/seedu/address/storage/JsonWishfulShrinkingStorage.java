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
import seedu.address.model.ReadOnlyWishfulShrinking;

/**
 * A class to access WishfulShrinking data stored as a json file on the hard disk.
 */
public class JsonWishfulShrinkingStorage implements WishfulShrinkingStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonWishfulShrinkingStorage.class);

    private Path filePath;

    public JsonWishfulShrinkingStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getWishfulShrinkingFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyWishfulShrinking> readWishfulShrinking() throws DataConversionException {
        return readWishfulShrinking(filePath);
    }

    /**
     * Similar to {@link #readWishfulShrinking()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyWishfulShrinking> readWishfulShrinking(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableWishfulShrinking> jsonWishfulShrinking = JsonUtil.readJsonFile(
                filePath, JsonSerializableWishfulShrinking.class);
        if (!jsonWishfulShrinking.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonWishfulShrinking.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveWishfulShrinking(ReadOnlyWishfulShrinking addressBook) throws IOException {
        saveWishfulShrinking(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveWishfulShrinking(ReadOnlyWishfulShrinking)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveWishfulShrinking(ReadOnlyWishfulShrinking addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableWishfulShrinking(addressBook), filePath);
    }

}
