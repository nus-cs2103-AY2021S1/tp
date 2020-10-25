package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ITEM_NAME_BANANA;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ITEMS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_LOCATIONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_RECIPES;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalItems.APPLE;
import static seedu.address.testutil.TypicalItems.BANANA;
import static seedu.address.testutil.TypicalLocations.DENVER;
import static seedu.address.testutil.TypicalLocations.NEW_YORK;
import static seedu.address.testutil.TypicalRecipes.APPLE_PIE;
import static seedu.address.testutil.TypicalRecipes.BANANA_PIE;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.item.Item;
import seedu.address.model.location.Location;
import seedu.address.model.recipe.Recipe;
import seedu.address.testutil.ItemListBuilder;
import seedu.address.testutil.LocationListBuilder;
import seedu.address.testutil.RecipeListBuilder;

public class ModelManagerTest {

    private static final Predicate<Item> ALWAYS_FALSE_PREDICATE_ITEM = unused -> false;
    private static final Predicate<Location> ALWAYS_FALSE_PREDICATE_LOCATION = unused -> false;
    private static final Predicate<Recipe> ALWAYS_FALSE_PREDICATE_RECIPE = unused -> false;

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new ItemList(), new ItemList(modelManager.getItemList()));
        assertEquals(new LocationList(), new LocationList(modelManager.getLocationList()));
        assertEquals(new RecipeList(), new RecipeList(modelManager.getRecipeList()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setItemListFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setItemListFilePath(null));
    }

    @Test
    public void setLocationListFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setLocationListFilePath(null));
    }

    @Test
    public void setRecipeListFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setRecipeListFilePath(null));
    }

    @Test
    public void setItemListFilePath_validPath_setsItemListFilePath() {
        Path path = Paths.get("item/list/file/path");
        modelManager.setItemListFilePath(path);
        assertEquals(path, modelManager.getItemListFilePath());
    }

    @Test
    public void setLocationListFilePath_validPath_setsLocationListFilePath() {
        Path path = Paths.get("location/list/file/path");
        modelManager.setLocationListFilePath(path);
        assertEquals(path, modelManager.getLocationListFilePath());
    }

    @Test
    public void setRecipeListFilePath_validPath_setsRecipeListFilePath() {
        Path path = Paths.get("recipe/list/file/path");
        modelManager.setRecipeListFilePath(path);
        assertEquals(path, modelManager.getRecipeListFilePath());
    }

    @Test
    public void hasItem_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasItem(null));
    }

    @Test
    public void hasLocation_nullLocation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasLocation(null));
    }

    @Test
    public void hasRecipe_nullRecipe_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasRecipe(null));
    }

    @Test
    public void hasItem_itemNotInItemList_returnsFalse() {
        assertFalse(modelManager.hasItem(APPLE));
    }

    @Test
    public void hasLocation_locationNotInLocationList_returnsFalse() {
        assertFalse(modelManager.hasLocation(NEW_YORK));
    }

    @Test
    public void hasRecipe_recipeNotInRecipeList_returnsFalse() {
        assertFalse(modelManager.hasRecipe(APPLE_PIE));
    }

    @Test
    public void hasItem_itemInItemList_returnsTrue() {
        modelManager.addItem(APPLE);
        assertTrue(modelManager.hasItem(APPLE));
    }

    @Test
    public void hasLocation_locationInLocationList_returnsTrue() {
        modelManager.addLocation(NEW_YORK);
        assertTrue(modelManager.hasLocation(NEW_YORK));
    }

    @Test
    public void hasRecipe_recipeInRecipeList_returnsTrue() {
        modelManager.addRecipe(APPLE_PIE);
        assertTrue(modelManager.hasRecipe(APPLE_PIE));
    }

    @Test
    public void getFilteredItemList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager
                .getFilteredItemList().remove(0));
    }

    @Test
    public void getFilteredLocationList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager
                .getFilteredLocationList().remove(0));
    }

    @Test
    public void getFilteredRecipeList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager
                .getFilteredRecipeList().remove(0));
    }

    @Test
    public void testUpdateRecipeNames() {
        modelManager.addRecipe(BANANA_PIE);
        modelManager.updateRecipeNames("Apple", "Banana");

        ModelManager expectedModelManager = new ModelManager();
        Recipe updatedRecipe = BANANA_PIE.setProductName(VALID_ITEM_NAME_BANANA);
        expectedModelManager.addRecipe(updatedRecipe);

        assertEquals(expectedModelManager, modelManager);
    }

    @Test
    public void equals() {
        ItemList itemList = new ItemListBuilder()
                .withItem(APPLE)
                .withItem(BANANA)
                .build();
        LocationList locationList = new LocationListBuilder()
                .withLocation(NEW_YORK)
                .withLocation(DENVER)
                .build();
        RecipeList recipeList = new RecipeListBuilder()
                .withRecipe(APPLE_PIE)
                .withRecipe(BANANA_PIE)
                .build();
        ItemList differentItemList = new ItemList();
        LocationList differentLocationList = new LocationList();
        RecipeList differentRecipeList = new RecipeList();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(itemList, locationList, recipeList, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(itemList, locationList, recipeList, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different lists -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentItemList,
                locationList, recipeList, userPrefs)));
        assertFalse(modelManager.equals(new ModelManager(itemList,
                differentLocationList, recipeList, userPrefs)));
        assertFalse(modelManager.equals(new ModelManager(itemList,
                locationList, differentRecipeList, userPrefs)));

        // different filteredItemList -> returns false
        modelManager.updateFilteredItemList(ALWAYS_FALSE_PREDICATE_ITEM);
        assertFalse(modelManager.equals(new ModelManager(itemList, locationList, recipeList, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);

        // different filteredLocationList -> returns false
        modelManager.updateFilteredLocationList(ALWAYS_FALSE_PREDICATE_LOCATION);
        assertFalse(modelManager.equals(new ModelManager(itemList, locationList, recipeList, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredLocationList(PREDICATE_SHOW_ALL_LOCATIONS);

        // different filteredItemList -> returns false
        modelManager.updateFilteredRecipeList(ALWAYS_FALSE_PREDICATE_RECIPE);
        assertFalse(modelManager.equals(new ModelManager(itemList, locationList, recipeList, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredRecipeList(PREDICATE_SHOW_ALL_RECIPES);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setItemListFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(itemList, locationList, recipeList, differentUserPrefs)));
    }
}
