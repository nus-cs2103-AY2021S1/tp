package nustorage.model.record;

import static nustorage.logic.commands.CommandTestUtil.COST_1;
import static nustorage.logic.commands.CommandTestUtil.COST_2;
import static nustorage.logic.commands.CommandTestUtil.DATE_TIME_A;
import static nustorage.logic.commands.CommandTestUtil.DATE_TIME_B;
import static nustorage.logic.commands.CommandTestUtil.DATE_TIME_C;
import static nustorage.logic.commands.CommandTestUtil.EDGE_COST;
import static nustorage.logic.commands.CommandTestUtil.EDGE_QUANTITY;
import static nustorage.logic.commands.CommandTestUtil.ID_A;
import static nustorage.logic.commands.CommandTestUtil.ID_B;
import static nustorage.logic.commands.CommandTestUtil.ID_C;
import static nustorage.logic.commands.CommandTestUtil.ITEM_NAME_1;
import static nustorage.logic.commands.CommandTestUtil.ITEM_NAME_2;
import static nustorage.logic.commands.CommandTestUtil.ITEM_NAME_3;
import static nustorage.logic.commands.CommandTestUtil.QUANTITY_1;
import static nustorage.logic.commands.CommandTestUtil.QUANTITY_2;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nustorage.model.Inventory;
import nustorage.model.record.exceptions.InventoryRecordNotFoundException;
import nustorage.testutil.TypicalInventoryRecords;

public class InventoryRecordListTest {

    private Inventory inventory = TypicalInventoryRecords.getTypicalInventory();

    private InventoryRecordList inventoryRecordList = inventory.getInventoryRecordsList();

    @BeforeEach
    public void reset() {
        inventory = TypicalInventoryRecords.getTypicalInventory();
        inventoryRecordList = inventory.getInventoryRecordsList();
    }

    @Test
    public void remove_listDoesNotContainSpecifiedRecord_failure() {
        String sampleItemName1 = "firstItem";
        String sampleItemName2 = "secondItem";
        int sampleQuantity1 = 3;
        int sampleQuantity2 = 4;
        Double unitCost1 = 10.0;
        Double unitCost2 = 16.2;
        LocalDateTime sampleDateTime1 = LocalDateTime.of(2020, 9, 9, 23, 59);
        LocalDateTime sampleDateTime2 = LocalDateTime.of(2020, 10, 10, 12, 34);
        InventoryRecord sampleInventoryRecord1 =
                new InventoryRecord(sampleItemName1, sampleQuantity1, unitCost1, sampleDateTime1);
        InventoryRecord sampleInventoryRecord2 =
                new InventoryRecord(sampleItemName2, sampleQuantity2, unitCost2, sampleDateTime2);

        assertThrows(InventoryRecordNotFoundException.class, () -> inventoryRecordList.remove(sampleInventoryRecord1));
        assertThrows(InventoryRecordNotFoundException.class, () -> inventoryRecordList.remove(sampleInventoryRecord1));
    }

    @Test
    public void setInventoryRecord_listContainsSpecifiedRecord_success() {
        InventoryRecord inList1 =
                new InventoryRecord(ITEM_NAME_1, QUANTITY_1, EDGE_COST, DATE_TIME_A, ID_A);
        InventoryRecord inList2 =
                new InventoryRecord(ITEM_NAME_2, EDGE_QUANTITY, COST_2, DATE_TIME_B, ID_B);
        InventoryRecord inList3 =
                new InventoryRecord(ITEM_NAME_3, QUANTITY_2, COST_1, DATE_TIME_C, ID_C);

        String sampleItemName = "newItem";
        int sampleQuantity = 6;
        Double unitCost = 20.0;
        LocalDateTime sampleDateTime = LocalDateTime.of(2020, 4, 4, 23, 59);
        InventoryRecord newInventoryRecord =
                new InventoryRecord(sampleItemName, sampleQuantity, unitCost, sampleDateTime);
        assertDoesNotThrow(() -> inventoryRecordList.setInventoryRecord(inList1, newInventoryRecord));

        //Now, inList1 is no longer inside the list and thus qualifies as a new edited record
        assertDoesNotThrow(() -> inventoryRecordList.setInventoryRecord(inList2, inList1));

        //Now, inList2 is no longer inside the list and thus qualifies as a new edited record
        assertDoesNotThrow(() -> inventoryRecordList.setInventoryRecord(inList3, inList2));
    }
}
