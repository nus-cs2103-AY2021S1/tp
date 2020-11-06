package nustorage.logic.commands;

import static java.util.Objects.requireNonNull;
import static nustorage.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import nustorage.logic.commands.exceptions.CommandException;
import nustorage.model.Inventory;
import nustorage.model.record.FinanceRecord;
import nustorage.model.record.InventoryRecord;
import nustorage.testutil.FinanceRecordBuilder;
import nustorage.testutil.InventoryRecordBuilder;
import nustorage.testutil.stub.ModelStub;

public class CreateInventoryCommandTest {

    @Test
    public void constructor_nullInventory_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateInventoryRecordCommand(null, null));
    }

    @Test
    public void execute_inventoryRecordAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingInventoryRecordAdded modelStub = new ModelStubAcceptingInventoryRecordAdded();
        InventoryRecord inventoryRecord = new InventoryRecordBuilder().build();
        FinanceRecord financeRecord = new FinanceRecordBuilder().build();

        CommandResult commandResult = new CreateInventoryRecordCommand(inventoryRecord,
                financeRecord).execute(modelStub);

        assertEquals(String.format(CreateInventoryRecordCommand.MESSAGE_SUCCESS, inventoryRecord),
                commandResult.getFeedbackToUser());

        assertEquals(Collections.singletonList(inventoryRecord), modelStub.inventoryRecordAdded);
    }

    @Test
    public void execute_containsInventoryRecord_duplicateRecordError() throws Exception {
        ModelStubAcceptingInventoryRecordAdded modelStub = new ModelStubAcceptingInventoryRecordAdded();
        InventoryRecord inventoryRecord = new InventoryRecordBuilder().build();
        FinanceRecord financeRecord = new FinanceRecordBuilder().build();
        CommandResult commandResult = new CreateInventoryRecordCommand(inventoryRecord,
                financeRecord).execute(modelStub);
        assertThrows(CommandException.class, () -> new CreateInventoryRecordCommand(inventoryRecord,
                financeRecord).execute(modelStub));
    }

    @Test
    public void equalsTest() {
        InventoryRecord inventoryRecord1 = new InventoryRecordBuilder().withName("John Doe").build();
        InventoryRecord inventoryRecord2 = new InventoryRecordBuilder().withName("Tony stark").build();
        FinanceRecord financeRecord1 = new FinanceRecordBuilder().withAmount(200).build();
        FinanceRecord financeRecord2 = new FinanceRecordBuilder().withAmount(10000).build();
        CreateInventoryRecordCommand createInventoryRecordCommand1 = new CreateInventoryRecordCommand(
                inventoryRecord1, financeRecord1);
        CreateInventoryRecordCommand createInventoryRecordCommand2 = new CreateInventoryRecordCommand(
                inventoryRecord2, financeRecord2);

        // inventory records with the same name are considered the same records.
        assertEquals(createInventoryRecordCommand1, createInventoryRecordCommand1);

        // a copy should be the same.
        CreateInventoryRecordCommand createInventoryRecordCommand1Copy = new CreateInventoryRecordCommand(
                inventoryRecord1, financeRecord1);
        assertEquals(createInventoryRecordCommand1, createInventoryRecordCommand1Copy);

        // different types should return false.
        assertNotEquals(createInventoryRecordCommand1, null);

        // different commands should not be the same.
        assertNotEquals(createInventoryRecordCommand1, createInventoryRecordCommand2);
    }

    private static class ModelStubAcceptingInventoryRecordAdded extends ModelStub {
        final ArrayList<InventoryRecord> inventoryRecordAdded = new ArrayList<>();
        final ArrayList<FinanceRecord> financeRecordAdded = new ArrayList<>();

        @Override
        public void addInventoryRecord(InventoryRecord inventoryRecord) {
            requireNonNull(inventoryRecord);
            inventoryRecordAdded.add(inventoryRecord);
        }

        @Override
        public void addFinanceRecord(FinanceRecord financeRecord) {
            requireNonNull(financeRecord);
            financeRecordAdded.add(financeRecord);
        }

        @Override
        public boolean hasInventoryRecord(InventoryRecord inventoryRecord) {
            return inventoryRecordAdded.contains(inventoryRecord);
        }

        @Override
        public Inventory getInventory() {
            return new Inventory();
        }
    }

}
