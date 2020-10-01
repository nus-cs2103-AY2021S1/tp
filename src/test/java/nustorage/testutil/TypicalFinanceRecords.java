package nustorage.testutil;


import static nustorage.logic.commands.CommandTestUtil.AMOUNT_1;
import static nustorage.logic.commands.CommandTestUtil.AMOUNT_2;
import static nustorage.logic.commands.CommandTestUtil.AMOUNT_3;
import static nustorage.logic.commands.CommandTestUtil.DATE_1;
import static nustorage.logic.commands.CommandTestUtil.DATE_2;
import static nustorage.logic.commands.CommandTestUtil.TIME_1;
import static nustorage.logic.commands.CommandTestUtil.TIME_2;

import nustorage.model.record.FinanceRecord;


public class TypicalFinanceRecords {

    public static final FinanceRecord RECORD_A = new FinanceRecord(AMOUNT_1, DATE_1, TIME_1);
    public static final FinanceRecord RECORD_B = new FinanceRecord(AMOUNT_2, DATE_2, TIME_1);
    public static final FinanceRecord RECORD_C = new FinanceRecord(AMOUNT_3, DATE_2, TIME_2);

}
