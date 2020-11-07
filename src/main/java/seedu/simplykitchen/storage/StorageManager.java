package seedu.simplykitchen.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.simplykitchen.commons.core.LogsCenter;
import seedu.simplykitchen.commons.exceptions.DataConversionException;
import seedu.simplykitchen.model.ReadOnlyFoodInventory;
import seedu.simplykitchen.model.ReadOnlyUserPrefs;
import seedu.simplykitchen.model.UserPrefs;

/**
 * Manages storage of FoodInventory data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private FoodInventoryStorage foodInventoryStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code FoodInventoryStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(FoodInventoryStorage foodInventoryStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.foodInventoryStorage = foodInventoryStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ FoodInventory methods ==============================

    @Override
    public Path getFoodInventoryFilePath() {
        return foodInventoryStorage.getFoodInventoryFilePath();
    }

    @Override
    public Optional<ReadOnlyFoodInventory> readFoodInventory()
            throws DataConversionException, IOException {
        return readFoodInventory(foodInventoryStorage.getFoodInventoryFilePath());
    }

    @Override
    public Optional<ReadOnlyFoodInventory> readFoodInventory(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return foodInventoryStorage.readFoodInventory(filePath);
    }

    @Override
    public void saveFoodInventory(ReadOnlyFoodInventory foodInventory) throws IOException {
        saveFoodInventory(foodInventory, foodInventoryStorage.getFoodInventoryFilePath());
    }

    @Override
    public void saveFoodInventory(ReadOnlyFoodInventory foodInventory, Path filePath)
            throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        foodInventoryStorage.saveFoodInventory(foodInventory, filePath);
    }

}
