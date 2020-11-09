package nustorage.model.record;

import static nustorage.logic.commands.CommandTestUtil.AMOUNT_A;
import static nustorage.logic.commands.CommandTestUtil.AMOUNT_B;
import static nustorage.logic.commands.CommandTestUtil.AMOUNT_C;
import static nustorage.logic.commands.CommandTestUtil.AMOUNT_D;
import static nustorage.logic.commands.CommandTestUtil.DATE_TIME_A;
import static nustorage.logic.commands.CommandTestUtil.DATE_TIME_B;
import static nustorage.logic.commands.CommandTestUtil.DATE_TIME_C;
import static nustorage.logic.commands.CommandTestUtil.DATE_TIME_D;
import static nustorage.logic.commands.CommandTestUtil.ID_A;
import static nustorage.logic.commands.CommandTestUtil.ID_B;
import static nustorage.logic.commands.CommandTestUtil.ID_C;
import static nustorage.logic.commands.CommandTestUtil.ID_D;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nustorage.model.FinanceAccount;
import nustorage.model.record.exceptions.DuplicateFinanceRecordException;
import nustorage.model.record.exceptions.FinanceRecordNotFoundException;
import nustorage.testutil.TypicalFinanceRecords;

public class FinanceRecordListTest {

    private FinanceAccount financeAccount = TypicalFinanceRecords.getTypicalFinanceAccount();

    private FinanceRecordList financeList = financeAccount.getFinanceRecordList();

    @BeforeEach
    public void reset() {
        financeAccount = TypicalFinanceRecords.getTypicalFinanceAccount();
        financeList = financeAccount.getFinanceRecordList();
    }

    @Test
    public void contains_listDoesNotContainRecord_failure() {
        FinanceRecord notInList1 = new FinanceRecord(1425, LocalDateTime.now());
        FinanceRecord notInList2 = new FinanceRecord(1111);
        FinanceRecord notInList3 = new FinanceRecord(123456789, 2000, LocalDateTime.now(), false);

        assertFalse(financeList.contains(notInList1));
        assertFalse(financeList.contains(notInList2));
        assertFalse(financeList.contains(notInList3));
    }

    @Test
    public void contains_listContainsRecord_success() {
        FinanceRecord inList1 = new FinanceRecord(ID_A, AMOUNT_A, DATE_TIME_A, false);
        FinanceRecord inList2 = new FinanceRecord(ID_B, AMOUNT_B, DATE_TIME_B, false);
        FinanceRecord inList3 = new FinanceRecord(ID_C, AMOUNT_C, DATE_TIME_C, false);
        FinanceRecord inList4 = new FinanceRecord(ID_D, AMOUNT_D, DATE_TIME_D, false);

        assertTrue(financeList.contains(inList1));
        assertTrue(financeList.contains(inList2));
        assertTrue(financeList.contains(inList3));
        assertTrue(financeList.contains(inList4));
    }

    @Test
    public void getFinanceRecord_listRetrievesRecord_success() {
        FinanceRecord firstRecord = financeList.getFinanceRecord(ID_A);
        FinanceRecord secondRecord = financeList.getFinanceRecord(ID_B);
        FinanceRecord thirdRecord = financeList.getFinanceRecord(ID_C);

        FinanceRecord firstExpectedRecord = new FinanceRecord(ID_A, AMOUNT_A, DATE_TIME_A, false);
        FinanceRecord secondExpectedRecord = new FinanceRecord(ID_B, AMOUNT_B, DATE_TIME_B, false);
        FinanceRecord thirdExpectedRecord = new FinanceRecord(ID_C, AMOUNT_C, DATE_TIME_C, false);

        assertEquals(firstRecord, firstExpectedRecord);
        assertEquals(secondRecord, secondExpectedRecord);
        assertEquals(thirdRecord, thirdExpectedRecord);
    }

    @Test
    public void getFinanceRecord_noSuchRecord_failure() {
        int firstInvalidId = 0;
        int secondInvalidId = -1;
        int thirdInvalidId = 1;

        assertThrows(FinanceRecordNotFoundException.class, () -> financeList.getFinanceRecord(firstInvalidId));
        assertThrows(FinanceRecordNotFoundException.class, () -> financeList.getFinanceRecord(secondInvalidId));
        assertThrows(FinanceRecordNotFoundException.class, () -> financeList.getFinanceRecord(thirdInvalidId));
    }

    @Test
    public void remove_listDoesNotContainSpecifiedRecord_failure() {
        FinanceRecord notInList1 = new FinanceRecord(1425, LocalDateTime.now());
        FinanceRecord notInList2 = new FinanceRecord(1111);
        FinanceRecord notInList3 = new FinanceRecord(123456789, 2000, LocalDateTime.now(), false);

        assertThrows(FinanceRecordNotFoundException.class, () -> financeList.remove(notInList1));
        assertThrows(FinanceRecordNotFoundException.class, () -> financeList.remove(notInList2));
        assertThrows(FinanceRecordNotFoundException.class, () -> financeList.remove(notInList3));
    }

    @Test
    public void add_listAlreadyHasRecord_failure() {
        FinanceRecord inList1 = new FinanceRecord(ID_A, AMOUNT_A, DATE_TIME_A, false);
        FinanceRecord inList2 = new FinanceRecord(ID_B, AMOUNT_B, DATE_TIME_B, false);
        FinanceRecord inList3 = new FinanceRecord(ID_C, AMOUNT_C, DATE_TIME_C, false);
        FinanceRecord inList4 = new FinanceRecord(ID_D, AMOUNT_D, DATE_TIME_D, false);

        assertThrows(DuplicateFinanceRecordException.class, () -> financeList.add(inList1));
        assertThrows(DuplicateFinanceRecordException.class, () -> financeList.add(inList2));
        assertThrows(DuplicateFinanceRecordException.class, () -> financeList.add(inList3));
        assertThrows(DuplicateFinanceRecordException.class, () -> financeList.add(inList4));
    }

    @Test
    public void setFinanceRecord_listDoesNotContainRecord_failure() {
        FinanceRecord notInList1 = new FinanceRecord(1425, LocalDateTime.now());
        FinanceRecord notInList2 = new FinanceRecord(1111);
        FinanceRecord notInList3 = new FinanceRecord(123456789, 2000, LocalDateTime.now(), false);

        FinanceRecord newRecord1 = new FinanceRecord(1728, LocalDateTime.now());
        FinanceRecord newRecord2 = new FinanceRecord(2222);
        FinanceRecord newRecord3 = new FinanceRecord(123, 2000, LocalDateTime.now(), false);

        assertThrows(FinanceRecordNotFoundException.class, () -> financeList.setFinanceRecord(notInList1, newRecord1));
        assertThrows(FinanceRecordNotFoundException.class, () -> financeList.setFinanceRecord(notInList2, newRecord2));
        assertThrows(FinanceRecordNotFoundException.class, () -> financeList.setFinanceRecord(notInList3, newRecord3));
    }
}
