package chopchop.storage;

import static chopchop.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import chopchop.commons.core.LogsCenter;
import chopchop.commons.exceptions.DataConversionException;
import chopchop.commons.exceptions.IllegalValueException;
import chopchop.commons.util.FileUtil;
import chopchop.commons.util.JsonUtil;
import chopchop.model.usage.IngredientUsage;

public class JsonIngredientUsageStorage implements UsageStorage<JsonAdaptedIngredientUsage, IngredientUsage> {
    private static final Logger logger = LogsCenter.getLogger(JsonIngredientUsageStorage.class);

    private final Path filePath;

    public JsonIngredientUsageStorage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns the file path of the data file.
     */
    @Override
    public Path getUsageFilePath() {
        return this.filePath;
    }

    @Override
    public Optional<List<IngredientUsage>> readUsages() throws DataConversionException {
        return this.readUsages(this.filePath);
    }

    @Override
    public Optional<List<IngredientUsage>> readUsages(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableIngredientUsageList> jsonUsageList = JsonUtil.readJsonFile(
            filePath, JsonSerializableIngredientUsageList.class);
        if (jsonUsageList.isEmpty()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonUsageList.get().toType());
        } catch (IllegalValueException e) {
            logger.info(e.getMessage());
            throw new DataConversionException(e);
        }
    }

    @Override
    public void saveUsages(List<JsonAdaptedIngredientUsage> usages) throws IOException {
        this.saveUsages(usages, this.filePath);
    }

    @Override
    public void saveUsages(List<JsonAdaptedIngredientUsage> usages, Path filePath) throws IOException {
        requireAllNonNull(usages, filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableIngredientUsageList(usages), filePath);
    }
}
