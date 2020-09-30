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
import seedu.address.model.ReadOnlyMcGymmy;

/**
 * A class to access McGymmy data stored as a json file on the hard disk.
 */
public class JsonMcGymmyStorage implements McGymmyStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonMcGymmyStorage.class);

    private Path filePath;

    public JsonMcGymmyStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getMcGymmyFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyMcGymmy> readMcGymmy() throws DataConversionException {
        return readMcGymmy(filePath);
    }

    /**
     * Similar to {@link #readMcGymmy()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyMcGymmy> readMcGymmy(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableMcGymmy> jsonMcGymmy = JsonUtil.readJsonFile(
                filePath, JsonSerializableMcGymmy.class);
        if (!jsonMcGymmy.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonMcGymmy.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveMcGymmy(ReadOnlyMcGymmy mcGymmy) throws IOException {
        saveMcGymmy(mcGymmy, filePath);
    }

    /**
     * Similar to {@link #saveMcGymmy(ReadOnlyMcGymmy)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveMcGymmy(ReadOnlyMcGymmy mcGymmy, Path filePath) throws IOException {
        requireNonNull(mcGymmy);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableMcGymmy(mcGymmy), filePath);
    }

}
