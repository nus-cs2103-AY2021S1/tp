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
import seedu.address.model.ReadOnlyHairStyleX;

/**
 * A class to access HairStyleX data stored as a json file on the hard disk.
 */
public class JsonHairStyleXStorage implements HairStyleXStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonHairStyleXStorage.class);

    private Path filePath;

    public JsonHairStyleXStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getHairStyleXFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyHairStyleX> readHairStyleX() throws DataConversionException {
        return readHairStyleX(filePath);
    }

    /**
     * Similar to {@link #readHairStyleX()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyHairStyleX> readHairStyleX(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableHairStyleX> jsonHairStyleX = JsonUtil.readJsonFile(
                filePath, JsonSerializableHairStyleX.class);
        if (!jsonHairStyleX.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonHairStyleX.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveHairStyleX(ReadOnlyHairStyleX hairStyleX) throws IOException {
        saveHairStyleX(hairStyleX, filePath);
    }

    /**
     * Similar to {@link #saveHairStyleX(ReadOnlyHairStyleX)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveHairStyleX(ReadOnlyHairStyleX hairStyleX, Path filePath) throws IOException {
        requireNonNull(hairStyleX);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableHairStyleX(hairStyleX), filePath);
    }

}
