package seedu.homerce.storage.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.homerce.testutil.Assert.assertThrows;
import static seedu.homerce.testutil.client.TypicalClients.ALICE;
import static seedu.homerce.testutil.client.TypicalClients.HOON;
import static seedu.homerce.testutil.client.TypicalClients.IDA;
import static seedu.homerce.testutil.client.TypicalClients.getTypicalClientManager;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.homerce.commons.exceptions.DataConversionException;
import seedu.homerce.model.manager.ClientManager;
import seedu.homerce.model.manager.ReadOnlyClientManager;

public class JsonClientStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "Client", "JsonClientStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readClientManager_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readClientManager(null));
    }

    private java.util.Optional<ReadOnlyClientManager> readClientManager(String filePath) throws Exception {
        return new JsonClientStorage(Paths.get(filePath)).readClientManager(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readClientManager("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readClientManager("notJsonFormatClient.json"));
    }

    @Test
    public void readClientManager_invalidClientManager_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readClientManager("invalidClientManager.json"));
    }

    @Test
    public void readClientManager_invalidAndValidClientManager_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readClientManager("invalidAndValidClientManager.json"));
    }

    @Test
    public void readAndSaveClientManager_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempClientManager.json");
        ClientManager original = getTypicalClientManager();
        JsonClientStorage jsonClientStorage = new JsonClientStorage(filePath);

        // Save in new file and read back
        jsonClientStorage.saveClientManager(original, filePath);
        ReadOnlyClientManager readBack = jsonClientStorage.readClientManager(filePath).get();
        assertEquals(original, new ClientManager(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addClient(HOON);
        original.removeClient(ALICE);
        jsonClientStorage.saveClientManager(original, filePath);
        readBack = jsonClientStorage.readClientManager(filePath).get();
        assertEquals(original, new ClientManager(readBack));

        // Save and read without specifying file path
        original.addClient(IDA);
        jsonClientStorage.saveClientManager(original); // file path not specified
        readBack = jsonClientStorage.readClientManager().get(); // file path not specified
        assertEquals(original, new ClientManager(readBack));
    }

    @Test
    public void saveClientManager_nullClientManager_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveClientManager(null, "SomeFile.json"));
    }

    /**
     * Saves {@code clientManager} at the specified {@code filePath}.
     */
    private void saveClientManager(ReadOnlyClientManager clientManager, String filePath) {
        try {
            new JsonClientStorage(Paths.get(filePath))
                    .saveClientManager(clientManager, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveClientManager_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveClientManager(new ClientManager(), null));
    }
}
