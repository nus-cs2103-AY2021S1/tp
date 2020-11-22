package nustorage.model;

import static nustorage.testutil.Assert.assertThrows;
import static nustorage.testutil.TypicalFinanceRecords.RECORD_A;
import static nustorage.testutil.TypicalInventoryRecords.INVENTORY_RECORD_A;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import nustorage.commons.core.GuiSettings;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    //=========== FinanceAccount ================================================================================

    @Test
    public void hasFinanceRecord_nullFinanceRecord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasFinanceRecord(null));
    }

    @Test
    public void hasFinanceRecord_financeRecordNotPresent_returnsFalse() {
        assertFalse(modelManager.hasFinanceRecord(RECORD_A));
    }

    @Test
    public void hasFinanceRecord_financeRecordPresent_returnsTrue() {
        modelManager.addFinanceRecord(RECORD_A);
        assertTrue(modelManager.hasFinanceRecord(RECORD_A));
    }

    //=========== Inventory ================================================================================

    @Test
    public void hasInventoryRecord_nullInventoryRecord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasInventoryRecord(null));
    }

    @Test
    public void hasInventoryRecord_inventoryRecordNotPresent_returnsFalse() {
        assertFalse(modelManager.hasInventoryRecord(INVENTORY_RECORD_A));
    }

    @Test
    public void hasInventoryRecord_inventoryRecordPresent_returnsTrue() {
        modelManager.addInventoryRecord(INVENTORY_RECORD_A);
        assertTrue(modelManager.hasInventoryRecord(INVENTORY_RECORD_A));
    }

    @Test
    public void deleteInventoryRecordSuccessful_returnsTrue() {
        modelManager.addInventoryRecord(INVENTORY_RECORD_A);
        modelManager.deleteInventoryRecord(INVENTORY_RECORD_A);
        assertTrue(modelManager.getInventory().getInventoryRecordList().isEmpty());
    }
}
