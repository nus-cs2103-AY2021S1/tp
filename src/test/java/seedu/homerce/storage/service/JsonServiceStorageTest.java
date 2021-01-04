package seedu.homerce.storage.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.homerce.testutil.Assert.assertThrows;
import static seedu.homerce.testutil.service.TypicalServices.EYE_WASH;
import static seedu.homerce.testutil.service.TypicalServices.LASH_LIFT;
import static seedu.homerce.testutil.service.TypicalServices.TOE_MASSAGE;
import static seedu.homerce.testutil.service.TypicalServices.getTypicalServiceManager;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.homerce.commons.exceptions.DataConversionException;
import seedu.homerce.model.manager.ReadOnlyServiceManager;
import seedu.homerce.model.manager.ServiceManager;

public class JsonServiceStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "Service", "JsonServiceStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readServiceManager_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readServiceManager(null));
    }

    private java.util.Optional<ReadOnlyServiceManager> readServiceManager(String filePath) throws Exception {
        return new JsonServiceStorage(Paths.get(filePath)).readServiceManager(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
            ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
            : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readServiceManager("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readServiceManager("notJsonFormatService.json"));
    }

    @Test
    public void readServiceManager_invalidServiceManager_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readServiceManager("invalidServiceManager.json"));
    }

    @Test
    public void readServiceManager_invalidAndValidServiceManager_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readServiceManager("invalidAndValidServiceManager.json"));
    }

    @Test
    public void readAndSaveServiceManager_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempServiceManager.json");
        ServiceManager original = getTypicalServiceManager();
        JsonServiceStorage jsonServiceStorage = new JsonServiceStorage(filePath);

        // Save in new file and read back
        jsonServiceStorage.saveServiceManager(original, filePath);
        ReadOnlyServiceManager readBack = jsonServiceStorage.readServiceManager(filePath).get();
        assertEquals(original, new ServiceManager(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addService(TOE_MASSAGE);
        original.removeService(LASH_LIFT);
        jsonServiceStorage.saveServiceManager(original, filePath);
        readBack = jsonServiceStorage.readServiceManager(filePath).get();
        assertEquals(original, new ServiceManager(readBack));

        // Save and read without specifying file path
        original.addService(EYE_WASH);
        jsonServiceStorage.saveServiceManager(original); // file path not specified
        readBack = jsonServiceStorage.readServiceManager().get(); // file path not specified
        assertEquals(original, new ServiceManager(readBack));
    }

    @Test
    public void saveServiceManager_nullServiceManager_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveServiceManager(null, "SomeFile.json"));
    }

    /**
     * Saves {@code serviceManager} at the specified {@code filePath}.
     */
    private void saveServiceManager(ReadOnlyServiceManager serviceManager, String filePath) {
        try {
            new JsonServiceStorage(Paths.get(filePath))
                .saveServiceManager(serviceManager, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveServiceManager_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveServiceManager(new ServiceManager(), null));
    }
}
