package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyProjact;

/**
 * A class to access Projact data stored as a json file on the hard disk.
 */
public class JsonProjactStorage implements ProjactStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonProjactStorage.class);

    private Path filePath;

    public JsonProjactStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getProjactFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyProjact> readProjact() throws DataConversionException {
        return readProjact(filePath);
    }

    /**
     * Similar to {@link #readProjact()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyProjact> readProjact(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableProjact> jsonProjact = JsonUtil.readJsonFile(
                filePath, JsonSerializableProjact.class);
        if (!jsonProjact.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonProjact.get().toModelType());
        } catch (IllegalValueException | MalformedURLException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveProjact(ReadOnlyProjact projact) throws IOException {
        saveProjact(projact, filePath);
    }

    /**
     * Similar to {@link #saveProjact(ReadOnlyProjact)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveProjact(ReadOnlyProjact projact, Path filePath) throws IOException {
        requireNonNull(projact);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableProjact(projact), filePath);
    }

}
