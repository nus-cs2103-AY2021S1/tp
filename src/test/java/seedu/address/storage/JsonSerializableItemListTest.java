package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ItemList;
import seedu.address.model.item.Item;
import seedu.address.testutil.TypicalItems;

public class JsonSerializableItemListTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src",
            "test", "data", "JsonSerializableItemListTest");
    private static final Path TYPICAL_ITEMS_FILE = TEST_DATA_FOLDER.resolve("typicalItemsItemList.json");
    private static final Path INVALID_ITEM_FILE = TEST_DATA_FOLDER.resolve("invalidItemItemList.json");
    private static final Path DUPLICATE_ITEM_FILE = TEST_DATA_FOLDER.resolve("duplicateItemItemList.json");

    @BeforeEach
    public void setUp() {
        Item.setIdCounter(0);
    }

    @Test
    public void toModelType_typicalItemsFile_success() throws Exception {
        JsonSerializableItemList dataFromFile = JsonUtil.readJsonFile(TYPICAL_ITEMS_FILE,
                JsonSerializableItemList.class).get();
        ItemList itemListFromFile = dataFromFile.toModelType();
        ItemList typicalItemsItemList = TypicalItems.getTypicalItemList();

        assertEquals(itemListFromFile, typicalItemsItemList);
    }

    @Test
    public void toModelType_invalidItemFile_throwsIllegalValueException() throws Exception {
        JsonSerializableItemList dataFromFile = JsonUtil.readJsonFile(INVALID_ITEM_FILE,
                JsonSerializableItemList.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateItems_throwsIllegalValueException() throws Exception {
        JsonSerializableItemList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ITEM_FILE,
                JsonSerializableItemList.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableItemList.MESSAGE_DUPLICATE_ITEM,
                dataFromFile::toModelType);
    }
}
