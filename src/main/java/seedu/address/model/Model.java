package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.item.Item;
import seedu.address.model.location.Location;
import seedu.address.model.person.Person;
import seedu.address.model.recipe.Recipe;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Item> PREDICATE_SHOW_ALL_ITEMS = unused -> true;
    Predicate<Recipe> PREDICATE_SHOW_ALL_RECIPES = unused -> true;

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
    Path getAddressBookFilePath();

    Path getItemListFilePath();

    Path getLocationListFilePath();

    Path getRecipeListFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    void setItemListFilePath(Path itemListFilePath);

    void setRecipeListFilePath(Path recipeListFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    void setItemList(ReadOnlyItemList itemList);

    void setRecipeList(ReadOnlyRecipeList recipeList);

    /**
     * Returns the AddressBook
     */
    ReadOnlyAddressBook getAddressBook();

    ReadOnlyItemList getItemList();

    ReadOnlyLocationList getLocationList();

    ReadOnlyRecipeList getRecipeList();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    boolean hasItem(Item item);

    boolean hasLocation(Location location);

    boolean hasRecipe(Recipe recipe);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    void deleteItem(Item target);

    void deleteRecipe(Recipe target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    void addItem(Item item);

    void addLocation(Location location);

    void addRecipe(Recipe recipe);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    void setItem(Item target, Item editedItem);

    void setRecipe(Recipe target, Recipe editedRecipe);

    /**
     * Returns an unmodifiable view of the filtered person list
     */
    ObservableList<Person> getFilteredPersonList();

    ObservableList<Item> getFilteredItemList();

    ObservableList<Location> getFilteredLocationList();

    ObservableList<Recipe> getFilteredRecipeList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    void updateFilteredItemList(Predicate<Item> predicate);

    void updateFilteredLocationList(Predicate<Location> predicate);

    void updateFilteredRecipeList(Predicate<Recipe> predicate);

    int findLocationID(Location toFind);
}
