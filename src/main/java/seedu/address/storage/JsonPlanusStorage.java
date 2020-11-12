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
import seedu.address.model.ReadOnlyPlanus;

/**
 * A class to access Planus data stored as a json file on the hard disk.
 */
public class JsonPlanusStorage implements PlanusStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonPlanusStorage.class);

    private Path filePath;

    public JsonPlanusStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getPlanusFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyPlanus> readPlanus() throws DataConversionException {
        return readPlanus(filePath);
    }

    /**
     * Similar to {@link #readPlanus()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyPlanus> readPlanus(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializablePlanus> jsonPlanus = JsonUtil.readJsonFile(
                filePath, JsonSerializablePlanus.class);
        if (!jsonPlanus.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonPlanus.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void savePlanus(ReadOnlyPlanus planus) throws IOException {
        savePlanus(planus, filePath);
    }

    /**
     * Similar to {@link #savePlanus(ReadOnlyPlanus)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void savePlanus(ReadOnlyPlanus planus, Path filePath) throws IOException {
        requireNonNull(planus);
        requireNonNull(filePath);

        logger.info("saving data to: " + filePath + ", with data: " + planus);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializablePlanus(planus), filePath);
    }

}
