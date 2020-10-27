package nustorage.model;


import static java.util.Objects.requireNonNull;
import static nustorage.commons.util.CollectionUtil.requireAllNonNull;

import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import nustorage.commons.core.GuiSettings;
import nustorage.commons.core.LogsCenter;
import nustorage.model.record.FinanceRecord;
import nustorage.model.record.InventoryRecord;


/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {

    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Inventory inventory;
    private final FinanceAccount financeAccount;
    private final UserPrefs userPrefs;
    private final FilteredList<InventoryRecord> filteredInventory;
    private final FilteredList<FinanceRecord> filteredFinance;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyUserPrefs userPrefs) {
        super();
        this.inventory = new Inventory();
        this.financeAccount = new FinanceAccount();
        filteredInventory = new FilteredList<>(this.inventory.getInventoryRecordList());
        filteredFinance = new FilteredList<>(this.financeAccount.getFinanceList());
        this.userPrefs = new UserPrefs(userPrefs);
    }

    /**
     * Initializes a ModelManager with the given financeAccount, inventory and userPrefs
     */
    public ModelManager(ReadOnlyFinanceAccount financeAccount, ReadOnlyInventory inventory,
                        ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(financeAccount, inventory, userPrefs);

        logger.fine("Initializing with finance account " + financeAccount
                + ", inventory " + inventory + " and user prefs " + userPrefs);

        this.financeAccount = new FinanceAccount(financeAccount);
        filteredFinance = new FilteredList<>(this.financeAccount.getFinanceList());

        this.inventory = new Inventory(inventory);
        filteredInventory = new FilteredList<>(this.inventory.getInventoryRecordList());
        this.userPrefs = new UserPrefs(userPrefs);
    }

    public ModelManager() {
        this(new FinanceAccount(), new Inventory(), new UserPrefs());
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

    //=========== Inventory ================================================================================

    public void addInventoryRecord(InventoryRecord newRecord) {
        inventory.addInventoryRecord(newRecord);
    }

    public ObservableList<InventoryRecord> getFilteredInventory() {
        return filteredInventory;
    }

    /**
     * Applies a predicate to the Inventory and returns those that pass it.
     *
     * @param predicate the predicate used to filter Inventory
     */
    public void updateFilteredInventoryList(Predicate<InventoryRecord> predicate) {
        requireNonNull(predicate);
        filteredInventory.setPredicate(predicate);
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public boolean hasInventoryRecord(InventoryRecord inventoryRecord) {
        requireNonNull(inventoryRecord);
        return inventory.hasInventoryRecord(inventoryRecord);
    }

    @Override
    public void setInventoryRecord(InventoryRecord target, InventoryRecord editedInventoryRecord) {
        requireAllNonNull(target, editedInventoryRecord);

        inventory.setInventoryRecord(target, editedInventoryRecord);
    }

    @Override
    public void deleteInventoryRecord(InventoryRecord target) {
        requireAllNonNull(target);

        inventory.removeInventoryRecord(target);
    }

    //=========== FinanceAccount ================================================================================

    @Override
    public void addFinanceRecord(FinanceRecord newRecord) {
        financeAccount.addFinanceRecord(newRecord);
    }

    @Override
    public ObservableList<FinanceRecord> getFilteredFinanceList() {
        return filteredFinance;
    }

    @Override
    public void setFinanceRecord(FinanceRecord target, FinanceRecord editedFinanceRecord) {
        requireAllNonNull(target, editedFinanceRecord);

        financeAccount.setFinanceRecord(target, editedFinanceRecord);
    }

    @Override
    public FinanceRecord getFinanceRecord(Integer target) {
        requireAllNonNull(target);
        return financeAccount.getFinanceRecord(target);
    }

    @Override
    public FinanceRecord getFinanceRecord(InventoryRecord inventoryRecord) {
        return financeAccount.getFinanceRecord(inventoryRecord.getFinanceId());
    }
    // @Override
    // public List<FinanceRecord> viewFinanceRecords() {
    //     // TODO: DORA IMPLEMENT VIEW FINANCE RECORDS.
    //     return null;
    // }

    @Override
    public void updateFilteredFinanceList(Predicate<FinanceRecord> predicate) {
        requireNonNull(predicate);
        filteredFinance.setPredicate(predicate);
    }

    @Override
    public void deleteFinanceRecord(FinanceRecord target) {
        requireNonNull(target);
        financeAccount.removeFinanceRecord(target);
    }

    @Override
    public FinanceAccount getFinanceAccount() {
        return financeAccount;
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
        return filteredInventory.equals(other.getFilteredInventory())
                && filteredFinance.equals(other.getFilteredFinanceList())
                && userPrefs.equals(other.userPrefs);
    }

}
