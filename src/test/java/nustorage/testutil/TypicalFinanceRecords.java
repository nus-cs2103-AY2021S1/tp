package nustorage.testutil;

import static nustorage.logic.commands.CommandTestUtil.AMOUNT_1;
import static nustorage.logic.commands.CommandTestUtil.AMOUNT_2;
import static nustorage.logic.commands.CommandTestUtil.AMOUNT_3;
import static nustorage.logic.commands.CommandTestUtil.DATE_TIME_1;
import static nustorage.logic.commands.CommandTestUtil.DATE_TIME_2;
import static nustorage.logic.commands.CommandTestUtil.DATE_TIME_3;
import static nustorage.logic.commands.CommandTestUtil.DATE_TIME_4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nustorage.model.FinanceAccount;
import nustorage.model.record.FinanceRecord;

public class TypicalFinanceRecords {

    public static final FinanceRecord RECORD_A = new FinanceRecord(AMOUNT_1, DATE_TIME_1);
    public static final FinanceRecord RECORD_B = new FinanceRecord(AMOUNT_2, DATE_TIME_2);
    public static final FinanceRecord RECORD_C = new FinanceRecord(AMOUNT_3, DATE_TIME_3);
    public static final FinanceRecord RECORD_D = new FinanceRecord(AMOUNT_3, DATE_TIME_4);


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

    public static List<FinanceRecord> getTypicalFinanceRecords() {
        return new ArrayList<>(Arrays.asList(RECORD_A, RECORD_B, RECORD_C, RECORD_D));
    }
}
