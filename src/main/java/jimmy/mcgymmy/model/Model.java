package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
<<<<<<< Updated upstream:src/main/java/seedu/address/model/Model.java
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Person;
=======
import jimmy.mcgymmy.commons.core.GuiSettings;
import jimmy.mcgymmy.model.food.Food;
>>>>>>> Stashed changes:src/main/java/jimmy/mcgymmy/model/Model.java

/**
 * The API of the Model component.
 */
public interface Model {
<<<<<<< Updated upstream:src/main/java/seedu/address/model/Model.java
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
=======
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Food> PREDICATE_SHOW_ALL_FOODS = unused -> true;
>>>>>>> Stashed changes:src/main/java/jimmy/mcgymmy/model/Model.java

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getMcGymmyFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setMcGymmyFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setMcGymmy(ReadOnlyMcGymmy addressBook);

<<<<<<< Updated upstream:src/main/java/seedu/address/model/Model.java
    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();
=======
    /**
     * Returns the McGymmy
     */
    ReadOnlyMcGymmy getMcGymmy();
>>>>>>> Stashed changes:src/main/java/jimmy/mcgymmy/model/Model.java

    /**
     * Returns true if a food with the same identity as {@code food} exists in the address book.
     */
    boolean hasFood(Food food);

    /**
     * Deletes the given food.
     * The food must exist in the address book.
     */
    void deleteFood(Food food);

    /**
     * Adds the given food.
     * {@code food} must not already exist in the address book.
     */
    void addFood(Food food);

    /**
     * Replaces the given food {@code target} with {@code editedFood}.
     * {@code target} must exist in the address book.
     * The food identity of {@code editedFood} must not be the same as another existing food in the address book.
     */
    void setFood(Food target, Food editedFood);

<<<<<<< Updated upstream:src/main/java/seedu/address/model/Model.java
    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
=======
    /**
     * Returns an unmodifiable view of the filtered food list
     */
    ObservableList<Food> getFilteredFoodList();

    /**
     * Updates the filter of the filtered food list to filter by the given {@code predicate}.
     *
>>>>>>> Stashed changes:src/main/java/jimmy/mcgymmy/model/Model.java
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredFoodList(Predicate<Food> predicate);
}
