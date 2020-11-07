package seedu.simplykitchen.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.simplykitchen.testutil.Assert.assertThrows;
import static seedu.simplykitchen.testutil.TypicalFood.ANCHOVIES;
import static seedu.simplykitchen.testutil.TypicalFood.HUMMUS;
import static seedu.simplykitchen.testutil.TypicalFood.ICEBERG_LETTUCE;
import static seedu.simplykitchen.testutil.TypicalFood.getTypicalFoodInventory;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.simplykitchen.commons.exceptions.DataConversionException;
import seedu.simplykitchen.model.FoodInventory;
import seedu.simplykitchen.model.ReadOnlyFoodInventory;

public class JsonFoodInventoryStorageTest {
    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonFoodInventoryStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readFoodInventory_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readFoodInventory(null));
    }

    private java.util.Optional<ReadOnlyFoodInventory> readFoodInventory(String filePath)
            throws Exception {
        return new JsonFoodInventoryStorage(Paths.get(filePath))
                .readFoodInventory(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readFoodInventory("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readFoodInventory(
                "notJsonFormatFoodInventory.json"));
    }

    @Test
    public void readFoodInventory_invalidFoodInventory_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readFoodInventory(
                "invalidFoodInventory.json"));
    }

    @Test
    public void readFoodInventory_invalidAndValidFoodInventory_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readFoodInventory(
                "invalidAndValidFoodInventory.json"));
    }

    @Test
    public void readAndSaveFoodInventory_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempFoodInventory.json");
        FoodInventory original = getTypicalFoodInventory();
        JsonFoodInventoryStorage jsonFoodInventoryStorage = new JsonFoodInventoryStorage(filePath);

        // Save in new file and read back
        jsonFoodInventoryStorage.saveFoodInventory(original, filePath);
        ReadOnlyFoodInventory readBack = jsonFoodInventoryStorage.readFoodInventory(filePath).get();
        assertEquals(original, new FoodInventory(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addFood(HUMMUS);
        original.removeFood(ANCHOVIES);
        jsonFoodInventoryStorage.saveFoodInventory(original, filePath);
        readBack = jsonFoodInventoryStorage.readFoodInventory(filePath).get();
        assertEquals(original, new FoodInventory(readBack));

        // Save and read without specifying file path
        original.addFood(ICEBERG_LETTUCE);
        jsonFoodInventoryStorage.saveFoodInventory(original); // file path not specified
        readBack = jsonFoodInventoryStorage.readFoodInventory().get(); // file path not specified
        assertEquals(original, new FoodInventory(readBack));

    }

    @Test
    public void saveFoodInventory_nullFoodInventory_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFoodInventory(
                null, "SomeFile.json"));
    }

    /**
     * Saves {@code foodInventory} at the specified {@code filePath}.
     */
    private void saveFoodInventory(ReadOnlyFoodInventory foodInventory, String filePath) {
        try {
            new JsonFoodInventoryStorage(Paths.get(filePath))
                    .saveFoodInventory(foodInventory, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveFoodInventory_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFoodInventory(
                new FoodInventory(), null));
    }
}
