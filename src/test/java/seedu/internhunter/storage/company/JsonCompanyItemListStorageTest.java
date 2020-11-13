package seedu.internhunter.storage.company;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.internhunter.testutil.Assert.assertThrows;
import static seedu.internhunter.testutil.company.SampleCompanyItems.FACEBOOK;
import static seedu.internhunter.testutil.company.SampleCompanyItems.GARENA;
import static seedu.internhunter.testutil.company.SampleCompanyItems.getSampleCompanyList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.internhunter.commons.exceptions.DataConversionException;
import seedu.internhunter.model.company.CompanyItem;
import seedu.internhunter.model.item.ItemList;
import seedu.internhunter.model.item.ReadOnlyItemList;
import seedu.internhunter.storage.JsonItemListStorage;

public class JsonCompanyItemListStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonCompanyItemListStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readCompanyItemList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readCompanyItemList(null));
    }

    private java.util.Optional<ReadOnlyItemList<CompanyItem>> readCompanyItemList(
            String filePath) throws Exception {
        return new JsonItemListStorage<>(Paths.get(filePath),
                CompanyItem.class, JsonAdaptedCompanyItem.class).readItemList(
                addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readCompanyItemList("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readCompanyItemList(
                "notJsonFormatCompanyItemList.json"));
    }

    @Test
    public void readCompanyItemList_invalidCompanyItemList_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readCompanyItemList(
                "invalidCompanyItemList.json"));
    }

    @Test
    public void readCompanyItemList_invalidAndValidCompanyItemList_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readCompanyItemList(
                "invalidAndValidCompanyItemList.json"));
    }

    @Test
    public void readAndSaveCompanyItemList_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempCompanyItemList.json");
        ItemList<CompanyItem> original = getSampleCompanyList();
        JsonItemListStorage<CompanyItem, JsonAdaptedCompanyItem> jsonItemListStorage =
                new JsonItemListStorage<>(filePath, CompanyItem.class, JsonAdaptedCompanyItem.class);

        // Save in new file and read back
        jsonItemListStorage.saveItemList(original, filePath);
        ReadOnlyItemList<CompanyItem> readBack = jsonItemListStorage.readItemList(filePath).get();
        assertEquals(original, new ItemList<>(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addItem(GARENA);
        original.removeItem(FACEBOOK);
        jsonItemListStorage.saveItemList(original, filePath);
        readBack = jsonItemListStorage.readItemList(filePath).get();
        assertEquals(original, new ItemList<>(readBack));

        // Save and read without specifying file path
        original.addItem(FACEBOOK);
        jsonItemListStorage.saveItemList(original); // file path not specified
        readBack = jsonItemListStorage.readItemList().get(); // file path not specified
        assertEquals(original, new ItemList<>(readBack));

    }

    @Test
    public void saveCompanyItemList_nullCompanyItemList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveCompanyItemList(null,
                "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveCompanyItemList(ItemList<CompanyItem> addressBook, String filePath) {
        try {
            new JsonItemListStorage<>(Paths.get(filePath), CompanyItem.class,
                    JsonAdaptedCompanyItem.class).saveItemList(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveCompanyItemList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveCompanyItemList(new ItemList<>(), null));
    }
}
