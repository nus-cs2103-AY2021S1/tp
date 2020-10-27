package nustorage.model;

import static nustorage.logic.commands.CommandTestUtil.AMOUNT_B;
import static nustorage.logic.commands.CommandTestUtil.ID_B;
import static nustorage.testutil.Assert.assertThrows;
import static nustorage.testutil.TypicalFinanceRecords.RECORD_A;
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
import nustorage.model.record.FinanceRecord;
import nustorage.model.record.exceptions.DuplicateFinanceRecordException;
import nustorage.testutil.FinanceRecordBuilder;

public class FinanceAccountTest {
    private FinanceAccount finance = new FinanceAccount();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), finance.getFinanceList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> finance.resetData(null));
    }

    @Test
    public void resetData_withValidReadonlyFinanceAccount_replacesData() {
        FinanceAccount newData = new FinanceAccount();
        finance.resetData(newData);
        assertEquals(newData, finance);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        FinanceRecord record = new FinanceRecordBuilder(RECORD_A).build();
        FinanceRecord editedRecord = new FinanceRecordBuilder(RECORD_A).build();
        List<FinanceRecord> newRecords = Arrays.asList(record, editedRecord);
        FinanceAccountStub newData = new FinanceAccountStub(newRecords);

        assertThrows(DuplicateFinanceRecordException.class, () -> finance.resetData(newData));
    }

    @Test
    public void hasFinanceRecord_nullFinanceRecord_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> finance.hasFinanceRecord(null));
    }

    @Test
    public void hasFinanceRecord_financeRecordNotInAddressBook_returnsFalse() {
        assertFalse(finance.hasFinanceRecord(RECORD_A));
    }

    @Test
    public void haFinanceRecord_financeRecordInFinanceAccount_returnsTrue() {
        finance.addFinanceRecord(RECORD_A);
        assertTrue(finance.hasFinanceRecord(RECORD_A));
    }

    @Test
    public void hasFinanceRecord_financeRecordWithSameIdentityFieldsInFinanceWithDifferentIds_returnsFalse() {
        finance.addFinanceRecord(RECORD_A);
        FinanceRecord financeRecord = new FinanceRecordBuilder(
                RECORD_A).withAmount(AMOUNT_B).withId(ID_B).build();
        // will return false since both the records although have the same field
        // but have different ids.
        assertFalse(finance.hasFinanceRecord(financeRecord));
    }

    @Test
    public void getFinanceRecordList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> finance.getFinanceList().remove(0));
    }

    /**
     * A stub ReadonlyFinance whose financeRecords list can violate interface constraints.
     */
    private static class FinanceAccountStub implements ReadOnlyFinanceAccount {
        private final ObservableList<FinanceRecord> financeRecords = FXCollections.observableArrayList();

        FinanceAccountStub(Collection<FinanceRecord> financeRecords) {
            this.financeRecords.setAll(financeRecords);
        }

        @Override
        public ObservableList<FinanceRecord> getFinanceList() {
            return financeRecords;
        }
    }
}
