package seedu.homerce.storage.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.homerce.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.homerce.commons.exceptions.IllegalValueException;
import seedu.homerce.commons.util.JsonUtil;
import seedu.homerce.model.manager.ClientManager;
import seedu.homerce.testutil.client.TypicalClients;

public class JsonSerializableClientManagerTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "Client",
            "JsonSerializableClientManagerTest");
    private static final Path TYPICAL_CLIENTS_FILE = TEST_DATA_FOLDER.resolve("typicalClientManager.json");
    private static final Path INVALID_CLIENT_FILE = TEST_DATA_FOLDER.resolve("invalidClientManager.json");
    private static final Path DUPLICATE_CLIENT_FILE = TEST_DATA_FOLDER.resolve("duplicateClient.json");

    @Test
    public void toModelType_typicalClientsFile_success() throws Exception {
        JsonSerializableClientManager dataFromFile = JsonUtil.readJsonFile(TYPICAL_CLIENTS_FILE,
                JsonSerializableClientManager.class).get();
        ClientManager clientManagerFromFile = dataFromFile.toModelType();
        ClientManager typicalClientsClientManager = TypicalClients.getTypicalClientManager();
        assertEquals(clientManagerFromFile, typicalClientsClientManager);
    }

    @Test
    public void toModelType_invalidClientFile_throwsIllegalValueException() throws Exception {
        JsonSerializableClientManager dataFromFile = JsonUtil.readJsonFile(INVALID_CLIENT_FILE,
            JsonSerializableClientManager.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateClients_throwsIllegalValueException() throws Exception {
        JsonSerializableClientManager dataFromFile = JsonUtil.readJsonFile(DUPLICATE_CLIENT_FILE,
            JsonSerializableClientManager.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableClientManager.MESSAGE_DUPLICATE_CLIENT,
            dataFromFile::toModelType);
    }
}
