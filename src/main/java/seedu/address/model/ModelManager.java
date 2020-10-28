package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.ingredient.IngredientName;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {

    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final SalesBook salesBook;
    private final IngredientBook ingredientBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Ingredient> filteredIngredients;
    private final FilteredList<SalesRecordEntry> filteredSalesRecordList;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlySalesBook salesBook,
                        ReadOnlyIngredientBook ingredientBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, salesBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook
                + " sales book: " + salesBook
                + " Ingredients book: " + ingredientBook
                + " and user prefs" + " " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.salesBook = new SalesBook(salesBook);
        this.ingredientBook = new IngredientBook(ingredientBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList(),
                Model.PREDICATE_SHOW_ALL_ACTIVE_PERSONS);
        filteredIngredients = new FilteredList<>(this.ingredientBook.getIngredientList(),
                Model.PREDICATE_SHOW_ALL_INGREDIENTS);
        filteredSalesRecordList = new FilteredList<>(this.salesBook.getSalesRecord(),
                Model.PREDICATE_SHOW_ALL_SALES_RECORD_ENTRY);
    }

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager() {
        this(new AddressBook(), new SalesBook(),
                new IngredientBook(), new UserPrefs());
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
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public Path getSalesBookFilePath() {
        return userPrefs.getSalesBookFilePath();
    }

    @Override
    public Path getIngredientBookFilePath() {
        return userPrefs.getIngredientBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    @Override
    public void setSalesBookFilePath(Path salesBookFilePath) {
        requireNonNull(salesBookFilePath);
        userPrefs.setSalesBookFilePath(salesBookFilePath);
    }

    @Override
    public void setIngredientBookFilePath(Path ingredientBookFilePath) {
        requireNonNull(ingredientBookFilePath);
        userPrefs.setIngredientBookFilePath(ingredientBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public void setIngredientBook(ReadOnlyIngredientBook ingredientBook) {
        this.ingredientBook.setData(ingredientBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public ReadOnlyIngredientBook getIngredientBook() {
        return ingredientBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    //Added here
    @Override
    public boolean hasIngredient(Ingredient ingredient) {
        requireNonNull(ingredient);
        return ingredientBook.hasIngredient(ingredient);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_ACTIVE_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }
    //=========== SalesBook ==================================================================================

    @Override
    public void setSalesBook(ReadOnlySalesBook salesBook) {
        this.salesBook.resetData(salesBook);
    }

    @Override
    public SalesBook getSalesBook() {
        return salesBook;
    }

    @Override
    public boolean isEmptySalesBook() {
        return salesBook.isEmptySalesRecord();
    }

    @Override
    public void overwrite(Map<Drink, Integer> salesInput) {
        salesBook.overwriteSales(salesInput);
    }

    @Override
    public void addSalesRecordEntry(SalesRecordEntry salesRecordEntry) {
        salesBook.addSalesRecordEntry(salesRecordEntry);
        updateFilteredSalesList(PREDICATE_SHOW_ALL_SALES_RECORD_ENTRY);
    }

    //=========== IngredientBook ==================================================================================

    @Override
    public void setIngredient(Ingredient target, Ingredient newAmount) {
        requireAllNonNull(target, newAmount);

        ingredientBook.setIngredient(target, newAmount);
    }

    @Override
    public Ingredient findIngredientByName(IngredientName ingredientName) {
        requireNonNull(ingredientName);

        return ingredientBook.findIngredientByName(ingredientName);
    }

    @Override
    public void addIngredient(Ingredient ingredient) {
        ingredientBook.addIngredient(ingredient);
        updateFilteredIngredientList(PREDICATE_SHOW_ALL_INGREDIENTS);
    }

    /**
     * Returns an ArrayList of ingredients that are in shortage. If
     * no ingredient is in shortage, return an empty ArrayList.
     *
     * @return an ArrayList of ingredients that are in shortage
     */
    @Override
    public ArrayList<Ingredient> findIngredientInShortage() {
        return ingredientBook.findIngredientInShortage();
    }


    //=========== Filtered List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Ingredient} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Ingredient> getFilteredIngredientList() {
        return filteredIngredients;
    }

    @Override
    public ObservableList<SalesRecordEntry> getFilteredSalesRecordList() {
        return filteredSalesRecordList;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public void updateFilteredSalesList(Predicate<SalesRecordEntry> predicate) {
        requireNonNull(predicate);
        filteredSalesRecordList.setPredicate(predicate);
    }

    @Override
    public void updateFilteredIngredientList(Predicate<Ingredient> predicate) {
        requireNonNull(predicate);
        filteredIngredients.setPredicate(predicate);
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
        return addressBook.equals(other.addressBook)
                && salesBook.equals(other.salesBook)
                && ingredientBook.equals(other.ingredientBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}
