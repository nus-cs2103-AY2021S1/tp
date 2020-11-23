package seedu.tr4cker.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.tr4cker.commons.core.LogsCenter;
import seedu.tr4cker.commons.exceptions.DataConversionException;
import seedu.tr4cker.commons.exceptions.IllegalValueException;
import seedu.tr4cker.commons.util.FileUtil;
import seedu.tr4cker.commons.util.JsonUtil;
import seedu.tr4cker.model.ReadOnlyTr4cker;

/**
 * A class to access Tr4cker data stored as a json file on the hard disk.
 */
public class JsonTr4cker implements Tr4cker {

    private static final Logger logger = LogsCenter.getLogger(JsonTr4cker.class);

    private Path filePath;

    public JsonTr4cker(Path filePath) {
        this.filePath = filePath;
    }

    public Path getTr4ckerFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyTr4cker> readTr4cker() throws DataConversionException {
        return readTr4cker(filePath);
    }

    /**
     * Similar to {@link #readTr4cker()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyTr4cker> readTr4cker(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableTr4cker> jsonTr4cker = JsonUtil.readJsonFile(
                filePath, JsonSerializableTr4cker.class);
        if (!jsonTr4cker.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonTr4cker.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveTr4cker(ReadOnlyTr4cker tr4cker) throws IOException {
        saveTr4cker(tr4cker, filePath);
    }

    /**
     * Similar to {@link #saveTr4cker(ReadOnlyTr4cker)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveTr4cker(ReadOnlyTr4cker tr4cker, Path filePath) throws IOException {
        requireNonNull(tr4cker);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableTr4cker(tr4cker), filePath);
    }

}
