package seedu.simplykitchen.model;

import static java.util.Objects.requireNonNull;
import static seedu.simplykitchen.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.simplykitchen.model.util.ComparatorUtil.SORT_BY_DESC_THEN_ASC_EXPIRY;
import static seedu.simplykitchen.model.util.ComparatorUtil.generateSortingComparatorsDescription;
import static seedu.simplykitchen.model.util.ComparatorUtil.getComparator;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.simplykitchen.commons.core.GuiSettings;
import seedu.simplykitchen.commons.core.LogsCenter;
import seedu.simplykitchen.model.food.ExpiryDate;
import seedu.simplykitchen.model.food.Food;

/**
 * Represents the in-memory model of the Food inventory data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final VersionedFoodInventory versionedFoodInventory;
    private final UserPrefs userPrefs;
    private Comparator<Food>[] sortingComparators;
    private final FilteredList<Food> filteredFoods;

    private FilteredList<Food> expiringFilteredFoods;
    private final FilteredList<Food> expiredFilteredFoods;
    private SortedList<Food> expiringSortedFoods;

    private boolean isDataFileInvalid;

    /**
     * Initializes a ModelManager with the given Food Inventory and userPrefs.
     */
    public ModelManager(ReadOnlyFoodInventory foodInventory, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(foodInventory, userPrefs);

        logger.fine("Initializing with Food inventory: " + foodInventory
                + " and user prefs " + userPrefs);

        this.versionedFoodInventory = new VersionedFoodInventory(foodInventory);
        this.userPrefs = new UserPrefs(userPrefs);
        this.sortingComparators = getComparator(userPrefs.getSortingComparatorsDescription());

        filteredFoods = new FilteredList<>(this.versionedFoodInventory.getFoods());

        expiringSortedFoods = new SortedList<>(this.versionedFoodInventory.getFoods());
        updateExpiringSortedFoodList();
        expiringFilteredFoods = new FilteredList<>(expiringSortedFoods);
        expiringFilteredFoods.setPredicate(getExpiringPredicate());
        expiredFilteredFoods = new FilteredList<>(expiringSortedFoods);
        expiredFilteredFoods.setPredicate(getExpiredPredicate());
    }

    /**
     * Initializes a ModelManager with the given Food Inventory and userPrefs.
     */
    public ModelManager(ReadOnlyFoodInventory foodInventory, ReadOnlyUserPrefs userPrefs, boolean isDataFileInvalid) {
        this(foodInventory, userPrefs);
        this.isDataFileInvalid = isDataFileInvalid;
    }

    public ModelManager() {
        this(new FoodInventory(), new UserPrefs());
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
    public Path getFoodInventoryFilePath() {
        return userPrefs.getFoodInventoryFilePath();
    }

    @Override
    public void setFoodInventoryFilePath(Path foodInventoryFilePath) {
        requireNonNull(foodInventoryFilePath);
        userPrefs.setFoodInventoryFilePath(foodInventoryFilePath);
    }

    @Override
    public void setSortingComparators(Comparator<Food>[] sortingComparators) {
        requireNonNull(sortingComparators);
        this.sortingComparators = sortingComparators;
    }

    //=========== FoodInventory ==========================================================================

    @Override
    public void setFoodInventory(ReadOnlyFoodInventory foodInventory) {
        versionedFoodInventory.resetData(foodInventory);
    }

    @Override
    public ReadOnlyFoodInventory getFoodInventory() {
        return versionedFoodInventory;
    }

    @Override
    public boolean hasFood(Food food) {
        requireNonNull(food);
        return versionedFoodInventory.hasFood(food);
    }

    @Override
    public void deleteFood(Food target) {
        versionedFoodInventory.removeFood(target);
    }

    @Override
    public void addFood(Food food) {
        requireNonNull(food);
        versionedFoodInventory.addFood(food);
        updateFilteredFoodList(PREDICATE_SHOW_ALL_FOODS);
        sortFoodInventoryBySortingComparators();
    }

    @Override
    public void setFood(Food target, Food editedFood) {
        requireAllNonNull(target, editedFood);
        versionedFoodInventory.setFood(target, editedFood);
        updateFilteredFoodList(PREDICATE_SHOW_ALL_FOODS);
        sortFoodInventoryBySortingComparators();
    }

    @Override
    public void sortFoodInventory(Comparator<Food>... comparators) {
        requireNonNull(comparators);
        versionedFoodInventory.sortFoods(comparators);
        setSortingComparators(comparators);
        userPrefs.setSortingComparatorsDescription(generateSortingComparatorsDescription(comparators));
    }

    @Override
    public void sortFoodInventoryBySortingComparators() {
        versionedFoodInventory.sortFoods(sortingComparators);
    }

    //=========== Filtered Food List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Food} backed by the internal list of
     * {@code versionedFoodInventory}
     */
    @Override
    public ObservableList<Food> getFilteredFoodList() {
        return filteredFoods;
    }

    @Override
    public ObservableList<Food> getFilteredExpiringFoodList() {
        return expiringFilteredFoods;
    }

    @Override
    public ObservableList<Food> getFilteredExpiredFoodList() {
        return expiredFilteredFoods;
    }

    @Override
    public void updateFilteredFoodList(Predicate<Food> predicate) {
        requireNonNull(predicate);
        filteredFoods.setPredicate(predicate);
        updateExpiringFilteredFoodList();
    }

    @Override
    public void updateExpiringFilteredFoodList() {
        updateExpiringSortedFoodList();
        expiringFilteredFoods = new FilteredList<>(expiringSortedFoods);
        expiringFilteredFoods.setPredicate(getExpiringPredicate());
    }

    //=========== Undo/Redo =================================================================================

    @Override
    public boolean canUndoFoodInventory() {
        return versionedFoodInventory.canUndo();
    }

    @Override
    public boolean canRedoFoodInventory() {
        return versionedFoodInventory.canRedo();
    }

    @Override
    public void undoFoodInventory() {
        versionedFoodInventory.undo();
    }

    @Override
    public void redoFoodInventory() {
        versionedFoodInventory.redo();
    }

    @Override
    public void commitFoodInventory() {
        versionedFoodInventory.commit();
    }

    // ============== Expiring Food List ========================================================================
    @Override
    public void updateExpiringSortedFoodList() {
        expiringSortedFoods.setComparator(SORT_BY_DESC_THEN_ASC_EXPIRY);
    }

    @Override
    public Predicate<Food> getExpiringPredicate() {
        return new Predicate<Food>() {
            @Override
            public boolean test(Food food) {
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d-M-yyyy");

                LocalDate today = LocalDate.now();
                String dateToday = today.format(dateFormat);
                ExpiryDate expiryToday = new ExpiryDate(dateToday);

                LocalDate nextWeek = LocalDate.now().plusDays(7);
                String dateNextWeek = nextWeek.format(dateFormat);
                ExpiryDate expiryDateNextWeek = new ExpiryDate(dateNextWeek);

                ExpiryDate foodExpiry = food.getExpiryDate();

                if ((!foodExpiry.isAfter(expiryDateNextWeek)
                        && foodExpiry.isAfter(expiryToday))
                        || foodExpiry.equals(expiryToday)) {
                    return true;
                }
                return false;
            }
        };
    }

    @Override
    public Predicate<Food> getExpiredPredicate() {
        return new Predicate<Food>() {
            @Override
            public boolean test(Food food) {
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d-M-yyyy");

                LocalDate today = LocalDate.now();
                String dateToday = today.format(dateFormat);
                ExpiryDate expiredToday = new ExpiryDate(dateToday);

                ExpiryDate foodExpiry = food.getExpiryDate();

                return expiredToday.isAfter(foodExpiry);
            }
        };
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
        return versionedFoodInventory.equals(other.versionedFoodInventory)
                && userPrefs.equals(other.userPrefs)
                && filteredFoods.equals(other.filteredFoods)
                && expiringFilteredFoods.equals(other.expiringFilteredFoods);
    }

    @Override
    public boolean isDataFileInvalid() {
        return this.isDataFileInvalid;
    }

}
