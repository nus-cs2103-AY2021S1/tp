package seedu.resireg.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.resireg.commons.core.LogsCenter;
import seedu.resireg.commons.exceptions.DataConversionException;
import seedu.resireg.commons.exceptions.IllegalValueException;
import seedu.resireg.commons.util.FileUtil;
import seedu.resireg.commons.util.JsonUtil;
import seedu.resireg.model.ReadOnlyUserPrefs;
import seedu.resireg.model.UserPrefs;

/**
 * A class to access UserPrefs stored in the hard disk as a json file
 */
public class JsonUserPrefsStorage implements UserPrefsStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonAddressBookStorage.class);

    private Path filePath;

    public JsonUserPrefsStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getUserPrefsFilePath() {
        return filePath;
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException {
        return readUserPrefs(filePath);
    }

    /**
     * Similar to {@link #readUserPrefs()}
     * @param prefsFilePath location of the data. Cannot be null.
     * @throws DataConversionException if the file format is not as expected.
     */
    public Optional<UserPrefs> readUserPrefs(Path prefsFilePath) throws DataConversionException {

        Optional<JsonSerializableUserPrefs> jsonUserPrefs = JsonUtil.readJsonFile(
            filePath, JsonSerializableUserPrefs.class);
        if (!jsonUserPrefs.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonUserPrefs.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        saveUserPrefs(userPrefs, filePath);
    }

    /**
     * Saves the UserPrefs object as a Json file.
     *
     * @param userPrefs the existing data to be saved
     * @param filePath the file path denoting the storage
     * @throws IOException
     */
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs, Path filePath) throws IOException {
        requireNonNull(userPrefs);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableUserPrefs(userPrefs), filePath);
    }

}
