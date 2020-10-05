package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
<<<<<<< Updated upstream:src/main/java/seedu/address/model/ModelManager.java
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
=======
import jimmy.mcgymmy.commons.core.GuiSettings;
import jimmy.mcgymmy.commons.core.LogsCenter;
import jimmy.mcgymmy.commons.util.CollectionUtil;
import jimmy.mcgymmy.model.food.Food;
>>>>>>> Stashed changes:src/main/java/jimmy/mcgymmy/model/ModelManager.java

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final McGymmy mcGymmy;
    private final UserPrefs userPrefs;
    private final FilteredList<Food> filteredFoodItems;

    /**
     * Initializes a ModelManager with the given mcGymmy and userPrefs.
     */
    public ModelManager(ReadOnlyMcGymmy mcGymmy, ReadOnlyUserPrefs userPrefs) {
        super();
<<<<<<< Updated upstream:src/main/java/seedu/address/model/ModelManager.java
        requireAllNonNull(addressBook, userPrefs);
=======
        CollectionUtil.requireAllNonNull(mcGymmy, userPrefs);
>>>>>>> Stashed changes:src/main/java/jimmy/mcgymmy/model/ModelManager.java

        logger.fine("Initializing with address book: " + mcGymmy + " and user prefs " + userPrefs);

        this.mcGymmy = new McGymmy(mcGymmy);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredFoodItems = new FilteredList<>(this.mcGymmy.getFoodList());
    }

    public ModelManager() {
        this(new McGymmy(), new UserPrefs());
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
    public Path getMcGymmyFilePath() {
        return userPrefs.getMcGymmyFilePath();
    }

    @Override
    public void setMcGymmyFilePath(Path mcGymmyFilePath) {
        requireNonNull(mcGymmyFilePath);
        userPrefs.setMcGymmyFilePath(mcGymmyFilePath);
    }

    //=========== McGymmy ================================================================================

    @Override
    public void setMcGymmy(ReadOnlyMcGymmy mcGymmy) {
        this.mcGymmy.resetData(mcGymmy);
    }

    @Override
    public ReadOnlyMcGymmy getMcGymmy() {
        return mcGymmy;
    }

    @Override
    public boolean hasFood(Food food) {
        requireNonNull(food);
        return mcGymmy.hasFood(food);
    }

    @Override
    public void deleteFood(Food target) {
        mcGymmy.removeFood(target);
    }

    @Override
    public void addFood(Food food) {
        mcGymmy.addFood(food);
        updateFilteredFoodList(PREDICATE_SHOW_ALL_FOODS);
    }

    @Override
<<<<<<< Updated upstream:src/main/java/seedu/address/model/ModelManager.java
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
=======
    public void setFood(Food target, Food editedFood) {
        CollectionUtil.requireAllNonNull(target, editedFood);
>>>>>>> Stashed changes:src/main/java/jimmy/mcgymmy/model/ModelManager.java

        mcGymmy.setFood(target, editedFood);
    }

    //=========== Filtered Food List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Food} backed by the internal list of
     * {@code versionedMcGymmy}
     */
    @Override
    public ObservableList<Food> getFilteredFoodList() {
        return filteredFoodItems;
    }

    @Override
    public void updateFilteredFoodList(Predicate<Food> predicate) {
        requireNonNull(predicate);
        filteredFoodItems.setPredicate(predicate);
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
<<<<<<< Updated upstream:src/main/java/seedu/address/model/ModelManager.java
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
=======
        return mcGymmy.equals(other.mcGymmy)
            && userPrefs.equals(other.userPrefs)
            && filteredFoodItems.equals(other.filteredFoodItems);
>>>>>>> Stashed changes:src/main/java/jimmy/mcgymmy/model/ModelManager.java
    }

}
