package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalItems.getTypicalItemList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ItemList;
import seedu.address.model.ReadOnlyItemList;

public class JsonItemListStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonItemListStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readItemList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readItemList(null));
    }

    private java.util.Optional<ReadOnlyItemList> readItemList(String filePath) throws Exception {
        return new JsonItemListStorage(Paths.get(filePath)).readItemList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readItemList("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readItemList("notJsonFormatItemList.json"));
    }

    @Test
    public void readItemList_invalidItemItemList_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readItemList("invalidItemItemList.json"));
    }

    @Test
    public void readItemList_invalidAndValidItemItemList_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readItemList("invalidAndValidItemItemList.json"));
    }

    /**
     * tests reading and saving
     */
    @Test
    public void readAndSaveItemList_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempItemList.json");
        ItemList original = getTypicalItemList();
        JsonItemListStorage jsonItemListStorage = new JsonItemListStorage(filePath);

        // Save in new file and read back
        jsonItemListStorage.saveItemList(original, filePath);
        ReadOnlyItemList readBack = jsonItemListStorage.readItemList(filePath).get();
        assertEquals(original, new ItemList(readBack));
    }

    @Test
    public void saveItemList_nullItemList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveItemList(null, "SomeFile.json"));
    }

    /**
     * Saves {@code itemList} at the specified {@code filePath}.
     */
    private void saveItemList(ReadOnlyItemList itemList, String filePath) {
        try {
            new JsonItemListStorage(Paths.get(filePath))
                    .saveItemList(itemList, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveItemList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveItemList(new ItemList(), null));
    }
}
