package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.getTypicalModuleList;
import static seedu.address.testutil.TypicalModules.CS2030;
import static seedu.address.testutil.TypicalModules.CS2103;
import static seedu.address.testutil.TypicalModules.ES2660;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ModuleList;
import seedu.address.model.ReadOnlyModuleList;

public class JsonModuleListStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonModuleListStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readModuleList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readModuleList(null));
    }

    private java.util.Optional<ReadOnlyModuleList> readModuleList(String filePath) throws Exception {
        return new JsonModuleListStorage(Paths.get(filePath)).readModuleList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readModuleList("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readModuleList("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readModuleList_invalidModuleModuleList_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readModuleList("invalidModuleModuleList.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidPersonModuleModuleList_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readModuleList("invalidAndValidModuleModuleList.json"));
    }

    @Test
    public void readAndSaveModuleList_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempModuleList.json");
        ModuleList original = getTypicalModuleList();
        JsonModuleListStorage jsonModuleListStorage = new JsonModuleListStorage(filePath);

        // Save in new file and read back
        jsonModuleListStorage.saveModuleList(original, filePath);
        ReadOnlyModuleList readBack = jsonModuleListStorage.readModuleList(filePath).get();
        assertEquals(original, new ModuleList(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addModule(CS2103);
        original.removeModule(CS2030);
        jsonModuleListStorage.saveModuleList(original, filePath);
        readBack = jsonModuleListStorage.readModuleList(filePath).get();
        assertEquals(original, new ModuleList(readBack));

        // Save and read without specifying file path
        original.addModule(ES2660);
        jsonModuleListStorage.saveModuleList(original); // file path not specified
        readBack = jsonModuleListStorage.readModuleList().get(); // file path not specified
        assertEquals(original, new ModuleList(readBack));
    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveModuleList(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveModuleList(ReadOnlyModuleList moduleList, String filePath) {
        try {
            new JsonModuleListStorage(Paths.get(filePath))
                    .saveModuleList(moduleList, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveModuleList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveModuleList(new ModuleList(), null));
    }
}
