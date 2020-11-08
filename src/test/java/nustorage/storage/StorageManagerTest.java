package nustorage.storage;


import static nustorage.logic.commands.CommandTestUtil.AMOUNT_B;
import static nustorage.logic.commands.CommandTestUtil.COST_1;
import static nustorage.logic.commands.CommandTestUtil.HAS_INVENTORY_A;
import static nustorage.logic.commands.CommandTestUtil.ID_A;
import static nustorage.logic.commands.CommandTestUtil.ITEM_NAME_1;
import static nustorage.logic.commands.CommandTestUtil.QUANTITY_1;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import nustorage.commons.core.GuiSettings;
import nustorage.model.FinanceAccount;
import nustorage.model.Inventory;
import nustorage.model.ReadOnlyFinanceAccount;
import nustorage.model.ReadOnlyInventory;
import nustorage.model.UserPrefs;
import nustorage.model.record.FinanceRecord;
import nustorage.model.record.InventoryRecord;


public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    private Path prefsFilePath;
    private Path financeFilePath;
    private Path inventoryFilePath;


    @BeforeEach
    public void setUp() {
        prefsFilePath = getTempFilePath("prefs");
        financeFilePath = getTempFilePath("finance");
        inventoryFilePath = getTempFilePath("inventory");

        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(prefsFilePath);
        JsonFinanceAccountStorage financeAccountStorage = new JsonFinanceAccountStorage(financeFilePath);
        JsonInventoryStorage inventoryStorage = new JsonInventoryStorage(inventoryFilePath);

        storageManager = new StorageManager(financeAccountStorage, inventoryStorage, userPrefsStorage);
    }


    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }


    @Test
    public void getUserPrefsFilePath_returnsCorrectPath() {
        assertEquals(prefsFilePath, storageManager.getUserPrefsFilePath());
    }


    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }


    @Test
    public void getInventoryFilePath_returnsCorrectPath() {
        assertEquals(inventoryFilePath, storageManager.getInventoryFilePath());
    }


    @Test
    public void inventoryReadSave() throws Exception {
        Inventory original = new Inventory();
        original.addInventoryRecord(new InventoryRecord(ITEM_NAME_1, QUANTITY_1, COST_1));
        storageManager.saveInventory(original);
        ReadOnlyInventory retrieved = storageManager.readInventory().get();
        assertEquals(original, retrieved);
    }


    @Test
    public void getFinanceFilePath_returnsCorrectPath() {
        assertEquals(financeFilePath, storageManager.getFinanceAccountFilePath());
    }


    @Test
    public void financeReadSave() throws Exception {
        FinanceAccount original = new FinanceAccount();
        original.addFinanceRecord(new FinanceRecord(ID_A, AMOUNT_B, HAS_INVENTORY_A));
        storageManager.saveFinanceAccount(original);
        ReadOnlyFinanceAccount retrieved = storageManager.readFinanceAccount().get();
        assertEquals(original, retrieved);
    }

}
