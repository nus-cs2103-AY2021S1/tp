package nustorage.model.record;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class InventoryRecordTest {

    @Test
    public void getDateAndTime_test() {
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

        //Tests for time
        assertEquals(sampleInventoryRecord1.getTime(), sampleDateTime1.toLocalTime());
        assertEquals(sampleInventoryRecord2.getTime(), sampleDateTime2.toLocalTime());

        //Tests for date
        assertEquals(sampleInventoryRecord1.getDate(), sampleDateTime1.toLocalDate());
        assertEquals(sampleInventoryRecord2.getDate(), sampleDateTime2.toLocalDate());
    }

    @Test
    public void setAndGetUiUsableIndex_test() {
        String sampleItemName1 = "firstItem";
        String sampleItemName2 = "secondItem";
        int sampleQuantity1 = 3;
        int sampleQuantity2 = 4;
        Double unitCost1 = 10.0;
        Double unitCost2 = 16.2;
        InventoryRecord sampleInventoryRecord1 =
                new InventoryRecord(sampleItemName1, sampleQuantity1, unitCost1);
        InventoryRecord sampleInventoryRecord2 =
                new InventoryRecord(sampleItemName2, sampleQuantity2, unitCost2);

        int newUiUsableIndex1 = 896745231;
        int newUiUsableIndex2 = 132457689;
        String expectedUiUsableIndex1 = "896745231";
        String expectedUiUsableIndex2 = "132457689";
        sampleInventoryRecord1.setUiUsableIndex(newUiUsableIndex1);
        sampleInventoryRecord2.setUiUsableIndex(newUiUsableIndex2);

        assertEquals(sampleInventoryRecord1.getUiUsableIndex(), expectedUiUsableIndex1);
        assertEquals(sampleInventoryRecord2.getUiUsableIndex(), expectedUiUsableIndex2);
    }

    @Test
    public void equals_test() {
        String sampleItemName1 = "firstItem";
        String sampleItemName2 = "secondItem";
        int sampleQuantity1 = 3;
        int sampleQuantity2 = 4;
        Double unitCost1 = 10.0;
        Double unitCost2 = 16.2;
        InventoryRecord sampleInventoryRecord1 =
                new InventoryRecord(sampleItemName1, sampleQuantity1, unitCost1);
        InventoryRecord sampleInventoryRecord2 =
                new InventoryRecord(sampleItemName2, sampleQuantity2, unitCost2);

        //Same records should be equal
        assertEquals(sampleInventoryRecord1, sampleInventoryRecord1);

        //Copies of records should be equal
        InventoryRecord sampleInventoryRecord1Copy =
                new InventoryRecord(sampleItemName1, sampleQuantity1, unitCost1);
        assertEquals(sampleInventoryRecord1, sampleInventoryRecord1Copy);

        //Different records should not be equal
        assertNotEquals(sampleInventoryRecord1, sampleInventoryRecord2);
    }
}
