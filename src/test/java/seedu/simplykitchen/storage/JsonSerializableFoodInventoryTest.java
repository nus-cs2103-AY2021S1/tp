package seedu.simplykitchen.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.simplykitchen.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.simplykitchen.commons.exceptions.IllegalValueException;
import seedu.simplykitchen.commons.util.JsonUtil;
import seedu.simplykitchen.model.FoodInventory;
import seedu.simplykitchen.testutil.TypicalFood;

public class JsonSerializableFoodInventoryTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableFoodInventoryTest");
    private static final Path TYPICAL_FOOD_FILE = TEST_DATA_FOLDER.resolve("typicalFoodInventory.json");
    private static final Path INVALID_FOOD_FILE = TEST_DATA_FOLDER.resolve("invalidFoodInventory.json");
    private static final Path DUPLICATE_FOOD_FILE = TEST_DATA_FOLDER.resolve(
            "duplicateFoodInventory.json");

    @Test
    public void toModelType_typicalFoodFile_success() throws Exception {
        JsonSerializableFoodInventory dataFromFile = JsonUtil.readJsonFile(TYPICAL_FOOD_FILE,
                JsonSerializableFoodInventory.class).get();
        FoodInventory simplyKitchenInventoryFromFile = dataFromFile.toModelType();
        FoodInventory typicalFoodSimplyKitchenInventory = TypicalFood.getTypicalFoodInventory();
        assertEquals(simplyKitchenInventoryFromFile, typicalFoodSimplyKitchenInventory);
    }

    @Test
    public void toModelType_invalidFoodFile_throwsIllegalValueException() throws Exception {
        JsonSerializableFoodInventory dataFromFile = JsonUtil.readJsonFile(INVALID_FOOD_FILE,
                JsonSerializableFoodInventory.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateFood_throwsIllegalValueException() throws Exception {
        JsonSerializableFoodInventory dataFromFile = JsonUtil.readJsonFile(DUPLICATE_FOOD_FILE,
                JsonSerializableFoodInventory.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableFoodInventory.MESSAGE_DUPLICATE_FOOD,
                dataFromFile::toModelType);
    }

}
