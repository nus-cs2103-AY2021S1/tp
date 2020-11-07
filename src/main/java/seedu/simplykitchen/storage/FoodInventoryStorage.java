package seedu.simplykitchen.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.simplykitchen.commons.exceptions.DataConversionException;
import seedu.simplykitchen.model.FoodInventory;
import seedu.simplykitchen.model.ReadOnlyFoodInventory;

/**
 * Represents a storage for {@link FoodInventory}.
 */
public interface FoodInventoryStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getFoodInventoryFilePath();

    /**
     * Returns FoodInventory data as a {@link ReadOnlyFoodInventory}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyFoodInventory> readFoodInventory() throws DataConversionException, IOException;

    /**
     * @see #getFoodInventoryFilePath()
     */
    Optional<ReadOnlyFoodInventory> readFoodInventory(Path filePath)
            throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyFoodInventory} to the storage.
     * @param foodInventory cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveFoodInventory(ReadOnlyFoodInventory foodInventory) throws IOException;

    /**
     * @see #saveFoodInventory(ReadOnlyFoodInventory)
     */
    void saveFoodInventory(ReadOnlyFoodInventory foodInventory, Path filePath) throws IOException;

}
