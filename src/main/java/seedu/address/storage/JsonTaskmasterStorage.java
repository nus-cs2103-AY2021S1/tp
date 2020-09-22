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
import seedu.address.model.ReadOnlyTaskmaster;

/**
 * A class to access Taskmaster data stored as a json file on the hard disk.
 */
public class JsonTaskmasterStorage implements TaskmasterStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonTaskmasterStorage.class);

    private Path filePath;

    public JsonTaskmasterStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTaskmasterFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTaskmaster> readTaskmaster() throws DataConversionException {
        return readTaskmaster(filePath);
    }

    /**
     * Similar to {@link #readTaskmaster()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyTaskmaster> readTaskmaster(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTaskmaster> jsonTaskmaster = JsonUtil.readJsonFile(
                filePath, JsonSerializableTaskmaster.class);
        if (!jsonTaskmaster.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTaskmaster.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveTaskmaster(ReadOnlyTaskmaster taskmaster) throws IOException {
        saveTaskmaster(taskmaster, filePath);
    }

    /**
     * Similar to {@link #saveTaskmaster(ReadOnlyTaskmaster)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveTaskmaster(ReadOnlyTaskmaster taskmaster, Path filePath) throws IOException {
        requireNonNull(taskmaster);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTaskmaster(taskmaster), filePath);
    }

}
