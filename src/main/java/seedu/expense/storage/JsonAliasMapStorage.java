package seedu.expense.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.expense.commons.core.LogsCenter;
import seedu.expense.commons.exceptions.DataConversionException;
import seedu.expense.commons.exceptions.IllegalValueException;
import seedu.expense.commons.util.FileUtil;
import seedu.expense.commons.util.JsonUtil;
import seedu.expense.model.alias.AliasMap;

/**
 * A class to access AliasMap data stored as a json file on the hard disk.
 */
public class JsonAliasMapStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonAliasMapStorage.class);

    private Path filePath;

    public JsonAliasMapStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getAliasMapFilePath() {
        return filePath;
    }

    public Optional<AliasMap> readAliasMap() throws DataConversionException {
        return readAliasMap(filePath);
    }

    /**
     * Read and create an Optional of AliasMap from JSON at specified filepath.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<AliasMap> readAliasMap(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableAliasMap> jsonAliasMap = JsonUtil.readJsonFile(
                filePath, JsonSerializableAliasMap.class);
        if (!jsonAliasMap.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAliasMap.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    public void saveAliasMap(AliasMap aliasMap) throws IOException {
        saveAliasMap(aliasMap, filePath);
    }

    /**
     * Save AliasMap as JSON in specified filepath.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveAliasMap(AliasMap aliasMap, Path filePath) throws IOException {
        requireNonNull(aliasMap);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableAliasMap(aliasMap), filePath);
    }

}
