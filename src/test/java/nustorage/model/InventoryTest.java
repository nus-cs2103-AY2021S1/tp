package nustorage.model;

import static nustorage.logic.commands.CommandTestUtil.ITEM_NAME_1;
import static nustorage.testutil.Assert.assertThrows;
import static nustorage.testutil.TypicalInventoryRecords.INVENTORY_RECORD_A;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nustorage.model.record.InventoryRecord;
import nustorage.model.record.exceptions.DuplicateInventoryRecordException;
import nustorage.testutil.InventoryRecordBuilder;

public class InventoryTest {
    private final Inventory inventory = new Inventory();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), inventory.getInventoryRecordList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> inventory.resetData(null));
    }

    @Test
    public void resetData_withValidReadonlyInventory_replacesData() {
        Inventory newData = new Inventory();
        inventory.resetData(newData);
        assertEquals(newData, inventory);
    }

    @Test
    public void resetData_withDuplicateInventoryRecords_throwsDuplicateInventoryException() {
        // Two inventoryRecords with the same identity fields
        InventoryRecord editedRecordA = new InventoryRecordBuilder(INVENTORY_RECORD_A).build();
        List<InventoryRecord> newInventoryRecords = Arrays.asList(INVENTORY_RECORD_A, editedRecordA);
        InventoryTest.InventoryStub newData = new InventoryTest.InventoryStub(newInventoryRecords);

        assertThrows(DuplicateInventoryRecordException.class, () -> inventory.resetData(newData));
    }

    @Test
    public void hasInventoryRecord_nullInventoryRecord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> inventory.hasInventoryRecord(null));
    }

    @Test
    public void hasInventoryRecord_inventoryRecordNotInAddressBook_returnsFalse() {
        assertFalse(inventory.hasInventoryRecord(INVENTORY_RECORD_A));
    }

    @Test
    public void hasInventoryRecord_inventoryRecordInInventory_returnsTrue() {
        inventory.addInventoryRecord(INVENTORY_RECORD_A);
        assertTrue(inventory.hasInventoryRecord(INVENTORY_RECORD_A));
    }

    @Test
    public void hasInventoryRecord_inventoryRecordWithSameIdentityFieldsInInventory_returnsTrue() {
        inventory.addInventoryRecord(INVENTORY_RECORD_A);
        InventoryRecord inventoryRecord = new InventoryRecordBuilder(
                INVENTORY_RECORD_A).withName(ITEM_NAME_1).build();
        assertTrue(inventory.hasInventoryRecord(inventoryRecord));
    }

    @Test
    public void getInventoryRecordList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> inventory.getInventoryRecordList().remove(0));
    }

    /**
     * A stub ReadonlyInventory whose inventoryRecords list can violate interface constraints.
     */
    private static class InventoryStub implements ReadOnlyInventory {
        private final ObservableList<InventoryRecord> inventoryRecords = FXCollections.observableArrayList();

        InventoryStub(Collection<InventoryRecord> inventoryRecords) {
            this.inventoryRecords.setAll(inventoryRecords);
        }

        @Override
        public ObservableList<InventoryRecord> getInventoryRecordList() {
            return inventoryRecords;
        }
    }
}
