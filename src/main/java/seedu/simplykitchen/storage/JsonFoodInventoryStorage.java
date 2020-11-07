package seedu.simplykitchen.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.simplykitchen.commons.core.LogsCenter;
import seedu.simplykitchen.commons.exceptions.DataConversionException;
import seedu.simplykitchen.commons.exceptions.IllegalValueException;
import seedu.simplykitchen.commons.util.FileUtil;
import seedu.simplykitchen.commons.util.JsonUtil;
import seedu.simplykitchen.model.ReadOnlyFoodInventory;

/**
 * A class to access FoodInventory data stored as a json file on the hard disk.
 */
public class JsonFoodInventoryStorage implements FoodInventoryStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonFoodInventoryStorage.class);

    private Path filePath;

    public JsonFoodInventoryStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getFoodInventoryFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyFoodInventory> readFoodInventory() throws DataConversionException {
        return readFoodInventory(filePath);
    }

    /**
     * Similar to {@link #readFoodInventory()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyFoodInventory> readFoodInventory(Path filePath)
            throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableFoodInventory> jsonSimplyKitchenInventory = JsonUtil.readJsonFile(
                filePath, JsonSerializableFoodInventory.class);
        if (!jsonSimplyKitchenInventory.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonSimplyKitchenInventory.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveFoodInventory(ReadOnlyFoodInventory foodInventory) throws IOException {
        saveFoodInventory(foodInventory, filePath);
    }

    /**
     * Similar to {@link #saveFoodInventory(ReadOnlyFoodInventory)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveFoodInventory(ReadOnlyFoodInventory foodInventory, Path filePath)
            throws IOException {
        requireNonNull(foodInventory);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableFoodInventory(foodInventory), filePath);
    }

}
