package nustorage.model;


import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import nustorage.commons.core.GuiSettings;
import nustorage.model.person.Person;
import nustorage.model.record.FinanceRecord;
import nustorage.model.record.InventoryRecord;


/**
 * The API of the Model component.
 */
public interface Model {

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<InventoryRecord> PREDICATE_SHOW_ALL_INVENTORY = unused -> true;
    Predicate<InventoryRecord> PREDICATE_REMOVE_ALL_INVENTORY = unused -> false;
    Predicate<FinanceRecord> PREDICATE_SHOW_ALL_FINANCE = unused -> true;
    Predicate<FinanceRecord> PREDICATE_REMOVE_ALL_FINANCE = unused -> false;


    // ---------- UserPrefs ----------


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


    // ---------- AddressBook ----------


    /**
     * * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();


    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);


    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);


    /**
     * Returns the AddressBook
     */
    ReadOnlyAddressBook getAddressBook();


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
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);


    /**
     * Returns an unmodifiable view of the filtered person list
     */
    ObservableList<Person> getFilteredPersonList();


    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);


    // --------- Finance Account ---------


    void deleteFinanceRecord(FinanceRecord target);


    ReadOnlyFinanceAccount getFinanceAccount();

    void addFinanceRecord(FinanceRecord newRecord);


    void deleteInventoryRecord(InventoryRecord target);


    void setFinanceRecord(FinanceRecord financeRecordToEdit, FinanceRecord editedFinanceRecord);


    ObservableList<FinanceRecord> getFilteredFinanceList();


    // --------- Inventory ---------


    void addInventoryRecord(InventoryRecord inventoryRecord);


    /**
     * Replaces the given InventoryRecord {@code target} with {@code editedInventoryRecord}.
     * {@code target} must exist in the Inventory.
     * The description of {@code editedInventoryRecord} must not be the same as another existing InventoryRecord.
     */
    void setInventoryRecord(InventoryRecord target, InventoryRecord editedInventoryRecord);


    /**
     * Returns true if a record with the same identity as {@code InventoryRecord} exists in the Inventory.
     */
    boolean hasInventoryRecord(InventoryRecord inventoryRecord);


    void updateFilteredInventoryList(Predicate<InventoryRecord> predicate);


    ObservableList<InventoryRecord> getFilteredInventory();


    Inventory getInventory();

    // <<<<<<< HEAD
    //
    // =======
    //     ObservableList<FinanceRecord> getFilteredFinanceList();
    //
    //     /**
    //      * Updates the filter of the filtered person list to filter by the given {@code predicate}.
    //      * @throws NullPointerException if {@code predicate} is null.
    //      */
    //     void updateFilteredPersonList(Predicate<Person> predicate);
    //
    //     void updateFilteredInventoryList(Predicate<InventoryRecord> predicate);


    void updateFilteredFinanceList(Predicate<FinanceRecord> predicate);
    // >>>>>>> ba85e094c7db7ddb7b4601fc17379125a0c3bc68
}
