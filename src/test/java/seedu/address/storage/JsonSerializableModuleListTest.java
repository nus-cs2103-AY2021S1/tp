package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ModuleList;
import seedu.address.testutil.TypicalModules;

public class JsonSerializableModuleListTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_MODULES_FILE = TEST_DATA_FOLDER.resolve("typicalModuleModuleList.json");
    private static final Path INVALID_MODULES_FILE = TEST_DATA_FOLDER.resolve("invalidModuleModuleList.json");
    private static final Path DUPLICATE_MODULES_FILE = TEST_DATA_FOLDER.resolve("duplicateModuleModuleList.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableModuleList dataFromFile = JsonUtil.readJsonFile(TYPICAL_MODULES_FILE,
                JsonSerializableModuleList.class).get();
        ModuleList moduleListFromFile = dataFromFile.toModelType();
        ModuleList typicalModuleModuleList = TypicalModules.getTypicalModuleList();
        assertEquals(moduleListFromFile, typicalModuleModuleList);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableModuleList dataFromFile = JsonUtil.readJsonFile(INVALID_MODULES_FILE,
                JsonSerializableModuleList.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableModuleList dataFromFile = JsonUtil.readJsonFile(DUPLICATE_MODULES_FILE,
                JsonSerializableModuleList.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableModuleList.MESSAGE_DUPLICATE_MODULE,
                 dataFromFile::toModelType);
    }

}
