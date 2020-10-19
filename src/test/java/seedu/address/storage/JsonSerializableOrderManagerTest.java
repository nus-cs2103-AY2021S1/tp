package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.order.OrderManager;
import seedu.address.testutil.TypicalOrderItems;

public class JsonSerializableOrderManagerTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableOrderManagerTest");
    private static final Path TYPICAL_ORDERITEMS_FILE = TEST_DATA_FOLDER.resolve("typicalOrderManager.json");
    private static final Path INVALID_ORDERITEM_FILE = TEST_DATA_FOLDER.resolve("invalidOrderManager.json");
    private static final Path DUPLICATE_ORDERITEM_FILE = TEST_DATA_FOLDER.resolve("duplicateOrderItems.json");

    @Test
    public void toModelType_typicalOrderFile_success() throws Exception {
        JsonSerializableOrderManager dataFromFile = JsonUtil.readJsonFile(TYPICAL_ORDERITEMS_FILE,
                JsonSerializableOrderManager.class).get();
        OrderManager orderManagerFromFile = dataFromFile.toModelType();
        OrderManager typicalVendorsOrderManager = TypicalOrderItems.getTypicalOrderManager();
        assertEquals(orderManagerFromFile, typicalVendorsOrderManager);
    }

    @Test
    public void toModelType_invalidOrderManagerFile_throwsIllegalValueException() throws Exception {
        JsonSerializableOrderManager dataFromFile = JsonUtil.readJsonFile(INVALID_ORDERITEM_FILE,
                JsonSerializableOrderManager.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateVendors_throwsIllegalValueException() throws Exception {
        JsonSerializableOrderManager dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ORDERITEM_FILE,
                JsonSerializableOrderManager.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableOrderManager.MESSAGE_DUPLICATE_ORDERITEM,
                dataFromFile::toModelType);
    }
}
