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
import seedu.address.model.ReadOnlyNusave;

/**
 * A class to access Nusave stored in the hard disk as a json file
 */
public class JsonNusaveStorage implements NusaveStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonNusaveStorage.class);

    private Path filePath;

    /**
     * Constructs a {@code JsonNusaveStorage} with the given {@code filePath}.
     * @param filePath {@code Path} containing the Nusave data file.
     */
    public JsonNusaveStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getNusaveFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyNusave> readNusave() throws DataConversionException {
        return readNusave(filePath);
    }

    @Override
    public Optional<ReadOnlyNusave> readNusave(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableNusave> jsonNusave = JsonUtil.readJsonFile(
                filePath, JsonSerializableNusave.class);
        if (!jsonNusave.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonNusave.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveNusave(ReadOnlyNusave nusave) throws IOException {
        saveNusave(nusave, filePath);
    }

    @Override
    public void saveNusave(ReadOnlyNusave nusave, Path filePath) throws IOException {
        requireNonNull(nusave);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableNusave(nusave), filePath);
    }
}
