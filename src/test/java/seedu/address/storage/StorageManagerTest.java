package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.ItemList;
import seedu.address.model.LocationList;
import seedu.address.model.ReadOnlyItemList;
import seedu.address.model.ReadOnlyLocationList;
import seedu.address.model.ReadOnlyRecipeList;
import seedu.address.model.RecipeList;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TypicalItems;
import seedu.address.testutil.TypicalLocations;
import seedu.address.testutil.TypicalRecipes;

public class StorageManagerTest {
    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonItemListStorage itemListStorage = new JsonItemListStorage(getTempFilePath("ab"));
        JsonRecipeListStorage recipeListStorage = new JsonRecipeListStorage(getTempFilePath("ac"));
        JsonLocationListStorage locationListStorage = new JsonLocationListStorage(getTempFilePath("ad"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));

        storageManager = new StorageManager(itemListStorage, locationListStorage, recipeListStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void itemListReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonItemListStorage} class.
         * More extensive testing of ItemList saving/reading is done in {@link JsonItemListStorageTest} class.
         *
         * Similar for the below tests.
         */
        ItemList original = TypicalItems.getTypicalItemList();
        storageManager.saveItemList(original);
        ReadOnlyItemList retrieved = storageManager.readItemList().get();
        assertEquals(original, new ItemList(retrieved));
    }

    @Test
    public void recipeListReadSave() throws Exception {
        RecipeList original = TypicalRecipes.getTypicalRecipeList();
        storageManager.saveRecipeList(original);
        ReadOnlyRecipeList retrieved = storageManager.readRecipeList().get();
        assertEquals(original, new RecipeList(retrieved));
    }

    @Test
    public void locationListReadSave() throws Exception {
        LocationList original = TypicalLocations.getTypicalLocationsList();
        storageManager.saveLocationList(original);
        ReadOnlyLocationList retrieved = storageManager.readLocationList().get();
        assertEquals(original, new LocationList(retrieved));
    }


    @Test
    public void getItemListFilePath() {
        assertNotNull(storageManager.getItemListFilePath());
    }

    @Test
    public void getRecipeListFilePath() {
        assertNotNull(storageManager.getRecipeListFilePath());
    }
}
