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
import seedu.address.model.ReadOnlyFitNus;

/**
 * A class to access FitNus data stored as a json file on the hard disk.
 */
public class JsonFitNusStorage implements FitNusStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonFitNusStorage.class);

    private Path filePath;

    public JsonFitNusStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getFitNusFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyFitNus> readFitNus() throws DataConversionException {
        return readFitNus(filePath);
    }

    /**
     * Similar to {@link #readFitNus()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyFitNus> readFitNus(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableFitNus> jsonFitNus = JsonUtil.readJsonFile(
                filePath, JsonSerializableFitNus.class);
        if (!jsonFitNus.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonFitNus.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveFitNus(ReadOnlyFitNus fitNus) throws IOException {
        saveFitNus(fitNus, filePath);
    }

    /**
     * Similar to {@link #saveFitNus(ReadOnlyFitNus)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveFitNus(ReadOnlyFitNus fitNus, Path filePath) throws IOException {
        requireNonNull(fitNus);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableFitNus(fitNus), filePath);
    }
}
