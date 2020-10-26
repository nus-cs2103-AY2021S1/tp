package nustorage.testutil;


import static nustorage.logic.commands.CommandTestUtil.DATE_TIME_A;
import static nustorage.logic.commands.CommandTestUtil.DATE_TIME_B;
import static nustorage.logic.commands.CommandTestUtil.DATE_TIME_C;
import static nustorage.logic.commands.CommandTestUtil.ID_A;
import static nustorage.logic.commands.CommandTestUtil.ID_B;
import static nustorage.logic.commands.CommandTestUtil.ID_C;
import static nustorage.logic.commands.CommandTestUtil.ITEM_NAME_1;
import static nustorage.logic.commands.CommandTestUtil.ITEM_NAME_2;
import static nustorage.logic.commands.CommandTestUtil.ITEM_NAME_3;
import static nustorage.logic.commands.CommandTestUtil.QUANTITY_1;
import static nustorage.logic.commands.CommandTestUtil.QUANTITY_2;
import static nustorage.logic.commands.CommandTestUtil.QUANTITY_3;

import nustorage.model.record.InventoryRecord;


public class TypicalInventoryRecords {

    public static final InventoryRecord INVENTORY_RECORD_A =
            new InventoryRecord(ITEM_NAME_1, QUANTITY_1, DATE_TIME_A, ID_A);
    public static final InventoryRecord INVENTORY_RECORD_B =
            new InventoryRecord(ITEM_NAME_2, QUANTITY_2, DATE_TIME_B, ID_B);
    public static final InventoryRecord INVENTORY_RECORD_C =
            new InventoryRecord(ITEM_NAME_3, QUANTITY_3, DATE_TIME_C, ID_C);

}
