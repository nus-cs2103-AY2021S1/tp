package nustorage.model;


import java.util.function.Predicate;

import javafx.collections.ObservableList;
import nustorage.commons.core.GuiSettings;
import nustorage.model.record.FinanceRecord;
import nustorage.model.record.InventoryRecord;


/**
 * The API of the Model component.
 */
public interface Model {

    /**
     * {@code Predicate} that always evaluate to true
     */
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

    // --------- FinanceWindow Account ---------
    void deleteFinanceRecord(FinanceRecord target);

    ReadOnlyFinanceAccount getFinanceAccount();

    void addFinanceRecord(FinanceRecord newRecord);

    FinanceRecord getFinanceRecord(Integer id);

    FinanceRecord getFinanceRecord(InventoryRecord inventoryRecord);

    void deleteInventoryRecord(InventoryRecord target);

    void setFinanceRecord(FinanceRecord financeRecordToEdit, FinanceRecord editedFinanceRecord);


    ObservableList<FinanceRecord> getFilteredFinanceList();


    // --------- InventoryWindow ---------

    void addInventoryRecord(InventoryRecord inventoryRecord);

    /**
     * Replaces the given InventoryRecord {@code target} with {@code editedInventoryRecord}.
     * {@code target} must exist in the InventoryWindow.
     * The description of {@code editedInventoryRecord} must not be the same as another existing InventoryRecord.
     */
    void setInventoryRecord(InventoryRecord target, InventoryRecord editedInventoryRecord);


    /**
     * Returns true if a record with the same identity as {@code InventoryRecord} exists in the InventoryWindow.
     */
    boolean hasInventoryRecord(InventoryRecord inventoryRecord);


    void updateFilteredInventoryList(Predicate<InventoryRecord> predicate);


    ObservableList<InventoryRecord> getFilteredInventory();

    Inventory getInventory();

    void updateFilteredFinanceList(Predicate<FinanceRecord> predicate);
}
