package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.Trackr;
import seedu.address.testutil.TypicalModules;

public class JsonSerializableModuleListTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableModuleListTest");
    private static final Path TYPICAL_MODULES_FILE = TEST_DATA_FOLDER.resolve("typicalModulesModuleList.json");
    private static final Path INVALID_MODULE_FILE = TEST_DATA_FOLDER.resolve("invalidModuleModuleList.json");
    private static final Path DUPLICATE_MODULE_FILE = TEST_DATA_FOLDER.resolve("duplicateModuleModuleList.json");

    @Test
    public void toModelType_typicalModuleList_success() throws Exception {
        JsonSerializableModuleList dataFromFile = JsonUtil.readJsonFile(TYPICAL_MODULES_FILE,
                JsonSerializableModuleList.class).get();
        Trackr moduleListFromFile = dataFromFile.toModelType();
        Trackr typicalPersonsAddressBook = TypicalModules.getTypicalModuleList();
        assertEquals(moduleListFromFile, typicalPersonsAddressBook);
    }

    @Test
    public void toModelType_invalidModule_throwsIllegalValueException() throws Exception {
        JsonSerializableModuleList dataFromFile = JsonUtil.readJsonFile(INVALID_MODULE_FILE,
                JsonSerializableModuleList.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateModules_throwsIllegalValueException() throws Exception {
        JsonSerializableModuleList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_MODULE_FILE,
                JsonSerializableModuleList.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableModuleList.MESSAGE_DUPLICATE_MODULE,
                dataFromFile::toModelType);
    }


}
