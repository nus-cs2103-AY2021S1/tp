package nustorage.storage;

import static nustorage.logic.commands.CommandTestUtil.AMOUNT_A;
import static nustorage.logic.commands.CommandTestUtil.AMOUNT_B;
import static nustorage.logic.commands.CommandTestUtil.AMOUNT_C;
import static nustorage.logic.commands.CommandTestUtil.AMOUNT_D;
import static nustorage.logic.commands.CommandTestUtil.DATE_TIME_A;
import static nustorage.logic.commands.CommandTestUtil.DATE_TIME_B;
import static nustorage.logic.commands.CommandTestUtil.DATE_TIME_C;
import static nustorage.logic.commands.CommandTestUtil.DATE_TIME_D;
import static nustorage.logic.commands.CommandTestUtil.HAS_INVENTORY_A;
import static nustorage.logic.commands.CommandTestUtil.HAS_INVENTORY_B;
import static nustorage.logic.commands.CommandTestUtil.HAS_INVENTORY_C;
import static nustorage.logic.commands.CommandTestUtil.HAS_INVENTORY_D;
import static nustorage.logic.commands.CommandTestUtil.ID_A;
import static nustorage.logic.commands.CommandTestUtil.ID_B;
import static nustorage.logic.commands.CommandTestUtil.ID_C;
import static nustorage.logic.commands.CommandTestUtil.ID_D;
import static nustorage.testutil.Assert.assertThrows;
import static nustorage.testutil.TypicalFinanceRecords.RECORD_A;
import static nustorage.testutil.TypicalFinanceRecords.RECORD_B;
import static nustorage.testutil.TypicalFinanceRecords.RECORD_C;
import static nustorage.testutil.TypicalFinanceRecords.RECORD_D;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import nustorage.commons.exceptions.IllegalValueException;
import nustorage.model.record.FinanceRecord;

class JsonAdaptedFinanceRecordTest {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Finance record's %s field is missing!";

    @Test
    void toModelType_validSourceRecordA_returnsFinanceRecord() throws Exception {
        JsonAdaptedFinanceRecord testFinanceRecord = new JsonAdaptedFinanceRecord(RECORD_A);
        assertEquals(RECORD_A, testFinanceRecord.toModelType());
    }

    @Test
    void toModelType_validSourceRecordB_returnsFinanceRecord() throws Exception {
        JsonAdaptedFinanceRecord testFinanceRecord = new JsonAdaptedFinanceRecord(RECORD_B);
        assertEquals(RECORD_B, testFinanceRecord.toModelType());
    }

    @Test
    void toModelType_validSourceRecordC_returnsFinanceRecord() throws Exception {
        JsonAdaptedFinanceRecord testFinanceRecord = new JsonAdaptedFinanceRecord(RECORD_C);
        assertEquals(RECORD_C, testFinanceRecord.toModelType());
    }

    @Test
    void toModelType_validSourceRecordD_returnsFinanceRecord() throws Exception {
        JsonAdaptedFinanceRecord testFinanceRecord = new JsonAdaptedFinanceRecord(RECORD_D);
        assertEquals(RECORD_D, testFinanceRecord.toModelType());
    }

    @Test
    void toModelType_validDetailsA_returnsFinanceRecord() throws Exception {
        JsonAdaptedFinanceRecord testFinanceRecord =
                new JsonAdaptedFinanceRecord(ID_A, AMOUNT_A, DATE_TIME_A, HAS_INVENTORY_A);
        assertEquals(new FinanceRecord(ID_A, AMOUNT_A, DATE_TIME_A, HAS_INVENTORY_A), testFinanceRecord.toModelType());
    }

    @Test
    void toModelType_validDetailsB_returnsFinanceRecord() throws Exception {
        JsonAdaptedFinanceRecord testFinanceRecord =
                new JsonAdaptedFinanceRecord(ID_B, AMOUNT_B, DATE_TIME_B, HAS_INVENTORY_B);
        assertEquals(new FinanceRecord(ID_B, AMOUNT_B, DATE_TIME_B, HAS_INVENTORY_B), testFinanceRecord.toModelType());
    }

    @Test
    void toModelType_validDetailsC_returnsFinanceRecord() throws Exception {
        JsonAdaptedFinanceRecord testFinanceRecord =
                new JsonAdaptedFinanceRecord(ID_C, AMOUNT_C, DATE_TIME_C, HAS_INVENTORY_C);
        assertEquals(new FinanceRecord(ID_C, AMOUNT_C, DATE_TIME_C, HAS_INVENTORY_C), testFinanceRecord.toModelType());
    }

    @Test
    void toModelType_validDetailsD_returnsFinanceRecord() throws Exception {
        JsonAdaptedFinanceRecord testFinanceRecord =
                new JsonAdaptedFinanceRecord(ID_D, AMOUNT_D, DATE_TIME_D, HAS_INVENTORY_D);
        assertEquals(new FinanceRecord(ID_D, AMOUNT_D, DATE_TIME_D, HAS_INVENTORY_D), testFinanceRecord.toModelType());
    }

    @Test
    void toModelType_invalidAmount_throwsIllegalValueException() {
        JsonAdaptedFinanceRecord testFinanceRecord =
                new JsonAdaptedFinanceRecord(ID_A, -1, DATE_TIME_D, HAS_INVENTORY_D);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "amount");
        assertThrows(IllegalValueException.class, expectedMessage, testFinanceRecord::toModelType);
    }

    @Test
    void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedFinanceRecord testFinanceRecord =
                new JsonAdaptedFinanceRecord(ID_B, AMOUNT_B, null, HAS_INVENTORY_B);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "date-time");
        assertThrows(IllegalValueException.class, expectedMessage, testFinanceRecord::toModelType);
    }
}
