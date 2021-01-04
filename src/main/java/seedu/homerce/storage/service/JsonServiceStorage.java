package seedu.homerce.storage.service;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.homerce.commons.core.LogsCenter;
import seedu.homerce.commons.exceptions.DataConversionException;
import seedu.homerce.commons.exceptions.IllegalValueException;
import seedu.homerce.commons.util.FileUtil;
import seedu.homerce.commons.util.JsonUtil;
import seedu.homerce.model.manager.ReadOnlyServiceManager;

/**
 * A class to access ServiceManager data stored as a json file on the hard disk.
 */
public class JsonServiceStorage implements ServiceStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonServiceStorage.class);

    private Path filePath;

    public JsonServiceStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getServiceManagerStorageFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyServiceManager> readServiceManager() throws DataConversionException, IOException {
        return readServiceManager(filePath);
    }

    /**
     * Similar to {@link #readServiceManager()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    @Override
    public Optional<ReadOnlyServiceManager> readServiceManager(Path filePath) throws DataConversionException,
            IOException {
        requireNonNull(filePath);

        Optional<JsonSerializableServiceManager> jsonServiceManager = JsonUtil.readJsonFile(
                filePath, JsonSerializableServiceManager.class);
        if (!jsonServiceManager.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonServiceManager.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveServiceManager(ReadOnlyServiceManager serviceManager) throws IOException {
        saveServiceManager(serviceManager, filePath);
    }

    @Override
    public void saveServiceManager(ReadOnlyServiceManager serviceManager, Path filePath) throws IOException {
        requireNonNull(serviceManager);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableServiceManager(serviceManager), filePath);
    }
}
