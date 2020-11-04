package nustorage.testutil;

import static nustorage.logic.commands.CommandTestUtil.AMOUNT_A;
import static nustorage.logic.commands.CommandTestUtil.AMOUNT_B;
import static nustorage.logic.commands.CommandTestUtil.AMOUNT_C;
import static nustorage.logic.commands.CommandTestUtil.AMOUNT_D;
import static nustorage.logic.commands.CommandTestUtil.DATE_TIME_A;
import static nustorage.logic.commands.CommandTestUtil.DATE_TIME_B;
import static nustorage.logic.commands.CommandTestUtil.DATE_TIME_C;
import static nustorage.logic.commands.CommandTestUtil.DATE_TIME_D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nustorage.model.FinanceAccount;
import nustorage.model.Inventory;
import nustorage.model.record.FinanceRecord;
import nustorage.model.record.InventoryRecord;

public class TypicalFinanceRecords {

    public static final FinanceRecord RECORD_A = new FinanceRecord(AMOUNT_A, DATE_TIME_A);
    public static final FinanceRecord RECORD_B = new FinanceRecord(AMOUNT_B, DATE_TIME_B);
    public static final FinanceRecord RECORD_C = new FinanceRecord(AMOUNT_C, DATE_TIME_C);
    public static final FinanceRecord RECORD_D = new FinanceRecord(AMOUNT_D, DATE_TIME_D);


    private TypicalFinanceRecords() {} // prevents instantiation

    /**
     * Returns an {@code FinanceAccount} with all the typical persons.
     */
    public static FinanceAccount getTypicalFinanceAccount() {
        FinanceAccount fa = new FinanceAccount();
        for (FinanceRecord record : getTypicalFinanceRecords()) {
            fa.addFinanceRecord(record);
        }
        return fa;
    }

    public static FinanceAccount getTypicalFinanceWithInventory() {
        FinanceAccount fa = getTypicalFinanceAccount();
        List<InventoryRecord> typicalInventoryRecords = TypicalInventoryRecords.getTypicalInventoryRecords();
        for (InventoryRecord record: typicalInventoryRecords) {
            FinanceRecord financeRecord = new FinanceRecord(record.getQuantity() * record.getUnitCost(), true);
            record.setFinanceRecord(financeRecord);
            fa.addFinanceRecord(financeRecord);
        }
        return fa;
    }

    public static List<FinanceRecord> getTypicalFinanceRecords() {
        return new ArrayList<>(Arrays.asList(RECORD_A, RECORD_B, RECORD_C, RECORD_D));
    }
}
