package seedu.homerce.storage.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.homerce.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.homerce.commons.exceptions.IllegalValueException;
import seedu.homerce.commons.util.JsonUtil;
import seedu.homerce.model.manager.ServiceManager;
import seedu.homerce.testutil.service.TypicalServices;

public class JsonSerializableServiceManagerTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "Service",
        "JsonSerializableServiceManagerTest");
    private static final Path TYPICAL_SERVICES_FILE = TEST_DATA_FOLDER.resolve("typicalServiceManager.json");
    private static final Path INVALID_SERVICE_FILE = TEST_DATA_FOLDER.resolve("invalidServiceManager.json");
    private static final Path DUPLICATE_SERVICE_FILE = TEST_DATA_FOLDER.resolve("duplicateService.json");

    @Test
    public void toModelType_typicalServicesFile_success() throws Exception {
        JsonSerializableServiceManager dataFromFile = JsonUtil.readJsonFile(TYPICAL_SERVICES_FILE,
            JsonSerializableServiceManager.class).get();
        ServiceManager serviceManagerFromFile = dataFromFile.toModelType();
        ServiceManager typicalServicesServiceManager = TypicalServices.getTypicalServiceManager();
        assertEquals(serviceManagerFromFile, typicalServicesServiceManager);
    }

    @Test
    public void toModelType_invalidServiceFile_throwsIllegalValueException() throws Exception {
        JsonSerializableServiceManager dataFromFile = JsonUtil.readJsonFile(INVALID_SERVICE_FILE,
            JsonSerializableServiceManager.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateServices_throwsIllegalValueException() throws Exception {
        JsonSerializableServiceManager dataFromFile = JsonUtil.readJsonFile(DUPLICATE_SERVICE_FILE,
            JsonSerializableServiceManager.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableServiceManager.MESSAGE_DUPLICATE_SERVICE,
            dataFromFile::toModelType);
    }
}
