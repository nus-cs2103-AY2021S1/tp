package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.vendor.VendorManager;
import seedu.address.testutil.TypicalVendors;

public class JsonSerializableVendorManagerTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableVendorManagerTest");
    private static final Path TYPICAL_VENDORS_FILE = TEST_DATA_FOLDER.resolve("typicalVendorManager.json");
    private static final Path STORAGE_FILE = TEST_DATA_FOLDER.resolve("storageVendorManager.json");
    private static final Path INVALID_VENDOR_FILE = TEST_DATA_FOLDER.resolve("invalidVendorManager.json");
    private static final Path DUPLICATE_VENDOR_FILE = TEST_DATA_FOLDER.resolve("duplicateVendorManager.json");

    @Test
    public void toModelType_typicalVendorsFile_success() throws Exception {
        VendorManager book = TypicalVendors.getTypicalVendorManager();
        JsonSerializableVendorManager dataFromFile = JsonUtil.readJsonFile(STORAGE_FILE,
            JsonSerializableVendorManager.class).get();
        VendorManager dataBook = dataFromFile.toModelType();
        assertEquals(dataBook, book);
    }

    @Test
    public void toModelType_invalidVendorFile_throwsIllegalValueException() throws Exception {
        JsonSerializableVendorManager dataFromFile = JsonUtil.readJsonFile(INVALID_VENDOR_FILE,
                JsonSerializableVendorManager.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateVendors_throwsIllegalValueException() throws Exception {
        JsonSerializableVendorManager dataFromFile = JsonUtil.readJsonFile(DUPLICATE_VENDOR_FILE,
                JsonSerializableVendorManager.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

}
