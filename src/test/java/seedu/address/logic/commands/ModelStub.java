package seedu.address.logic.commands;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyItemList;
import seedu.address.model.ReadOnlyRecipeList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.item.Item;
import seedu.address.model.location.Location;
import seedu.address.model.recipe.Recipe;

/**
 * A default model stub that have all of the methods failing EXCEPT:
 *
 * 1. processPrecursors and getFilteredItemList
 * This is because we minimally need the processPrecursors method to handle AddItemCommand and RecipeDeletion
 * to reset the data to its original state (when needed).
 *
 * 2. getItemList(), getRecipeList(), getLocationList()
 * This is because these methods are accessed via VersionedInventory#commit by nearly all commands.
 */
public class ModelStub extends ModelManager implements Model {
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public GuiSettings getGuiSettings() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getItemListFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getLocationListFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getRecipeListFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setItemListFilePath(Path itemListFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setRecipeListFilePath(Path recipeListFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addItem(Item item) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addLocation(Location location) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addRecipe(Recipe recipe) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setItemList(ReadOnlyItemList itemList) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setRecipeList(ReadOnlyRecipeList recipeList) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasItem(Item item) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasLocation(Location location) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasRecipe(Recipe recipe) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteItem(Item target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteRecipe(Recipe target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setItem(Item target, Item editedItem) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setRecipe(Recipe target, Recipe editedRecipe) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Location> getFilteredLocationList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Recipe> getFilteredRecipeList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredItemList(Predicate<Item> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredLocationList(Predicate<Location> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredRecipeList(Predicate<Recipe> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public int findLocationID(Location toFind) {
        throw new AssertionError("This method should not be called.");
    }
}
