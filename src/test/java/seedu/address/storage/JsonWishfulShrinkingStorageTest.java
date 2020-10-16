package seedu.address.storage;

//import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRecipes.BURGER;
import static seedu.address.testutil.TypicalRecipes.SANDWICH;
import static seedu.address.testutil.TypicalRecipes.SOUP;
import static seedu.address.testutil.TypicalRecipes.getTypicalWishfulShrinking;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyWishfulShrinking;
import seedu.address.model.WishfulShrinking;

public class JsonWishfulShrinkingStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonWishfulShrinkingStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readWishfulShrinking_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readWishfulShrinking(null));
    }

    private java.util.Optional<ReadOnlyWishfulShrinking> readWishfulShrinking(String filePath) throws Exception {
        return new JsonWishfulShrinkingStorage(Paths.get(filePath))
                .readWishfulShrinking(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readWishfulShrinking("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readWishfulShrinking("notJsonFormatWishfulShrinking.json"));
    }

    @Test
    public void readWishfulShrinking_invalidRecipeWishfulShrinking_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readWishfulShrinking("invalidRecipeWishfulShrinking.json"));
    }

    @Test
    public void readWishfulShrinking_invalidAndValidRecipeWishfulShrinking_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
            readWishfulShrinking("invalidAndValidRecipeWishfulShrinking.json"));
    }

    @Test
    public void readAndSaveWishfulShrinking_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempWishfulShrinking.json");
        WishfulShrinking original = getTypicalWishfulShrinking();
        JsonWishfulShrinkingStorage jsonWishfulShrinkingStorage = new JsonWishfulShrinkingStorage(filePath);

        // Save in new file and read back
        jsonWishfulShrinkingStorage.saveWishfulShrinking(original, filePath);
        ReadOnlyWishfulShrinking readBack = jsonWishfulShrinkingStorage.readWishfulShrinking(filePath).get();
        assertEquals(original, new WishfulShrinking(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addRecipe(BURGER);
        original.removeRecipe(SANDWICH);
        jsonWishfulShrinkingStorage.saveWishfulShrinking(original, filePath);
        readBack = jsonWishfulShrinkingStorage.readWishfulShrinking(filePath).get();
        assertEquals(original, new WishfulShrinking(readBack));

        // Save and read without specifying file path
        original.addRecipe(SOUP);
        jsonWishfulShrinkingStorage.saveWishfulShrinking(original); // file path not specified
        readBack = jsonWishfulShrinkingStorage.readWishfulShrinking().get(); // file path not specified
        assertEquals(original, new WishfulShrinking(readBack));

    }

    @Test
    public void saveWishfulShrinking_nullWishfulShrinking_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveWishfulShrinking(null, "SomeFile.json"));
    }

    /**
     * Saves {@code wishfulShrinking} at the specified {@code filePath}.
     */
    private void saveWishfulShrinking(ReadOnlyWishfulShrinking wishfulShrinking, String filePath) {
        try {
            new JsonWishfulShrinkingStorage(Paths.get(filePath))
                    .saveWishfulShrinking(wishfulShrinking, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveWishfulShrinking_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveWishfulShrinking(new WishfulShrinking(), null));
    }
}
