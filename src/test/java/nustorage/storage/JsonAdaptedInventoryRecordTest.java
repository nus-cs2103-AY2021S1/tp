package nustorage.storage;


import static nustorage.logic.commands.CommandTestUtil.DATE_1;
import static nustorage.logic.commands.CommandTestUtil.DATE_2;
import static nustorage.logic.commands.CommandTestUtil.ITEM_NAME_1;
import static nustorage.logic.commands.CommandTestUtil.ITEM_NAME_2;
import static nustorage.logic.commands.CommandTestUtil.ITEM_NAME_3;
import static nustorage.logic.commands.CommandTestUtil.QUANTITY_1;
import static nustorage.logic.commands.CommandTestUtil.QUANTITY_2;
import static nustorage.logic.commands.CommandTestUtil.QUANTITY_3;
import static nustorage.logic.commands.CommandTestUtil.TIME_1;
import static nustorage.logic.commands.CommandTestUtil.TIME_2;
import static nustorage.testutil.Assert.assertThrows;
import static nustorage.testutil.TypicalInventoryRecords.INVENTORY_RECORD_A;
import static nustorage.testutil.TypicalInventoryRecords.INVENTORY_RECORD_B;
import static nustorage.testutil.TypicalInventoryRecords.INVENTORY_RECORD_C;
import static org.junit.jupiter.api.Assertions.assertEquals;

import nustorage.commons.exceptions.IllegalValueException;
import nustorage.model.record.InventoryRecord;
import org.junit.jupiter.api.Test;


class JsonAdaptedInventoryRecordTest {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Inventory record's %s field is missing!";


    @Test
    void toModelType_validSourceRecordA_returnsInventoryRecord() throws Exception {
        JsonAdaptedInventoryRecord IR = new JsonAdaptedInventoryRecord(INVENTORY_RECORD_A);
        assertEquals(INVENTORY_RECORD_A, IR.toModelType());
    }


    @Test
    void toModelType_validSourceRecordB_returnsInventoryRecord() throws Exception {
        JsonAdaptedInventoryRecord IR = new JsonAdaptedInventoryRecord(INVENTORY_RECORD_B);
        assertEquals(INVENTORY_RECORD_B, IR.toModelType());
    }


    @Test
    void toModelType_validSourceRecordC_returnsInventoryRecord() throws Exception {
        JsonAdaptedInventoryRecord IR = new JsonAdaptedInventoryRecord(INVENTORY_RECORD_C);
        assertEquals(INVENTORY_RECORD_C, IR.toModelType());
    }


    @Test
    void toModelType_validDetails1_returnsInventoryRecord() throws Exception {
        JsonAdaptedInventoryRecord IR = new JsonAdaptedInventoryRecord(ITEM_NAME_1, QUANTITY_1, DATE_1, TIME_1);
        assertEquals(new InventoryRecord(ITEM_NAME_1, QUANTITY_1, DATE_1, TIME_1), IR.toModelType());
    }


    @Test
    void toModelType_validDetails2_returnsInventoryRecord() throws Exception {
        JsonAdaptedInventoryRecord IR = new JsonAdaptedInventoryRecord(ITEM_NAME_2, QUANTITY_2, DATE_2, TIME_2);
        assertEquals(new InventoryRecord(ITEM_NAME_2, QUANTITY_2, DATE_2, TIME_2), IR.toModelType());
    }


    @Test
    void toModelType_validDetails3_returnsInventoryRecord() throws Exception {
        JsonAdaptedInventoryRecord IR = new JsonAdaptedInventoryRecord(ITEM_NAME_3, QUANTITY_3, DATE_1, TIME_2);
        assertEquals(new InventoryRecord(ITEM_NAME_3, QUANTITY_3, DATE_1, TIME_2), IR.toModelType());
    }


    @Test
    void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedInventoryRecord IR = new JsonAdaptedInventoryRecord(null, QUANTITY_1, DATE_1, TIME_1);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "item name");
        assertThrows(IllegalValueException.class, expectedMessage, IR::toModelType);
    }


    @Test
    void toModelType_invalidQuantity_throwsIllegalValueException() {
        JsonAdaptedInventoryRecord IR = new JsonAdaptedInventoryRecord(ITEM_NAME_2, -1, DATE_2, TIME_2);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "quantity");
        assertThrows(IllegalValueException.class, expectedMessage, IR::toModelType);
    }


    @Test
    void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedInventoryRecord IR = new JsonAdaptedInventoryRecord(ITEM_NAME_3, QUANTITY_3, null, TIME_1);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "date");
        assertThrows(IllegalValueException.class, expectedMessage, IR::toModelType);
    }


    @Test
    void toModelType_invalidTime_throwsIllegalValueException() {
        JsonAdaptedInventoryRecord IR = new JsonAdaptedInventoryRecord(ITEM_NAME_3, QUANTITY_3, DATE_1, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "time");
        assertThrows(IllegalValueException.class, expectedMessage, IR::toModelType);
    }

}
