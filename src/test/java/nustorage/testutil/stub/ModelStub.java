package nustorage.testutil.stub;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import nustorage.commons.core.GuiSettings;
import nustorage.model.Inventory;
import nustorage.model.Model;
import nustorage.model.ReadOnlyAddressBook;
import nustorage.model.ReadOnlyFinanceAccount;
import nustorage.model.ReadOnlyUserPrefs;
import nustorage.model.person.Person;
import nustorage.model.record.FinanceRecord;
import nustorage.model.record.InventoryRecord;

/**
 * A default model stub that have all of the methods failing.
 */
public class ModelStub implements Model {
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
    public void addInventoryRecord(InventoryRecord newRecord) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteInventoryRecord(InventoryRecord target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<InventoryRecord> getFilteredInventory() {
        throw new AssertionError("This method should not be called.");
    }


    @Override
    public Inventory getInventory() {
        throw new AssertionError("This method should not be called.");
    }


    @Override
    public boolean hasInventoryRecord(InventoryRecord inventoryRecord) {
        return true;
    }

    @Override
    public void setInventoryRecord(InventoryRecord a, InventoryRecord b) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addFinanceRecord(FinanceRecord newRecord) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<FinanceRecord> getFilteredFinanceList() {
        return null;
    }

    @Override
    public void setFinanceRecord(FinanceRecord target, FinanceRecord editedFinanceRecord) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deleteFinanceRecord(FinanceRecord target) {

    }

    @Override
    public ReadOnlyFinanceAccount getFinanceAccount() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public Path getAddressBookFilePath() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void addPerson(Person person) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setAddressBook(ReadOnlyAddressBook newData) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public boolean hasPerson(Person person) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void deletePerson(Person target) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredInventoryList(Predicate<InventoryRecord> predicate) {
        throw new AssertionError("This method should not be called.");
    }

    @Override
    public void updateFilteredFinanceList(Predicate<FinanceRecord> predicate) {
        throw new AssertionError("This method should not be called.");
    }
}
