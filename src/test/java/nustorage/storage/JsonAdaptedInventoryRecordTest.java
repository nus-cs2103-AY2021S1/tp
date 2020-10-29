package nustorage.storage;


import static nustorage.logic.commands.CommandTestUtil.COST_1;
import static nustorage.logic.commands.CommandTestUtil.COST_2;
import static nustorage.logic.commands.CommandTestUtil.COST_3;
import static nustorage.logic.commands.CommandTestUtil.DATE_TIME_A;
import static nustorage.logic.commands.CommandTestUtil.DATE_TIME_B;
import static nustorage.logic.commands.CommandTestUtil.ID_A;
import static nustorage.logic.commands.CommandTestUtil.ID_B;
import static nustorage.logic.commands.CommandTestUtil.ITEM_NAME_1;
import static nustorage.logic.commands.CommandTestUtil.ITEM_NAME_2;
import static nustorage.logic.commands.CommandTestUtil.ITEM_NAME_3;
import static nustorage.logic.commands.CommandTestUtil.QUANTITY_1;
import static nustorage.logic.commands.CommandTestUtil.QUANTITY_2;
import static nustorage.logic.commands.CommandTestUtil.QUANTITY_3;
import static nustorage.testutil.Assert.assertThrows;
import static nustorage.testutil.TypicalInventoryRecords.INVENTORY_RECORD_A;
import static nustorage.testutil.TypicalInventoryRecords.INVENTORY_RECORD_B;
import static nustorage.testutil.TypicalInventoryRecords.INVENTORY_RECORD_C;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import nustorage.commons.exceptions.IllegalValueException;
import nustorage.model.record.InventoryRecord;


class JsonAdaptedInventoryRecordTest {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Inventory record's %s field is missing!";


    @Test
    void toModelType_validSourceRecordA_returnsInventoryRecord() throws Exception {
        JsonAdaptedInventoryRecord testInventoryRecord = new JsonAdaptedInventoryRecord(INVENTORY_RECORD_A);
        assertEquals(INVENTORY_RECORD_A, testInventoryRecord.toModelType());
    }


    @Test
    void toModelType_validSourceRecordB_returnsInventoryRecord() throws Exception {
        JsonAdaptedInventoryRecord testInventoryRecord = new JsonAdaptedInventoryRecord(INVENTORY_RECORD_B);
        assertEquals(INVENTORY_RECORD_B, testInventoryRecord.toModelType());
    }


    @Test
    void toModelType_validSourceRecordC_returnsInventoryRecord() throws Exception {
        JsonAdaptedInventoryRecord testInventoryRecord = new JsonAdaptedInventoryRecord(INVENTORY_RECORD_C);
        assertEquals(INVENTORY_RECORD_C, testInventoryRecord.toModelType());
    }


    @Test
    void toModelType_validDetails1_returnsInventoryRecord() throws Exception {
        JsonAdaptedInventoryRecord testInventoryRecord =
                new JsonAdaptedInventoryRecord(ITEM_NAME_1, QUANTITY_1, COST_1, DATE_TIME_A, ID_A);
        assertEquals(new InventoryRecord(ITEM_NAME_1, QUANTITY_1, COST_1, DATE_TIME_A, ID_A),
                testInventoryRecord.toModelType());
    }


    @Test
    void toModelType_validDetails2_returnsInventoryRecord() throws Exception {
        JsonAdaptedInventoryRecord testInventoryRecord =
                new JsonAdaptedInventoryRecord(ITEM_NAME_2, QUANTITY_2, COST_2, DATE_TIME_B, ID_B);
        assertEquals(new InventoryRecord(ITEM_NAME_2, QUANTITY_2, COST_2, DATE_TIME_B, ID_B),
                testInventoryRecord.toModelType());
    }


    @Test
    void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedInventoryRecord testInventoryRecord =
                new JsonAdaptedInventoryRecord(null, QUANTITY_1, COST_1, DATE_TIME_A, ID_A);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "item name");
        assertThrows(IllegalValueException.class, expectedMessage, testInventoryRecord::toModelType);
    }


    @Test
    void toModelType_invalidQuantity_throwsIllegalValueException() {
        JsonAdaptedInventoryRecord testInventoryRecord =
                new JsonAdaptedInventoryRecord(ITEM_NAME_2, -1, COST_2, DATE_TIME_B, ID_B);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "quantity");
        assertThrows(IllegalValueException.class, expectedMessage, testInventoryRecord::toModelType);
    }


    @Test
    void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedInventoryRecord testInventoryRecord =
                new JsonAdaptedInventoryRecord(ITEM_NAME_2, QUANTITY_2, COST_2, null, ID_B);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "dateTime");
        assertThrows(IllegalValueException.class, expectedMessage, testInventoryRecord::toModelType);
    }

    @Test
    void toModelType_invalidCost_throwsIllegalValueException() {
        JsonAdaptedInventoryRecord testInventoryRecord =
                new JsonAdaptedInventoryRecord(ITEM_NAME_2, QUANTITY_2, -15.7, DATE_TIME_B, ID_B);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "unitCost");
        assertThrows(IllegalValueException.class, expectedMessage, testInventoryRecord::toModelType);
    }

}
