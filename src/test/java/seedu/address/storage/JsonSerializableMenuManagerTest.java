package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.menu.MenuManager;
import seedu.address.testutil.TypicalMenuItems;

public class JsonSerializableMenuManagerTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableMenuManagerTest");
    private static final Path TYPICAL_FOODS_FILE = TEST_DATA_FOLDER.resolve("typicalMenuManager.json");
    private static final Path INVALID_FOOD_FILE = TEST_DATA_FOLDER.resolve("invalidMenuManager.json");
    private static final Path DUPLICATE_FOOD_FILE = TEST_DATA_FOLDER.resolve("duplicateFoods.json");

    @Test
    public void toModelType_typicalMenuFile_success() throws Exception {
        JsonSerializableMenuManager dataFromFile = JsonUtil.readJsonFile(TYPICAL_FOODS_FILE,
                JsonSerializableMenuManager.class).get();
        MenuManager menuManagerFromFile = dataFromFile.toModelType();
        MenuManager typicalVendorsMenuManager = TypicalMenuItems.getTypicalMenuManager();
        assertEquals(menuManagerFromFile, typicalVendorsMenuManager);
    }

    @Test
    public void toModelType_invalidMenuManagerFile_throwsIllegalValueException() throws Exception {
        JsonSerializableMenuManager dataFromFile = JsonUtil.readJsonFile(INVALID_FOOD_FILE,
                JsonSerializableMenuManager.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateVendors_throwsIllegalValueException() throws Exception {
        JsonSerializableMenuManager dataFromFile = JsonUtil.readJsonFile(DUPLICATE_FOOD_FILE,
                JsonSerializableMenuManager.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableMenuManager.MESSAGE_DUPLICATE_FOOD,
                dataFromFile::toModelType);
    }

}
