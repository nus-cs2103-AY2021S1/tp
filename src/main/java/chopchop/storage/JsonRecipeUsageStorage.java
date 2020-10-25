package chopchop.storage;

import static chopchop.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import chopchop.commons.core.LogsCenter;
import chopchop.commons.exceptions.DataConversionException;
import chopchop.commons.exceptions.IllegalValueException;
import chopchop.commons.util.FileUtil;
import chopchop.commons.util.JsonUtil;
import chopchop.model.UsageList;
import chopchop.model.usage.RecipeUsage;

public class JsonRecipeUsageStorage implements UsageStorage<RecipeUsage, RecipeUsage> {
    private static final Logger logger = LogsCenter.getLogger(JsonIngredientUsageStorage.class);

    private final Path filePath;

    public JsonRecipeUsageStorage(Path filePath) {
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
    public Optional<UsageList<RecipeUsage>> readUsages() throws DataConversionException {
        return this.readUsages(this.filePath);
    }

    @Override
    public Optional<UsageList<RecipeUsage>> readUsages(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableRecipeUsageList> jsonUsageList = JsonUtil.readJsonFile(
            filePath, JsonSerializableRecipeUsageList.class);
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
    public void saveUsages(UsageList<RecipeUsage> usages) throws IOException {
        this.saveUsages(usages, this.filePath);
    }

    @Override
    public void saveUsages(UsageList<RecipeUsage> usages, Path filePath) throws IOException {
        requireAllNonNull(usages, filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableRecipeUsageList(usages), filePath);
    }
}
