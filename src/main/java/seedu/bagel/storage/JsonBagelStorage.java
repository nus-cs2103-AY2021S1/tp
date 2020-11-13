package seedu.bagel.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.bagel.commons.core.LogsCenter;
import seedu.bagel.commons.exceptions.DataConversionException;
import seedu.bagel.commons.exceptions.IllegalValueException;
import seedu.bagel.commons.util.FileUtil;
import seedu.bagel.commons.util.JsonUtil;
import seedu.bagel.model.ReadOnlyBagel;

/**
 * A class to access Bagel data stored as a json file on the hard disk.
 */
public class JsonBagelStorage implements BagelStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonBagelStorage.class);

    private Path filePath;

    public JsonBagelStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getBagelFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyBagel> readBagel() throws DataConversionException {
        return readBagel(filePath);
    }

    /**
     * Similar to {@link #readBagel()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyBagel> readBagel(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableBagel> jsonBagel = JsonUtil.readJsonFile(
                filePath, JsonSerializableBagel.class);
        if (!jsonBagel.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonBagel.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveBagel(ReadOnlyBagel bagel) throws IOException {
        saveBagel(bagel, filePath);
    }

    /**
     * Similar to {@link #saveBagel(ReadOnlyBagel)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveBagel(ReadOnlyBagel bagel, Path filePath) throws IOException {
        requireNonNull(bagel);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableBagel(bagel), filePath);
    }

}
