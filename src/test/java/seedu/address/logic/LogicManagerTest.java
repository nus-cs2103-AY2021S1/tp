package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalItems.APPLE;
import static seedu.address.testutil.TypicalItems.getTypicalItemList;
import static seedu.address.testutil.TypicalRecipes.BANANA_PIE;
import static seedu.address.testutil.TypicalRecipes.FOUND_APPLE;
import static seedu.address.testutil.TypicalRecipes.getTypicalRecipeList;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.InventoryComponent;
import seedu.address.model.LocationList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyItemList;
import seedu.address.model.ReadOnlyLocationList;
import seedu.address.model.ReadOnlyRecipeList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.storage.Storage;
import seedu.address.ui.DisplayedInventoryType;

/**
 * Contains integration tests with the model.
 */
public class LogicManagerTest {

    private Model model;
    private LogicManager logicManager;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalItemList(), new LocationList(), getTypicalRecipeList(), new UserPrefs());
        logicManager = new LogicManager(model, new StorageStub());
    }

    /**
     * Tests for successful processing of Item, Recipe and Detailed Item.
     */
    @Test
    public void getInventoryList_success() {
        ArrayList<InventoryComponent> items = logicManager.getInventoryList(DisplayedInventoryType.ITEMS);
        ArrayList<InventoryComponent> expectedItems = new ArrayList<>(getTypicalItemList().getItemList());
        assertEquals(expectedItems, items);

        ArrayList<InventoryComponent> recipes = logicManager.getInventoryList(DisplayedInventoryType.RECIPES);
        ArrayList<InventoryComponent> expectedRecipes = new ArrayList<>();
        model.getFilteredRecipeList().forEach(recipe -> expectedRecipes.add(recipe.print(model.getFilteredItemList())));
        assertEquals(expectedRecipes, recipes);

        // Detailed view only has 1 item with its associated recipes
        model.updateFilteredItemList(item -> item.getName().equals("Apple"));
        model.updateFilteredRecipeList(recipe -> recipe.getProductName().equals("Apple"));
        ArrayList<InventoryComponent> detailed = logicManager.getInventoryList(DisplayedInventoryType.DETAILED_ITEM);
        ArrayList<InventoryComponent> expectedDetailed = new ArrayList<>();
        // manually add the item and relevant recipes
        expectedDetailed.add(APPLE);
        expectedDetailed.add(BANANA_PIE);
        expectedDetailed.add(FOUND_APPLE);
        assertEquals(expectedDetailed, detailed);
    }

    /**
     * Tests for assertion error thrown when attempt to retrieve "unchanged" type.
     */
    @Test
    public void getInventoryList_error() {
        assertThrows(AssertionError.class, () -> logicManager.getInventoryList(DisplayedInventoryType.UNCHANGED));
    }

    /**
     * Storage Stub used for testing of getInventoryList which is not dependent on storage
     */
    private class StorageStub implements Storage {

        @Override
        public Path getUserPrefsFilePath() {
            return null;
        }

        @Override
        public Optional<UserPrefs> readUserPrefs() {
            return Optional.empty();
        }

        @Override
        public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) {

        }

        @Override
        public Path getItemListFilePath() {
            return null;
        }

        @Override
        public Optional<ReadOnlyItemList> readItemList() {
            return Optional.empty();
        }

        @Override
        public Optional<ReadOnlyItemList> readItemList(Path filePath) {
            return Optional.empty();
        }

        @Override
        public void saveItemList(ReadOnlyItemList itemList) {

        }

        @Override
        public void saveItemList(ReadOnlyItemList addressBook, Path filePath) {

        }

        @Override
        public void saveLocationList(ReadOnlyLocationList addressBook) {

        }

        @Override
        public void saveLocationList(ReadOnlyLocationList addressBook, Path filePath) {

        }

        @Override
        public Path getLocationListFilePath() {
            return null;
        }

        @Override
        public Optional<ReadOnlyLocationList> readLocationList() {
            return Optional.empty();
        }

        @Override
        public Optional<ReadOnlyLocationList> readLocationList(Path filePath) {
            return Optional.empty();
        }

        @Override
        public Path getRecipeListFilePath() {
            return null;
        }

        @Override
        public Optional<ReadOnlyRecipeList> readRecipeList() {
            return Optional.empty();
        }

        @Override
        public Optional<ReadOnlyRecipeList> readRecipeList(Path filePath) {
            return Optional.empty();
        }

        @Override
        public void saveRecipeList(ReadOnlyRecipeList recipeList) {

        }

        @Override
        public void saveRecipeList(ReadOnlyRecipeList recipeList, Path filePath) {

        }

        @Override
        public void saveModel(Model model) {

        }
    }
}
