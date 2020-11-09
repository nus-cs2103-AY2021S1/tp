package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemPrecursor;
import seedu.address.model.item.exceptions.ItemNotFoundException;
import seedu.address.model.location.Location;
import seedu.address.model.recipe.IngredientList;
import seedu.address.model.recipe.IngredientPrecursor;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.recipe.RecipePrecursor;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final UserPrefs userPrefs;
    private final Inventory inventory;
    private final VersionedInventory versionedInventory;
    private final FilteredList<Item> filteredItems;
    private final FilteredList<Location> filteredLocations;
    private final FilteredList<Recipe> filteredRecipes;

    /**
     * Initializes a ModelManager with the given itemList, locationList, recipeList and userPrefs.
     */
    public ModelManager(ReadOnlyItemList itemList, ReadOnlyLocationList locationList,
                        ReadOnlyRecipeList recipeList, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(userPrefs, itemList, locationList, recipeList);

        logger.fine("Initializing with item list: " + itemList
                + " location list: " + locationList
                + " recipe list: " + recipeList
                + " and user prefs " + userPrefs);

        this.userPrefs = new UserPrefs(userPrefs);
        this.inventory = new Inventory(new ItemList(itemList), new LocationList(locationList),
                new RecipeList(recipeList));
        this.versionedInventory = new VersionedInventory(inventory);
        filteredItems = new FilteredList<>(this.inventory.getItemList().getItemList());
        filteredLocations = new FilteredList<>(this.inventory.getLocationList().getLocationList());
        filteredRecipes = new FilteredList<>(this.inventory.getRecipeList().getRecipeList());

    }

    public ModelManager() {
        this(new ItemList(), new LocationList(), new RecipeList(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getItemListFilePath() {
        return userPrefs.getItemListFilePath();
    }

    @Override
    public Path getLocationListFilePath() {
        return userPrefs.getLocationListFilePath();
    }

    @Override
    public Path getRecipeListFilePath() {
        return userPrefs.getRecipeListFilePath();
    }

    @Override
    public void setItemListFilePath(Path itemListFilePath) {
        requireNonNull(itemListFilePath);
        userPrefs.setItemListFilePath(itemListFilePath);
    }

    @Override
    public void setLocationListFilePath(Path locationListFilePath) {
        requireNonNull(locationListFilePath);
        userPrefs.setLocationListFilePath(locationListFilePath);
    }

    @Override
    public void setRecipeListFilePath(Path recipeListFilePath) {
        requireNonNull(recipeListFilePath);
        userPrefs.setRecipeListFilePath(recipeListFilePath);
    }

    //=========== Item and Location List =====================================================================

    @Override
    public void setItemList(ReadOnlyItemList itemList) {
        this.inventory.getItemList().resetData(itemList);
    }

    @Override
    public void setLocationList(LocationList locationList) {
        this.inventory.getLocationList().resetData(locationList);
    }

    @Override
    public void setRecipeList(ReadOnlyRecipeList recipeList) {
        this.inventory.getRecipeList().resetData(recipeList);
    }

    @Override
    public ReadOnlyItemList getItemList() {
        return this.inventory.getItemList();
    }

    @Override
    public ReadOnlyLocationList getLocationList() {
        return this.inventory.getLocationList();
    }

    @Override
    public ReadOnlyRecipeList getRecipeList() {
        return this.inventory.getRecipeList();
    }

    @Override
    public boolean hasItem(Item item) {
        requireNonNull(item);
        return this.inventory.getItemList().hasItem(item);
    }

    @Override
    public boolean hasLocation(Location location) {
        requireNonNull(location);
        return this.inventory.getLocationList().hasLocation(location);
    }

    @Override
    public boolean hasRecipe(Recipe recipe) {
        requireNonNull(recipe);
        return this.inventory.getRecipeList().hasRecipe(recipe);
    }

    @Override
    public void deleteItem(Item target) {
        this.inventory.getItemList().deleteItem(target);
    }

    @Override
    public void deleteRecipe(Recipe target) {
        this.inventory.getRecipeList().deleteRecipe(target);
    }

    @Override
    public void addItem(Item item) {
        this.inventory.getItemList().addItem(item);
        updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS);
    }

    @Override
    public void addLocation(Location location) {
        this.inventory.getLocationList().addLocation(location);
    }

    @Override
    public void addRecipe(Recipe recipe) {
        this.inventory.getRecipeList().addRecipe(recipe);
        this.inventory.getItemList().addRecipeIdToItem(recipe.getProductId(), recipe.getId());
        updateFilteredRecipeList(PREDICATE_SHOW_ALL_RECIPES);
    }

    @Override
    public void setItem(Item target, Item editedItem) {
        requireAllNonNull(target, editedItem);

        this.inventory.getItemList().setItem(target, editedItem);
    }

    @Override
    public void setRecipe(Recipe target, Recipe editedRecipe) {
        requireAllNonNull(target, editedRecipe);

        this.inventory.getRecipeList().setRecipe(target, editedRecipe);
    }

    //=========== Filtered Item and Location List Accessors ==================================================

    @Override
    public ObservableList<Item> getFilteredItemList() {
        return filteredItems;
    }

    @Override
    public void resetItemFilters() {
        filteredItems.setPredicate(x -> true);
    }

    @Override
    public ObservableList<Location> getFilteredLocationList() {
        return filteredLocations;
    }

    @Override
    public ObservableList<Recipe> getFilteredRecipeList() {
        return filteredRecipes;
    }

    @Override
    public void resetRecipeFilters() {
        filteredRecipes.setPredicate(x -> true);
    }

    @Override
    public void updateFilteredItemList(Predicate<Item> predicate) {
        requireNonNull(predicate);
        filteredItems.setPredicate(predicate);
    }

    @Override
    public void updateFilteredLocationList(Predicate<Location> predicate) {
        requireNonNull(predicate);
        filteredLocations.setPredicate(predicate);
    }

    @Override
    public void updateFilteredRecipeList(Predicate<Recipe> predicate) {
        requireNonNull(predicate);
        filteredRecipes.setPredicate(predicate);
    }

    @Override
    public int findLocationID(Location toFind) {
        requireNonNull(toFind);
        return this.inventory.getLocationList().findLocationID(toFind);
    }

    private int findItemIdByName(String itemName) throws ItemNotFoundException {
        requireNonNull(itemName);
        int id = this.inventory.getItemList().findItemIdByName(itemName);
        if (id == -1) {
            throw new ItemNotFoundException();
        }
        return id;
    }

    @Override
    public Recipe processPrecursor(RecipePrecursor recipePrecursor) throws ItemNotFoundException {
        int productId = findItemIdByName(recipePrecursor.getProductName());

        List<IngredientPrecursor> ingredientPrecursors = recipePrecursor.getIngredientPrecursors();
        IngredientList ingredients = new IngredientList();
        for (IngredientPrecursor precursor : ingredientPrecursors) {
            int ingredientId = findItemIdByName(precursor.getKey());
            ingredients.add(precursor.toIngredient(ingredientId));
        }
        return recipePrecursor.toRecipe(productId, ingredients);
    }

    @Override
    public Item processPrecursor(ItemPrecursor itemPrecursor) {
        Set<Integer> locationIds = new HashSet<>();
        for (String locationName : itemPrecursor.getLocationNames()) {
            locationIds.add(findLocationIdByName(locationName));
        }
        return itemPrecursor.toItem(locationIds, new HashSet<>());
    }

    @Override
    public void updateRecipeNames(String originalName, String updatedName) {
        filteredRecipes.stream()
                .filter(recipe -> recipe.getProductName().equals(originalName))
                .forEach(recipe -> setRecipe(recipe, recipe.setProductName(updatedName)));
    }

    private int findLocationIdByName(String location) {
        requireNonNull(location);
        String trimmedLocation = location.trim();
        Location toAdd = new Location(trimmedLocation);

        if (hasLocation(toAdd)) {
            Location.decrementIdCounter();
            return findLocationID(toAdd);
        }

        addLocation(toAdd);
        return toAdd.getId();
    }

    //=========== Undo/Redo helpers ========================================================================

    @Override
    public void commitInventory() {
        versionedInventory.commit(this);
    }

    @Override
    public boolean undoInventory() {
        return versionedInventory.undo(this);
    }

    @Override
    public boolean redoInventory() {
        return versionedInventory.redo(this);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return userPrefs.equals(other.userPrefs)
                && inventory.equals(other.inventory)
                && filteredItems.equals(other.filteredItems)
                && filteredLocations.equals(other.filteredLocations)
                && filteredRecipes.equals(other.filteredRecipes);
    }

    @Override
    public String toString() {
        return "ModelManager{"
                + "userPrefs=" + userPrefs
                + ", itemList=" + inventory.getItemList()
                + ", filteredItems=" + filteredItems
                + ", locationList=" + inventory.getLocationList()
                + ", filteredLocations=" + filteredLocations
                + ", recipeList=" + inventory.getRecipeList()
                + ", filteredRecipes=" + filteredRecipes
                + '}';
    }
}
