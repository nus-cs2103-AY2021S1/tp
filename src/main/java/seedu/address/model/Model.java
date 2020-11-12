package seedu.address.model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {

    /** {@code Predicate} that evaluates to true if person's archive status is false. */
    Predicate<Person> PREDICATE_SHOW_ALL_ACTIVE_PERSONS = person ->
            !(person.getArchiveStatus().archiveStatus);

    /** {@code Predicate} that evaluates to true if person's archive status is true. */
    Predicate<Person> PREDICATE_SHOW_ALL_ARCHIVED_PERSONS = person -> (
            person.getArchiveStatus().archiveStatus);

    Predicate<Person> PREDICATE_SHOW_ALL_ACTIVE_MONDAY_PERSONS = person ->((
            !person.getArchiveStatus().archiveStatus)
                    && person.getTags().toString().toLowerCase().contains("monday"));
    Predicate<Person> PREDICATE_SHOW_ALL_ACTIVE_TUESDAY_PERSONS = person -> ((
            !person.getArchiveStatus().archiveStatus)
                    && person.getTags().toString().toLowerCase().contains("tuesday"));
    Predicate<Person> PREDICATE_SHOW_ALL_ACTIVE_WEDNESDAY_PERSONS = person -> ((
            !person.getArchiveStatus().archiveStatus)
                    && person.getTags().toString().toLowerCase().contains("wednesday"));
    Predicate<Person> PREDICATE_SHOW_ALL_ACTIVE_THURSDAY_PERSONS = person -> ((
            !person.getArchiveStatus().archiveStatus)
                    && person.getTags().toString().toLowerCase().contains("thursday"));
    Predicate<Person> PREDICATE_SHOW_ALL_ACTIVE_FRIDAY_PERSONS = person -> ((
            !person.getArchiveStatus().archiveStatus)
                    && person.getTags().toString().toLowerCase().contains("friday"));
    Predicate<Person> PREDICATE_SHOW_ALL_ACTIVE_SATURDAY_PERSONS = person -> ((
            !person.getArchiveStatus().archiveStatus)
                    && person.getTags().toString().toLowerCase().contains("saturday"));
    Predicate<Person> PREDICATE_SHOW_ALL_ACTIVE_SUNDAY_PERSONS = person -> ((
            !person.getArchiveStatus().archiveStatus)
                    && person.getTags().toString().toLowerCase().contains("sunday"));

    Predicate<Ingredient> PREDICATE_SHOW_ALL_INGREDIENTS = unused -> true;
    Predicate<SalesRecordEntry> PREDICATE_SHOW_ALL_SALES_RECORD_ENTRY = unused -> true;

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

    /**
     * Returns the user prefs' sales book file path.
     */
    Path getSalesBookFilePath();

    /**
     * Returns the user prefs' ingredient book file path.
     */
    Path getIngredientBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Sets the user prefs' sales book file path.
     */
    void setSalesBookFilePath(Path salesBookFilePath);

    /**
     * Sets the user prefs' ingredient book file path.
     */
    void setIngredientBookFilePath(Path ingredientBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /**
     * Replaces ingredient book data with the data in {@code ingredientBook}.
     */
    void setIngredientBook(ReadOnlyIngredientBook ingredientBook);

    /**
     * Returns the AddressBook
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns the IngredientBook
     */
    ReadOnlyIngredientBook getIngredientBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Adds an ingredient to the ingredient book.
     * The ingredient must not already exist in the ingredient book.
     */
    void addIngredient(Ingredient ingredient);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    void setIngredient(Ingredient target, Ingredient newAmount);

    /**
     * Returns an ArrayList of ingredients that are in shortage. If
     * no ingredient is in shortage, return an empty ArrayList.
     *
     * @return an ArrayList of ingredients that are in shortage
     */
    ArrayList<Ingredient> findIngredientInShortage();

    /**
     * Replaces sales book data with the data in {@code salesBook}.
     */
    void setSalesBook(ReadOnlySalesBook salesBook);

    /**
     * Returns the SalesBook.
     */
    SalesBook getSalesBook();

    /**
     * Returns true if the SalesBook does not contain any sales record.
     * @return
     */
    boolean isEmptySalesBook();

    /**
     * Overwrite the sales records in SalesBook based on the given {@Code salesInput}
     * @param salesInput
     */
    void overwrite(Map<Drink, Integer> salesInput);

    /**
     * Sorts the sales records in the SalesBook in descending order
     */
    void sortSalesBook();

    /**
     * Adds an SalesRecordEntry to the SalesBook.
     */
    void addSalesRecordEntry(SalesRecordEntry salesRecordEntry);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns an unmodifiable view of the filtered ingredient list
     */
    ObservableList<Ingredient> getFilteredIngredientList();

    /**
     * Returns an unmodifiable view of the sales record list
     */
    ObservableList<SalesRecordEntry> getFilteredSalesRecordList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Updates the filter of the filtered sales record list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredSalesRecordList(Predicate<SalesRecordEntry> predicate);

    /**
     * Updates the filter of the filtered ingredient list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredIngredientList(Predicate<Ingredient> predicate);
}

