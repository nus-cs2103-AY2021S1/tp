package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalRecipes.getTypicalRecipeList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyRecipeList;
import seedu.address.model.RecipeList;

public class JsonRecipeListStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonRecipeListStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readRecipeList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readRecipeList(null));
    }

    private java.util.Optional<ReadOnlyRecipeList> readRecipeList(String filePath) throws Exception {
        return new JsonRecipeListStorage(Paths.get(filePath)).readRecipeList(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readRecipeList("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readRecipeList("notJsonFormatRecipeList.json"));
    }

    @Test
    public void readRecipeList_invalidRecipeRecipeList_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readRecipeList("invalidRecipeRecipeList.json"));
    }

    @Test
    public void readRecipeList_invalidAndValidRecipeRecipeList_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readRecipeList("invalidAndValidRecipeRecipeList.json"));
    }

    /**
     * Tests reading and saving.
     */
    @Test
    public void readAndSaveRecipeList_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempRecipeList.json");
        RecipeList original = getTypicalRecipeList();
        JsonRecipeListStorage jsonRecipeListStorage = new JsonRecipeListStorage(filePath);

        // Save in new file and read back
        jsonRecipeListStorage.saveRecipeList(original, filePath);
        ReadOnlyRecipeList readBack = jsonRecipeListStorage.readRecipeList(filePath).get();
        assertEquals(original, new RecipeList(readBack));
    }

    @Test
    public void saveRecipeList_nullRecipeList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveRecipeList(null, "SomeFile.json"));
    }

    /**
     * Saves {@code recipeList} at the specified {@code filePath}.
     */
    private void saveRecipeList(ReadOnlyRecipeList recipeList, String filePath) {
        try {
            new JsonRecipeListStorage(Paths.get(filePath))
                    .saveRecipeList(recipeList, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveRecipeList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveRecipeList(new RecipeList(), null));
    }
}
