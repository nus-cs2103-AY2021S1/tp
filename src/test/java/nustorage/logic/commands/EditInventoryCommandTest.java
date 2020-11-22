package nustorage.logic.commands;

import static java.util.Objects.requireNonNull;
import static nustorage.commons.util.CollectionUtil.requireAllNonNull;
import static nustorage.logic.commands.CommandTestUtil.COST_1;
import static nustorage.logic.commands.CommandTestUtil.COST_2;
import static nustorage.logic.commands.CommandTestUtil.INDEX_ONE;
import static nustorage.logic.commands.CommandTestUtil.INDEX_TEN;
import static nustorage.logic.commands.CommandTestUtil.ITEM_NAME_1;
import static nustorage.logic.commands.CommandTestUtil.ITEM_NAME_2;
import static nustorage.logic.commands.CommandTestUtil.QUANTITY_1;
import static nustorage.logic.commands.CommandTestUtil.QUANTITY_2;
import static nustorage.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nustorage.commons.core.index.Index;
import nustorage.logic.commands.exceptions.CommandException;
import nustorage.model.Inventory;
import nustorage.model.record.FinanceRecord;
import nustorage.model.record.InventoryRecord;
import nustorage.testutil.FinanceRecordBuilder;
import nustorage.testutil.InventoryRecordBuilder;
import nustorage.testutil.stub.ModelStub;

public class EditInventoryCommandTest {

