package com.eva.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import com.eva.commons.core.LogsCenter;
import com.eva.commons.exceptions.DataConversionException;
import com.eva.commons.exceptions.IllegalValueException;
import com.eva.commons.util.FileUtil;
import com.eva.commons.util.JsonUtil;
import com.eva.model.ReadOnlyEvaDatabase;

/**
 * A class to access EvaDatabase data stored as a json file on the hard disk.
 */
public class JsonEvaStorage implements EvaStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonEvaStorage.class);

    private Path filePath;

    public JsonEvaStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getEvaDatabaseFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyEvaDatabase> readEvaDatabase() throws DataConversionException {
        return readEvaDatabase(filePath);
    }

    /**
     * Similar to {@link #readEvaDatabase()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyEvaDatabase> readEvaDatabase(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableEvaDatabase> jsonEvaDatabase = JsonUtil.readJsonFile(
                filePath, JsonSerializableEvaDatabase.class);
        if (!jsonEvaDatabase.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonEvaDatabase.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveEvaDatabase(ReadOnlyEvaDatabase addressBook) throws IOException {
        saveEvaDatabase(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveEvaDatabase(ReadOnlyEvaDatabase)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveEvaDatabase(ReadOnlyEvaDatabase addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableEvaDatabase(addressBook), filePath);
    }

}
