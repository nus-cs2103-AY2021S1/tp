package nustorage.logic.commands;

import static nustorage.logic.commands.CommandTestUtil.assertCommandSuccess;
import static nustorage.logic.commands.UpdateInventoryCommand.MESSAGE_UPDATE_INVENTORY_SUCCESS;
import static nustorage.logic.commands.UpdateInventoryCommand.MESSAGE_INVALID_UPDATE_OPERATION;
import static nustorage.testutil.Assert.assertThrows;
import static nustorage.testutil.TypicalIndexes.INDEX_FIRST;

import nustorage.logic.commands.exceptions.CommandException;
import org.junit.jupiter.api.Test;

import nustorage.logic.commands.UpdateInventoryCommand.UpdateInventoryDescriptor;
import nustorage.model.Model;
import nustorage.model.ModelManager;
import nustorage.model.UserPrefs;
import nustorage.model.record.FinanceRecord;
import nustorage.model.record.InventoryRecord;
import nustorage.testutil.InventoryRecordBuilder;
import nustorage.testutil.TypicalFinanceRecords;
import nustorage.testutil.TypicalInventoryRecords;
import nustorage.testutil.UpdateInventoryDescriptorBuilder;

public class UpdateInventoryCommandTest {
    private Model model = new ModelManager(TypicalFinanceRecords
            .getTypicalFinanceWithInventory(),
            TypicalInventoryRecords.getTypicalInventory(),
            new UserPrefs());

    @Test
    public void execute_positiveIncreaseInQuantityParsed_success() {
        InventoryRecord oldInventoryRecord = model.getFilteredInventory().get(0);
        InventoryRecord updatedInventoryRecord = new InventoryRecordBuilder(oldInventoryRecord).build();
        int changeInQuantity = updatedInventoryRecord.getQuantity() - oldInventoryRecord.getQuantity();
        FinanceRecord newFinanceRecord =
                new FinanceRecord(oldInventoryRecord.getFinanceId(),
                        updatedInventoryRecord.getQuantity() * updatedInventoryRecord.getUnitCost(),
                        updatedInventoryRecord.getDateTime(),
                        true);
        updatedInventoryRecord.setFinanceRecord(newFinanceRecord);
        UpdateInventoryDescriptor descriptor = new UpdateInventoryDescriptorBuilder(changeInQuantity).build();
        UpdateInventoryCommand command = new UpdateInventoryCommand(INDEX_FIRST, descriptor);
        String expectedMessage = String.format(MESSAGE_UPDATE_INVENTORY_SUCCESS, updatedInventoryRecord);
        Model expectedModel = model;
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_decreaseInQuantityParsed_success() {
        InventoryRecord oldInventoryRecord = model.getFilteredInventory().get(0);
        InventoryRecord updatedInventoryRecord = new InventoryRecordBuilder(oldInventoryRecord)
                .withQuantity(5)
                .build();
        int changeInQuantity = updatedInventoryRecord.getQuantity() - oldInventoryRecord.getQuantity();
        FinanceRecord newFinanceRecord =
                new FinanceRecord(oldInventoryRecord.getFinanceId(),
                        updatedInventoryRecord.getQuantity() * updatedInventoryRecord.getUnitCost(),
                        updatedInventoryRecord.getDateTime(),
                        true);
        updatedInventoryRecord.setFinanceRecord(newFinanceRecord);
        UpdateInventoryDescriptor descriptor = new UpdateInventoryDescriptorBuilder(changeInQuantity).build();
        UpdateInventoryCommand command = new UpdateInventoryCommand(INDEX_FIRST, descriptor);
        String expectedMessage = String.format(MESSAGE_UPDATE_INVENTORY_SUCCESS, updatedInventoryRecord);
        Model expectedModel = model;
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_decreaseInQuantityToZeroParsed_success() {
        InventoryRecord oldInventoryRecord = model.getFilteredInventory().get(0);
        InventoryRecord updatedInventoryRecord = new InventoryRecordBuilder(oldInventoryRecord)
                .withQuantity(0)
                .build();
        int changeInQuantity = updatedInventoryRecord.getQuantity() - oldInventoryRecord.getQuantity();
        FinanceRecord newFinanceRecord =
                new FinanceRecord(oldInventoryRecord.getFinanceId(),
                        updatedInventoryRecord.getQuantity() * updatedInventoryRecord.getUnitCost(),
                        updatedInventoryRecord.getDateTime(),
                        true);
        updatedInventoryRecord.setFinanceRecord(newFinanceRecord);
        UpdateInventoryDescriptor descriptor = new UpdateInventoryDescriptorBuilder(changeInQuantity).build();
        UpdateInventoryCommand command = new UpdateInventoryCommand(INDEX_FIRST, descriptor);
        String expectedMessage = String.format(MESSAGE_UPDATE_INVENTORY_SUCCESS, updatedInventoryRecord);
        Model expectedModel = model;
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_decreaseInQuantityBeyondZeroParsed_failure() {
        InventoryRecord oldInventoryRecord = model.getFilteredInventory().get(0);
        int currentQuantity = oldInventoryRecord.getQuantity();
        int newQuantity = -500;
        int changeInQuantity = newQuantity + currentQuantity;
        UpdateInventoryDescriptor descriptor = new UpdateInventoryDescriptorBuilder(changeInQuantity).build();
        UpdateInventoryCommand command = new UpdateInventoryCommand(INDEX_FIRST, descriptor);
        String expectedMessage = MESSAGE_INVALID_UPDATE_OPERATION;
        Model modelCopy = new ModelManager(TypicalFinanceRecords
                .getTypicalFinanceWithInventory(),
                TypicalInventoryRecords.getTypicalInventory(),
                new UserPrefs());
        assertThrows(CommandException.class, () -> command.execute(modelCopy));
    }

}
