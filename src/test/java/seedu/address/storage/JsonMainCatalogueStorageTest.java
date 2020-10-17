package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalProjects.APEAKAPP;
import static seedu.address.testutil.TypicalProjects.HOON;
import static seedu.address.testutil.TypicalProjects.IDA;
import static seedu.address.testutil.TypicalProjects.getTypicalMainCatalogue;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.MainCatalogue;
import seedu.address.model.ReadOnlyMainCatalogue;

public class JsonMainCatalogueStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonMainCatalogueStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readMainCatalogue_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readMainCatalogue(null));
    }

    private java.util.Optional<ReadOnlyMainCatalogue> readMainCatalogue(String filePath) throws Exception {
        return new JsonMainCatalogueStorage(Paths.get(filePath))
                .readMainCatalogue(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readMainCatalogue("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readMainCatalogue("notJsonFormatMainCatalogue.json"));
    }

    @Test
    public void readMainCatalogue_invalidProjectMainCatalogue_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readMainCatalogue("invalidProjectMainCatalogue.json"));
    }

    @Test
    public void readMainCatalogue_invalidAndValidProjectMainCatalogue_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readMainCatalogue("invalidAndValidProjectMainCatalogue.json"));
    }

    @Test
    public void readAndSaveMainCatalogue_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempMainCatalogue.json");
        MainCatalogue original = getTypicalMainCatalogue();
        JsonMainCatalogueStorage jsonMainCatalogueStorage = new JsonMainCatalogueStorage(filePath);

        // Save in new file and read back
        jsonMainCatalogueStorage.saveMainCatalogue(original, filePath);
        ReadOnlyMainCatalogue readBack = jsonMainCatalogueStorage.readMainCatalogue(filePath).get();
        assertEquals(original, new MainCatalogue(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addProject(HOON);
        original.removeProject(APEAKAPP);
        jsonMainCatalogueStorage.saveMainCatalogue(original, filePath);
        readBack = jsonMainCatalogueStorage.readMainCatalogue(filePath).get();
        assertEquals(original, new MainCatalogue(readBack));

        // Save and read without specifying file path
        original.addProject(IDA);
        jsonMainCatalogueStorage.saveMainCatalogue(original); // file path not specified
        readBack = jsonMainCatalogueStorage.readMainCatalogue().get(); // file path not specified
        assertEquals(original, new MainCatalogue(readBack));

    }

    @Test
    public void saveMainCatalogue_nullMainCatalogue_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveMainCatalogue(null, "SomeFile.json"));
    }

    /**
     * Saves {@code mainCatalogue} at the specified {@code filePath}.
     */
    private void saveMainCatalogue(ReadOnlyMainCatalogue mainCatalogue, String filePath) {
        try {
            new JsonMainCatalogueStorage(Paths.get(filePath))
                    .saveMainCatalogue(mainCatalogue, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveMainCatalogue_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveMainCatalogue(new MainCatalogue(), null));
    }
}
