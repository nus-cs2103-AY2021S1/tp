package seedu.pivot.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.pivot.commons.core.LogsCenter;
import seedu.pivot.commons.exceptions.DataConversionException;
import seedu.pivot.commons.exceptions.IllegalValueException;
import seedu.pivot.commons.util.FileUtil;
import seedu.pivot.commons.util.JsonUtil;
import seedu.pivot.model.ReadOnlyPivot;

/**
 * A class to access PIVOT data stored as a json file on the hard disk.
 */
public class JsonPivotStorage implements PivotStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonPivotStorage.class);

    private Path filePath;

    public JsonPivotStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getPivotFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyPivot> readPivot() throws DataConversionException {
        return readPivot(filePath);
    }

    /**
     * Similar to {@link #readPivot()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyPivot> readPivot(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializablePivot> jsonPivot = JsonUtil.readJsonFile(
                filePath, JsonSerializablePivot.class);
        if (!jsonPivot.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonPivot.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void savePivot(ReadOnlyPivot pivot) throws IOException {
        savePivot(pivot, filePath);
    }

    /**
     * Similar to {@link #savePivot(ReadOnlyPivot)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void savePivot(ReadOnlyPivot pivot, Path filePath) throws IOException {
        requireNonNull(pivot);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializablePivot(pivot), filePath);
    }

}
