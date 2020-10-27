package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.Preset.Preset;
import seedu.address.model.order.ReadOnlyOrderManager;

public class JsonPresetManagerStorage implements PresetManagerStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonPresetManagerStorage.class);

    private Path filePath;

    public JsonPresetManagerStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getPresetManagerFilePath() {
        return filePath;
    }

    @Override
    public Optional<List<Preset>> readPresetManager() throws DataConversionException {
        return readPresetManager(filePath);
    }

    /**
     * Similar to {@link #readPresetManager()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<List<Preset>> readPresetManager(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializablePresetManager> jsonPresetManager = JsonUtil.readJsonFile(
                filePath, JsonSerializablePresetManager.class);
        if (!jsonPresetManager.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonPresetManager.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void savePresetManager(ReadOnlyOrderManager orderManager, String name, int index) throws IOException {
        savePresetManager(orderManager, filePath, name, index);
    }

    /**
     * Similar to {@link #savePresetManager(ReadOnlyOrderManager orderManager, String name, int index)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void savePresetManager(ReadOnlyOrderManager orderManager, Path filePath, String name, int index) throws IOException {
        requireNonNull(orderManager);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializablePresetManager(orderManager, name, index), filePath);
    }

}
