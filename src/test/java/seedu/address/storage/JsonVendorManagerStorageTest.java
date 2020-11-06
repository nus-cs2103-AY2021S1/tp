package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.vendor.ReadOnlyVendorManager;
import seedu.address.model.vendor.VendorManager;
import seedu.address.testutil.TypicalVendors;

public class JsonVendorManagerStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonVendorManagerStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readVendorManager_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readVendorManager(null));
    }

    private java.util.Optional<ReadOnlyVendorManager> readVendorManager(String filePath) throws Exception {
        return new JsonVendorManagerStorage(Paths.get(filePath))
                .readVendorManager(addToTestDataPathIfNotNull(filePath));
    }

    private void saveVendorManager(ReadOnlyVendorManager vendorManager, String filePath) throws Exception {
        JsonVendorManagerStorage jsonVendorManagerStorage = new JsonVendorManagerStorage(
                addToTestDataPathIfNotNull(filePath));
        jsonVendorManagerStorage.saveVendorManager(vendorManager);
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readVendorManager("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readVendorManager("notJsonFormatVendorManager.json"));
    }

    @Test
    public void readVendorManager_invalidVendorVendorManager_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readVendorManager("invalidVendorManager.json"));
    }

    @Test
    public void readVendorManager_invalidAndValidVendorVendorManager_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readVendorManager(
                "invalidAndValidVendorManager.json"));
    }

    @Test
    public void readAndSaveVendorManager_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempVendorManager.json");
        VendorManager original = TypicalVendors.getTypicalVendorManager();
        JsonVendorManagerStorage jsonVendorManagerStorage = new JsonVendorManagerStorage(filePath);

        // Save in new file and read back
        jsonVendorManagerStorage.saveVendorManager(original, filePath);
        ReadOnlyVendorManager readBack = jsonVendorManagerStorage.readVendorManager(filePath).get();
        assertEquals(original, new VendorManager(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addVendor(TypicalVendors.HOON);
        original.removeVendor(TypicalVendors.ALICE);
        jsonVendorManagerStorage.saveVendorManager(original, filePath);
        readBack = jsonVendorManagerStorage.readVendorManager(filePath).get();
        assertEquals(original, new VendorManager(readBack));

        // Save and read without specifying file path
        original.addVendor(TypicalVendors.IDA);
        jsonVendorManagerStorage.saveVendorManager(original); // file path not specified
        readBack = jsonVendorManagerStorage.readVendorManager().get(); // file path not specified
        assertEquals(original, new VendorManager(readBack));

    }

    @Test
    public void saveVendorManager_nullVendorManager_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveVendorManager(null, "SomeFile.json"));
    }

    //    /**
    //     * Saves {@code vendorManager} at the specified {@code filePath}.
    //     */
    //    private void saveVendorManager(ReadOnlyVendorManager vendorManager, String filePath) {
    //        try {
    //            new JsonVendorManagerStorage(Paths.get(filePath))
    //                    .saveVendorManager(vendorManager, addToTestDataPathIfNotNull(filePath));
    //        } catch (IOException ioe) {
    //            throw new AssertionError("There should not be an error writing to the file.", ioe);
    //        }
    //    }

    @Test
    public void saveVendorManager_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveVendorManager(new VendorManager(), null));
    }
}
