package seedu.address.storage;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.order.ReadOnlyOrderManager;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;

public class JsonOrderManagerStorage implements OrderManagerStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonOrderManagerStorage.class);

    private Path filePath;

    public JsonOrderManagerStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getOrderManagerFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyOrderManager> readOrderManager() throws DataConversionException {
        return readOrderManager(filePath);
    }

    /**
     * Similar to {@link #readOrderManager()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyOrderManager> readOrderManager(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableOrderManager> jsonOrderManager = JsonUtil.readJsonFile(
                filePath, JsonSerializableOrderManager.class);
        if (!jsonOrderManager.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonOrderManager.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveOrderManager(ReadOnlyOrderManager orderManager) throws IOException {
        saveOrderManager(orderManager, filePath);
    }

    /**
     * Similar to {@link #saveOrderManager(ReadOnlyOrderManager)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveOrderManager(ReadOnlyOrderManager orderManager, Path filePath) throws IOException {
        requireNonNull(orderManager);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableOrderManager(orderManager), filePath);
    }

}
