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
import seedu.address.model.ReadOnlyProductiveNus;

/**
 * A class to access ProductiveNus data stored as a json file on the hard disk.
 */
public class JsonProductiveNusStorage implements ProductiveNusStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonProductiveNusStorage.class);

    private Path filePath;

    public JsonProductiveNusStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getProductiveNusFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyProductiveNus> readProductiveNus() throws DataConversionException {
        return readProductiveNus(filePath);
    }

    /**
     * Similar to {@link #readProductiveNus()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyProductiveNus> readProductiveNus(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableProductiveNus> jsonProductiveNus = JsonUtil.readJsonFile(
                filePath, JsonSerializableProductiveNus.class);
        if (!jsonProductiveNus.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonProductiveNus.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveProductiveNus(ReadOnlyProductiveNus productiveNus) throws IOException {
        saveProductiveNus(productiveNus, filePath);
    }

    /**
     * Similar to {@link #saveProductiveNus(ReadOnlyProductiveNus)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveProductiveNus(ReadOnlyProductiveNus productiveNus, Path filePath) throws IOException {
        requireNonNull(productiveNus);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableProductiveNus(productiveNus), filePath);
    }

}
