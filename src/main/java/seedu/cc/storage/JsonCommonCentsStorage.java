package seedu.cc.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.cc.commons.core.LogsCenter;
import seedu.cc.commons.exceptions.DataConversionException;
import seedu.cc.commons.exceptions.IllegalValueException;
import seedu.cc.commons.util.FileUtil;
import seedu.cc.commons.util.JsonUtil;
import seedu.cc.model.ReadOnlyCommonCents;

/**
 * A class to access CommonCents data stored as a json file on the hard disk.
 */
public class JsonCommonCentsStorage implements CommonCentsStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonCommonCentsStorage.class);

    private Path filepath;

    public JsonCommonCentsStorage(Path filepath) {
        this.filepath = filepath;
    }

    /**
     * Returns the file path of the data file.
     */
    @Override
    public Path getCommonCentsFilePath() {
        return filepath;
    }

    /**
     * Returns CommonCents data as a {@link ReadOnlyCommonCents}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    @Override
    public Optional<ReadOnlyCommonCents> readCommonCents() throws DataConversionException, IOException {
        return readCommonCents(filepath);
    }

    /**
     * Similar to {@link #readCommonCents()}
     * @param filePath location of the data. Cannot be null.
     * @see #getCommonCentsFilePath()
     */
    @Override
    public Optional<ReadOnlyCommonCents> readCommonCents(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableCommonCents> jsonCommonCents = JsonUtil.readJsonFile(
                filePath, JsonSerializableCommonCents.class);
        if (jsonCommonCents.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonCommonCents.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    /**
     * Saves the given {@link ReadOnlyCommonCents} to the storage.
     *
     * @param commonCents cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    @Override
    public void saveCommonCents(ReadOnlyCommonCents commonCents) throws IOException {
        saveCommonCents(commonCents, filepath);
    }

    /**
     * Similar to {@link #saveCommonCents(ReadOnlyCommonCents)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    @Override
    public void saveCommonCents(ReadOnlyCommonCents commonCents, Path filePath) throws IOException {
        requireNonNull(commonCents);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableCommonCents(commonCents), filePath);
    }

}
