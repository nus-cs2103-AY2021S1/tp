package seedu.simplykitchen.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.simplykitchen.commons.exceptions.DataConversionException;
import seedu.simplykitchen.model.ReadOnlyFoodInventory;
import seedu.simplykitchen.model.ReadOnlyUserPrefs;
import seedu.simplykitchen.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends FoodInventoryStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getFoodInventoryFilePath();

    @Override
    Optional<ReadOnlyFoodInventory> readFoodInventory() throws DataConversionException, IOException;

    @Override
    void saveFoodInventory(ReadOnlyFoodInventory foodInventory) throws IOException;

}