    @Test
    public void constructor_nullEditInventory_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditInventoryCommand(null, null));
    }

    @Test
    public void execute_editedInventoryRecordAcceptedByModel_addSuccessful() throws Exception {
        ModelStubForEditingInventoryCommandTest modelStub = new ModelStubForEditingInventoryCommandTest();
        EditInventoryCommand.EditInventoryDescriptor editInventoryDescriptor =
                new EditInventoryCommand.EditInventoryDescriptor();
        InventoryRecord inventoryRecord = new InventoryRecordBuilder().withName(ITEM_NAME_1)
                .withCost(COST_1).withQuantity(QUANTITY_1).build();
        FinanceRecord financeRecord = new FinanceRecordBuilder().build();
        inventoryRecord.setFinanceRecord(financeRecord);
        modelStub.addInventoryRecord(inventoryRecord);
        modelStub.addFinanceRecord(financeRecord);
        editInventoryDescriptor.setQuantity(QUANTITY_1);
        editInventoryDescriptor.setDescription(ITEM_NAME_2); // name changed
        editInventoryDescriptor.setUnitCost(COST_1);
        Index index = Index.fromOneBased(INDEX_ONE);

        CommandResult commandResult = new EditInventoryCommand(index, editInventoryDescriptor).execute(modelStub);

        //result is supposed to be this record
        InventoryRecord editedInventoryRecord = new InventoryRecordBuilder().withName(ITEM_NAME_2)
                .withCost(COST_1).withQuantity(QUANTITY_1).updateDateTime().build();
        editedInventoryRecord.setFinanceRecord(financeRecord);

        //ensure that results are correct
        assertEquals(String.format(EditInventoryCommand.MESSAGE_EDIT_INVENTORY_SUCCESS, editedInventoryRecord),
                commandResult.getFeedbackToUser());

        // ensure that number of inventory records stay the same
        assertEquals(modelStub.inventoryRecordAdded.size(), 1);
    }

    @Test
    public void execute_containsInventoryRecord_duplicateRecordError() {
        ModelStubForEditingInventoryCommandTest modelStub = new ModelStubForEditingInventoryCommandTest();
        EditInventoryCommand.EditInventoryDescriptor editInventoryDescriptor =
                new EditInventoryCommand.EditInventoryDescriptor();
        //Inventory record one
        InventoryRecord inventoryRecord = new InventoryRecordBuilder().withName(ITEM_NAME_1)
                .withCost(COST_1).withQuantity(QUANTITY_1).build();
        FinanceRecord financeRecord = new FinanceRecordBuilder().withAmount(COST_1 * QUANTITY_1).build();
        inventoryRecord.setFinanceRecord(financeRecord);
        modelStub.addInventoryRecord(inventoryRecord);
        modelStub.addFinanceRecord(financeRecord);
        // inventory record two
        InventoryRecord inventoryRecordTwo = new InventoryRecordBuilder().withName(ITEM_NAME_2)
                .withCost(COST_2).withQuantity(QUANTITY_2).build();
        FinanceRecord financeRecordTwo = new FinanceRecordBuilder().withAmount(COST_2 * QUANTITY_2).build();
        inventoryRecordTwo.setFinanceRecord(financeRecordTwo);
        modelStub.addInventoryRecord(inventoryRecordTwo);
        modelStub.addFinanceRecord(financeRecordTwo);

        // edit inventory record 1 to have the same name as inventory record 2
        editInventoryDescriptor.setQuantity(QUANTITY_1);
        editInventoryDescriptor.setDescription(ITEM_NAME_2); // name changed
        editInventoryDescriptor.setUnitCost(COST_1);
        Index index = Index.fromOneBased(INDEX_ONE);

        // command error thrown when changing to name that already exists
        assertThrows(CommandException.class, () ->
                new EditInventoryCommand(index, editInventoryDescriptor).execute(modelStub));
    }

    @Test
    public void execute_indexLargerThanInventory_invalidIndexError() {
        ModelStubForEditingInventoryCommandTest modelStub = new ModelStubForEditingInventoryCommandTest();
        EditInventoryCommand.EditInventoryDescriptor editInventoryDescriptor =
                new EditInventoryCommand.EditInventoryDescriptor();

        // Add inventory and finance records
        InventoryRecord inventoryRecord = new InventoryRecordBuilder().build();
        FinanceRecord financeRecord = new FinanceRecordBuilder().build();
        inventoryRecord.setFinanceRecord(financeRecord);
        modelStub.addFinanceRecord(financeRecord);
        modelStub.addInventoryRecord(inventoryRecord);

        // set index as 10 as size of Inventory is 1
        Index index = Index.fromZeroBased(INDEX_TEN);
        assertThrows(CommandException.class, () ->
                new EditInventoryCommand(index, editInventoryDescriptor).execute(modelStub));
    }



    private static class ModelStubForEditingInventoryCommandTest extends ModelStub {
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
        public FinanceRecord getFinanceRecord(InventoryRecord inventoryRecord) {
            requireNonNull(inventoryRecord);
            Optional<FinanceRecord> financeRecord = financeRecordAdded.stream().filter(r ->
                    r.getID() == inventoryRecord.getFinanceId()).findFirst();
            if (financeRecord.isEmpty()) {
                return null;
            }
            return financeRecord.get();
        }

        @Override
        public void setInventoryRecord(InventoryRecord inventoryRecordA, InventoryRecord inventoryRecordB) {
            requireAllNonNull(inventoryRecordB, inventoryRecordA);
            int index = inventoryRecordAdded.indexOf(inventoryRecordA);
            inventoryRecordAdded.set(index, inventoryRecordB);
        }

        @Override
        public void setFinanceRecord(FinanceRecord financeRecordA, FinanceRecord financeRecordB) {
            requireAllNonNull(financeRecordA, financeRecordB);
            int index = financeRecordAdded.indexOf(financeRecordA);
            financeRecordAdded.add(index, financeRecordB);
        }

        @Override
        public boolean hasInventoryRecord(InventoryRecord inventoryRecord) {
            return inventoryRecordAdded.contains(inventoryRecord);
        }

        @Override
        public Inventory getInventory() {
            return new Inventory();
        }

        @Override
        public ObservableList<InventoryRecord> getFilteredInventory() {
            return FXCollections.observableList(inventoryRecordAdded);
        }

        @Override
        public void updateFilteredInventoryList(Predicate<InventoryRecord> p) {}
    }

}
