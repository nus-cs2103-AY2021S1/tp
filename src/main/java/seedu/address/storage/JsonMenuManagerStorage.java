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
import seedu.address.model.menu.ReadOnlyMenuManager;


/**
 * A class to access MenuManager data stored as a json file on the hard disk.
 */
public class JsonMenuManagerStorage implements MenuManagerStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonMenuManagerStorage.class);

    private Path filePath;

    public JsonMenuManagerStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getMenuManagerFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyMenuManager> readMenuManager() throws DataConversionException {
        return readMenuManager(filePath);
    }

    /**
     * Similar to {@link #readMenuManager()}.

     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyMenuManager> readMenuManager(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableMenuManager> jsonMenuManager = JsonUtil.readJsonFile(
                filePath, JsonSerializableMenuManager.class);
        if (!jsonMenuManager.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonMenuManager.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveMenuManager(ReadOnlyMenuManager menuManager) throws IOException {
        saveMenuManager(menuManager, filePath);
    }

    /**
     * Similar to {@link #saveMenuManager(ReadOnlyMenuManager)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveMenuManager(ReadOnlyMenuManager menuManager, Path filePath) throws IOException {
        requireNonNull(menuManager);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableMenuManager(menuManager), filePath);
    }

}
